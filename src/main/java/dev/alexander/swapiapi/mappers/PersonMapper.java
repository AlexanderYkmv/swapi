package dev.alexander.swapiapi.mappers;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import dev.alexander.swapiapi.dto.PersonDTO;
import dev.alexander.swapiapi.entity.Film;
import dev.alexander.swapiapi.entity.Person;
import dev.alexander.swapiapi.entity.Planet;
import dev.alexander.swapiapi.repos.FilmRepo;
import dev.alexander.swapiapi.repos.PlanetRepo;

@Component
public class PersonMapper {
    
    @Autowired
    ModelMapper modelMapper;
    
    @Autowired
    FilmRepo filmRepo;
    
    @Autowired
    PlanetRepo planetRepo;

    public PersonDTO convertPersontoDTO (Person person) {
        PersonDTO personDTO = modelMapper.map(person, PersonDTO.class);
        if (person.getHomeworld() != null) {
            personDTO.setPlanetId(person.getHomeworld().getId());
        }

        if (person.getFilms() != null) {
            Set<Integer> filmIds = person.getFilms().stream()
                .map(Film::getId)
                .collect(Collectors.toSet());
            personDTO.setFilmIds(filmIds);
        }
        return personDTO;
    }

    public Person convertDTOtoPerson(PersonDTO personDTO) {
        Person person = modelMapper.map(personDTO, Person.class);
         if (personDTO.getPlanetId() > 0) {
            Planet planet = planetRepo.findById(personDTO.getPlanetId()).orElse(null);
            person.setHomeworld(planet);
        }
        if (personDTO.getFilmIds() != null && !personDTO.getFilmIds().isEmpty()) {
            Set<Film> films = personDTO.getFilmIds().stream()
                .map(filmRepo::findById) 
                .filter(Optional::isPresent) 
                .map(Optional::get) 
                .collect(Collectors.toSet());
    
            person.setFilms(films);
        }
        return person;
    }
    
}
