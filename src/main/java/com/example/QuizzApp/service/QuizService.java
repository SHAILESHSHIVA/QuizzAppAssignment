package com.example.QuizzApp.service;

import com.example.QuizzApp.entity.Quiz;
import com.example.QuizzApp.exception.ResourceNotFound;
import com.example.QuizzApp.payLoad.Response;
import com.example.QuizzApp.payLoad.ResponseAnswer;
import com.example.QuizzApp.payLoad.ResponseCreateQuizz;
import com.example.QuizzApp.payLoad.ResponseListOfQuizes;
import com.example.QuizzApp.repository.QuizzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {


    @Autowired
    QuizzRepository quizzRepository;


//    change status of quiz based on startDateTime

    @Scheduled(cron = "0 * * * * *")
    public void updateQuizStatus(){
        LocalDateTime time = LocalDateTime.now();

        for(Quiz quiz: quizzRepository.findAll()){
            if(time.isBefore(quiz.getStartDate())){
                quiz.setStatus("inactive");
            }
            else if(time.isAfter(quiz.getEndDate())){
                quiz.setStatus("finished");
            }
            else{
                quiz.setStatus("active");
            }

            quizzRepository.save(quiz);
        }
    }


//    create quiz service
    public ResponseCreateQuizz createQuizz(Quiz quiz){
        if (quiz.getQuestion() == null || quiz.getOptions() == null ||
                quiz.getAns() < 0 || quiz.getAns() >= quiz.getOptions().size() ||
                quiz.getStartDate() == null || quiz.getEndDate() == null) {
            return new ResponseCreateQuizz(null,"please fill every field",false);
        }

        if (quiz.getStartDate().isAfter(quiz.getEndDate())) {
            return new ResponseCreateQuizz(null,"startDate should be before endDate",false);
        }
        Quiz res = quizzRepository.save(quiz);

        return new ResponseCreateQuizz(res,"quizz created successfully",true);


    }


// get correct answer service

  public ResponseAnswer getCorrectAns(int Id) throws ResourceNotFound{

        Quiz quiz = quizzRepository.findById(Id).orElseThrow(()->new ResourceNotFound("this id "+ Id + " not exist in database"));

        LocalDateTime time = LocalDateTime.now();


      if(time.isBefore(quiz.getEndDate().plusMinutes(5)) ){

          ResponseAnswer res = new ResponseAnswer("Quiz not finished",false);
          return res;
      }

        List<String> options = quiz.getOptions();
        String ans = options.get(quiz.getAns());
        return new ResponseAnswer(ans,true);

  }

//  get all quizzes list service
  public ResponseListOfQuizes getAllQuizzes(){

        List<Quiz> listOfQuizzes = quizzRepository.findAll();

        return new ResponseListOfQuizes(listOfQuizzes,true);

  }

//  get all active quizzes
    public ResponseListOfQuizes getActiveQuizzes(){

        List<Quiz>  quizzes = quizzRepository.findAll();

        List<Quiz> active = new ArrayList<>();

        for(Quiz quiz: quizzes){

            if(quiz.getStatus().equals("active")){
                active.add(quiz);
            }
        }


        return new ResponseListOfQuizes(active,true);

    }



}
