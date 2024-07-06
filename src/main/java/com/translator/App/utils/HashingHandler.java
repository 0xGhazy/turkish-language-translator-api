package com.translator.App.utils;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class HashingHandler {

    public static String sha256Value(String plaintext) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(plaintext.getBytes(StandardCharsets.UTF_8));
        String hashed = Base64.getEncoder().encodeToString(hash);
        return hashed;
    }
}
