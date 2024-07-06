package com.translator.App.controller;


import com.translator.App.service.PaymentService;
import com.translator.App.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

@RestController
@RequestMapping("api/v1/payments")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @PostMapping("/webhook")
    public ResponseEntity<?> createPayment(@RequestBody HashMap<String, Object> payment) throws NoSuchAlgorithmException {
        Response response = service._insertPayment(payment);
        return new ResponseEntity<>(response.jsonfy(), response.getStatusCode());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPaymentById(@PathVariable String id)
    {
        Response response = service._getPaymentById(id);
        return new ResponseEntity<>(response.jsonfy(), response.getStatusCode());
    }

    @GetMapping("")
    public ResponseEntity<?> getAllPayments(@RequestParam(defaultValue = "0") Integer year)
    {
        Response response;
        if(year == 0) response = service._getPayments();
        else response = service._getPaymentsByYear(year);
        return new ResponseEntity<>(response.jsonfy(), response.getStatusCode());
    }

}
