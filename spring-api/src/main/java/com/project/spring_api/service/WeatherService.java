package com.project.spring_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.spring_api.entity.Weather;

@Service
public interface WeatherService {

	public List<Weather> findAll();
	
	public List<String> findAllUnique();
	
	public Weather latestWeather(String city);
	
//	public String latestWeatherProperty(String city, String property);
	
	public Weather create(Weather weather);
}
