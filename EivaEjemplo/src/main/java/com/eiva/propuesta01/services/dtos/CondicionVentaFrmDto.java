package com.eiva.propuesta01.services.dtos;

public class CondicionVentaFrmDto {
	
	private String nombre = null;
	
	public CondicionVentaFrmDto() {}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre == null || nombre.trim().isEmpty() ? null : nombre.trim().toUpperCase();
	}
}
