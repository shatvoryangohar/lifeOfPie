package com.example.lifeofpie.controller;

import com.example.lifeofpie.dto.CreateUserRequest;
import com.example.lifeofpie.entity.Order;
import com.example.lifeofpie.entity.Role;
import com.example.lifeofpie.entity.User;
import com.example.lifeofpie.repository.OrderRepository;
import com.example.lifeofpie.service.MailService;
import com.example.lifeofpie.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapper mapper;
    private final MailService mailService;

    @GetMapping("/addUser")
    public String addUserPage() {
        return "addUser";

    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute
                          @Valid CreateUserRequest createUserRequest, BindingResult bindingResult,
                          ModelMap map, Locale locale) throws MessagingException {
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (ObjectError allError : bindingResult.getAllErrors()) {
                errors.add(allError.getDefaultMessage());
            }
            map.addAttribute("errors", errors);
            return "addUser";
        } else {
            User user = mapper.map(createUserRequest, User.class);
            Optional<User> byEmail = userService.findByEmail(user.getEmail());
            if (byEmail.isPresent()) {
                map.addAttribute("message", "This email exists, please input another email");
                return "addUser";
            } else {
                user.setActive(false);
                user.setToken(UUID.randomUUID().toString());
                user.setTokenCreatedDate(LocalDateTime.now());
                user.setRole(Role.USER);
                userService.createUser(user);
                mailService.sendHtmlEmail(user.getEmail(), "Welcome" + user.getName(), user,
                        "http://localhost:8080/user/activate?token=" + user.getToken(), "verifyTemplate", locale);
            }
        }
        return "redirect:/loginPage";
    }

    @GetMapping("/user/activate")
    public String activateUser(ModelMap map, @RequestParam String token) {
        Optional<User> user = userService.findByToken(token);
        if (!user.isPresent()) {
            map.addAttribute("message", "User does not exists");
            return "activateUser";
        }
        User userFromDb = user.get();
        if (userFromDb.isActive()) {
            map.addAttribute("message", "User already active");
            return "activateUser";

        }
        userFromDb.setActive(true);
        userFromDb.setToken(null);
        userFromDb.setTokenCreatedDate(null);
        userService.saveUser(userFromDb);
        map.addAttribute("message", "User activated,please login");
        return "redirect:/";
    }

    @GetMapping("/admin")
    public String admin() {
        return "adminPage";
    }

    @GetMapping("/order")
    public String order() {

        return "orderPage";
    }
}
