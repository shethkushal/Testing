package com.project.api.service.implementation;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.project.api.entity.Weather;
import com.project.api.entity.Wind;
import com.project.api.repository.WeatherRepo;
import com.project.api.service.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService{

	public WeatherRepo repo;
	
	public WeatherServiceImpl(WeatherRepo repo) {
		this.repo = repo;
	}
	
	@Override
	public List<Weather> findAll() {
		return repo.findAll();
	}

	@Override
	public List<String> findAllUnique() {
		return repo.findAllUnique();
	}
	
	@Override
	public Weather latestWeather(String city) {
		return repo.latestWeather(city);
	}
	
	@Override
	public String latestWeatherProperty(String city, String property){
		return repo.latestWeatherProperty(city, property);
	}
	
	@Override
	@Transactional
	public Weather create(Weather weather) {
		Wind wind = weather.getWind();
		return repo.create(weather, wind);	
	}
}
