package com.translator.App.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.translator.App.exception.InvalidLessonType;
import com.translator.App.exception.ResourceNotFound;
import com.translator.App.service.LessonService;
import com.translator.App.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/levels")
public class LevelController {

    @Autowired
    private LessonService service;

    @GetMapping("/{level}/{lessonType}")
    public ResponseEntity<?> getLevelLessons(@PathVariable Integer level,
                                             @PathVariable String lessonType) throws JsonProcessingException {
        if(lessonType.equalsIgnoreCase("text") || lessonType.equalsIgnoreCase("video"))
        {
            if (level > 0 && level <= 3)
            {
                Response response = service._getLevelLessons(level, lessonType);
                return new ResponseEntity<>(response.jsonfy(), response.getStatusCode());
            }
            else
                throw new ResourceNotFound("Lesson", "level", ""+level);
        }
        throw new InvalidLessonType("lessonType", lessonType);
    }
}
