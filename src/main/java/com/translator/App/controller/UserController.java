package com.translator.App.controller;


import com.translator.App.domain.User;
import com.translator.App.service.UserService;
import com.translator.App.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id)
    {
        Response response = service.getUser(id);
        return new ResponseEntity<>(response.jsonfy(), response.getStatusCode());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String id) throws NoSuchAlgorithmException
    {
        Response response = service.updateUser(id, user);
        return new ResponseEntity<>(response.jsonfy(), response.getStatusCode());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id)
    {
        Response response = service.deleteUser(id);
        return new ResponseEntity<>(response.jsonfy(), response.getStatusCode());
    }

}
