package com.dhernandez.partidos.repositories;

import com.dhernandez.partidos.entities.Equipo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepository extends CrudRepository<Equipo,Long> {
}
