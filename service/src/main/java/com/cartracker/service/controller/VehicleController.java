package com.cartracker.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cartracker.service.entity.VehicleData;
import com.cartracker.service.entity.Vehicles;
import com.cartracker.service.service.VehicleService;

@RestController
@CrossOrigin
public class VehicleController {
	
	@Autowired
	private VehicleService service;
	
	@RequestMapping(method=RequestMethod.GET , value="/all/vehicles")
	public List<Vehicles> findAll(){	
		return service.findAll();
	}
	
	@RequestMapping(method=RequestMethod.GET , value="/all/vehicles/data")
	public List<VehicleData> findAllData(){	
		return service.findAllData();
	}
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.PUT, value="/vehicles")
	public List<Vehicles> update(@RequestBody List<Vehicles> vehicle){
		return service.update(vehicle);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/readings")
	public VehicleData create(@RequestBody VehicleData data){
		return service.create(data);
	}
}
