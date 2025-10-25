package com.example.springsec.Service;

import com.example.springsec.Repository.UserRepo;
import com.example.springsec.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {

    UserRepo userRepo;
    BCryptPasswordEncoder bCryptPasswordEncoder =  new BCryptPasswordEncoder(12);
    AuthenticationManager authenticationManager;
    JWTService jwtService;

    public UsersService(UserRepo userRepo,AuthenticationManager authenticationManager,JWTService jwtService) {
        this.userRepo = userRepo;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public Users save(Users user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public String verify(Users user) {
        Optional<Users> Opuser = userRepo.findByUsername(user.getUsername());

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        return authentication.isAuthenticated() ? jwtService.generateToken(user.getUsername()) : "failure";

//        return Opuser.map(users
//                -> bCryptPasswordEncoder.matches(user.getPassword(), users.getPassword()) ? "Success" : "password incorrect")
//                .orElse("username not found");
    }
}
