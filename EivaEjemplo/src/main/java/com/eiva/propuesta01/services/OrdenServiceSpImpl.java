package com.eiva.propuesta01.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class OrdenServiceSpImpl implements OrdenService {

	@Autowired private OrdenVentaRepository ordenVentaRepository = null;
	@Autowired private OrdenVentaItemRepository ordenVentaItemRepository = null;
	@Autowired private CondicionVentaRepository condicionVentaRepository = null;
	
	@Override
	public Iterable<OrdenDto> todos() {

		Iterable<OrdenVentaEntity> ordenesVenta = ordenVentaRepository.findAll();
		Iterable<OrdenDto> ordenDtos = OrdenDto.crearLista(ordenesVenta);
		
		return ordenDtos;
	}

	@Override
	public OrdenDto uno(Long id) throws EivaException {

		OrdenVentaEntity ordenVenta = ordenVentaRepository.findOne(id);
		
		if(ordenVenta == null) {
			throw new EivaException(String.format("NO SE ENCUENTRA EL RECURSO CON ID %s", id));
		}
		
		return new OrdenDto(ordenVenta);
	}

	@Override
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

	@Override
	public OrdenDto cargarOrden(OrdenFrmDto frmDto) throws EivaException {
		
		CondicionVentaEntity condicionVenta = condicionVentaRepository.findOne(frmDto.getCondicionVentaID());
		
		if(condicionVenta == null) {
			throw new EivaException(String.format("NO SE ENCUENTRA LA CONDICION DE VENTA CON ID %s", frmDto.getCondicionVentaID()));
		}
		
		Long ordenID = ordenVentaRepository.nueva(
				new Date(), 
				frmDto.getNombre(), 
				frmDto.getDireccion(), 
				frmDto.getCorreoElectronico(), 
				frmDto.getCondicionVentaID(), 
				BigDecimal.ZERO);
		
		OrdenVentaEntity ordenVenta = new OrdenVentaEntity();
		
		ordenVenta.setId(ordenID);
		ordenVenta.setFechaEmision(new Date());
		ordenVenta.setNombre(frmDto.getNombre());
		ordenVenta.setDireccion(frmDto.getDireccion());
		ordenVenta.setCorreoElectronico(frmDto.getCorreoElectronico());
		ordenVenta.setCondicionVenta(condicionVenta);
		ordenVenta.setTotal(BigDecimal.ZERO);
		
		Long item = 1L;
		BigDecimal total = BigDecimal.ZERO;
		ArrayList<OrdenVentaItemEntity> detalle = new ArrayList<OrdenVentaItemEntity>();
		
		for(OrdenItemFrmDto itemFrmDto : frmDto.getDetalle()) {
			
			BigDecimal totalitem = itemFrmDto.getPrecio().multiply(BigDecimal.valueOf(itemFrmDto.getCantidad()));
			
			item++;
			total = total.add(totalitem);
			
			ordenVentaItemRepository.agregarItem(
					ordenID, 
					item, 
					itemFrmDto.getDescripcion(), 
					itemFrmDto.getCantidad(), 
					itemFrmDto.getPrecio(), 
					totalitem);
			
			OrdenVentaItemEntity ordenVentaItemEntity = new OrdenVentaItemEntity(ordenVenta, item);
			
			ordenVentaItemEntity.setDescripcion(itemFrmDto.getDescripcion());
			ordenVentaItemEntity.setCantidad(itemFrmDto.getCantidad());
			ordenVentaItemEntity.setPrecio(itemFrmDto.getPrecio());
			ordenVentaItemEntity.setTotal(totalitem);
			
			detalle.add(ordenVentaItemEntity);
		}
		
		ordenVenta.setTotal(total);
		
		OrdenDto ordenDto = new OrdenDto(ordenVenta, detalle);
		
		return ordenDto;
	}

}
