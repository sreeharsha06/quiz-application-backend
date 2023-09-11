package com.quiz.quizapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.quiz.quizapp.models.Question;

import java.util.List;


public interface Questiondao extends JpaRepository<Question, Integer>{

    List<Question> findBydifficultyLevel(String level);

    @Query(value = "select * from question where difficulty_level = :level order by RANDOM() limit :numQ", nativeQuery = true)
    List<Question> findBydifficultyLevel(String level, Integer numQ);
    
}
