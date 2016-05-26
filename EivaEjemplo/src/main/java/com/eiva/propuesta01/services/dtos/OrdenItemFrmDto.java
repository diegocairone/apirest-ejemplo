package com.eiva.propuesta01.services.dtos;

import java.math.BigDecimal;

public class OrdenItemFrmDto {
	
	private String descripcion = null;
	private Integer cantidad = null;
	private BigDecimal precio = null;
	
	public OrdenItemFrmDto() {}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion == null || descripcion.trim().isEmpty() ? null : descripcion.trim().toUpperCase();
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
}
