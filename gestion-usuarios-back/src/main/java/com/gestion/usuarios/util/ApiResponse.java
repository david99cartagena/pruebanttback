package com.gestion.usuarios.util;

public class ApiResponse<T> {

	private int status;
	private T data;
	private String error;

	//
	public ApiResponse(int status, T data, String error) {
		this.status = status;
		this.data = data;
		this.error = error;
	}

	//
	public ApiResponse(int status, String error) {
		this.status = status;
		this.data = null;
		this.error = error;
	}

	// Getters y setters
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
