package com.example.QuizzApp.payLoad;


import com.example.QuizzApp.entity.Quiz;

import java.util.List;

public class ResponseListOfQuizes {

    private List<Quiz> listofQuizs;
    private  boolean status;

    public ResponseListOfQuizes(List<Quiz> listofQuizs, boolean status) {
        this.listofQuizs = listofQuizs;
        this.status = status;
    }

    public List<Quiz> getListofQuizs() {
        return listofQuizs;
    }

    public void setListofQuizs(List<Quiz> listofQuizs) {
        this.listofQuizs = listofQuizs;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
