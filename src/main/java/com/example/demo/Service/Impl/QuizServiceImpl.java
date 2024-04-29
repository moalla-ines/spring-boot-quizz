package com.example.demo.Service.Impl;

import com.example.demo.Config.QuizNotFoundException;
import com.example.demo.Entity.Question;
import com.example.demo.Entity.Quiz;
import com.example.demo.Repository.QuestionRepository;
import com.example.demo.Repository.QuizRepository;
import com.example.demo.Service.QuizService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public  class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;



    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    public QuizServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    @Override
    public Optional<Quiz> getQuizById(Integer id) {
        return quizRepository.findById(id);
    }


    public Quiz createQuiz(Quiz quiz) throws QuizNotFoundException {
        if (quiz.getIdquiz() != null) {
            Optional<Quiz> existingQuizOptional = quizRepository.findById(quiz.getIdquiz());
            if (existingQuizOptional.isPresent()) {
                Quiz existingQuiz = existingQuizOptional.get();
                existingQuiz.setTitre_quiz(quiz.getTitre_quiz());
                existingQuiz.setDescription(quiz.getDescription());
                existingQuiz.setNb_questions(quiz.getNb_questions());
                existingQuiz.setQuestions(quiz.getQuestions());
                if (existingQuiz.getQuestions() != null) {
                    existingQuiz.getQuestions().forEach(question -> question.setQuiz(existingQuiz));
                }
                return quizRepository.save(existingQuiz);
            } else {

                throw new QuizNotFoundException("Quiz not found with ID: " + quiz.getIdquiz());
            }
        } else {
            if (quiz.getQuestions() != null) {
                quiz.getQuestions().forEach(question -> question.setQuiz(quiz));
            }
            if (quiz.getNiveau() != null && quiz.getCategorie() != null) {

            }
            return quizRepository.save(quiz);
        }
    }



    @Override
    public Quiz updateQuiz(Integer idquiz, Quiz updatedQuiz) {
        Optional<Quiz> existingQuizOptional = quizRepository.findById(idquiz);
        if (existingQuizOptional.isPresent()) {
            Quiz existingQuiz = existingQuizOptional.get();
            existingQuiz.setTitre_quiz(updatedQuiz.getTitre_quiz());
            existingQuiz.setDescription(updatedQuiz.getDescription());
            existingQuiz.setNb_questions(updatedQuiz.getNb_questions());
            existingQuiz.setCategorie(updatedQuiz.getCategorie());

            return quizRepository.save(existingQuiz);
        } else {
            throw new IllegalArgumentException("Quiz not found with id " + idquiz);
        }
    }



    @Override
    public void deleteQuiz(Integer idquiz) {
        quizRepository.deleteById(idquiz);
    }

    @Override
    public List<Quiz> getQuizzesByCategorie(Integer idcategorie) {
        return quizRepository.findByCategorieIdcategorie(idcategorie);
    }

    @Override
    public List<Quiz> getQuizzesByNiveau(Integer idNiveau) {
        return quizRepository.findByNiveauIdNiveau(idNiveau);
    }

}
