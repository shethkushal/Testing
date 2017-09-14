package com.project.api.repository.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.json.JSONException;
import org.json.JSONObject;

import com.project.api.entity.Weather;
import com.project.api.entity.Wind;
import com.project.api.repository.WeatherRepo;

@Repository
public class WeatherRepoImpl implements WeatherRepo{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Weather> findAll() {
		TypedQuery<Weather> query = em.createNamedQuery("Weather.findAll", Weather.class);
		return query.getResultList();
	}

	@Override
	public List<String> findAllUnique() {
		List<String> result = new ArrayList<String>();
		findAll().forEach((x) -> {
			if (!result.contains(x.getCity())) {
				result.add(x.getCity());
			}
		});
		return result;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Weather latestWeather(String city) {
		List<Weather> result = new ArrayList<Weather>();
		TypedQuery<Weather> q = (TypedQuery<Weather>) em.createQuery("SELECT e FROM Weather e WHERE city = :city ORDER BY timestamp DESC");
		q.setParameter("city", city);
		result = q.getResultList();
		return result.get(0);
	}
	
	@Override
	public String latestWeatherProperty(String city, String property){
		Weather exists = latestWeather(city);
		JSONObject json = new JSONObject(exists);        
		String value = "";
		try {
			value = json.getString(property);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	    return  city.toUpperCase() + ", " + property.toUpperCase() + " = " + value;
	}
	
	@Override
	public Weather create(Weather weather, Wind wind) {
		em.persist(wind);
		weather.setWind(wind);
		em.persist(weather);
		return weather;
	}
}
