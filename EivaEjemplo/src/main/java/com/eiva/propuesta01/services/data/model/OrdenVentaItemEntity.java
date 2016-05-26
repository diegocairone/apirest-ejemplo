package com.eiva.propuesta01.services.data.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToOne;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import com.eiva.propuesta01.services.data.model.pk.OrdenVentaItemPkEntity;

@Entity @Table(name = "app_ordenes_items")
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "mysp_orden_item_insert", procedureName = "mysp_orden_item_insert", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "id_orden", type = Long.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "item", type = Long.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "descripcion", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "cantidad", type = Integer.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "precio", type = BigDecimal.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "total", type = BigDecimal.class)
	})
})
public class OrdenVentaItemEntity implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId private OrdenVentaItemPkEntity pk = null;
	
	@OneToOne @JoinColumn(name = "id_orden", insertable = false, updatable = false)
	private OrdenVentaEntity ordenVenta = null;
	
	@Column(name = "item", insertable = false, updatable = false)
	private Long item = null;
	
	@Column(name = "descripcion", nullable = false, length = 300)
	private String descripcion = null;
	
	@Column(name = "cantidad", nullable = false)
	private Integer cantidad = null;
	
	@Column(name = "precio", nullable = false)
	private BigDecimal precio = null;
	
	@Column(name = "total", nullable = false)
	private BigDecimal total = null;
	
	public OrdenVentaItemEntity() {
		this.pk = new OrdenVentaItemPkEntity();
	}

	public OrdenVentaItemEntity(OrdenVentaEntity ordenVenta, Long item) {
		this.ordenVenta = ordenVenta;
		this.item = item;
		this.pk = new OrdenVentaItemPkEntity(ordenVenta.getId(), item);
	}

	public OrdenVentaEntity getOrdenVenta() {
		return ordenVenta;
	}

	public void setOrdenVenta(OrdenVentaEntity ordenVenta) {
		this.ordenVenta = ordenVenta;
		this.pk.setOrdenVentaId(ordenVenta.getId());
	}

	public Long getItem() {
		return item;
	}

	public void setItem(Long item) {
		this.item = item;
		this.pk.setItem(item);
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
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
		OrdenVentaItemEntity other = (OrdenVentaItemEntity) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}
}
