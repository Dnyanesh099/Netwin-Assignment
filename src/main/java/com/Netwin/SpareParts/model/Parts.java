package com.Netwin.SpareParts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Parts")
public class Parts {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int partId;

	@ManyToOne
	@JoinColumn(name = "model_id")
	private Model model;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	private String partTitle;
	private double partPrice;

	public Parts() {
		// Default constructor
	}

	public Parts(Model model, Category category, String partTitle, double partPrice) {
		this.model = model;
		this.category = category;
		this.partTitle = partTitle;
		this.partPrice = partPrice;
	}

	public int getPartId() {
		return partId;
	}

	public void setPartId(int partId) {
		this.partId = partId;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getPartTitle() {
		return partTitle;
	}

	public void setPartTitle(String partTitle) {
		this.partTitle = partTitle;
	}

	public double getPartPrice() {
		return partPrice;
	}

	public void setPartPrice(double partPrice) {
		this.partPrice = partPrice;
	}
	

	@Override
	public String toString() {
		return "Parts [partId=" + partId + ", model=" + model + ", category=" + category + ", partTitle=" + partTitle
				+ ", partPrice=" + partPrice + "]";
	}

	
}
