package com.publica.desafio_pub.repositories;

import com.publica.desafio_pub.models.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    @Query(value = "select * from tb_despesas where data_pagamento between ?1 and ?2", nativeQuery = true)
    List<Despesa> filtroPorData(LocalDate localDate1, LocalDate localDate2);

    @Query(value = "select * from tb_despesas where tipo_despesa = ?1", nativeQuery = true)
    List<Despesa> filtroPorTipo(Integer tipoDespesaId);

    @Query(value = "select * from tb_despesas order by conta_id", nativeQuery = true)
    List<Despesa> findAllOrderByContaId();

}
