package com.cartracker.service.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cartracker.service.entity.Tires;
import com.cartracker.service.entity.VehicleData;
import com.cartracker.service.entity.Vehicles;
import com.cartracker.service.repository.VehicleRepo;
import com.cartracker.service.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleRepo repo;
	
	@Override
	public List<Vehicles> findAll() {
		return repo.findAll();
	}
	
	@Override
	public List<VehicleData> findAllData() {
		return repo.findAllData();
	}
	
	@Override
	@Transactional
	public List<Vehicles> update(List<Vehicles> vehicle) {
		return repo.update(vehicle);
	}
	
	@Override
	@Transactional
	public VehicleData create(VehicleData data) {
		Tires tire = data.getTires();
		return repo.create(data, tire);	
	}

}
