package com.gamble.drivers_license.services;

import org.springframework.stereotype.Service;

import com.gamble.drivers_license.models.License;
import com.gamble.drivers_license.models.Person;
import com.gamble.drivers_license.repositories.LicenseRepository;

@Service
public class LicenseService {
	
    private LicenseRepository licenseRepository;

    public LicenseService(LicenseRepository licenseRepository){
        this.licenseRepository = licenseRepository;
    }

	public void addLicense(License license) {
		licenseRepository.save(license);
	}

	public void updateLicense(License license) {
		licenseRepository.save(license);
	}

	public void destroyLicense(Long id) {
		licenseRepository.removeById(id);;
	}

	public License findLicenseByPerson(Person person) {
	    return licenseRepository.findLicenseByPerson(person);
	}

	public License findLicenseByNumber(String number) {
	    return licenseRepository.findLicenseByNumber(number);
	}

}
