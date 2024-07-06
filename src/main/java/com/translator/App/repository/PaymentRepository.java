package com.translator.App.repository;


import com.translator.App.domain.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface PaymentRepository extends MongoRepository<Payment, String> {
    ArrayList<Payment> findByYear(Integer year);
}
