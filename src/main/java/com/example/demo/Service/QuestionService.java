package com.example.demo.Service;

import com.example.demo.Entity.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    List<Question> getAllQuestions();

    Optional<Question> getQuestionById(Integer id);

    Question createQuestion(Question question);

    Question updateQuestion(Integer idquestion, Question  updatedQuestion);

    void deleteQuestion(Integer idquestion);


    List<Question> findAllByIdQuiz(Integer idquiz);
}
