package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.jsonAdaptedModels.product;

public class RemovalInformationProduct {
	private String barcode;
	private int removeAmount;

	public RemovalInformationProduct(String barcode, int removeAmount) {
		super();
		this.barcode = barcode;
		this.removeAmount = removeAmount;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public int getRemoveAmount() {
		return removeAmount;
	}

	public void setRemoveAmount(int removeAmount) {
		this.removeAmount = removeAmount;
	}

}
