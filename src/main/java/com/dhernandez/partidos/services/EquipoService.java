package com.dhernandez.partidos.services;

import com.dhernandez.partidos.entities.Equipo;

import java.util.List;
import java.util.Optional;

public interface EquipoService {

    public List<Equipo> obtenerTodos();

    Optional<Equipo> obtenerPorId(Long id);

    Equipo guardar(Equipo equipo);

    Equipo eliminar(Long id);
}
