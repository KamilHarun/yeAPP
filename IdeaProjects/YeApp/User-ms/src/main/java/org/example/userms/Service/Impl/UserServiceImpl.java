package org.example.userms.Service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.commonms.Exceptions.*;
import org.example.userms.Config.AddressMapper;
import org.example.userms.Config.UserMapper;
import org.example.userms.Dto.request.*;
import org.example.userms.Dto.response.AddressResponseDto;
import org.example.userms.Dto.response.UserResponseDto;
import org.example.userms.Model.Address;
import org.example.userms.Model.Roles;
import org.example.userms.Model.User;
import org.example.userms.Repository.AddressRepository;
import org.example.userms.Repository.RolesRepository;
import org.example.userms.Repository.UserRepository;
import org.example.userms.Service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static org.example.commonms.Exceptions.ErrorMessage.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class  UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RolesRepository rolesRepository;
    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;

    @Override
    public UserResponseDto getMyProfile(CreateUserDto createUserDto) {
        Optional<User> myProfile = userRepository.getMyProfile(createUserDto.getUsername(), createUserDto.getSurname());
        if (myProfile.isEmpty()) {
            throw new UserNotFoundException(USER_NOT_FOUND_EXCEPTION);
        }
        return userMapper.userToUserResponseDto(myProfile.get());
    }


    @Override
    public UserResponseDto updateProfile(Long userId, CreateUserDto createUserDto) {
        log.info("Updating user profile for userId: {}", userId);

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_EXCEPTION));

        updateIfPresent(existingUser::setUsername, createUserDto.getUsername());
        updateIfPresent(existingUser::setSurname, createUserDto.getSurname());
        updateIfPresent(existingUser::setEmail, createUserDto.getEmail());

        updateUserIfChanged(existingUser, createUserDto.getPassword());

        User updatedUser = userRepository.save(existingUser);
        log.info("User with ID {} updated successfully.", userId);

        return userMapper.userToUserResponseDto(updatedUser);
    }

    @Override
    public void deleteProfile(Long userId) {
        userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException(USER_NOT_FOUND_EXCEPTION));
        userRepository.deleteById(userId);
        log.info("User with ID {} deleted successfully.", userId);

    }

    @Override
    public Page<UserSummeryResponseDto> getAllUser(Pageable pageable) {
        log.info("Getting all users");
        Page<User> all = userRepository.findAll(pageable);
        if (all.isEmpty()) {
            log.error("No users found");
            throw new UserNotFoundException(USER_NOT_FOUND_EXCEPTION);
        }

        List<UserSummeryResponseDto> userResponseDtosList = all.stream().map(
                user -> UserSummeryResponseDto.builder()
                        .email(user.getEmail())
                        .username(user.getUsername())
                        .lastName(user.getSurname())
                        .build()).toList();
        return new PageImpl<>(userResponseDtosList, pageable, all.getTotalElements());

    }

    @Override
    public Long createUser(CreateUserDto createUserDto) {
        log.info("Creating new user: {}", createUserDto.getUsername());

        // Check if user already exists
        if (userRepository.findByEmail(createUserDto.getEmail()).isPresent()) {
            log.warn("User creation failed. User with email '{}' already exists", createUserDto.getEmail());
            throw new UserAlreadyExistException(USER_ALREADY_EXISTS_BY_EMAIL);
        }

        // Map DTO to Entity
        User user = userMapper.requestDtoToUser(createUserDto);
        User savedUser = userRepository.save(user);

        log.info("User created successfully with ID: {}", savedUser.getId());
        return savedUser.getId();
    }


    @Override
    @Transactional
    public UserResponseDto updateUserRoles(Long userId, RolesRequestDto rolesRequestDto) {

        log.info("Updating user roles for userId: {}", userId);

        log.info("Finding roles by ID: {}", rolesRequestDto.getId());
        Roles roles = rolesRepository.findById(rolesRequestDto.getId()).orElseThrow(() ->
                new RoleNotFoundException(ROLE_NOT_FOUND_EXCEPTION));

        log.info("Finding user roles for userId: {}", userId);
        User user = userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException(USER_NOT_FOUND_BY_ID));

        Set<Roles> updateRoles = new HashSet<>(user.getRoles());
        updateRoles.add(roles);
        user.setRoles(updateRoles);


        userRepository.save(user);
        log.info("User with ID {} updated successfully.", userId);

        return userMapper.userToUserResponseDto(user);

    }

    @Override
    public UserResponseDto confirmResetPassword(ChangePasswordDto changePasswordDto) {

        Long userId = changePasswordDto.getUserId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_EXCEPTION));


        if (!changePasswordDto.getNewPassword().equals(changePasswordDto.getConfirmPassword())) {
            log.warn("New password and confirmation do not match for userId {}", userId);
            throw new InvalidOldPasswordException (INVALID_OLD_PASSWORD_EXCEPTION);
        }

        updateUserIfChanged(user, changePasswordDto.getNewPassword());
        userRepository.save(user);

        log.info("Password reset confirmed for userId {}", userId);

        return userMapper.userToUserResponseDto(user);
    }


    @Override
    public UserResponseDto changePassword(ChangePasswordDto dto, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new RuntimeException("User with id '" + username + "' not found"));

        // Şifrə dəyişdirmə üçün yoxlamaları keçir
        validatePasswordChange(dto, user);

        // Yeni şifrəni istifadəçinin məlumatına tətbiq et
        updateUserIfChanged(user, dto.getNewPassword());
        userRepository.save(user);

        log.info("Password changed successfully for username {}", username);

        return userMapper.userToUserResponseDto(user);

    }

    @Override
    public void sendPasswordResetCode(String email) {



    }


    @Override
    public void resetPassword(String email, String code, String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_BY_EMAIL));
        user.setPassword(newPassword); // encode etməyi unutma!
        userRepository.save(user);
        log.info("Password reset for {}", email);
    }

    @Override
    public String uploadProfilePicture(Long userId, MultipartFile file) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_BY_ID));

        String url = "/uploads/" + file.getOriginalFilename();
        user.setAvatarUrl(url);
        userRepository.save(user);
        return url;
    }

    @Override
    public List<AddressRequestDto> getUserAddresses(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()
                -> new UserNotFoundException(USER_NOT_FOUND_BY_ID));

        List<Address> addresses = user.getAddresses();

        if (addresses == null || addresses.isEmpty()) {
            throw new AddressNotFoundException(ADDRESS_NOT_FOUND_EXCEPTION);
        }
        return addressMapper.toDtoList(addresses);
    }


    @Override
    public AddressResponseDto updateAddress(Long userId, Long addressId, AddressRequestDto dto) {
        User user = userRepository.findById(userId).orElseThrow(()
                -> new UserNotFoundException(USER_NOT_FOUND_BY_ID));

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException(ADDRESS_NOT_FOUND_EXCEPTION));

        if (!address.getUser().getId().equals(user.getId())) {
            throw new AddressOwnershipException (ADDRESS_NOT_FOUND_BELONG_TO_USER);
        }

        addressMapper.updateAddressFromDto(address , dto);

        return addressMapper.toDto(addressRepository.save(address));
    }
