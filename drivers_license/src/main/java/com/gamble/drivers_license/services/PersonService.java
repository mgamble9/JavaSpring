package com.gamble.drivers_license.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gamble.drivers_license.models.Person;
import com.gamble.drivers_license.repositories.PersonRepository;

@Service
public class PersonService {

    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

	public void addPerson(Person person) {
		personRepository.save(person);
	}
	
	public Person findPersonById(Long id) {
	    return personRepository.findOne(id);
	}

    public List<Person> allPeople() {
		return (List<Person>) personRepository.findAll();
    }

	
}
