package com.translator.App.service;

import com.translator.App.domain.RuleLesson;
import com.translator.App.exception.ResourceNotFound;
import com.translator.App.repository.RuleRepository;
import com.translator.App.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RuleService {

    @Autowired
    private RuleRepository repository;

    public Response _insertRuleLesson(RuleLesson lesson)
    {
        lesson.setCreationTime(System.currentTimeMillis());
        RuleLesson savedLesson = repository.save(lesson);
        return new Response.ResponseBuilder()
                .data(savedLesson)
                .message("Lesson created successfully.")
                .status(HttpStatus.CREATED)
                .build();
    }

    public Response _getRuleLessonByType(String type)
    {
        List<RuleLesson> lessons;
        if(type.toLowerCase(Locale.ROOT).equals("all"))
        {
            lessons = repository.findAll();
        }
        else {
            lessons = repository.findByType(type);
        }
        Collections.sort(lessons, Comparator.comparing(RuleLesson::getCreationTime));
        return new Response.ResponseBuilder()
                .data(lessons)
                .message("Lesson retrieved successfully.")
                .status(HttpStatus.OK)
                .build();
    }

    public Response _getRuleLessonById(String id)
    {
        RuleLesson lesson = repository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Rule Lesson", "id", id));
        return new Response.ResponseBuilder()
                .data(lesson)
                .message("Lesson retrieved successfully.")
                .status(HttpStatus.OK)
                .build();
    }

    public Response _getRuleLessonsTypes()
    {
        List<RuleLesson> lessons = repository.findAll();
        HashSet<String> types = new HashSet<>();
        for (RuleLesson lesson: lessons) {
            types.add(lesson.getType());
        }
        return new Response.ResponseBuilder()
                .data(types)
                .message("Types retrieved successfully.")
                .status(HttpStatus.OK)
                .build();
    }

    public Response _updateLesson(RuleLesson lesson, String id)
    {
        RuleLesson oldLesson = repository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Rule Lesson", "id", id));
        if(lesson.getTitle() != null)
            oldLesson.setTitle(lesson.getTitle());
        if(lesson.getVideoUrl() != null)
            oldLesson.setVideoUrl(lesson.getVideoUrl());
        if(lesson.getType() != null)
            oldLesson.setType(lesson.getType());
        oldLesson.setModificationTime(System.currentTimeMillis());
        repository.save(oldLesson);
        return new Response.ResponseBuilder()
                .data(oldLesson)
                .message("Lesson updated successfully.")
                .status(HttpStatus.OK)
                .build();
    }

    public Response _deleteRuleLesson(String id)
    {
        RuleLesson oldLesson = repository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Rule Lesson", "id", id));
        repository.deleteById(id);
        return new Response.ResponseBuilder()
                .data(oldLesson)
                .message("Lesson deleted successfully.")
                .status(HttpStatus.OK)
                .build();
    }

}