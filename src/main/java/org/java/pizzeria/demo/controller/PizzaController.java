package org.java.pizzeria.demo.controller;

import java.util.List;
import java.util.Optional;

import org.java.pizzeria.demo.pojo.Ingredienti;
import org.java.pizzeria.demo.pojo.Pizza;
import org.java.pizzeria.demo.serv.IngredientiService;
import org.java.pizzeria.demo.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class PizzaController {
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private IngredientiService ingredientiservice;
	
	@GetMapping("/")
	public String getPizze(Model model) {
		List<Pizza> pizze = pizzaService.findAll();
		model.addAttribute("pizze", pizze);
		return "index";
	}
	@PostMapping("/")
	public String goToPizzaIndexResearch(Model model, @RequestParam(required = false) String name) {

		List<Pizza> pizze = pizzaService.findByNameContaining(name);

		model.addAttribute("pizze", pizze);
		model.addAttribute("searchTerm", name);

		return "index";
	}
	
	
	@GetMapping("/user/pizza/{id}")
	public String show(@PathVariable("id") int id, Model model ) {
		
		List<Ingredienti> ingredienti = ingredientiservice.findAll();
		
		
		
		Optional <Pizza> optPizza = pizzaService.findByIdWithOffertaSpeciale(id);
		
		Pizza pizza = optPizza.get();
		
		model.addAttribute("pizza",pizza);
		model.addAttribute("ingredienti", ingredienti);
		
		
		return "show";
	}
	
	@GetMapping("/admin/pizza/create")
	public String create(Model model) {
		
		List<Ingredienti> ingredienti = ingredientiservice.findAll();
		
		model.addAttribute("ingredienti", ingredienti);
		model.addAttribute("pizza", new Pizza());
		return "create";
	}
	@PostMapping("/admin/pizza/create")
	public String storeBook(Model model, @Valid @ModelAttribute Pizza pizza,
							BindingResult bindingResult) {
		
		
			if (bindingResult.hasErrors()) {
			
			for (ObjectError err : bindingResult.getAllErrors()) 
				System.err.println("error: " + err.getDefaultMessage());
			
			model.addAttribute("pizza", pizza);
			model.addAttribute("errors", bindingResult);
			
			return "create";
		}
		
		

		pizzaService.save(pizza);

		return "redirect:/";
	}
	
	@PostMapping("/admin/pizza/delete/{id}")
	public String deletePizza(@PathVariable("id") int id) {

		pizzaService.deleteById(id);

		return "redirect:/";
	}
	
	@GetMapping("/admin/pizza/edit/{id}")
	public String editPizza(Model model, @PathVariable("id") int id) {
		
		List<Ingredienti> ingredienti = ingredientiservice.findAll();
		
		
		Optional <Pizza> Optpizza = pizzaService.findById(id);
		Pizza pizza = Optpizza.get();
		
		model.addAttribute("ingredienti", ingredienti);
		model.addAttribute("pizza", pizza);
		return "edit-form";
	}
	@PostMapping("/admin/pizza/update/{id}")
	public String updatePizza(  Model model, @PathVariable int id, @ModelAttribute @Valid Pizza pizza,
								BindingResult bindingResult) {
		
			if (bindingResult.hasErrors()) {
			
			for (ObjectError err : bindingResult.getAllErrors()) 
				System.err.println("error: " + err.getDefaultMessage());
			
			model.addAttribute("pizza", pizza);
			model.addAttribute("errors", bindingResult);
			
			return "edit-form";
		}
		
		

		pizzaService.save(pizza);

		return "redirect:/user/pizza/" + id;
	}
}
