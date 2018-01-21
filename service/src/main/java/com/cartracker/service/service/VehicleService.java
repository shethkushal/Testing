package com.cartracker.service.service;

import java.util.List;

import com.cartracker.service.entity.VehicleData;
import com.cartracker.service.entity.Vehicles;

public interface VehicleService {
	
	public List<Vehicles> findAll();
	
	public List<VehicleData> findAllData();
	
	public List<Vehicles> update(List<Vehicles> vehicle);
	
	public VehicleData create(VehicleData data);
	
}
