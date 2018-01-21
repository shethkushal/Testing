package com.cartracker.service.repository;

import java.util.List;

import com.cartracker.service.entity.Tires;
import com.cartracker.service.entity.VehicleData;
import com.cartracker.service.entity.Vehicles;

public interface VehicleRepo {

	public List<Vehicles> findAll();
	
	public List<VehicleData> findAllData();

	public List<Vehicles> update(List<Vehicles> vehicle);

	public VehicleData create(VehicleData data, Tires tire);
}
