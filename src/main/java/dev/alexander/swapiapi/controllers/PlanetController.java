package dev.alexander.swapiapi.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.alexander.swapiapi.dto.PlanetDTO;
import dev.alexander.swapiapi.service.PlanetService;




@RestController
@RequestMapping("/api")
public class PlanetController {
    
    @Autowired
    PlanetService planetService;

    public PlanetController(PlanetService planetService) {this.planetService = planetService;}

    @GetMapping("/planets")
    public ResponseEntity<List<PlanetDTO>> getAllPlanets() {
        List<PlanetDTO> planets = planetService.getAll();
        return ResponseEntity.ok(planets);
    }
    
    @GetMapping("/planets/{id}")
    public ResponseEntity<PlanetDTO> getPlanetById(@PathVariable int id) {
        PlanetDTO planetDTO = planetService.getById(id);
        return new ResponseEntity<>(planetDTO,HttpStatus.FOUND);
    }

    @PostMapping("/planets")
    public ResponseEntity<PlanetDTO> postPlanet(@RequestBody PlanetDTO planetDTO) {
        planetDTO = planetService.save(planetDTO);
        return new ResponseEntity<>(planetDTO,HttpStatus.CREATED);
    }
   
    
    
}
