package com.eiva.propuesta01.services.dtos;

import com.eiva.propuesta01.services.data.model.CondicionVentaEntity;

public class CondicionVentaDto implements Comparable<CondicionVentaDto> {
	
	private Long id = null;
	private String nombre = null;
	
	public CondicionVentaDto() {}

	public CondicionVentaDto(Long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	public CondicionVentaDto(CondicionVentaEntity condicionVenta) {
		this(condicionVenta.getId(), condicionVenta.getNombre());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public int compareTo(CondicionVentaDto o) {
		return this.nombre.compareTo(o.getNombre());
	}
	
}
