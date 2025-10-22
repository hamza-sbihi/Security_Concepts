package com.example.springsec.Service;

import com.example.springsec.Repository.UserRepo;
import com.example.springsec.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    UserRepo userRepo;
    BCryptPasswordEncoder bCryptPasswordEncoder =  new BCryptPasswordEncoder(12);

    public UsersService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Users save(Users user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

}
