package com.gamble.drivers_license.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gamble.drivers_license.models.License;
import com.gamble.drivers_license.models.Person;

@Repository
public interface LicenseRepository extends CrudRepository<License, Long> {

	void removeById(Long id);

	License findLicenseByPerson(Person person);


 


	
}
