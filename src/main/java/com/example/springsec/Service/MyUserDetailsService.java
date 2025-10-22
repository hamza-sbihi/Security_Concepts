package com.example.springsec.Service;

import com.example.springsec.Repository.UserRepo;
import com.example.springsec.entity.MyUserDetails;
import com.example.springsec.entity.Users;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    UserRepo userRepo;
    public MyUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserDetails loadUserByUsername(String username){

        Users user = userRepo.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("user not found"));

        return new MyUserDetails(user);
    }
}
