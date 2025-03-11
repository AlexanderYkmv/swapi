package dev.alexander.swapiapi.service;

import java.util.List;
import java.util.Set;
import dev.alexander.swapiapi.dto.PersonDTO;

public interface PersonService {
    
    public List<PersonDTO> getAll();
    
    public PersonDTO getById(int id);

    public PersonDTO save(PersonDTO persondDto);

    public void deleteById(int id);

    public void setPersonFilms(int id, Set<Integer> filmIds);
}
