package org.java.pizzeria.demo.controller;

import java.util.List;

import org.java.pizzeria.demo.pojo.Ingredienti;
import org.java.pizzeria.demo.serv.IngredientiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
public class IngredientiController {
	
	@Autowired
	private IngredientiService ingredientiservice;
	
	@GetMapping("/user/ingredients")
	public String getPizze(Model model) {
		List<Ingredienti> ingredienti = ingredientiservice.findAll();
		model.addAttribute("ingredienti", ingredienti);
		return "ingredienti-index";
	}
	
	@GetMapping("/admin/ingredients/create")
	public String create(Model model) {
		
		model.addAttribute("ingrediente", new Ingredienti());
		return "ingredienti-create";
	}
	@PostMapping("/admin/ingredients/create")
	public String storeBook(Model model, @Valid @ModelAttribute Ingredienti ingrediente,
							BindingResult bindingResult) {
		
		
			if (bindingResult.hasErrors()) {
			
			for (ObjectError err : bindingResult.getAllErrors()) 
				System.err.println("error: " + err.getDefaultMessage());
			
			model.addAttribute("ingrediente", ingrediente);
			model.addAttribute("errors", bindingResult);
			
			return "ingredienti-create";
		}
		
		

		ingredientiservice.save(ingrediente);

		return "redirect:/user/ingredients";
	}
	@PostMapping("admin/ingredients/delete/{id}")
	public String deletePizza(@PathVariable("id") int id) {

		ingredientiservice.deleteById(id);

		return "redirect:/user/ingredients";
	}

}
