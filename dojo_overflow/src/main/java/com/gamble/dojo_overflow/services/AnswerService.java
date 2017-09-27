package com.gamble.dojo_overflow.services;

import org.springframework.stereotype.Service;

import com.gamble.dojo_overflow.models.Answer;
import com.gamble.dojo_overflow.repositories.AnswerRepository;

@Service
public class AnswerService {

	private AnswerRepository answerRepository;

	public AnswerService(AnswerRepository answerRepository) {
		super();
		this.answerRepository = answerRepository;
	}
	
	public void addAnswer(Answer answer) {
		answerRepository.save(answer);
	}
}
