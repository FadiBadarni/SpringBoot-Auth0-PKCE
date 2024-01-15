package com.devfadi.ecommercewebsite.mapper;

import com.devfadi.ecommercewebsite.features.user.dto.AddressDTO;
import com.devfadi.ecommercewebsite.features.user.entity.Address;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AddressMapper
{
    @Transactional
    public AddressDTO addressToDTO(Address address) {
        return AddressDTO.builder()
                         .id(address.getId())
                         .street(address.getStreet())
                         .city(address.getCity())
                         .district(address.getDistrict())
                         .postalCode(address.getPostalCode())
                         .additionalInfo(address.getAdditionalInfo())
                         .build();
    }

    @Transactional
    public Address addressFromDTO(AddressDTO addressDTO) {
        return Address.builder()
                      .id(addressDTO.getId())
                      .street(addressDTO.getStreet())
                      .city(addressDTO.getCity())
                      .district(addressDTO.getDistrict())
                      .postalCode(addressDTO.getPostalCode())
                      .additionalInfo(addressDTO.getAdditionalInfo())
                      .build();
    }
}
