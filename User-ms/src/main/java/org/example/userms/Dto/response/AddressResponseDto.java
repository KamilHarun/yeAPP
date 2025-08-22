package org.example.userms.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressResponseDto {
    Long id;
    String title;
    String city;
    String district;
    String street;
    String buildingNo;
    String apartmentNo;
    String postalCode;
    String description;
}
