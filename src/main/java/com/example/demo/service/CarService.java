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
	
	Integer count();
	
	Car save(Car car);
	
	void delete(Long id);
	
	void deleteAll();
	
}
