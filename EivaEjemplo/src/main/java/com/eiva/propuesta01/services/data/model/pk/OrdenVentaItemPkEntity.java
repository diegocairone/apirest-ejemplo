package com.eiva.propuesta01.services.data.model.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.eiva.propuesta01.services.data.model.OrdenVentaEntity;

@Embeddable
public class OrdenVentaItemPkEntity implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "id_orden") private Long ordenVentaId = null;
	@Column(name = "item") private Long item = null;
	
	public OrdenVentaItemPkEntity() {}
	
	public OrdenVentaItemPkEntity(OrdenVentaEntity ordenVenta, Long item) {
		this(ordenVenta.getId(), item);
	}
	
	public OrdenVentaItemPkEntity(Long ordenVentaId, Long item) {
		this.ordenVentaId = ordenVentaId;
		this.item = item;
	}

	public Long getOrdenVentaId() {
		return ordenVentaId;
	}

	public void setOrdenVentaId(Long ordenVentaId) {
		this.ordenVentaId = ordenVentaId;
	}

	public Long getItem() {
		return item;
	}

	public void setItem(Long item) {
		this.item = item;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result
				+ ((ordenVentaId == null) ? 0 : ordenVentaId.hashCode());
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
		OrdenVentaItemPkEntity other = (OrdenVentaItemPkEntity) obj;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (ordenVentaId == null) {
			if (other.ordenVentaId != null)
				return false;
		} else if (!ordenVentaId.equals(other.ordenVentaId))
			return false;
		return true;
	}
}
