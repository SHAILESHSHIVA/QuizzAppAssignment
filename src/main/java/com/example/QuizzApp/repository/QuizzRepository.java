package com.example.QuizzApp.repository;

import com.example.QuizzApp.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizzRepository extends JpaRepository<Quiz,Integer> {

//    Quiz findById(int id);
}
