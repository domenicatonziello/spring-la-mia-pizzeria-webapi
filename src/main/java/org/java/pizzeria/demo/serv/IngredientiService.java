package org.java.pizzeria.demo.serv;

import java.util.List;
import java.util.Optional;

import org.java.pizzeria.demo.pojo.Ingredienti;
import org.java.pizzeria.demo.repo.IngredientiRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientiService {
	
	@Autowired
	private IngredientiRepo ingredientirepo;
	
	public List<Ingredienti> findAll() {
		
		return ingredientirepo.findAll();
	}
	public Ingredienti save(Ingredienti ingredienti) {
		
		return ingredientirepo.save(ingredienti);
	}
	public Optional<Ingredienti> findById(int id) {
		
		return ingredientirepo.findById(id);
	}
	public void deleteById(int id) {

		ingredientirepo.deleteById(id);
	}
}
