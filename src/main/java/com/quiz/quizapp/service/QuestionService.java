package com.quiz.quizapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.quizapp.dao.Questiondao;
import com.quiz.quizapp.models.Question;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    Questiondao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionByLevel(String level){
        try{
            return new ResponseEntity<>(questionDao.findBydifficultyLevel(level), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
        

    public ResponseEntity<String> addQuestion(Question question){
        try{
            questionDao.save(question);
            return new ResponseEntity<>("Successfully added", HttpStatus.CREATED);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteQuestion(Integer id){
        try{
            questionDao.deleteById(id);
            return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
    }

}
