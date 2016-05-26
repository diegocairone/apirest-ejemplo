package com.eiva.propuesta01.services;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eiva.propuesta01.services.data.model.CondicionVentaEntity;
import com.eiva.propuesta01.services.data.repositories.CondicionVentaRepository;
import com.eiva.propuesta01.services.dtos.CondicionVentaDto;
import com.eiva.propuesta01.services.dtos.CondicionVentaFrmDto;
import com.eiva.propuesta01.services.exceptions.EivaException;

@Service
public class CondicionVentaService {
	
	@Autowired private CondicionVentaRepository condicionVentaRepository = null;
	
	@Transactional(readOnly = true)
	public Iterable<CondicionVentaDto> todos() {
		
		ArrayList<CondicionVentaDto> condicionVentaDtos = new ArrayList<CondicionVentaDto>();
		Iterable<CondicionVentaEntity> condicionesVenta = condicionVentaRepository.findAll();
		
		for(CondicionVentaEntity condicionVenta : condicionesVenta) {
			condicionVentaDtos.add(new CondicionVentaDto(condicionVenta));
		}
		
		Collections.sort(condicionVentaDtos);
		
		return condicionVentaDtos;
	}
	
	@Transactional(readOnly = true)
	public CondicionVentaDto uno(Long id) throws EivaException {
		
		CondicionVentaEntity condicionVentaEntity = condicionVentaRepository.findOne(id);
		
		if(condicionVentaEntity == null) {
			throw new EivaException(String.format("NO SE ENCUENTRA EL RECURSO CON ID %s", id));
		}
		
		return new CondicionVentaDto(condicionVentaEntity);
	}
	
	@Transactional
	public CondicionVentaDto nuevo(CondicionVentaFrmDto frmDto) throws EivaException {
		
		CondicionVentaEntity condicionVentaEntity = new CondicionVentaEntity();
		
		condicionVentaEntity.setNombre(frmDto.getNombre());
		
		condicionVentaRepository.save(condicionVentaEntity);
		
		return new CondicionVentaDto(condicionVentaEntity);
	}
	
	@Transactional
	public CondicionVentaDto actualizar(Long condventaID, CondicionVentaFrmDto frmDto) throws EivaException {
		
		CondicionVentaEntity condicionVentaEntity = condicionVentaRepository.findOne(condventaID);
		
		if(condicionVentaEntity == null) {
			throw new EivaException(String.format("NO SE ENCUENTRA EL RECURSO CON ID %s", condventaID));
		}
		
		condicionVentaEntity.setNombre(frmDto.getNombre());
		
		condicionVentaRepository.save(condicionVentaEntity);
		
		return new CondicionVentaDto(condicionVentaEntity);
	}
	
	@Transactional
	public void borrar(Long id) throws EivaException {
		
		CondicionVentaEntity condicionVentaEntity = condicionVentaRepository.findOne(id);
		
		if(condicionVentaEntity == null) {
			throw new EivaException(String.format("NO SE ENCUENTRA EL RECURSO CON ID %s", id));
		}
		
		condicionVentaRepository.delete(condicionVentaEntity);
	}
}
