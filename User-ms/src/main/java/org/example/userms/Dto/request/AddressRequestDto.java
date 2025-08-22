package org.example.userms.Dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequestDto {

   @NotBlank
   String title;

   @NotBlank
   String city;

   String district;
   String street;
   String buildingNo;
   String apartmentNo;
   String postalCode;
   String description;

}
