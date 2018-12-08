package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.jsonAdaptedModels.category;

public class UpdateInformationCategory {

	private String categoryClass;
	private double newDiscount;

	public UpdateInformationCategory(String categoryClass, double newDiscount) {
		super();
		this.categoryClass = categoryClass;
		this.newDiscount = newDiscount;
	}

	public String getCategoryClass() {
		return categoryClass;
	}

	public void setCategoryClass(String categoryClass) {
		this.categoryClass = categoryClass;
	}

	public double getNewDiscount() {
		return newDiscount;
	}

	public void setNewDiscount(double newDiscount) {
		this.newDiscount = newDiscount;
	}

}
