package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.jsonAdaptedModels.Request;

public class UnauthenticatedRequest<T> {
	T data;
	
	public UnauthenticatedRequest(T _data) {
		this.data = _data;
	}
	
	T getData() {
		return data;
	}
}
