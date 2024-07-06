package com.translator.App.service;

import com.translator.App.domain.User;
import com.translator.App.exception.InvalidEmailOrPasswordException;
import com.translator.App.exception.ResourceNotFound;
import com.translator.App.exception.UserAlreadyExistException;
import com.translator.App.repository.UserRepository;
import com.translator.App.utils.HashingHandler;
import com.translator.App.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;

@Component
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Response createUser(User user) {
        User testUser = repository.findUserByEmail(user.getEmail());
        if(testUser != null)
            throw new UserAlreadyExistException("Email address is already exist", new Date(), testUser.getEmail());
        user.setCreationTime(System.currentTimeMillis());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setSecQ1A(user.getSecQ1A());
        user.setSecQ2A(user.getSecQ2A());
        user.setSubscriptionStatus("InActive");
        User createdUser = repository.save(user);
        return new Response.ResponseBuilder()
                .data(createdUser)
                .message("User created Successfully")
                .status(HttpStatus.CREATED)
                .build();
    }

    public Response getUser(String id)
    {
        User testUser = repository.findById(id).orElseThrow(() -> new ResourceNotFound("User", "id", id));
        return new Response.ResponseBuilder()
                .status(HttpStatus.OK)
                .message("User retrieved successfully")
                .data(testUser)
                .build();
    }

    public User updateUserSubscription(String id, User user)
    {
        User storedUser = repository.findById(id).orElseThrow(
                () -> new ResourceNotFound("User", "id", id)
        );
        storedUser.setExpirationDate(user.getExpirationDate());
        storedUser.setSubscriptionDate(user.getSubscriptionDate());
        storedUser.setSubscriptionStatus(user.getSubscriptionStatus());
        return repository.save(storedUser);
    }

    public Response updateUser(String id, User user) throws NoSuchAlgorithmException
    {
        User testUser = repository.findById(id).orElseThrow(
                () -> new ResourceNotFound("User", "id", id)
        );
        if (user.getCountry() != null)
            testUser.setCountry(user.getCountry());
        if (user.getEmail() != null) {
            User temp = repository.findUserByEmail(user.getEmail());
            if (temp != null)
                throw new UserAlreadyExistException("Email address is already exist", new Date(), testUser.getEmail());
            testUser.setEmail(user.getEmail());
        }
        if (user.getPassword() != null)
            testUser.setPassword(HashingHandler.sha256Value(user.getPassword()));
        if (user.getFullName() != null)
            testUser.setFullName(user.getFullName());
        if(user.getPhone() != null)
            testUser.setPhone(user.getPhone());
        testUser.setModificationTime(System.currentTimeMillis());
        return new Response.ResponseBuilder()
                .status(HttpStatus.OK)
                .message("User updated successfully")
                .data(repository.save(testUser))
                .build();
    }

    public Response deleteUser(String id)
    {
        User testUser = repository.findById(id).orElseThrow(() -> new ResourceNotFound("User", "id", id));
        repository.deleteById(id);
        return new Response.ResponseBuilder()
                .status(HttpStatus.OK)
                .message("User deleted successfully")
                .data(testUser)
                .build();
    }

    public Response getUserByEmailAndPassword(String email, String password)
    {
        User storedUser = repository.findUserByEmail(email);
        if (storedUser == null) {
            throw new InvalidEmailOrPasswordException("Invalid email or password", new Date());
        }
        else {
            if(passwordEncoder.matches(password,storedUser.getPassword())){
                return new Response.ResponseBuilder()
                        .status(HttpStatus.OK)
                        .message("User login successfully")
                        .data(storedUser)
                        .build();
            }
        }
        return new Response.ResponseBuilder()
                .status(HttpStatus.OK)
                .message("Invalid password passed")
                .data(storedUser)
                .build();
    }

    public Response getUserSecurityAnswers(String email)
    {
        User user = repository.findUserByEmail(email);
        if(user== null)
            throw new InvalidEmailOrPasswordException("Invalid email address", new Date());
        HashMap<String, String> data = new HashMap<>();
        data.put("answer_1", user.getSecQ1A());
        data.put("answer_2", user.getSecQ2A());
        return new Response.ResponseBuilder()
                .status(HttpStatus.OK)
                .message("User retrieved successfully")
                .data(data)
                .build();
    }

    public Response resetPassword(String email, String newPassword) throws NoSuchAlgorithmException {
        User user = repository.findUserByEmail(email);
        if(user == null)
            throw new InvalidEmailOrPasswordException("Invalid email address", new Date());
        user.setPassword(HashingHandler.sha256Value(newPassword));
        repository.save(user);
        System.out.println(user);
        return new Response.ResponseBuilder()
                .status(HttpStatus.FOUND)
                .message("User password reset successfully")
                .data(user)
                .build();
    }
}
