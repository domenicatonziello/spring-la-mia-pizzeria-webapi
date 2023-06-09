package org.java.pizzeria.demo.api.controller;

import java.util.List;
import java.util.Optional;

import org.java.pizzeria.demo.pojo.Pizza;
import org.java.pizzeria.demo.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PizzaApiController {
	
	@Autowired
	private PizzaService pizzaService;

	
	@GetMapping("/pizze")
	public ResponseEntity<List<Pizza>> getAllPizza(@RequestParam(required = false) String name){
		
		if(name != null && !name.isEmpty()) {
			
			List<Pizza> pizzaList = pizzaService.findByNameContaining(name);
			return new ResponseEntity<>(pizzaList, HttpStatus.OK);
			
		}
		
		List<Pizza> pizzaList = pizzaService.findAll();
		
		return new ResponseEntity<>(pizzaList, HttpStatus.OK);
		
	}

	@GetMapping("pizze/filter")
	public ResponseEntity<List<Pizza>> filterByName(@RequestParam(required = false) String name) {

		List<Pizza> pizze = pizzaService.findByNameContaining(name);

		if (pizze.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(pizze, HttpStatus.OK);
		}
	}

	@GetMapping("/pizze/{id}")
	public ResponseEntity<Pizza> apiShow(@PathVariable("id") int id) {

		Optional<Pizza> pizzaOpt = pizzaService.findById(id);

		if (pizzaOpt.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			Pizza pizza = pizzaOpt.get();
			return new ResponseEntity<>(pizza, HttpStatus.OK);
		}
	}

	@PostMapping("/pizze/store")
	public ResponseEntity<Pizza> apiCreate(@RequestBody Pizza pizza) {

		Pizza newPizza = pizzaService.save(pizza);

		return new ResponseEntity<>(newPizza, HttpStatus.OK);
	}

	@PutMapping("/pizze/update")
	public ResponseEntity<Pizza> updatePizza(@RequestBody Pizza pizza) {

		Pizza updatedPizza = pizzaService.save(pizza);

		return new ResponseEntity<>(updatedPizza, HttpStatus.OK);	
	}

	@DeleteMapping("/pizze/delete/{id}")
	public ResponseEntity<Pizza> deletePizza(@PathVariable int id) {

		Optional<Pizza> optPizza = pizzaService.findById(id);

		if (optPizza.isEmpty()) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		}
		Pizza pizza = optPizza.get();
		pizzaService.deleteById(pizza.getId());

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
