package com.eiva.propuesta01.api.ctrls;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eiva.propuesta01.api.dtos.ApiResponseDto;
import com.eiva.propuesta01.api.exceptions.BadRequestException;
import com.eiva.propuesta01.api.exceptions.ResourceNotFoundException;
import com.eiva.propuesta01.services.OrdenService;
import com.eiva.propuesta01.services.dtos.OrdenDto;
import com.eiva.propuesta01.services.dtos.OrdenFrmDto;
import com.eiva.propuesta01.services.dtos.OrdenItemDto;
import com.eiva.propuesta01.services.dtos.validators.OrdenFrmDtoValidator;
import com.eiva.propuesta01.services.exceptions.EivaException;

@RestController @RequestMapping("/api/ordenes")
public class OrdenCtrl 
{
	@Autowired @Qualifier("ordenServiceSpImpl") 
	private OrdenService ordenService = null;
	
	@Autowired private OrdenFrmDtoValidator ordenFrmDtoValidator = null;

	@InitBinder("ordenFrmDto")
	public void initBinderOrdenFrmDto(WebDataBinder binder) {
		binder.setValidator(ordenFrmDtoValidator);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public ApiResponseDto listarTodas() {
		
		Iterable<OrdenDto> ordenDtos = ordenService.todos();
		return new ApiResponseDto(ordenDtos);
	}
	
	@RequestMapping(value = "/{ordenID}", method = RequestMethod.GET)
    public ApiResponseDto buscarUna(@PathVariable Long ordenID) {
		
		try 
		{
			OrdenDto ordenDto = ordenService.uno(ordenID);
			
			return new ApiResponseDto(ordenDto);
			
		} catch (EivaException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/{ordenID}/detalle", method = RequestMethod.GET)
    public ApiResponseDto buscarDetalle(@PathVariable Long ordenID) {
		
		try 
		{
			Iterable<OrdenItemDto> ordenItemDtos = ordenService.detalle(ordenID);
			
			return new ApiResponseDto(ordenItemDtos);
			
		} catch (EivaException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/{ordenID}/all", method = RequestMethod.GET)
    public ApiResponseDto buscarOrdenConDetalle(@PathVariable Long ordenID) {
		
		try 
		{
			OrdenDto ordenDto = ordenService.uno(ordenID);
			Iterable<OrdenItemDto> ordenItemDtos = ordenService.detalle(ordenID);
			
			ordenDto.setDetalle(ordenItemDtos);
			
			return new ApiResponseDto(ordenDto);
			
		} catch (EivaException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
    public ApiResponseDto cargarOrden(@RequestBody @Valid OrdenFrmDto ordenFrmDto, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			throw new BadRequestException("LOS DATOS SUMINISTRADOS NO SON VALIDOS");
		}
		
		try 
		{
			OrdenDto ordenDto = ordenService.cargarOrden(ordenFrmDto);
			
			return new ApiResponseDto(ordenDto);
			
		} catch (EivaException e) {
			throw new BadRequestException(e.getMessage());
		}
	}
}
