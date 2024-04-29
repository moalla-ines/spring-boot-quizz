package com.example.demo.Service.Impl;

import com.example.demo.Entity.Question;
import com.example.demo.Entity.Quiz;
import com.example.demo.Repository.QuestionRepository;
import com.example.demo.Repository.QuizRepository;
import com.example.demo.Service.QuizService;
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

    @Override
    public Quiz createQuiz(Quiz quiz) {
        if (quiz.getIdquiz() != null) {
            Optional<Quiz> existingQuizOptional = quizRepository.findById(quiz.getIdquiz());
            if (existingQuizOptional.isPresent()) {
                Quiz existingQuiz = existingQuizOptional.get();
                existingQuiz.setTitre_quiz(quiz.getTitre_quiz());
                existingQuiz.setDescription(quiz.getDescription());
                existingQuiz.setNb_questions(quiz.getNb_questions());
                existingQuiz.setCategorie(quiz.getCategorie());
                existingQuiz.setQuestions(quiz.getQuestions());
                existingQuiz.setNiveau(quiz.getNiveau());
                if (existingQuiz.getQuestions() != null) {
                    existingQuiz.getQuestions().forEach(question -> question.setQuiz(existingQuiz));
                }
                return quizRepository.save(existingQuiz);
            } else {
                // Gérer l'erreur ici si l'identifiant existe mais le quiz n'a pas été trouvé
                return null;
            }
        } else {
            if (quiz.getQuestions() != null) {
                quiz.getQuestions().forEach(question -> question.setQuiz(quiz));
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
            existingQuiz.setQuestions(updatedQuiz.getQuestions());
            for (Question question : existingQuiz.getQuestions()) {
                question.setQuiz(existingQuiz);
            }

            return quizRepository.save(existingQuiz);
        } else {
            throw new IllegalArgumentException("Quiz not found with id " + idquiz);
        }
    }

    @Override
    public void deleteQuiz(Integer id) {
        quizRepository.deleteById(id);
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
