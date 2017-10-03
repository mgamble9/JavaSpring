package com.gamble.queries_and_joins.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gamble.queries_and_joins.models.City;
import com.gamble.queries_and_joins.models.Country;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long>{

	@Query("SELECT c, l FROM Country c JOIN c.languages l WHERE l.language = ?1 ORDER BY l.percentage DESC")
	List<Object[]> findAllByLanguage(String language);

	@Query(value="SELECT c.name, COUNT(cities.id) AS num_cities FROM countries c JOIN cities ON c.id = cities.country_id GROUP BY c.id ORDER BY num_cities DESC", nativeQuery=true)
	List<Object[]> findTotalCitiesByCountry();
	
	@Query("SELECT cities FROM Country c JOIN c.cities cities WHERE c.name =?1 AND cities.population > ?2 ORDER BY cities.population DESC")
	List<City> findAllCitiesAndPopulation(String country, int pop);

	@Query("SELECT c, l FROM Country c JOIN c.languages l WHERE l.percentage > ?1 ORDER BY l.percentage DESC")
	List<Object[]> findCountryLanguagesbyPercent(float percentage);

	@Query("SELECT c FROM Country c WHERE c.surface_area < ?1 AND c.population > ?2")
	List<Country> findCountryBySurfaceAreaAndPop(float surface_area, int pop);

	@Query("SELECT c FROM Country c WHERE c.government_form = ?1 AND c.capital > ?2 AND c.life_expectancy > ?3")
	List<Country> findCountryByGovFormCapLifeExp(String gov_form, int cap, float life_exp);

	@Query("SELECT c, cities FROM Country c JOIN c.cities cities WHERE c.name = ?1 AND cities.district = ?2 AND cities.population > ?3")
	List<Object[]> findCountryByNameCityDistCityPop(String name, String district, int pop);

	@Query(value="SELECT c.region, COUNT(c.id) AS num_countries FROM countries c GROUP BY c.region ORDER BY num_countries DESC", nativeQuery=true)
	List<Object[]> findRegionCountryCount();
}
