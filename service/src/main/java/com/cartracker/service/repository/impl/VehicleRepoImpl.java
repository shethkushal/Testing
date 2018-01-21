package com.cartracker.service.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cartracker.service.entity.Tires;
import com.cartracker.service.entity.VehicleData;
import com.cartracker.service.entity.Vehicles;
import com.cartracker.service.repository.VehicleRepo;

@Repository
public class VehicleRepoImpl implements VehicleRepo {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Vehicles> findAll() {
		TypedQuery<Vehicles> query = em.createNamedQuery("Vehicles.findAll", Vehicles.class);
		return query.getResultList();
	}
	
	@Override
	public List<VehicleData> findAllData() {
		TypedQuery<VehicleData> query = em.createNamedQuery("VehicleData.findAll", VehicleData.class);
		return query.getResultList();
	}
	
	@Override
	public List<Vehicles> update(List<Vehicles> vehicle) {
		List<Vehicles> dupVehicle = vehicle;
		List<Vehicles> tempList = findAll();
		Vehicles temp = null;
		
		for (int i = 0; i < vehicle.size(); i++) {
			for (int j = 0; j < tempList.size(); j++) {
				if (vehicle.get(i).getVin().equals(tempList.get(j).getVin())) {
					temp = tempList.get(j);
					temp.setLastServiceDate(dupVehicle.get(i).getLastServiceDate()); 
					temp.setMake(dupVehicle.get(i).getMake());
					temp.setMaxFuelVolume(dupVehicle.get(i).getMaxFuelVolume());
					temp.setModel(dupVehicle.get(i).getModel());
					temp.setRedlineRpm(dupVehicle.get(i).getRedlineRpm());
					temp.setYear(dupVehicle.get(i).getYear());

					em.merge(temp);
					dupVehicle.remove(i);
				}
			}
		}
		for (int i = 0; i < dupVehicle.size(); i++) {
				em.persist(dupVehicle.get(i));
		}	
		return vehicle;
	}
	
	public String rulesAlert(VehicleData data, Vehicles vehicle){
		if(data.getEngineRpm() > vehicle.getRedlineRpm()){
			return "HIGH";
		}else if(data.getFuelVolume() < (vehicle.getMaxFuelVolume() * 0.01)){
			return "MEDIUM";
		}else if(data.getTires().getFrontLeft() < 32 || data.getTires().getFrontLeft() > 36 ||
				data.getTires().getFrontRight() < 32 || data.getTires().getFrontRight() > 36 ||
				data.getTires().getRearLeft() < 32 || data.getTires().getRearLeft() > 36 ||
				data.getTires().getRearRight() < 32 || data.getTires().getRearRight() > 36){
			return "LOW";
		}else if(data.isEngineCoolantLow() == true || data.isCheckEngineLightOn() == true){
			return "LOW";
		}else{
			return "NONE";
		}
	}
	
	
	@Override
	public VehicleData create(VehicleData data, Tires tire) {
		List<Vehicles> tempList = findAll();
		String alert = null;
		for(int i = 0; i < tempList.size(); i++){
			if(data.getVin().equals(tempList.get(i).getVin())){
				alert = rulesAlert(data, tempList.get(i));
			}
		}
		em.persist(tire);
		data.setTires(tire);
		data.setPriority(alert);
		em.persist(data);
		return data;
	}
}