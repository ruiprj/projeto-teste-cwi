package com.rui.api.repository.associado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rui.api.model.Associado;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long>, AssociadoRepositoryQuery {

}
