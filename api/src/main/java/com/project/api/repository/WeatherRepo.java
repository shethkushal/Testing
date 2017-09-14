package com.project.api.repository;

import java.util.List;

import com.project.api.entity.Weather;
import com.project.api.entity.Wind;

public interface WeatherRepo {
	

	public List<Weather> findAll();
	
	public List<String> findAllUnique();
	
	public Weather latestWeather(String city);
	
	public String latestWeatherProperty(String city, String property);
	
	public Weather create(Weather weather, Wind wind);

}
