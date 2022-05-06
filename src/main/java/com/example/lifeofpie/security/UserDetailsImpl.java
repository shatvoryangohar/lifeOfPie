package com.example.lifeofpie.security;

import com.example.lifeofpie.entity.User;
import com.example.lifeofpie.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class  UserDetailsImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> byEmail = userRepository.findByEmail(email);
        if (!byEmail.isPresent()) {
            throw new UsernameNotFoundException("username" + email + "not found");
        }
        return new CurrentUser(byEmail.get());
    }
}
