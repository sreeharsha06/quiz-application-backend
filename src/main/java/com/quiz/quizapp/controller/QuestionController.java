package com.quiz.quizapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.quizapp.models.Question;
import com.quiz.quizapp.service.QuestionService;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Question>> GetAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("level/{level}")
    public ResponseEntity<List<Question>> getQuestionByLevel(@PathVariable String level) {
        return questionService.getQuestionByLevel(level);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @GetMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id){
        return questionService.deleteQuestion(id);
    }
}
