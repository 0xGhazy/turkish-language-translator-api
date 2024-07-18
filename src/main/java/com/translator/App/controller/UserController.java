package com.translator.App.controller;

import com.translator.App.service.UserService;
import com.translator.App.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
