package com.svtkrp.nutrinexus.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "nutrinexus_user")
@Data
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "hashed_password", nullable = false)
    private String hashedPassword;

    @Column(name = "salt", nullable = false)
    private String salt;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    // profile

    @Column(name = "name")
    private String name;

    @Column(name = "avatar_id")
    private Long avatarId;

    @Column(name = "bio")
    private String bio;

}
