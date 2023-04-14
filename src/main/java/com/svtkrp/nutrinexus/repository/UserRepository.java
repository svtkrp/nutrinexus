package com.svtkrp.nutrinexus.repository;

import com.svtkrp.nutrinexus.domain.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

}
