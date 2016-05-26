package com.eiva.propuesta01.services.data.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToOne;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity @Table(name = "app_ordenes")
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "mysp_orden_insert", procedureName = "mysp_orden_insert", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "fecha_emision", type = Date.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "nombre", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "direccion", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "correo_electronico", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "id_condventa", type = Long.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "total_orden", type = BigDecimal.class),
			@StoredProcedureParameter(mode = ParameterMode.OUT, name = "id_orden", type = Long.class)
	})
})
public class OrdenVentaEntity implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id @Column(name = "id_orden")
	@TableGenerator(name = "GEN_APP_ORDEN", table="SEQUENCE_TABLE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", pkColumnValue="APP_ORDEN_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GEN_APP_ORDEN") 
	private Long id = null;
	
	@Column(name = "fecha_emision", nullable = false) @Temporal(TemporalType.DATE)
	private Date fechaEmision = null;
	
	@Column(name = "nombre", nullable = false, length = 200)
	private String nombre = null;
	
	@Column(name = "direccion", nullable = false, length = 200)
	private String direccion = null;
	
	@Column(name = "correo_electronico", nullable = true, length = 200)
	private String correoElectronico = null;
	
	@OneToOne @JoinColumn(name = "id_condventa", nullable = false)
	private CondicionVentaEntity condicionVenta = null;
	
	@Column(name = "total_orden", nullable = false)
	private BigDecimal total = null;
	
	public OrdenVentaEntity() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public CondicionVentaEntity getCondicionVenta() {
		return condicionVenta;
	}

	public void setCondicionVenta(CondicionVentaEntity condicionVenta) {
		this.condicionVenta = condicionVenta;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
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
		OrdenVentaEntity other = (OrdenVentaEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
