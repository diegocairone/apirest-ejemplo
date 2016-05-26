package com.eiva.propuesta01.api.dtos;

import com.eiva.propuesta01.api.dtos.interfaces.ClaveValor;

public class ClaveValorDto implements Comparable<ClaveValorDto>
{	
	private Long id = null;
	private String nombre = null;
	
	public ClaveValorDto() {}

	public ClaveValorDto(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	
	public ClaveValorDto(ClaveValor claveValor) {
		super();
		this.id = claveValor.getId();
		this.nombre = claveValor.getNombre();
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClaveValorDto other = (ClaveValorDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}

	@Override
	public int compareTo(ClaveValorDto o) {
		return this.nombre.compareTo(o.getNombre());
	}
}
