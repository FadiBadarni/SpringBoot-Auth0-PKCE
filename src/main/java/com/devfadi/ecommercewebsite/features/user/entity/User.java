package com.devfadi.ecommercewebsite.features.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User
{

    @Id
    @Column(unique = true)
    private String auth0Id;

    @Email
    @NotNull
    @Column(unique = true)
    private String email;

    private String fullName;


    private String profilePicture;

    @NotNull
    @Column(nullable = false)
    private Boolean emailVerified;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Address> addresses;

    private String paymentPreference;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_preferences", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "preference")
    private Set<String> preferences;

    private String contactPreference;

    private String accountStatus;

    private String language;
}