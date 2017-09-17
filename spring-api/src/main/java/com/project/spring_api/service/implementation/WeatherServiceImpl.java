package com.project.spring_api.service.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.spring_api.entity.Weather;
import com.project.spring_api.entity.Wind;
import com.project.spring_api.repository.WeatherRepo;
import com.project.spring_api.service.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService{

	@Autowired
	public WeatherRepo repo;

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
		List<Weather> result = new ArrayList<Weather>();
		result = repo.findByCityOrderByTimestampDesc(city);
		return result.get(0);
	}
	
//	@Override
//	public String latestWeatherProperty(String city, String property){
//		Weather result = latestWeather(city);
//		return repo.latestWeatherProperty(result, property);
//	}
	
	@Override
	@Transactional
	public Weather create(Weather weather) {
		Wind wind = weather.getWind();
		repo.save(wind);
		return repo.save(weather);	
	}
}
