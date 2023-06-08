package org.java.pizzeria.demo.pojo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Pizza {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Name can't be null")
	private String name;
	
	@NotBlank(message = "Description can't be null")
	private String description;
	
	@NotBlank(message = "Image can't be null")
	private String image;
	
	@Min(value = 0, message = "Price has to be from 0 to 1000 euro")
	@Max(value = 1000)
	private float price;
	
	@OneToMany(mappedBy = "pizza")
	private List<OffertaSpeciale> offerte;
	
	@ManyToMany
	private List<Ingredienti> ingredienti;
	
	public Pizza() {}
	
	public Pizza(String name, String description, String image, float price) {
		setName(name);
		setDescription(description);
		setImage(image);
		setPrice(price);
	}
	public Pizza(String name, String description, String image, float price, List<Ingredienti> ingredienti) {
		setName(name);
		setDescription(description);
		setImage(image);
		setPrice(price);
		setIngredienti(ingredienti);
	}
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public List<OffertaSpeciale> getOfferte() {
		return offerte;
	}
	public void setOfferte(List<OffertaSpeciale> offerte) {
		this.offerte = offerte;
	}
	
	public String getFormattedPrezzo() {
        return String.format("%,.2f€", price);
	}
	public List<Ingredienti> getIngredienti() {
		return ingredienti;
	}
	public void setIngredienti(List<Ingredienti> ingredienti) {
		this.ingredienti = ingredienti;
	}

	@Override
	public String toString() {
		return "[" + getId() + "]" + 
				getName() + ":" + getDescription() +
				"\nimmagine: " + getImage() + 
				"\nPrezzo: " + getPrice() + "E";
	}
	
	
	
}
