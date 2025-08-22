package org.example.userms.Dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequestDto {

    private String firstName;
    private String lastName;
    private Integer age;
    private String phoneNumber;


}
