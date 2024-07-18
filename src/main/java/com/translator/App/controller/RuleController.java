package com.translator.App.controller;


import com.translator.App.domain.RuleLesson;
import com.translator.App.service.RuleService;
import com.translator.App.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rules-lessons")
public class RuleController {

    @Autowired
    private RuleService service;

    @PostMapping("")
    public ResponseEntity<?> createLesson(@RequestBody RuleLesson ruleLesson)
    {
        Response response = service._insertRuleLesson(ruleLesson);
        return new ResponseEntity<>(response.jsonfy(), response.getStatusCode());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLessonById(@PathVariable String id)
    {
        Response response = service._getRuleLessonById(id);
        return new ResponseEntity<>(response.jsonfy(), response.getStatusCode());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<?> getLessonsByType(@PathVariable String type)
    {
        Response response = service._getRuleLessonByType(type);
        return new ResponseEntity<>(response.jsonfy(), response.getStatusCode());
    }

    @GetMapping("/types")
    public ResponseEntity<?> getLessonsTypes()
    {
        Response response = service._getRuleLessonsTypes();
        return new ResponseEntity<>(response.jsonfy(), response.getStatusCode());
    }


}
