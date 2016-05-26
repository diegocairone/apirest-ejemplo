package com.eiva.propuesta01.api.ctrls;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.eiva.propuesta01.services.CondicionVentaService;
import com.eiva.propuesta01.services.dtos.CondicionVentaDto;
import com.eiva.propuesta01.services.dtos.CondicionVentaFrmDto;
import com.eiva.propuesta01.services.dtos.validators.CondicionVentaFrmDtoValidator;
import com.eiva.propuesta01.services.exceptions.EivaException;

@RestController @RequestMapping("/api/condventa")
public class CondicionVentaCtrl 
{
	@Autowired private CondicionVentaService condicionVentaService = null;
	
	@Autowired private CondicionVentaFrmDtoValidator condicionVentaFrmDtoValidator = null;
	
	@InitBinder("condicionVentaFrmDto")
	public void initBinderCondicionVentaFrmDto(WebDataBinder binder) {
		binder.setValidator(condicionVentaFrmDtoValidator);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public ApiResponseDto listarTodas() {
		
		Iterable<CondicionVentaDto> condicionVentaDtos = condicionVentaService.todos();
        return new ApiResponseDto(condicionVentaDtos);
    }
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMINISTRADOR')")
    public ApiResponseDto nueva(@RequestBody @Valid CondicionVentaFrmDto condicionVentaFrmDto, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			throw new BadRequestException("LOS DATOS SUMINISTRADOS NO SON VALIDOS");
		}
		
		try 
		{
			CondicionVentaDto condicionVentaDto = condicionVentaService.nuevo(condicionVentaFrmDto);
			
			return new ApiResponseDto(condicionVentaDto);
			
		} catch (EivaException e) {
			throw new BadRequestException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/{condventaID}", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('ADMINISTRADOR')")
    public ApiResponseDto actualizar(@PathVariable Long condventaID, @RequestBody @Valid CondicionVentaFrmDto condicionVentaFrmDto, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			throw new BadRequestException("LOS DATOS SUMINISTRADOS NO SON VALIDOS");
		}
		
		try 
		{
			CondicionVentaDto condicionVentaDto = condicionVentaService.actualizar(condventaID, condicionVentaFrmDto);
			
			return new ApiResponseDto(condicionVentaDto);
			
		} catch (EivaException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/{condventaID}", method = RequestMethod.GET)
    public ApiResponseDto buscarUna(@PathVariable Long condventaID) {
		
		try 
		{
			CondicionVentaDto condicionVentaDto = condicionVentaService.uno(condventaID);
			
			return new ApiResponseDto(condicionVentaDto);
			
		} catch (EivaException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
    }
	
	@RequestMapping(value = "/{condventaID}", method = RequestMethod.DELETE)
    public void borrar(@PathVariable Long condventaID) {
		
		try 
		{
			condicionVentaService.borrar(condventaID);
			
		} catch (EivaException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
	}
}
