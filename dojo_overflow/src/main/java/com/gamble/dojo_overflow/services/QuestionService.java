package com.gamble.dojo_overflow.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gamble.dojo_overflow.models.Question;
import com.gamble.dojo_overflow.repositories.QuestionRepository;

@Service
public class QuestionService {

	private QuestionRepository questionRepository;

	public QuestionService(QuestionRepository questionRepository) {
		super();
		this.questionRepository = questionRepository;
	}
	
	public void addQuestion(Question question) {
		questionRepository.save(question);
	}

	public List<Question> findAll() {
		return (List<Question>) questionRepository.findAll();
	}

	public Question findQuestionById(int id) {
		// TODO Auto-generated method stub
		return questionRepository.findById(id);
	}
}
