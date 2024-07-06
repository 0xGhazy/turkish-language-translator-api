package com.translator.App.controller;

import com.translator.App.domain.Credentials;
import com.translator.App.domain.User;
import com.translator.App.service.UserService;
import com.translator.App.utils.Response;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    private UserService service;

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody @Valid User user) {
        Response response = service.createUser(user);
        return new ResponseEntity<>(response.jsonfy(), response.getStatusCode());
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid Credentials credentials) throws NoSuchAlgorithmException {
        Response response = service.getUserByEmailAndPassword(credentials.getEmail(), credentials.getPassword());
        return new ResponseEntity<>(response.jsonfy(), response.getStatusCode());
    }

    @GetMapping("/security-answers")
    public ResponseEntity<?> getSecurityAnswers(@RequestBody User user)
    {
        Response response = service.getUserSecurityAnswers(user.getEmail());
        return new ResponseEntity<>(response.jsonfy(), response.getStatusCode());
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Credentials credentials) throws NoSuchAlgorithmException {
        Response response = service.resetPassword(credentials.getEmail(), credentials.getPassword());
        return new ResponseEntity<>(response.jsonfy(), response.getStatusCode());
    }

}
