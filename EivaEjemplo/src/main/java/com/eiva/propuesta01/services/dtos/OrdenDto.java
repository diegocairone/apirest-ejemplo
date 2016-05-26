package com.eiva.propuesta01.services.dtos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import com.eiva.propuesta01.services.data.model.OrdenVentaEntity;
import com.eiva.propuesta01.services.data.model.OrdenVentaItemEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrdenDto implements Comparable<OrdenDto> {

	private Date fecha = null;
	private String nombre = null;
	private String direccion = null;
	private String correoElectronico = null;
	private CondicionVentaDto condicionVenta = null;
	private BigDecimal total = null;
	private Iterable<OrdenItemDto> detalle = null;
	
	public OrdenDto(OrdenVentaEntity ordenVenta, Iterable<OrdenVentaItemEntity> ordenVentaItems) {
		this(ordenVenta);
		this.detalle = OrdenItemDto.crearLista(ordenVentaItems);
	}
	
	public OrdenDto(OrdenVentaEntity ordenVenta) {
		this.fecha = ordenVenta.getFechaEmision();
		this.nombre = ordenVenta.getNombre();
		this.direccion = ordenVenta.getDireccion();
		this.correoElectronico = ordenVenta.getCorreoElectronico();
		this.condicionVenta = new CondicionVentaDto(ordenVenta.getCondicionVenta());
		this.total = ordenVenta.getTotal();
	}

	public Date getFecha() {
		return fecha;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public CondicionVentaDto getCondicionVenta() {
		return condicionVenta;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public Iterable<OrdenItemDto> getDetalle() {
		return detalle;
	}
	
	public void setDetalle(Iterable<OrdenItemDto> detalle) {
		this.detalle = detalle;
	}

	@Override
	public String toString() {
		return this.nombre;
	}

	@Override
	public int compareTo(OrdenDto o) {
		int rv = this.fecha.compareTo(o.getFecha());
		
		if(rv == 0) {
			return this.nombre.compareTo(o.getNombre());
		}
		
		return rv;
	}
	
	public static final Iterable<OrdenDto> crearLista(Iterable<OrdenVentaEntity> ordenesVenta) {
		
		ArrayList<OrdenDto> ordenDtos = new ArrayList<OrdenDto>();
		
		for(OrdenVentaEntity ordenVenta : ordenesVenta) {
			ordenDtos.add(new OrdenDto(ordenVenta));
		}
		
		Collections.sort(ordenDtos);
		
		return ordenDtos;
	}
}
