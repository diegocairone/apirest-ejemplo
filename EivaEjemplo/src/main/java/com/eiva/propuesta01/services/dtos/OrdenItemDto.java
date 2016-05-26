package com.eiva.propuesta01.services.dtos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

import com.eiva.propuesta01.services.data.model.OrdenVentaItemEntity;

public class OrdenItemDto implements Comparable<OrdenItemDto> {
	
	private Long item = null;
	private String descripcion = null;
	private Integer cantidad = null;
	private BigDecimal precio = null;
	private BigDecimal total = null;
	
	public OrdenItemDto(OrdenVentaItemEntity ordenVentaItem) {
		this.item = ordenVentaItem.getItem();
		this.descripcion = ordenVentaItem.getDescripcion();
		this.cantidad = ordenVentaItem.getCantidad();
		this.precio = ordenVentaItem.getPrecio();
		this.total = ordenVentaItem.getTotal();
	}

	public Long getItem() {
		return item;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public BigDecimal getTotal() {
		return total;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
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
		OrdenItemDto other = (OrdenItemDto) obj;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return this.descripcion;
	}

	@Override
	public int compareTo(OrdenItemDto o) {
		return this.descripcion.compareTo(o.getDescripcion());
	}
	
	public static final Iterable<OrdenItemDto> crearLista(Iterable<OrdenVentaItemEntity> ordenVentaItems) {
		
		ArrayList<OrdenItemDto> lista = new ArrayList<OrdenItemDto>();
		
		for(OrdenVentaItemEntity ordenVentaItem : ordenVentaItems) {
			lista.add(new OrdenItemDto(ordenVentaItem));
		}
		
		Collections.sort(lista);
		
		return lista;
	}
}