//
//    @Override
//    public void deleteAddress(Long userId, Long addressId) {
//        Address address = addressRepository.findByIdAndUserId(addressId, userId)
//                .orElseThrow(() -> new AddressNotFoundException("Address not found"));
//        addressRepository.delete(address);
//    }
//
@Transactional
@Override
public void setPrimaryAddress(Long userId, Long addressId) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_BY_ID));

    Address address = addressRepository.findById(addressId)
            .filter(a -> a.getUser().getId().equals(userId))
            .orElseThrow(() -> new AddressNotFoundException(ADDRESS_NOT_FOUND_EXCEPTION));


    user.getAddresses().forEach(a -> a.setPrimary(false));

    address.setPrimary(true);

    addressRepository.save(address);
}

//    @Override
//    public void addFavoriteRestaurant(Long userId, Long restaurantId) {
//        Users user = userRepository.findById(userId)
//                .orElseThrow(() -> new UserNotFoundException("User not found"));
//        Restaurant restaurant = restaurantRepository.findById(restaurantId)
//                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found"));
//        user.getFavoriteRestaurants().add(restaurant);
//        userRepository.save(user);
//    }
//
//    @Override
//    public void removeFavoriteRestaurant(Long userId, Long restaurantId) {
//        Users user = userRepository.findById(userId)
//                .orElseThrow(() -> new UserNotFoundException("User not found"));
//        user.getFavoriteRestaurants().removeIf(r -> r.getId().equals(restaurantId));
//        userRepository.save(user);
//    }
//
//    @Override
//    public List<RestaurantDto> getFavoriteRestaurants(Long userId) {
//        return userRepository.findFavoriteRestaurantsByUserId(userId)
//                .stream().map(RestaurantDto::fromEntity).toList();
//    }
//
//    @Override
//    public List<OrderDto> getOrderHistory(Long userId) {
//        return orderRepository.findByUserId(userId)
//                .stream().map(OrderDto::fromEntity).toList();
//    }
//
//    @Override
//    public NotificationSettingsDto getNotificationSettings(Long userId) {
//        return notificationSettingsRepository.findByUserId(userId)
//                .map(NotificationSettingsDto::fromEntity)
//                .orElseThrow(() -> new NotificationNotFoundException("Not found"));
//    }
//
//    @Override
//    public NotificationSettingsDto updateNotificationSettings(Long userId, NotificationSettingsDto dto) {
//        NotificationSettings settings = notificationSettingsRepository.findByUserId(userId)
//                .orElse(new NotificationSettings(userId));
//        settings.setEmailEnabled(dto.isEmailEnabled());
//        settings.setSmsEnabled(dto.isSmsEnabled());
//        return NotificationSettingsDto.fromEntity(notificationSettingsRepository.save(settings));
//    }
//
//    @Override
//    public CouponResponseDto applyCoupon(Long userId, CouponApplyDto dto) {
//        Coupon coupon = couponRepository.findByCode(dto.getCode())
//                .orElseThrow(() -> new CouponNotFoundException("Invalid code"));
//        // logic: discount hesabla
//        return new CouponResponseDto(coupon.getCode(), coupon.getDiscount());
//    }
//
//    @Override
//    public void deleteUserByAdmin(Long userId) {
//        userRepository.deleteById(userId);
//    }
//
//    @Override
//    public UserResponseDto updateUserByAdmin(Long userId, UpdateUserDto dto) {
//        Users user = userRepository.findById(userId)
//                .orElseThrow(() -> new UserNotFoundException("User not found"));
//        user.setFirstName(dto.getFirstName());
//        user.setLastName(dto.getLastName());
//        return userMapper.toDto(userRepository.save(user));
//    }
//
//    @Override
//    public List<UserResponseDto> searchUsers(String keyword, Pageable pageable) {
//        return userRepository.searchByKeyword(keyword, pageable)
//                .map(userMapper::toDto)
//                .toList();
//    }


    public void updateUserIfChanged(User user, String password) {
        if (StringUtils.hasText(password)) {
            if (!passwordEncoder.matches(password, user.getPassword())) {
                user.setPassword(passwordEncoder.encode(password));
                log.info("Password for userId {} updated successfully.", user.getId());
            } else {
                log.warn("New password is same as current for userId {}. Skipping update.", user.getId());
            }
        }

    }

    public void validatePasswordChange(ChangePasswordDto changePasswordDto, User user) {

        if (!passwordEncoder.matches(changePasswordDto.getOldPassword(), user.getPassword())) {
            log.warn("Invalid old password for user ID");
            throw new RuntimeException("Old password is incorrect");
        }

        if (!changePasswordDto.getNewPassword().equals(changePasswordDto.getConfirmPassword())) {
            log.warn("New password and confirmation do not match for user ID");
            throw new RuntimeException("New passwords do not match");
        }

        if (passwordEncoder.matches(changePasswordDto.getNewPassword(), user.getPassword())) {
            log.warn("New password cannot be same as old password for user ID");
            throw new RuntimeException("New password cannot be same as old password");
        }
    }

    private void updateIfPresent(Consumer<String> setter, String value) {
        if (value != null && !value.trim().isEmpty()) {
            setter.accept(value.trim());
        }
    }

}
