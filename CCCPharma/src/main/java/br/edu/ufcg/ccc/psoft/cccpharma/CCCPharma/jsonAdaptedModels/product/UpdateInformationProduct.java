package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.jsonAdaptedModels.product;

public class UpdateInformationProduct {
	private String barcode;
	private double newPrice;

	public UpdateInformationProduct(String barcode, double newPrice) {
		super();
		this.barcode = barcode;
		this.newPrice = newPrice;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public double getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(double newPrice) {
		this.newPrice = newPrice;
	}

}
