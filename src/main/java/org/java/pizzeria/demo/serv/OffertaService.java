package org.java.pizzeria.demo.serv;

import java.util.List;
import java.util.Optional;

import org.java.pizzeria.demo.pojo.OffertaSpeciale;
import org.java.pizzeria.demo.repo.OfferteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OffertaService {
	
	@Autowired
	private OfferteRepo offerterepo;
	
	public List<OffertaSpeciale> findAll() {
		
		return offerterepo.findAll();
	}
	public OffertaSpeciale save(OffertaSpeciale offerta) {
		
		return offerterepo.save(offerta);
	}
	public Optional<OffertaSpeciale> findById(int id) {
		
		return offerterepo.findById(id);
	}

}
