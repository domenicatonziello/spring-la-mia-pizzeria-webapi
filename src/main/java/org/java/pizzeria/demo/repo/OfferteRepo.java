package org.java.pizzeria.demo.repo;

import org.java.pizzeria.demo.pojo.OffertaSpeciale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferteRepo extends JpaRepository<OffertaSpeciale, Integer> {

}
