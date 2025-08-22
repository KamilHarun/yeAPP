package org.example.userms.Service;

import jakarta.validation.Valid;
import org.example.userms.Dto.request.*;
import org.example.userms.Dto.response.AddressResponseDto;
import org.example.userms.Dto.response.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface UserService {
    UserResponseDto getMyProfile(CreateUserDto createUserDto);

    UserResponseDto updateProfile(Long userId, CreateUserDto createUserDto);

    void deleteProfile(Long userId);

    Page<UserSummeryResponseDto> getAllUser(Pageable pageable);

    Long createUser(@Valid CreateUserDto createUserDto);

    UserResponseDto updateUserRoles(Long userId, RolesRequestDto rolesRequestDto);

    UserResponseDto confirmResetPassword(@Valid ChangePasswordDto changePasswordDto);

    UserResponseDto changePassword(@Valid ChangePasswordDto dto, String name);

    void sendPasswordResetCode(String email);

    void resetPassword(String email, String code, String newPassword);

    String uploadProfilePicture(Long userId, MultipartFile file);

    List<AddressRequestDto> getUserAddresses(Long userId);

     AddressResponseDto updateAddress(Long userId, Long addressId, AddressRequestDto addressDto);
    // void deleteAddress(Long userId, Long addressId);
     void setPrimaryAddress(Long userId, Long addressId);

    // Favorite restaurant funksiyaları (hazırda istifadə olunmur)
    // void addFavoriteRestaurant(Long userId, Long restaurantId);
    // void removeFavoriteRestaurant(Long userId, Long restaurantId);

}
