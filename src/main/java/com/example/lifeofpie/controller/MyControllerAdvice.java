package com.example.lifeofpie.controller;

import com.example.lifeofpie.security.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class MyControllerAdvice {

    public CurrentUser currentUser(@AuthenticationPrincipal CurrentUser currentUser) {
        return currentUser;
    }
}
