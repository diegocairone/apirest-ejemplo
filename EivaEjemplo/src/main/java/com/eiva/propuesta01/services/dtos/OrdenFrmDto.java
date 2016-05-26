package com.eiva.propuesta01.services.dtos;

public class OrdenFrmDto {
	
	private String nombre = null;
	private String direccion = null;
	private String correoElectronico = null;
	private Long condicionVentaID = null;
	private Iterable<OrdenItemFrmDto> detalle = null;
	
	public OrdenFrmDto() {}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre == null || nombre.trim().isEmpty() ? null : nombre.trim().toUpperCase();
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion == null || direccion.trim().isEmpty() ? null : direccion.trim().toUpperCase();
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico == null || correoElectronico.trim().isEmpty() ? null : correoElectronico.trim().toLowerCase();
	}

	public Long getCondicionVentaID() {
		return condicionVentaID;
	}

	public void setCondicionVentaID(Long condicionVentaID) {
		this.condicionVentaID = condicionVentaID;
	}

	public Iterable<OrdenItemFrmDto> getDetalle() {
		return detalle;
	}

	public void setDetalle(Iterable<OrdenItemFrmDto> detalle) {
		this.detalle = detalle;
	}
}
