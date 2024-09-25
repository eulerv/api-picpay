package com.github.eulerv.picpaydesafiobackend.authenticationPackage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.eulerv.picpaydesafiobackend.authenticationPackage.exception.UserAlreadyExistsException;
import com.github.eulerv.picpaydesafiobackend.authenticationPackage.model.User;
import com.github.eulerv.picpaydesafiobackend.authenticationPackage.repository.UserRepository;


@Service
public class UserService {
    // Logger LOG = Logger.getLogger(UserService.class.getName());
    // LOG.info("Getting all users");
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new UserAlreadyExistsException("Usuário com email " + username + " já existe. Favor criar novo usuário.");
        }
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(username, encodedPassword);
        return userRepository.save(user);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}