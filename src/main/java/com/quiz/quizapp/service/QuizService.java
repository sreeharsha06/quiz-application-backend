package com.quiz.quizapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.quizapp.dao.Questiondao;
import com.quiz.quizapp.dao.Quizdao;
import com.quiz.quizapp.models.Question;
import com.quiz.quizapp.models.QuestionWrapper;
import com.quiz.quizapp.models.Quiz;
import com.quiz.quizapp.models.SelectedAnswer;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    Quizdao quizDao;

    @Autowired
    Questiondao questiondao;


    public ResponseEntity<String> createQuiz(String level, Integer numOfQ, String title) {
        try {
            List<Question> questions = questiondao.findBydifficultyLevel(level, numOfQ);

            Quiz quiz = new Quiz();
            quiz.setTitle(level);

            quiz.setQuestions(questions);

            quizDao.save(quiz);

            return new ResponseEntity<>("Quiz created", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsById(Integer id) throws NoSuchElementException{
        try{
            Optional<Quiz> quiz = quizDao.findById(id);
            System.out.println(quiz);
            List<Question> questionsFromDb = quiz.get().getQuestions();
            List<QuestionWrapper> questionsForUsers = new ArrayList<>();

            for (Question q : questionsFromDb){
                QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
                questionsForUsers.add(qw);
            }

            return new ResponseEntity<>(questionsForUsers, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Double> calculateScore(Integer id, List<SelectedAnswer> answers){
        try{
            Quiz quiz = quizDao.findById(id).get();
            List<Question> que = quiz.getQuestions();

            double result = 0;
            double wrongAns = 0;
            int i = 0;
            for (SelectedAnswer ans : answers){

                if (ans.getOption().equals(que.get(i).getRightAnswer()))
                    result = result + 2;
                else
                    wrongAns = wrongAns + (0.5);
                i++;
            }   
            double finalResult = result - wrongAns;

            return new ResponseEntity<>(finalResult, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(0.0, HttpStatus.BAD_GATEWAY);
    }

}
