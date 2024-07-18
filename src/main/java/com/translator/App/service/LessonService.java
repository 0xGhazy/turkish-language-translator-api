package com.translator.App.service;

import com.translator.App.domain.Lesson;
import com.translator.App.exception.ResourceNotFound;
import com.translator.App.repository.LessonRepository;
import com.translator.App.utils.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class LessonService {

    @Autowired
    private LessonRepository repository;
    private static final Logger logger = LogManager.getLogger(LessonService.class);

    public Response _insertLesson(Lesson lesson) {
        String method = "_insertLesson";
        String action = "LessonRepository.save";
        lesson.setCreationTime( System.currentTimeMillis() );
        lesson.setStatementsWords(lesson.getStatementsWords());
        Lesson savedLesson = repository.save(lesson);
        logger.debug("method={}, action={}, status={}", method, action, "SUCCESS");
        return new Response.ResponseBuilder()
                .data(savedLesson)
                .message("Lesson created successfully.")
                .status(HttpStatus.CREATED)
                .build();
    }

    public Response _getLessonById(String id) {
        String method = "_getLessonById";
        String action = "LessonRepository.findById";
        Lesson lesson = repository.findById(id).orElseThrow(
                ()-> new ResourceNotFound("Lesson", "id", id));
        logger.debug("method={}, action={}, idParam={}, status={}", method, action, id,"SUCCESS");
        return new Response.ResponseBuilder()
                .data(lesson)
                .message("Lesson retrieved successfully.")
                .status(HttpStatus.OK)
                .build();
    }

    public Response _getLevelLessons(Integer level, String lessonType) {
        String method = "_getLevelLessons";
        String action = "LessonRepository.findAllByLevelAndLessonType";
        ArrayList<Lesson> lessons = repository.findAllByLevelAndLessonType(level, lessonType);
        Collections.sort(lessons, Comparator.comparing(Lesson::getCreationTime));
        logger.debug("method={}, action={}, params=[level:{}, type:{}], lessonsCount={}, status={}",
                method, action, level, lessonType,lessons.size(), "SUCCESS");
        return new Response.ResponseBuilder()
                .data(lessons)
                .message("Lessons retrieved successfully.")
                .status(HttpStatus.OK)
                .build();
    }

    public Response _getAllLessons() {
        String method = "_getAllLessons";
        String action = "LessonRepository.findAll";
        List<Lesson> lessons = repository.findAll();
        logger.debug("method={}, action={}, lessonsCount={}, status={}", method, action, lessons.size(), "SUCCESS");
        return new Response.ResponseBuilder()
                .data(lessons)
                .message("Lessons retrieved successfully.")
                .status(HttpStatus.OK)
                .build();
    }

}
