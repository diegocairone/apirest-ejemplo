package com.eiva.propuesta01.services.data.repositories;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.eiva.propuesta01.services.data.model.OrdenVentaItemEntity;
import com.eiva.propuesta01.services.data.model.pk.OrdenVentaItemPkEntity;

public interface OrdenVentaItemRepository extends JpaRepository<OrdenVentaItemEntity, OrdenVentaItemPkEntity>, QueryDslPredicateExecutor<OrdenVentaItemEntity>
{
	@Procedure(name = "mysp_orden_item_insert")
	public void agregarItem(
			@Param("id_orden") Long id, 
			@Param("item") Long item,
			@Param("descripcion") String descripcion,
			@Param("cantidad") Integer direccion,
			@Param("precio") BigDecimal precio,
			@Param("total") BigDecimal total);
}
