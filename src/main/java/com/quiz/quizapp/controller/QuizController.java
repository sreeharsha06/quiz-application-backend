package com.quiz.quizapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import com.quiz.quizapp.service.QuizService;
import com.quiz.quizapp.models.QuestionWrapper;
import com.quiz.quizapp.models.SelectedAnswer;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String level, @RequestParam Integer numOfQ,
            @RequestParam String title) {
        return quizService.createQuiz(level, numOfQ, title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id) {
        return quizService.getQuestionsById(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Double> calculateScore(@PathVariable Integer id, @RequestBody List<SelectedAnswer> answers){
        return quizService.calculateScore(id, answers);
    }
}
