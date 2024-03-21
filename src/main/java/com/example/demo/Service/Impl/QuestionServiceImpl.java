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
        return questionRepository.save(question);
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
            existingQuestion.setIndice_optionCorrecte(newQuestion.getIndice_optionCorrecte());
            return questionRepository.save(existingQuestion);
        } else {
            throw new RuntimeException("Question not found with id: " + id);
        }
    }

    @Override
    public void deleteQuestion(Integer id) {
        questionRepository.deleteById(id);
    }
}
