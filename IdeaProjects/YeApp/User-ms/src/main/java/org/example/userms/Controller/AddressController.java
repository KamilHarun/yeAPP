package org.example.userms.Controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.userms.Dto.request.AddressRequestDto;
import org.example.userms.Dto.response.AddressResponseDto;
import org.example.userms.Service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService addressService;

    @Operation(summary = "Add new address for user")
    @PostMapping("/{userId}")
    public ResponseEntity<AddressResponseDto> addAddress(@PathVariable Long userId,
                                                         @RequestBody AddressRequestDto dto) {
        return ResponseEntity.ok(addressService.addAddress(userId, dto));
    }

    @Operation(summary = "Get all addresses of a user")
    @GetMapping("/{userId}")
    public ResponseEntity<List<AddressResponseDto>> getUserAddresses(@PathVariable Long userId) {
        return ResponseEntity.ok(addressService.getUserAddresses(userId));
    }

    @Operation(summary = "Get one address by ID")
    @GetMapping("/{userId}/{addressId}")
    public ResponseEntity<AddressResponseDto> getAddress(@PathVariable Long userId,
                                                         @PathVariable Long addressId) {
        return ResponseEntity.ok(addressService.getAddressById(userId, addressId));
    }

    @Operation(summary = "Update an address")
    @PutMapping("/{userId}/{addressId}")
    public ResponseEntity<AddressResponseDto> updateAddress(@PathVariable Long userId,
                                                            @PathVariable Long addressId,
                                                            @RequestBody AddressRequestDto dto) {
        return ResponseEntity.ok(addressService.updateAddress(userId, addressId, dto));
    }

    @Operation(summary = "Delete an address")
    @DeleteMapping("/{userId}/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long userId,
                                              @PathVariable Long addressId) {
        addressService.deleteAddress(userId, addressId);
        return ResponseEntity.noContent().build();
    }
}
