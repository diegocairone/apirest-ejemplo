package com.eiva.propuesta01.services.dtos.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.eiva.propuesta01.services.dtos.OrdenFrmDto;
import com.eiva.propuesta01.services.dtos.OrdenItemFrmDto;
import com.eiva.propuesta01.services.utils.Regexps;

@Component
public class OrdenFrmDtoValidator implements Validator
{
	@Autowired private OrdenItemFrmDtoValidator ordenItemFrmDtoValidator = null;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return (OrdenFrmDto.class).isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		OrdenFrmDto frmDto = (OrdenFrmDto) target;
		
		ValidationUtils.rejectIfEmpty(errors, "nombre", "required", new Object[] {"NOMBRE DEL CLIENTE"});
		ValidationUtils.rejectIfEmpty(errors, "direccion", "required", new Object[] {"DIRECCION DEL CLIENTE"});
		ValidationUtils.rejectIfEmpty(errors, "condicionVentaID", "required", new Object[] {"CONDICION DE VENTA"});
		ValidationUtils.rejectIfEmpty(errors, "detalle", "required", new Object[] {"DETALLE"});
		
		if(frmDto.getCorreoElectronico() != null && !frmDto.getCorreoElectronico().matches(Regexps.REGEX_EMAIL)) {
			errors.rejectValue("correoElectronico", "invalid", new Object[] {"CORREO ELECTRONICO"}, null);
		}
		
		if(!errors.hasFieldErrors("condicionVentaID") && frmDto.getCondicionVentaID().compareTo(0L) < 1) {
			errors.rejectValue("condicionVentaID", "invalid", new Object[] {"CONDICION DE VENTA"}, null);
		}
		
		int i = 0;
		
		for(OrdenItemFrmDto itemFrmDto : frmDto.getDetalle()) {
			errors.pushNestedPath("detalle[" + i + "]");
			ValidationUtils.invokeValidator(ordenItemFrmDtoValidator, itemFrmDto, errors);
			errors.popNestedPath();
			i++;
		}
	}
}
