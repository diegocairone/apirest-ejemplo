package com.eiva.propuesta01.services.dtos.validators;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.eiva.propuesta01.services.dtos.OrdenItemFrmDto;

@Component
public class OrdenItemFrmDtoValidator implements Validator
{
	@Override
	public boolean supports(Class<?> clazz) {
		return (OrdenItemFrmDto.class).isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		OrdenItemFrmDto itemFrmDto = (OrdenItemFrmDto) target;
		
		ValidationUtils.rejectIfEmpty(errors, "descripcion", "required", new Object[] {"DESCRIPCION DEL ITEM"});
		ValidationUtils.rejectIfEmpty(errors, "cantidad", "required", new Object[] {"CANTIDAD DEL ITEM"});
		ValidationUtils.rejectIfEmpty(errors, "precio", "required", new Object[] {"PRECIO DEL ITEM"});
		
		if(!errors.hasFieldErrors("cantidad") && itemFrmDto.getCantidad().compareTo(0) < 1) {
			errors.rejectValue("cantidad", "invalid", new Object[] {"CANTIDAD DEL ITEM"}, null);
		}
		
		if(!errors.hasFieldErrors("precio") && itemFrmDto.getPrecio().compareTo(BigDecimal.ZERO) != 1) {
			errors.rejectValue("precio", "invalid", new Object[] {"PRECIO DEL ITEM"}, null);
		}
	}
}
