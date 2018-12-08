package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.jsonAdaptedModels.product;

public class PartialInformationAvailableProduct extends PartialInformationProduct {
	private double price;
	
	public PartialInformationAvailableProduct(String name, double price, String status) {
		this.name = name;
		this.status = status;
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
