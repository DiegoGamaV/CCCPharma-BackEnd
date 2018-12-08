package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.jsonAdaptedModels.product;

public class PartialInformationProduct {
	private String name;
	private double price;
	private String status;
	
	public PartialInformationProduct(String name, double price, String status) {
		this.name = name;
		this.price = price;
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}	
	
}
