package com.example.demo.Service.Impl;

import com.example.demo.Entity.Question;
import com.example.demo.Repository.QuestionRepository;
import com.example.demo.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Optional<Question> getQuestionById(Integer id) {
        return questionRepository.findById(id);
    }

    @Override
    public Question createQuestion(Question question) {
        Optional<Question> existingQuestionOptional = questionRepository.findById(question.getIdquestion());
        if (existingQuestionOptional.isPresent()) {
            Question existingQuestion = existingQuestionOptional.get();
            existingQuestion.setText(question.getText());
            existingQuestion.setOption1(question.getOption1());
            existingQuestion.setOption2(question.getOption2());
            existingQuestion.setOption3(question.getOption3());
            existingQuestion.setOption4(question.getOption4());
            existingQuestion.setIndiceoptionCorrecte(question.getIndiceoptionCorrecte());
            existingQuestion.setQuiz(question.getQuiz());
            return questionRepository.save(existingQuestion);
        } else {
            return questionRepository.save(question);
        }
    }


    @Override
    public Question updateQuestion(Integer id, Question newQuestion) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if (optionalQuestion.isPresent()) {
            Question existingQuestion = optionalQuestion.get();
            existingQuestion.setText(newQuestion.getText());
            existingQuestion.setOption1(newQuestion.getOption1());
            existingQuestion.setOption2(newQuestion.getOption2());
            existingQuestion.setOption3(newQuestion.getOption3());
            existingQuestion.setOption4(newQuestion.getOption4());
            existingQuestion.setIndiceoptionCorrecte(newQuestion.getIndiceoptionCorrecte());
            existingQuestion.setQuiz(newQuestion.getQuiz());
            return questionRepository.save(existingQuestion);
        } else {
            throw new RuntimeException("Question not found with id: " + id);
        }
    }

    @Override
    public void deleteQuestion(Integer id) {
        questionRepository.deleteById(id);
    }

    @Override
    public List<Question> findAllByIdQuiz(Integer idquiz) {
        return questionRepository.findAllByIdQuiz(idquiz);
    }
}
