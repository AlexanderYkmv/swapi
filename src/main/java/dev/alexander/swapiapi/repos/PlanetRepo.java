package dev.alexander.swapiapi.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import dev.alexander.swapiapi.entity.Planet;

public interface PlanetRepo extends JpaRepository <Planet,Integer> {}