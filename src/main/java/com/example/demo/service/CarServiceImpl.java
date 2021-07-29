package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.demo.domain.Car;
import com.example.demo.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService {
	
	private static final Integer MIN_DOORS = 3;

	private final Logger log = LoggerFactory.getLogger(CarServiceImpl.class);
	
	private CarRepository carRepository;
	
	public CarServiceImpl(CarRepository carRepository) {
		this.carRepository = carRepository;
	}
	
	@Override
	public List<Car> findAll() {
		log.info("Showing all the car in the database");
		return this.carRepository.findAll();
	}

	@Override
	public Optional<Car> findOne(Long id) {
		log.info("Showing a car by Id");
		return this.carRepository.findById(id);
	}

	@Override
	public Car save(Car car) {
		log.info("Saving a car in the database");
		
		if(!this.validateCar(car))
			return null;
			
		return this.carRepository.save(car);
	}

	private boolean validateCar(Car car) {
		if (car == null) {
			log.warn("Trying to save a null car");
			return false; 
		}
		if(car.getDoors() == null || car.getDoors() < MIN_DOORS) {
			log.warn("Trying to create a car with not allowed num of doors");
			return false;
		}
		
		return true;
	}

	@Override
	public List<Car> findByDoors(Integer doors) {
		log.info("Looking for by num of doors");
		if (doors < MIN_DOORS) {
			log.warn("Trying to search less than allowed doors");
			return new ArrayList<Car>();
		}
		
		return this.carRepository.findByDoors(doors);
	}

	@Override
	public Long count() {
		log.info("Counting all the cars");
		return this.carRepository.count() ;
	}

	@Override
	public void deleteById(Long id) {
		log.info("Deleting a car by Id");
		
		if(id == null || id < 0 || id == 0) {
			log.warn("Trying to delete a car with a wrong id");
			return;
		}
		
		try {
			this.carRepository.deleteById(id);
		} catch(Exception e) {
			log.error("Error trying to delete a car by Id{}", id, e);
		}
	}

	@Override
	public void deleteAll() {
		log.info("Deleting all the cars in database");
		this.carRepository.deleteAll();
		
	}

	@Override
	public void deleteAll(List<Car> cars) {
		log.info("Deleting a specific list of cars");
		
		if(cars == null || cars.isEmpty()) {
		log.warn("Trying to delete an empty or null list");
		// opcion 2 (hace las dos comprobaciones en una)
		// if(CollectionUtils.isempty(cars))
		return;
		}
			
		this.carRepository.deleteAll(cars);
		
	}

	@Override
	public void deleteAllByIds(List<Long> ids) {
		log.info("Deleting car by id");
		if (CollectionUtils.isEmpty(ids)) {
			log.warn("Trying to delete an empty or null car list");
			return;
		}
		this.carRepository.deleteAllById(ids);
		
	}

	
	

}
