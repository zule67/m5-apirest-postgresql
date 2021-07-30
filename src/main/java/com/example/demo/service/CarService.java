package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.demo.domain.Car;

public interface CarService {
	// SPRING  REPOSITORY METHODS
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
	
	Long count();
	
	Car save(Car car);
	
	void deleteById(Long id);
	
	void deleteAll();
	
	void deleteAll(List<Car> cars);
	
	void deleteAllByIds(List<Long> ids);
	
	// CUSTOM METHODS
	
	List<Car> findByDoors(Integer doors);
	
	List<Car> findByManufacturerAndModel(String manufacturer, String model);
	
	List<Car> findByDoorGreaterThanEqual(Integer doors);
	
	List<Car> findByModelContaining(String model);
	
	List<Car> findByYearIn(List<Integer> years);
	
	List<Car> findByYearBetween(Integer startYear, Integer endYear);
	
	List<Car> findByReleaseDateBetween(LocalDate startDate, LocalDate endDate);
	
	List<Car> findByAvailableTrue();
	
	Long deleteAllByAvailableFalse();
	
}
