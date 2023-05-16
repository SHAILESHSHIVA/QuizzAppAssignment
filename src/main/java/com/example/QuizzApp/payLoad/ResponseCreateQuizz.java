package com.example.QuizzApp.payLoad;

import com.example.QuizzApp.entity.Quiz;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCreateQuizz {

    private Quiz quiz;
    private String message;
    private boolean Status;
}
