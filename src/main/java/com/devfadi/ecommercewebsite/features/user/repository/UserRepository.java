package com.devfadi.ecommercewebsite.features.user.repository;

import com.devfadi.ecommercewebsite.features.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{

    User findByEmail(String email);
}
