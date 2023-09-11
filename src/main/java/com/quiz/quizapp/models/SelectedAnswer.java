package com.quiz.quizapp.models;

public class SelectedAnswer{

    private Integer id;
    private String option;

    public SelectedAnswer(Integer id, String option) {
        this.id = id;
        this.option = option;
    }

    public Integer getId(){
        return this.id;
    }

    public String getOption(){
        return this.option;
    }

}