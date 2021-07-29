package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.Car;

public interface CarService {
	
	/**
	 * It retrieves all cars from database
	 * @return List<Car>
	 */
	List<Car> findAll();
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Optional<Car> findOne(Long id);
	
	List<Car> findByDoors(Integer doors);
	
	Long count();
	
	Car save(Car car);
	
	void deleteById(Long id);
	
	void deleteAll();
	
	void deleteAll(List<Car> cars);
	
	void deleteAllByIds(List<Long> ids);
	
}
