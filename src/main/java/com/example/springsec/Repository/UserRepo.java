package com.example.springsec.Repository;

import com.example.springsec.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users,Integer> {

    Optional<Users> findByUsername(String username);
}
