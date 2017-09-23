package com.gamble.dojo_and_ninjas.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gamble.dojo_and_ninjas.models.Ninja;
import com.gamble.dojo_and_ninjas.repositories.NinjaRepository;

@Service
public class NinjaService {

	private NinjaRepository ninjaRepository;

	public NinjaService(NinjaRepository ninjaRepository) {
		this.ninjaRepository = ninjaRepository;
	}
	
	public void addNinja(Ninja ninja) {
		ninjaRepository.save(ninja);
	}
	
	public List<Ninja> allNinjas() {
		// TODO Auto-generated method stub
		return (List<Ninja>) ninjaRepository.findAll();
	}

}
