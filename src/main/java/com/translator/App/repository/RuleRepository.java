package com.translator.App.repository;

import com.translator.App.domain.RuleLesson;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface RuleRepository extends MongoRepository<RuleLesson, String> {
    ArrayList<RuleLesson> findByType(String type);
}
