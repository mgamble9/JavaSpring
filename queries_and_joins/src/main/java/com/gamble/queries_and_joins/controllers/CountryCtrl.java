package com.gamble.queries_and_joins.controllers;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gamble.queries_and_joins.models.City;
import com.gamble.queries_and_joins.models.Country;
import com.gamble.queries_and_joins.models.Language;
import com.gamble.queries_and_joins.services.ApiService;

@Controller
public class CountryCtrl {

	private final ApiService apiService;
	
	public CountryCtrl(ApiService apiService) {
		this.apiService = apiService;
	}
	
	@RequestMapping("/")
	public String home(Model model) {
	
		//What query would you run to get all the countries
		//that speak Slovene? Your query should return the
		//name of the country, language and language
		//percentage. Your query should arrange the
		//result by language percentage in descending
		//order.
		List<Object[]> table = apiService.findAllByLanguage("Slovene");

		//What query would you run to display the total
		//number of cities for each country? Your query
		//should return the name of the country and the
		//total number of cities. Your query should
		//arrange the result by the number of cities in
		//descending order.
		table = apiService.findTotalCitiesByCountry();
		//System.out.println("table" + table.toString());
		//for (Object[] row: table) {
		//	String country = (String) row[0];
		//	BigInteger num_cities = (BigInteger) row[1];
		//	System.out.println("Country: " + country + " no. of cities: " + num_cities);
		//}

		//What query would you run to get all the cities
		//in Mexico with a population of greater than 500,000?
		//Your query should arrange the result by population in
		//descending order.
		List<City> table2= apiService.findAllCitiesAndPopulation("Mexico", 500000);
//		for (City row: table2) {
//			String cityname = row.getName();
//			int pop = row.getPopulation();
//			System.out.println("City: " + cityname + " pop: " + pop);
//		}

		//What query would you run to get all languages
		//in each country with a percentage greater than
		//89%? Your query should arrange the result by
		//percentage in descending order.
		table = apiService.findCountryLanguagesbyPercent(89);
//		for (Object[] row: table) {
//			Country country = (Country) row[0];
//			Language language = (Language) row[1];
//			System.out.println("Country: " + country.getName() + " language: " + language.getLanguage() +
//						" percentage: " + language.getPercentage());
//		}

		//What query would you run to get all the
		//countries with Surface Area below 501 and
		//Population greater than 100,000?
		List<Country> table3 = apiService.findCountryBySurfaceAreaAndPop(501, 100000);
//		for (Country row: table3) {
//		System.out.println("Country: " + row.getName() + " surface area: " + row.getSurface_area() +
//					" population: " + row.getPopulation());
//		}

		// What query would you run to get countries with
		//only Constitutional Monarchy with a capital greater
		//than 200 and a life expectancy greater than 75 years?
		table3 = apiService.findCountryByGovFormCapLifeExp("Constitutional Monarchy", 200, 75);
//		for (Country row: table3) {
//		System.out.println("Country: " + row.getName() + " government form: " + row.getGovernment_form() +
//					" life expectancy: " + row.getLife_expectancy());
//		System.out.println("CONTINENT: " + row.getContinent());
//		}

		//What query would you run to get all the cities of
		//Argentina inside the Buenos Aires district and have
		//the population greater than 500, 000? The query
		//should return the Country Name, City Name, District
		//and Population.
		table = apiService.findCountryByNameCityDistCityPop("Argentina", "Buenos Aires", 500000);
//		for (Object[] row: table) {
//		Country country = (Country) row[0];
//		City city = (City) row[1];
//		System.out.println("Country: " + country.getName() + " city name: " + city.getName() +
//					" district: " + city.getDistrict() + " pop: " + city.getPopulation());
//		}

		//What query would you run to summarize the number of
		//countries in each region? The query should display the
		//name of the region and the number of countries. Also,
		//the query should arrange the result by the number of
		//countries in descending order.
		table = apiService.findRegionCountryCount();
		for (Object[] row: table) {
		String region_name = (String) row[0];
		BigInteger num_countries = (BigInteger) row[1];
		System.out.println("Region: " + region_name + " no. of countries: " + num_countries);
		}

		
		model.addAttribute("table", table2);
		return "WEB-INF/views/index.jsp";
	}
}
