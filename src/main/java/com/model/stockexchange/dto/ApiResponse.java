package com.model.stockexchange.dto;
import java.io.Serializable;

public class ApiResponse implements Serializable{

	private String apiError;

	public String getApiError() {
		return apiError;
	}
	public void setApiError(String apiError) {
		this.apiError = apiError;
	}

}
