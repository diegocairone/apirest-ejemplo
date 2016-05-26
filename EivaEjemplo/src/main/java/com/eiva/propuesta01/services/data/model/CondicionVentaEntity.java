package com.eiva.propuesta01.services.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity @Table(name = "app_condiciones_venta")
public class CondicionVentaEntity implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id @Column(name = "id_condventa")
	@TableGenerator(name = "GEN_APP_CONDVENTA", table="SEQUENCE_TABLE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", pkColumnValue="APP_CONDVENTA_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GEN_APP_CONDVENTA") 
	private Long id = null;
	
	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre = null;
	
	public CondicionVentaEntity() {}

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
		CondicionVentaEntity other = (CondicionVentaEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
