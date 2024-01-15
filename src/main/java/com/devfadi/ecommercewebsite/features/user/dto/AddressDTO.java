package com.devfadi.ecommercewebsite.features.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO
{
    private Long id;
    private String street;
    private String city;
    private String district;
    private String postalCode;
    private String additionalInfo;
}
