package com.project.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.api.constants.URI;
import com.project.api.entity.Weather;
import com.project.api.service.WeatherService;

@RestController
@CrossOrigin
@RequestMapping(value=URI.WEATHER)

@Api(tags="Weather")
public class WeatherController {
	
	private WeatherService service;
	
	public WeatherController(WeatherService service) {
		this.service = service;
	}

	@RequestMapping(method=RequestMethod.GET , value="/all")
	@ApiOperation(value="Find all the records of weather", notes="Searches for all the existing records of weather for the cities")
	public List<Weather> findAll(){	
		return service.findAll();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/all/unique-cities")
	@ApiOperation(value="Find all unique cities", notes="Returns all the cities that whose weather is rported previously")
	public List<String> findAllUnique(){
		return service.findAllUnique();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/latest-weather/{city}")
	@ApiOperation(value="Find latest weather of city", notes="Returns the the latest weather for the required city from the database")
	public Weather latestWeather(@PathVariable("city") String city){
		return service.latestWeather(city);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/latest-weather/{city}/{property}")
	@ApiOperation(value="Find particular weather property", notes="Returns the value of particular weather property for the given city")
	public String latestWeatherProperty(@PathVariable("city") String city, @PathVariable("property") String property){
		return service.latestWeatherProperty(city, property);
	}

	@RequestMapping(method=RequestMethod.POST)
	@ApiOperation(value="Records weather or cities", notes="Fetches the weather records and puts them into the backend database")
	public Weather create(@RequestBody Weather weather){
		return service.create(weather);
	}
}
