package com.gamble.dojo_and_ninjas.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gamble.dojo_and_ninjas.models.Dojo;
import com.gamble.dojo_and_ninjas.repositories.DojoRepository;

@Service
public class DojoService {
	
	private DojoRepository dojoRepository;
	
	public DojoService(DojoRepository dojoRepository) {
		this.dojoRepository = dojoRepository;
	}
	
	public void addDojo(Dojo dojo) {
		dojoRepository.save(dojo);
	}
	
	public Dojo findDojoByName(String name) {
		return dojoRepository.findDojoByName(name);
	}

	public List<Dojo> allDojos() {
		// TODO Auto-generated method stub
		return (List<Dojo>) dojoRepository.findAll();
	}
	
	public Dojo findDojoById(Long id) {
		return dojoRepository.findDojoById(id);
	}
}
