package com.project.spring_api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.project.spring_api.entity.Weather;
import com.project.spring_api.entity.Wind;


public interface WeatherRepo extends Repository<Weather, String> {
	
	public List<Weather> findAll();
	
	@Query("SELECT DISTINCT city FROM Weather")
	public List<String> findAllUnique();
	
	@Query("SELECT e FROM Weather e WHERE city = :cityName")
	public List<Weather> findByCityOrderByTimestampDesc(@Param("cityName") String city);
	
	
//	public String latestWeatherProperty(Weather city, String property);
	
	public void save(Wind wind);
	
	public Weather save(Weather weather);
}
