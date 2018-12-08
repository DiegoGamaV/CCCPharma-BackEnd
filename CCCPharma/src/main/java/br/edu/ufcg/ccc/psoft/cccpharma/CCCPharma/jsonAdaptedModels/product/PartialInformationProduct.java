package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.jsonAdaptedModels.product;

public abstract class PartialInformationProduct {
	protected String name;
	protected String status;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}	
	
}
