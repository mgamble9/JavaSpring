package com.gamble.queries_and_joins.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gamble.queries_and_joins.models.City;
import com.gamble.queries_and_joins.models.Country;
import com.gamble.queries_and_joins.repositories.CityRepository;
import com.gamble.queries_and_joins.repositories.CountryRepository;
import com.gamble.queries_and_joins.repositories.LanguageRepository;

@Service
public class ApiService {

	private CountryRepository countryRepository;
	private CityRepository cityRepository;
	private LanguageRepository languageRepository;
	
	public ApiService(CountryRepository countryRepository, CityRepository cityRepository,
			LanguageRepository languageRepository) {
		super();
		this.countryRepository = countryRepository;
		this.cityRepository = cityRepository;
		this.languageRepository = languageRepository;
	}
	
	//What query would you run to get all the countries
	//that speak Slovene? Your query should return the
	//name of the country, language and language
	//percentage. Your query should arrange the
	//result by language percentage in descending
	//order.

	public List<Object[]> findAllByLanguage(String language) {
		return countryRepository.findAllByLanguage(language);
	}

	public List<Object[]> findTotalCitiesByCountry() {
		return countryRepository.findTotalCitiesByCountry();
	}
	
	public List<City> findAllCitiesAndPopulation(String country, int pop) {
		return countryRepository.findAllCitiesAndPopulation(country, pop);
	}
	
	public List<Object[]> findCountryLanguagesbyPercent(float percentage) {
		return countryRepository.findCountryLanguagesbyPercent(percentage);
	}

	public List<Country> findCountryBySurfaceAreaAndPop(float surface_area, int pop) {
		return countryRepository.findCountryBySurfaceAreaAndPop(surface_area, pop);
	}
	
	public List<Country> findCountryByGovFormCapLifeExp(String gov_form, int cap, float life_exp) {
		return countryRepository.findCountryByGovFormCapLifeExp(gov_form, cap, life_exp);
	}

	public List<Object[]> findCountryByNameCityDistCityPop(String name, String district, int pop) {
		return countryRepository.findCountryByNameCityDistCityPop(name, district, pop);
	}
	
	public List<Object[]> findRegionCountryCount() {
		return countryRepository.findRegionCountryCount();
	}
}
