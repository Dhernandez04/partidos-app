package com.dhernandez.partidos.controllers;

import com.dhernandez.partidos.entities.Equipo;
import com.dhernandez.partidos.exceptions.ModelNotFoundException;
import com.dhernandez.partidos.services.EquipoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {
    Logger LOG = LoggerFactory.getLogger(EquipoController.class);
    @Value("${mensaje.noencontrado}")
    String NOTFOUND;
    @Autowired
    EquipoService equipoService;

    @GetMapping("/obtener-todos")
    public ResponseEntity<List<Equipo>> obtenerTodos(){
        return ResponseEntity.ok(equipoService.obtenerTodos());
    }
    @GetMapping("/obtenerPorId/{id}")
    public ResponseEntity<Equipo> listar(@PathVariable("id") Long id){
        return ResponseEntity.ok(equipoService.obtenerPorId(id).get());
    }

    @PostMapping("/guardar")
    public ResponseEntity<Equipo> guardar(@RequestBody Equipo equipo){
        return ResponseEntity.ok(equipoService.guardar(equipo));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Equipo> actualizar(@RequestBody Equipo equipo){
        Optional<Equipo> equipoDB = equipoService.obtenerPorId(equipo.getId());
        if(!equipoDB.isPresent()){
            LOG.error(NOTFOUND);
            throw new ModelNotFoundException(NOTFOUND);
        }
        return ResponseEntity.ok(equipoService.guardar(equipo));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Equipo> eliminar(@PathVariable("id") Long id){
        Optional<Equipo> equipoDB = equipoService.obtenerPorId(id);
        if(!equipoDB.isPresent()){
            LOG.error(NOTFOUND);
            throw new ModelNotFoundException(NOTFOUND);
        }
        return ResponseEntity.ok(equipoService.eliminar(id));
    }
}
