package com.dhernandez.partidos.services;

import com.dhernandez.partidos.entities.Equipo;
import com.dhernandez.partidos.repositories.EquipoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class EquipoServiceImpl implements EquipoService{
    Logger LOG  = LoggerFactory.getLogger(EquipoServiceImpl.class);
    @Autowired
    EquipoRepository equipoRepository;
    @Override
    @Transactional(readOnly = true)
    public List<Equipo> obtenerTodos() {
        LOG.info("Inicio metodo obtenerTodos()");
        List<Equipo> equipos  = (List<Equipo>) equipoRepository.findAll();
        LOG.info("Finaliza metodo obtenerTodos()");
        return equipos;

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Equipo> obtenerPorId(Long id) {
        LOG.info("Inicio metodo obtenerPorId(), {}",id);
        Equipo equipo = equipoRepository.findById(id).orElse(null);
        LOG.info("Finaliza metodo obtenerPorId()");
        return Optional.ofNullable(equipo);
    }

    @Override
    @Transactional
    public Equipo guardar(Equipo equipo) {
        LOG.info("Inicio metodo guardar(), {}",equipo);
        Equipo equipoDB =equipoRepository.save(equipo);
        LOG.info("Finaliza metodo guardar()");
        return equipoDB;
    }

    @Override
    @Transactional
    public Equipo eliminar(Long id) {
        LOG.info("Inicio metodo eliminar(), {}",id);
        Optional<Equipo> equipoDB = obtenerPorId(id);
        if(equipoDB.isPresent()){
            equipoRepository.delete(equipoDB.get());
        }
        LOG.info("Finaliza metodo eliminar()");
        return equipoDB.get();
    }
}
