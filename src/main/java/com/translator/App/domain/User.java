package com.translator.App.domain;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="Users")
public class User {

    @Id
    private String id;
    @NotBlank
    private String phone;
    private boolean isStudent;
    @NotBlank
    private String fullName;
    @NotBlank
    @NotNull
    private String password;
    @Email
    private String email;
    private String country;
    private String subscriptionStatus;
    private LocalDate subscriptionDate;
    private LocalDate expirationDate;
    private String secQ1A;
    private String secQ2A;
    private long creationTime;
    private long modificationTime;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                ", isStudent=" + isStudent +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", subscriptionStatus='" + subscriptionStatus + '\'' +
                ", subscriptionDate='" + subscriptionDate + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", secQ1A='" + secQ1A + '\'' +
                ", secQ2A='" + secQ2A + '\'' +
                ", creationTime='" + creationTime + '\'' +
                ", modificationTime='" + modificationTime + '\'' +
                '}';
    }
}