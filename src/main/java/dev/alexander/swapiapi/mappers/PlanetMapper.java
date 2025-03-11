package dev.alexander.swapiapi.mappers;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import dev.alexander.swapiapi.dto.PlanetDTO;
import dev.alexander.swapiapi.entity.Film;
import dev.alexander.swapiapi.entity.Person;
import dev.alexander.swapiapi.entity.Planet;
import dev.alexander.swapiapi.repos.FilmRepo;
import dev.alexander.swapiapi.repos.PersonRepo;

@Component
public class PlanetMapper {
    
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PersonRepo personRepo;

    @Autowired
    FilmRepo filmRepo;



    public PlanetDTO convertPlanetToDTO(Planet planet) {
        PlanetDTO planetDTO = modelMapper.map(planet, PlanetDTO.class);
        if (planet.getPeople() != null) {
            Set<Integer> peopleIds = planet.getPeople().stream()
                .map(Person::getId)
                .collect(Collectors.toSet());
            planetDTO.setPeople_ids(peopleIds);
        }
         if (planet.getFilms() != null) {
            Set<Integer> filmIds = planet.getFilms().stream()
                .map(Film::getId)
                .collect(Collectors.toSet());
            planetDTO.setFilm_ids(filmIds);
        }
        return planetDTO;
    }

    public Planet convertDTOToPlanet(PlanetDTO planetdDto) {
        Planet planet = modelMapper.map(planetdDto, Planet.class);
        if (planetdDto.getPeople_ids() != null && !planetdDto.getPeople_ids().isEmpty()) {
            Set<Person> people = planetdDto.getPeople_ids().stream()
                .map(personRepo::findById) 
                .filter(Optional::isPresent) 
                .map(Optional::get) 
                .collect(Collectors.toSet());
            planet.setPeople(people);
        }
        return planet;
    }
}   
