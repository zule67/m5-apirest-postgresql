package com.example.demo.rest;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Car;
import com.example.demo.dto.CarListDTO;
import com.example.demo.dto.CountDTO;
import com.example.demo.service.CarService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")
public class CarController {

	private final Logger log = LoggerFactory.getLogger(CarController.class);

	//dependencia
	private CarService carService;

	public CarController(CarService carService) { // spring injecta la dependencia
		this.carService = carService;
	}

	/* ========== SPRING METHODS ============ */
	
	/**
	 * http://localhost:8080/api/cars/1
	 * 
	 * @return a response entity with car
	 */
	@GetMapping("/cars/{id}")
	@ApiOperation("Buscar coche por Id")
	public ResponseEntity<Car> findOne(@ApiParam("Clave primaria car") @PathVariable Long id) {
		log.info("REST request to find one car");

		Optional<Car> carOpt = this.carService.findOne(id);
		
		//opcion1
		if (carOpt.isPresent())
			return ResponseEntity.ok(carOpt.get());
		
		return ResponseEntity.notFound().build();
		
		//opcion2
		//return carOpt.map(car -> ResponseEntity.ok(car)).orElseGet(
		//() -> new ResponseEntity<>(HttpStatus.ACCEPTED));

	}

	/**
	 * http://localhost:8080/api/cars
	 */
	@GetMapping("/cars")
	public List<Car> findAll() {
		log.info("REST request to find all cars");

		return this.carService.findAll();
	}

	/**
	 * @POST http://localhost:8080/api/cars
	 * @param car
	 */
	@PostMapping("/cars")
	public ResponseEntity<Car> create(@RequestBody Car car) {
		log.info("REST request to create a new car");

		if (car.getId() != null) { // Hay id, quiere decir que ya existe
			log.warn("Trying to create a new car with existing id");
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(this.carService.save(car));

	}

	/**
	 * @PUT http://localhost:8080/api/cars
	 * @param car
	 * @return car
	 */
	@PutMapping("/cars")
	public ResponseEntity<Car> update(@RequestBody Car car) {
		log.info("REST request to UPDATE an existing car");

		if (car.getId() == null) { // No hay id y por tanto no existe el coche a actualizar
			log.warn("Trying to update an existing car withOUT id");
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(this.carService.save(car));
	}

	/**
	 * @DELETE http://localhost:8080/api/cars/{id}
	 * @return
	 */
	@DeleteMapping("/cars/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		log.info("REST request to DELETE an existing car");

		this.carService.deleteById(id);

		return ResponseEntity.noContent().build();

	}

	/**
	 * @DELETE http://localhost:8080/api/cars
	 * @return
	 */
	@DeleteMapping("/cars")
	public ResponseEntity<Void> deleteAll() {
		log.info("REST request to DELETE all cars");

		this.carService.deleteAll();

		return ResponseEntity.noContent().build();
	}
	
	
	@GetMapping("/cars/count")
	public ResponseEntity<CountDTO> count(){
		log.info("REST request to count all cars");
		
		Long count = this.carService.count();
		CountDTO dto = new CountDTO(count);
		dto.setMessage("Respuesta del método count");
		return ResponseEntity.ok(dto);
		
		//opcion 2
		//return ResponseEntity.ok(new CountDTO(this.carService.count()));
	}
	
	@PostMapping("/cars/deletemany")
	public ResponseEntity<Car> deleteMany(@RequestBody CarListDTO carListDTO){
		log.info("REST request to delete a list of cars");
		
		this.carService.deleteAll(carListDTO.getCars());
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/cars/deletemany/{ids}")
	public ResponseEntity<Car> deleteMany(@PathVariable List<Long> ids){
		log.info("REST request to delete a list of cars");
		
		this.carService.deleteAllByIds(ids);
		
		return ResponseEntity.noContent().build();
	}
	
	/* ========== SPRING CUSTOM METHODS ============ */

	@GetMapping("/cars/doors/{doors}")
	public List<Car> findByDoors(@PathVariable Integer doors){
		log.info("REST request to find cars by num doors");
		return this.carService.findByDoors(doors);
	}
	
	@GetMapping("/cars/manufacturer/{manufacturer}/model/{model}")
	public List<Car> findByManufacturerAndModel(@PathVariable String manufacturer,
			@PathVariable String model){
		return this.carService.findByManufacturerAndModel(manufacturer, model);
	}
	
	@GetMapping("/cars/doors-gte/{doors}")
	public List<Car> findByDoorGreaterThanEqual(@PathVariable Integer doors){
		return this.carService.findByDoorsGreaterThanEqual(doors);
	}
		
}
