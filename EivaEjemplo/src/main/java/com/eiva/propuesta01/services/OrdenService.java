package com.eiva.propuesta01.services;

import com.eiva.propuesta01.services.dtos.OrdenDto;
import com.eiva.propuesta01.services.dtos.OrdenFrmDto;
import com.eiva.propuesta01.services.dtos.OrdenItemDto;
import com.eiva.propuesta01.services.exceptions.EivaException;

public interface OrdenService {
	
	public Iterable<OrdenDto> todos();
	public OrdenDto uno(Long id) throws EivaException;
	public Iterable<OrdenItemDto> detalle(Long id) throws EivaException;
	public OrdenDto cargarOrden(OrdenFrmDto frmDto) throws EivaException;
	
}
