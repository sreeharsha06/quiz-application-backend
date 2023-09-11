package com.quiz.quizapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.quizapp.models.Quiz;


public interface Quizdao extends JpaRepository<Quiz, Integer> {
    
}
