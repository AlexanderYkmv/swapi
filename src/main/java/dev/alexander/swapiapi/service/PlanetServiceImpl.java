package dev.alexander.swapiapi.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dev.alexander.swapiapi.dto.PlanetDTO;
import dev.alexander.swapiapi.entity.Planet;
import dev.alexander.swapiapi.errors.PlanetNotFoundException;
import dev.alexander.swapiapi.mappers.PlanetMapper;
import dev.alexander.swapiapi.repos.PlanetRepo;

@Service
public class PlanetServiceImpl implements PlanetService {
    
    @Autowired
    PlanetRepo planetRepo;

    @Autowired
    PlanetMapper planetMapper;

    public PlanetServiceImpl(PlanetRepo planetRepo, PlanetMapper planetMapper) {
        this.planetRepo = planetRepo;
        this.planetMapper = planetMapper;
    }

    @Override
    public List<PlanetDTO> getAll() {
       return planetRepo.findAll().stream().map(planetMapper::convertPlanetToDTO).collect(Collectors.toList());
    }

    @Override
    public PlanetDTO getById(int id) {
        Planet planet = planetRepo.findById(id)
            .orElseThrow( () -> new PlanetNotFoundException("Planet not found: " + id));
        return planetMapper.convertPlanetToDTO(planet);
    }

    @Override
    public PlanetDTO save(PlanetDTO planetDTO) {
        Planet planet = planetMapper.convertDTOToPlanet(planetDTO);
        planetRepo.save(planet);
        return planetMapper.convertPlanetToDTO(planet);
    }

    @Override
    public void deleteById(int id) {
        planetRepo.deleteById(id);
    }

}
