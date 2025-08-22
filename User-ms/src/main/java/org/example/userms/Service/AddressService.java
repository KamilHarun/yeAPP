package org.example.userms.Service;

import org.example.userms.Dto.request.AddressRequestDto;
import org.example.userms.Dto.response.AddressResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {
    AddressResponseDto addAddress(Long userId, AddressRequestDto dto);

    List<AddressResponseDto> getUserAddresses(Long userId);

    AddressResponseDto getAddressById(Long userId, Long addressId);

    AddressResponseDto updateAddress(Long userId, Long addressId, AddressRequestDto dto);

    void deleteAddress(Long userId, Long addressId);


}
