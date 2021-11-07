package com.rui.api.repository.voto;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rui.api.model.Voto;

public interface VotoRepository extends JpaRepository<Voto, Long>, VotoRepositoryQuery {

}
