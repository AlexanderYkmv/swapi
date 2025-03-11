package dev.alexander.swapiapi.service;

import java.util.List;
import dev.alexander.swapiapi.dto.PlanetDTO;

public interface PlanetService {

    public List<PlanetDTO> getAll();
    
    public PlanetDTO getById(int id);

    public PlanetDTO save(PlanetDTO planetDTO);

    public void deleteById(int id);
}
