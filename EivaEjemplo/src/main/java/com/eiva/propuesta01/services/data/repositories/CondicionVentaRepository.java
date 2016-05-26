package com.eiva.propuesta01.services.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.eiva.propuesta01.services.data.model.CondicionVentaEntity;

public interface CondicionVentaRepository extends JpaRepository<CondicionVentaEntity, Long>, QueryDslPredicateExecutor<CondicionVentaEntity>
{

}
