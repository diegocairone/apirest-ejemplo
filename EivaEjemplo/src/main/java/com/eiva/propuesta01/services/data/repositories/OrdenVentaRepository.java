package com.eiva.propuesta01.services.data.repositories;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.eiva.propuesta01.services.data.model.OrdenVentaEntity;

public interface OrdenVentaRepository extends JpaRepository<OrdenVentaEntity, Long>, QueryDslPredicateExecutor<OrdenVentaEntity>
{
	@Procedure(name = "mysp_orden_insert")
	public Long nueva(
			@Param("fecha_emision") Date fechaEmision,
			@Param("nombre") String nombre,
			@Param("direccion") String direccion,
			@Param("correo_electronico") String correoElectronico,
			@Param("id_condventa") Long condicionVentaID,
			@Param("total_orden") BigDecimal total);
}
