package org.example.userms.Controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.userms.Dto.request.*;
import org.example.userms.Dto.response.AddressResponseDto;
import org.example.userms.Dto.response.UserResponseDto;
import org.example.userms.Service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UserController {

    private final UserService userService;

    @Operation(summary = "Create user")
    @PostMapping("/create")
    public ResponseEntity<Long> createUser(@Valid @RequestBody CreateUserDto createUserDto) {
        return new ResponseEntity<>(userService.createUser(createUserDto), CREATED);
    }

    @Operation(summary = "Get user profile by last name")
    @GetMapping("/getMyProfile")
    public ResponseEntity<UserResponseDto> getMyProfile(@RequestParam CreateUserDto createUserDto) {
        return new ResponseEntity<>(userService.getMyProfile(createUserDto), OK);
    }

    @Operation(summary = "Update users profile")
    @PutMapping("/updateMyProfile{userId}")
    public ResponseEntity<UserResponseDto> updateMyProfile(@PathVariable Long userId,
                                                           @RequestBody CreateUserDto createUserDto) {
        return new ResponseEntity<>(userService.updateProfile(userId, createUserDto), OK);
    }

    @Operation(summary = "Delete a User", description = "Delete a user by their ID.")
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteProfile(@RequestParam Long userId) {
        userService.deleteProfile(userId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get all users", description = "Getting all users")
    @GetMapping("/getAll")
//    @PreAuthorize("hasRole('ADMIN')")
    public Page<UserSummeryResponseDto> getAllUsers(Pageable pageable) {
        return userService.getAllUser(pageable);
    }

    @Operation(summary = "UpdateUserRoles")
    @PutMapping("/updateRole{userId}")
    public ResponseEntity<UserResponseDto> updateRoles(@PathVariable Long userId,
                                                       @RequestBody RolesRequestDto rolesRequestDto) {
        return new ResponseEntity<>(userService.updateUserRoles(userId, rolesRequestDto), OK);
    }


    // 1) Daha təhlükəsiz: principal ilə change-password (alternative secure endpoint)
    @Operation(summary = "Change password for authenticated user (uses Principal)")
    @PostMapping("/me/change-password-secure")
    public ResponseEntity<UserResponseDto> changePassword(@Valid @RequestBody ChangePasswordDto dto,
                                                                Principal principal) {
        log.info("Change password (secure) requested by: {}", principal == null ? "anonymous" : principal.getName());
        assert principal != null;
        UserResponseDto resp = userService.changePassword(dto, principal.getName());
        return ResponseEntity.ok(resp);
    }

    // 2) Password reset (request & confirm)
    @Operation(summary = "Request password reset (send code to email)")
    @PostMapping("/password/reset-request")
    public ResponseEntity<Void> requestPasswordReset(@RequestParam String email) {
        userService.sendPasswordResetCode(email);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Confirm password reset with code")
    @PostMapping("/password/reset-confirm")
    public ResponseEntity<Void> confirmPasswordReset(@RequestParam String email,
                                                     @RequestParam String code,
                                                     @RequestParam String newPassword) {
        userService.resetPassword(email, code, newPassword);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Upload profile avatar")
    @PostMapping("/me/avatar")
    public ResponseEntity<String> uploadAvatar(@RequestParam Long userId,
                                               @RequestParam("file") MultipartFile file) {
        String url = userService.uploadProfilePicture(userId, file);
        return ResponseEntity.ok(url);
    }

    @Operation(summary = "Get all addresses of a user")
    @GetMapping("/me/addresses")
    public ResponseEntity<List<AddressRequestDto>> getUserAddresses(@RequestParam Long userId) {
        return ResponseEntity.ok(userService.getUserAddresses(userId));
    }

    @Operation(summary = "Update an address")
    @PutMapping("/me/addresses/{addressId}")
    public ResponseEntity<AddressResponseDto> updateAddress(@RequestParam Long userId,
                                                            @PathVariable Long addressId,
                                                            @RequestBody AddressRequestDto addressDto) {
        return ResponseEntity.ok(userService.updateAddress(userId, addressId, addressDto));
    }
//
    @Operation(summary = "Set primary address")
    @PutMapping("/me/addresses/{addressId}/set-primary")
    public ResponseEntity<Void> setPrimaryAddress(@RequestParam Long userId,
                                                  @PathVariable Long addressId) {
        userService.setPrimaryAddress(userId, addressId);
        return ResponseEntity.ok().build();
    }
//
//    // 5) Favorit restoranlar / yeməklər
//    @Operation(summary = "Add restaurant to favorites")
//    @PostMapping("/me/favorites/restaurants/{restaurantId}")
//    public ResponseEntity<Void> addFavoriteRestaurant(@RequestParam Long userId,
//                                                      @PathVariable Long restaurantId) {
//        userService.addFavoriteRestaurant(userId, restaurantId);
//        return ResponseEntity.ok().build();
//    }
//
//    @Operation(summary = "Remove restaurant from favorites")
//    @DeleteMapping("/me/favorites/restaurants/{restaurantId}")
//    public ResponseEntity<Void> removeFavoriteRestaurant(@RequestParam Long userId,
//                                                         @PathVariable Long restaurantId) {
//        userService.removeFavoriteRestaurant(userId, restaurantId);
//        return ResponseEntity.ok().build();
//    }

//    @Operation(summary = "Get favorite restaurants")
//    @GetMapping("/me/favorites/restaurants")
//    public ResponseEntity<List<RestaurantDto>> getFavoriteRestaurants(@RequestParam Long userId) {
//        return ResponseEntity.ok(userService.getFavoriteRestaurants(userId));
//    }
//
//    // 6) Sifariş tarixçəsi
//    @Operation(summary = "Get order history for user")
//    @GetMapping("/me/orders")
//    public ResponseEntity<List<OrderDto>> getOrderHistory(@RequestParam Long userId) {
//        return ResponseEntity.ok(userService.getOrderHistory(userId));
//    }
//
//    // 7) Bildiriş ayarları
//    @Operation(summary = "Get notification settings")
//    @GetMapping("/me/notifications/settings")
//    public ResponseEntity<NotificationSettingsDto> getNotificationSettings(@RequestParam Long userId) {
//        return ResponseEntity.ok(userService.getNotificationSettings(userId));
//    }
//
//    @Operation(summary = "Update notification settings")
//    @PutMapping("/me/notifications/settings")
//    public ResponseEntity<NotificationSettingsDto> updateNotificationSettings(@RequestParam Long userId,
//                                                                              @RequestBody NotificationSettingsDto dto) {
//        return ResponseEntity.ok(userService.updateNotificationSettings(userId, dto));
//    }
//
//    // 8) Kupon/Promo tətbiqi
//    @Operation(summary = "Apply coupon for user")
//    @PostMapping("/me/coupons/apply")
//    public ResponseEntity<CouponResponseDto> applyCoupon(@RequestParam Long userId,
//                                                         @RequestBody CouponApplyDto couponApplyDto) {
//        return ResponseEntity.ok(userService.applyCoupon(userId, couponApplyDto));
//    }
//
//    // 9) Admin üçün əlavə: istifadəçini sil (admin)
//    @Operation(summary = "Admin delete user")
//    @PreAuthorize("hasRole('ADMIN')")
//    @DeleteMapping("/admin/{userId}")
//    public ResponseEntity<Void> adminDeleteUser(@PathVariable Long userId) {
//        userService.deleteUserByAdmin(userId);
//        return ResponseEntity.noContent().build();
//    }
//
//    // 10) Admin üçün istifadəçi yeniləmə (admin)
//    @Operation(summary = "Admin update user")
//    @PreAuthorize("hasRole('ADMIN')")
//    @PutMapping("/admin/{userId}")
//    public ResponseEntity<UserResponseDto> adminUpdateUser(@PathVariable Long userId,
//                                                           @RequestBody UpdateUserDto updateUserDto) {
//        return ResponseEntity.ok(userService.updateUserByAdmin(userId, updateUserDto));
//    }
//
//    // 11) Sadə user axtarışı (ad / email / telefon)
//    @Operation(summary = "Search users by keyword")
//    @GetMapping("/search")
//    public ResponseEntity<List<UserResponseDto>> searchUsers(@RequestParam String keyword,
//                                                             Pageable pageable) {
//        return ResponseEntity.ok(userService.searchUsers(keyword, pageable));
//    }

}