package com.svtkrp.nutrinexus.repository;

import com.svtkrp.nutrinexus.domain.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserModel getUserModelById(Long id);

    UserModel getUserModelByUsername(String username);

    UserModel getUserModelByEmail(String email);

    List<UserModel> getAllByUsernameContainingIgnoreCase(String username);

}
