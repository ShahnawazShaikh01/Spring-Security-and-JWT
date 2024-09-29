package com.shanu.SpringSecurity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyUserRepo extends JpaRepository<MyUser,Integer> {
    Optional<MyUser> findUserByUsername(String username);
}
