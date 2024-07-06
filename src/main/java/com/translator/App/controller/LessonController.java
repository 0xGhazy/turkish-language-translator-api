package com.translator.App.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.translator.App.domain.Lesson;
import com.translator.App.service.LessonService;
import com.translator.App.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/lessons")
public class LessonController {

    @Autowired
    private LessonService service;

    @PostMapping("")
    public ResponseEntity<?> createLesson(@RequestBody Lesson lesson) {
        Response response = service._insertLesson(lesson);
        return new ResponseEntity<>(response.jsonfy(), response.getStatusCode());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getLessonById(@PathVariable String id) throws JsonProcessingException {
        Response response = service._getLessonById(id);
        return new ResponseEntity<>(response.jsonfy(), response.getStatusCode());
    }

    @GetMapping("")
    public ResponseEntity<?> getAllLessons() {
        Response response = service._getAllLessons();
        return new ResponseEntity<>(response.jsonfy(), response.getStatusCode());
    }

}
