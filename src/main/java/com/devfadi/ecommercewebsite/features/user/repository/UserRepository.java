package com.devfadi.ecommercewebsite.features.user.repository;

import com.devfadi.ecommercewebsite.features.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String>
{

    Optional<User> findByAuth0Id(String auth0Id);
}
