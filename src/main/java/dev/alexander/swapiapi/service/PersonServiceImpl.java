package dev.alexander.swapiapi.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dev.alexander.swapiapi.dto.PersonDTO;
import dev.alexander.swapiapi.entity.Film;
import dev.alexander.swapiapi.entity.Person;
import dev.alexander.swapiapi.errors.PersonNotFoundException;
import dev.alexander.swapiapi.mappers.PersonMapper;
import dev.alexander.swapiapi.repos.FilmRepo;
import dev.alexander.swapiapi.repos.PersonRepo;
import dev.alexander.swapiapi.repos.PlanetRepo;

@Service
public class PersonServiceImpl implements PersonService {
    
    @Autowired
    PersonRepo personRepo;
    
    @Autowired
    PersonMapper personMapper;
    
    @Autowired
    PlanetRepo planetRepo;

    @Autowired
    FilmRepo filmRepo;
    
    public PersonServiceImpl(PersonRepo personRepo, PersonMapper personMapper, PlanetRepo planetRepo) {
        this.personRepo = personRepo; 
        this.personMapper = personMapper;
        this.planetRepo = planetRepo;
    }

    

    @Override
    public List<PersonDTO> getAll() {
        return personRepo.findAll().stream().map(personMapper::convertPersontoDTO).collect(Collectors.toList());
    }

    @Override 
    public PersonDTO getById(int id) {
        Person person = personRepo.findById(id)
            .orElseThrow(() -> new PersonNotFoundException("Person not found: " + id));
        
        return personMapper.convertPersontoDTO(person);
    }

    @Override
    public PersonDTO save(PersonDTO persondDto) {
        
        Person person = personMapper.convertDTOtoPerson(persondDto);
        personRepo.save(person);
        return personMapper.convertPersontoDTO(person);
    }

    @Override
    public void deleteById(int id) {
        personRepo.deleteById(id);
    }

    @Override
    public void setPersonFilms(int id, Set<Integer> filmIds){
            Person person = personRepo.findById(id)
            .orElseThrow(() -> new PersonNotFoundException("Person not found: " + id));
        
        Set<Film> allFilms = new HashSet<>(filmRepo.findAllById(filmIds));
        person.setFilms(allFilms);
        personRepo.save(person);
    }
}