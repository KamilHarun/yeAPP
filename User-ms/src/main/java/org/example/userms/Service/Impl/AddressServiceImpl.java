package org.example.userms.Service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.commonms.Exceptions.AddressNotFoundException;
import org.example.commonms.Exceptions.ErrorMessage;
import org.example.userms.Config.AddressMapper;
import org.example.userms.Dto.request.AddressRequestDto;
import org.example.userms.Dto.response.AddressResponseDto;
import org.example.userms.Model.Address;
import org.example.userms.Model.User;
import org.example.userms.Repository.AddressRepository;
import org.example.userms.Repository.UserRepository;
import org.example.userms.Service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.commonms.Exceptions.ErrorMessage.ADDRESS_NOT_FOUND_BELONG_TO_USER;
import static org.example.commonms.Exceptions.ErrorMessage.ADDRESS_NOT_FOUND_EXCEPTION;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final AddressMapper addressMapper;

    @Override
    public AddressResponseDto addAddress(Long userId, AddressRequestDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AddressNotFoundException(ADDRESS_NOT_FOUND_EXCEPTION));

        Address address = addressMapper.toEntity(dto);
        address.setUser(user);

        return addressMapper.toDto(addressRepository.save(address));
    }

    @Override
    public List<AddressResponseDto> getUserAddresses(Long userId) {
        return addressMapper.toDtoList(addressRepository.findByUserId(userId));
    }

    @Override
    public AddressResponseDto getAddressById(Long userId, Long addressId) {
        Address address = addressRepository.findById(addressId)
                .filter(a -> a.getUser().getId().equals(userId))
                .orElseThrow(() -> new AddressNotFoundException(ADDRESS_NOT_FOUND_BELONG_TO_USER));

        return addressMapper.toDto(address);
    }

    @Override
    public AddressResponseDto updateAddress(Long userId, Long addressId, AddressRequestDto dto) {
        Address address = addressRepository.findById(addressId)
                .filter(a -> a.getUser().getId().equals(userId))
                .orElseThrow(() -> new AddressNotFoundException(ADDRESS_NOT_FOUND_BELONG_TO_USER));

        address.setTitle(dto.getTitle());
        address.setCity(dto.getCity());
        address.setDistrict(dto.getDistrict());
        address.setStreet(dto.getStreet());
        address.setBuildingNo(dto.getBuildingNo());
        address.setApartmentNo(dto.getApartmentNo());
        address.setPostalCode(dto.getPostalCode());
        address.setDescription(dto.getDescription());

        return addressMapper.toDto(addressRepository.save(address));
    }

    @Override
    public void deleteAddress(Long userId, Long addressId) {
        Address address = addressRepository.findById(addressId)
                .filter(a -> a.getUser().getId().equals(userId))
                .orElseThrow(() -> new AddressNotFoundException(ADDRESS_NOT_FOUND_BELONG_TO_USER));

        addressRepository.delete(address);

    }


}