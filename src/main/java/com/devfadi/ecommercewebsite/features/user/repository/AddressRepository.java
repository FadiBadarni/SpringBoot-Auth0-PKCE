package com.devfadi.ecommercewebsite.features.user.repository;

import com.devfadi.ecommercewebsite.features.user.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>
{
}
