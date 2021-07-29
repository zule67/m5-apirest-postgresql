package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Car;
import com.example.demo.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService {
	
	private CarRepository carRepository;
	
	public CarServiceImpl(CarRepository carRepository) {
		this.carRepository = carRepository;
	}
	
	@Override
	public List<Car> findAll() {
		return this.carRepository.findAll();
	}

	@Override
	public Optional<Car> findOne(Long id) {
		return this.findOne(id);
	}

	@Override
	public Car save(Car car) {
		return this.save(car);
	}

	@Override
	public List<Car> findByDoors(Integer doors) {
		return this.findByDoors(doors);
	}

	@Override
	public Integer count() {
		return this.count();
	}

	@Override
	public void delete(Long id) {
		this.delete(id);
	}

	@Override
	public void deleteAll() {
		this.deleteAll();
		
	}
	
	

}
