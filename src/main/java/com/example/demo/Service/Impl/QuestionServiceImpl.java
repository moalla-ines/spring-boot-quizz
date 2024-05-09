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
        if (question.getIdquestion() != null) {
            Optional<Question> existingQuestionOptional = questionRepository.findById(question.getIdquestion());
            if (existingQuestionOptional.isPresent()) {
                Question existingQuestion = existingQuestionOptional.get();
                existingQuestion.setText(question.getText());
                existingQuestion.setOption1(question.getOption1());
                existingQuestion.setOption2(question.getOption2());
                existingQuestion.setOption3(question.getOption3());
                existingQuestion.setOption4(question.getOption4());
                existingQuestion.setIndiceoptionCorrecte(question.getIndiceoptionCorrecte());
                return questionRepository.save(existingQuestion);
            }
        } else if (question.getQuiz() != null) {
            // Save a new question if idquestion is null and quiz is not null
            return questionRepository.save(question);
        }

        throw new IllegalArgumentException("idquestion is null and quiz is null");
    }


    @Override
    public Question updateQuestion(Integer idquestion, Question updatedQuestion) {
        return questionRepository.findById(idquestion)
                .map(existingQuestion -> {
                    existingQuestion.setText(updatedQuestion.getText());
                    existingQuestion.setOption1(updatedQuestion.getOption1());
                    existingQuestion.setOption2(updatedQuestion.getOption2());
                    existingQuestion.setOption3(updatedQuestion.getOption3());
                    existingQuestion.setOption4(updatedQuestion.getOption4());
                    existingQuestion.setIndiceoptionCorrecte(updatedQuestion.getIndiceoptionCorrecte());
                    existingQuestion.setQuiz(updatedQuestion.getQuiz());
                    return questionRepository.save(existingQuestion);
                })
                .orElseThrow(() -> new RuntimeException("Question not found with id: " + idquestion));
    }


    @Override
    public void deleteQuestion(Integer idquestion) {
        questionRepository.deleteById(idquestion);
    }

    @Override
    public List<Question> findAllByIdQuiz(Integer idquiz) {
        return questionRepository.findAllByIdQuiz(idquiz);
    }
}
