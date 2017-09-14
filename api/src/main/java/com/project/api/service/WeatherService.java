package com.project.api.service;

import java.util.List;

import com.project.api.entity.Weather;

public interface WeatherService {

	public List<Weather> findAll();
	
	public List<String> findAllUnique();
	
	public Weather latestWeather(String city);
	
	public String latestWeatherProperty(String city, String property);
	
	public Weather create(Weather weather);
}
