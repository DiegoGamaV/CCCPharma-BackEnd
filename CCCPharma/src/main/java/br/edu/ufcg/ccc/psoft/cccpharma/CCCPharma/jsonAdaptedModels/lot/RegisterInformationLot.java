package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.jsonAdaptedModels.lot;

import java.util.Date;

public class RegisterInformationLot {

	private String productBarcode;
	private Date shelfLife;
	private int amount;

	public RegisterInformationLot(String productBarcode, Date shelfLife, int amount) {
		super();
		this.productBarcode = productBarcode;
		this.shelfLife = shelfLife;
		this.amount = amount;
	}

	public String getProductBarcode() {
		return productBarcode;
	}

	public void setProductBarcode(String productBarcode) {
		this.productBarcode = productBarcode;
	}

	public Date getShelfLife() {
		return shelfLife;
	}

	public void setShelfLife(Date shelfLife) {
		this.shelfLife = shelfLife;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
