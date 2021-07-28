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
		return null;
	}

}
