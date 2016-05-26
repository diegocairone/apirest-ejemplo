package com.eiva.propuesta01.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eiva.propuesta01.services.data.model.CondicionVentaEntity;
import com.eiva.propuesta01.services.data.model.OrdenVentaEntity;
import com.eiva.propuesta01.services.data.model.OrdenVentaItemEntity;
import com.eiva.propuesta01.services.data.model.QOrdenVentaItemEntity;
import com.eiva.propuesta01.services.data.repositories.CondicionVentaRepository;
import com.eiva.propuesta01.services.data.repositories.OrdenVentaItemRepository;
import com.eiva.propuesta01.services.data.repositories.OrdenVentaRepository;
import com.eiva.propuesta01.services.dtos.OrdenDto;
import com.eiva.propuesta01.services.dtos.OrdenFrmDto;
import com.eiva.propuesta01.services.dtos.OrdenItemDto;
import com.eiva.propuesta01.services.dtos.OrdenItemFrmDto;
import com.eiva.propuesta01.services.exceptions.EivaException;
import com.mysema.query.types.expr.BooleanExpression;

@Service
public class OrdenServiceImpl implements OrdenService {
	
	@Autowired private OrdenVentaRepository ordenVentaRepository = null;
	@Autowired private OrdenVentaItemRepository ordenVentaItemRepository = null;
	
	@Autowired private CondicionVentaRepository condicionVentaRepository = null;
	
	@Transactional(readOnly = true)
	public Iterable<OrdenDto> todos() {
		
		Iterable<OrdenVentaEntity> ordenesVenta = ordenVentaRepository.findAll();
		Iterable<OrdenDto> ordenDtos = OrdenDto.crearLista(ordenesVenta);
		
		return ordenDtos;
	}
	
	@Transactional(readOnly = true)
	public OrdenDto uno(Long id) throws EivaException {
		
		OrdenVentaEntity ordenVenta = ordenVentaRepository.findOne(id);
		
		if(ordenVenta == null) {
			throw new EivaException(String.format("NO SE ENCUENTRA EL RECURSO CON ID %s", id));
		}
		
		return new OrdenDto(ordenVenta);
	}
	
	@Transactional(readOnly = true)
	public Iterable<OrdenItemDto> detalle(Long id) throws EivaException {
		
		OrdenVentaEntity ordenVenta = ordenVentaRepository.findOne(id);
		
		if(ordenVenta == null) {
			throw new EivaException(String.format("NO SE ENCUENTRA EL RECURSO CON ID %s", id));
		}
		
		QOrdenVentaItemEntity q = QOrdenVentaItemEntity.ordenVentaItemEntity;
		BooleanExpression exp = q.ordenVenta.eq(ordenVenta);
		
		Iterable<OrdenVentaItemEntity> ordenVentaItems = ordenVentaItemRepository.findAll(exp);
		Iterable<OrdenItemDto> ordenItemDtos = OrdenItemDto.crearLista(ordenVentaItems);
		
		return ordenItemDtos;
	}
	
	@Transactional
	public OrdenDto cargarOrden(OrdenFrmDto frmDto) throws EivaException {
		
		CondicionVentaEntity condicionVenta = condicionVentaRepository.findOne(frmDto.getCondicionVentaID());
		
		if(condicionVenta == null) {
			throw new EivaException(String.format("NO SE ENCUENTRA LA CONDICION DE VENTA CON ID %s", frmDto.getCondicionVentaID()));
		}
		
		OrdenVentaEntity ordenVenta = new OrdenVentaEntity();
				
		ordenVenta.setFechaEmision(new Date());
		ordenVenta.setNombre(frmDto.getNombre());
		ordenVenta.setDireccion(frmDto.getDireccion());
		ordenVenta.setCorreoElectronico(frmDto.getCorreoElectronico());
		ordenVenta.setCondicionVenta(condicionVenta);
		ordenVenta.setTotal(BigDecimal.ZERO);
		
		ordenVentaRepository.save(ordenVenta);
		
		Long item = 1L;
		BigDecimal total = BigDecimal.ZERO;
		ArrayList<OrdenVentaItemEntity> detalle = new ArrayList<OrdenVentaItemEntity>();
		
		for(OrdenItemFrmDto itemFrmDto : frmDto.getDetalle()) {
			
			BigDecimal totalitem = itemFrmDto.getPrecio().multiply(BigDecimal.valueOf(itemFrmDto.getCantidad()));
			
			item++;
			total = total.add(totalitem);
			
			OrdenVentaItemEntity itemEntity = new OrdenVentaItemEntity(ordenVenta, item);
			
			itemEntity.setDescripcion(itemFrmDto.getDescripcion());
			itemEntity.setCantidad(itemFrmDto.getCantidad());
			itemEntity.setPrecio(itemFrmDto.getPrecio());
			itemEntity.setTotal(totalitem);
			
			ordenVentaItemRepository.save(itemEntity);
			
			detalle.add(itemEntity);
		}
		
		ordenVenta.setTotal(total);
		
		return new OrdenDto(ordenVenta, detalle);
	}
}
