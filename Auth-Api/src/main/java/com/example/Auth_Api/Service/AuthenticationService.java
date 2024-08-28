package com.example.Auth_Api.Service;

import com.example.Auth_Api.Entity.User;
import com.example.Auth_Api.Repository.UserRepository;
import com.example.Auth_Api.dtos.LoginUserDto;
import com.example.Auth_Api.dtos.RegisterUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {



    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input1) {
        User user = new User();
        user.setEmail(input1.getEmail());
        user.setEmail(input1.getEmail());
        user.setFullName(input1.getFullName());
        user.setPassword(passwordEncoder.encode(input1.getPassword()));

            //    .setFullName(input1.getFullName())
             //   .setEmail(input1.getEmail())
               // .setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}