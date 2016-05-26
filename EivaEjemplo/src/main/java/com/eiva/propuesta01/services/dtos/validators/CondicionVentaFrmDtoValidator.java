package com.eiva.propuesta01.services.dtos.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.eiva.propuesta01.services.dtos.CondicionVentaFrmDto;

@Component
public class CondicionVentaFrmDtoValidator implements Validator
{
	@Override
	public boolean supports(Class<?> clazz) {
		return (CondicionVentaFrmDto.class).isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		
		ValidationUtils.rejectIfEmpty(errors, "nombre", "required", new Object[] {"NOMBRE DE LA CONDICION DE VENTA"});
	}
}
