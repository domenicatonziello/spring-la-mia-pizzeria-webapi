package org.java.pizzeria.demo.controller;

import java.util.Optional;

import org.java.pizzeria.demo.pojo.OffertaSpeciale;
import org.java.pizzeria.demo.pojo.Pizza;
import org.java.pizzeria.demo.serv.OffertaService;
import org.java.pizzeria.demo.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
public class OffertaController {

	@Autowired
	private OffertaService offertaService;
	
	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping("/admin/pizza/{id}/offerta/create")
	public String getOfferCreate(@PathVariable int id, Model model){

		model.addAttribute("offerta", new OffertaSpeciale());

		return "offerta-create";

	}

	@PostMapping("/admin/pizza/{id}/offerta/create")
	public String storeSpecialOffer(@PathVariable int id, Model model, @ModelAttribute @Valid OffertaSpeciale offerta, BindingResult bindingResult) {


		if(bindingResult.hasErrors()) {

			model.addAttribute("offerta", offerta);
			model.addAttribute("errors", bindingResult);

			return "offerta-create";
		}

//		Optional<Pizza> optPizza = pizzaService.findById(id);
//		Pizza pizza = optPizza.get();
//
//		offerta.setPizza(pizza);

		offertaService.save(offerta);

		return "redirect:/user/pizza/" + offerta.getPizza().getId();
	}

	@GetMapping("/admin/pizza/{pizzaId}/offerta/edit/{id}")
	public String editOffer(Model model, @PathVariable int pizzaId, @PathVariable int id) {

		Optional<OffertaSpeciale> optSpecialOffer = offertaService.findById(id);
		OffertaSpeciale specialOffer = optSpecialOffer.get();

		model.addAttribute("pizzaId", pizzaId);
		model.addAttribute("offerta", specialOffer);

		return "offerta-edit";

	}

	@PostMapping("/admin/pizza/{pizzaId}/offerta/update/{id}")
	public String updatePizza(Model model, @PathVariable int pizzaId, @PathVariable int id, @ModelAttribute @Valid OffertaSpeciale offerta, BindingResult bindingResult) {


		if(bindingResult.hasErrors()) {

			model.addAttribute("offerta", offerta);
			model.addAttribute("errors", bindingResult);

			return "offerta-edit";

		}

//		Optional<Pizza> optPizza = pizzaService.findById(pizzaId);
//		Pizza pizza = optPizza.get();
//
//		offerta.setPizza(pizza);

		offertaService.save(offerta);

		return "redirect:/user/pizza/" + pizzaId;

	}
}
