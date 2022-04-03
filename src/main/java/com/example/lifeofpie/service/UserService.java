package com.example.lifeofpie.service;

import com.example.lifeofpie.dto.CreateUserRequest;
import com.example.lifeofpie.entity.User;
import com.example.lifeofpie.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }


    public Optional<User> findByToken(String token) {
        return userRepository.findByToken(token);
    }

}

