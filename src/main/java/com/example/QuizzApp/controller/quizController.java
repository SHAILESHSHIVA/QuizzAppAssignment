package com.example.QuizzApp.controller;

import com.example.QuizzApp.entity.Quiz;
import com.example.QuizzApp.exception.ResourceNotFound;
import com.example.QuizzApp.payLoad.Response;
import com.example.QuizzApp.payLoad.ResponseAnswer;
import com.example.QuizzApp.payLoad.ResponseCreateQuizz;
import com.example.QuizzApp.payLoad.ResponseListOfQuizes;
import com.example.QuizzApp.repository.QuizzRepository;
import com.example.QuizzApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class quizController {



    @Autowired
    QuizService quizService;
    @Autowired
    QuizzRepository quizzRepository;



//    create new quizzes
    @PostMapping("/quizzes")
    public ResponseEntity<ResponseCreateQuizz> createQuiz(@RequestBody Quiz quiz) {


        ResponseCreateQuizz res = quizService.createQuizz(quiz);


        return new ResponseEntity<>(res,HttpStatus.CREATED);
    }



//    get correct ans enpPoint
    @GetMapping("/quizzes/{Id}/result")
    public ResponseEntity<ResponseAnswer> getCorrectAns(@PathVariable int Id) throws ResourceNotFound {

        ResponseAnswer ans = quizService.getCorrectAns(Id);

        return ResponseEntity.ok(ans);
    }


//    get all quizzes list

    @GetMapping("/quizzes/all")
    public ResponseEntity<ResponseListOfQuizes> getAllQuizzes(){

        ResponseListOfQuizes listOfQuizzes = quizService.getAllQuizzes();
        return ResponseEntity.ok(listOfQuizzes);
    }

//    get all active quizzes list
    @GetMapping("/quizzes/active")
    public ResponseEntity<ResponseListOfQuizes> getActiveQuiz(){

        ResponseListOfQuizes active = quizService.getActiveQuizzes();
        return ResponseEntity.ok(active);
    }








}
