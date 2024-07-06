package com.translator.App.repository;


import com.translator.App.domain.Lesson;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface LessonRepository extends MongoRepository<Lesson, String> {
    ArrayList<Lesson> findAllByLevelAndLessonType(Integer level, String lessonType);
}
