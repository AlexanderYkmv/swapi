package dev.alexander.swapiapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dev.alexander.swapiapi.dto.PersonDTO;
import dev.alexander.swapiapi.entity.Person;
import dev.alexander.swapiapi.errors.PersonNotFoundException;
import dev.alexander.swapiapi.mappers.PersonMapper;
import dev.alexander.swapiapi.repos.PersonRepo;

@Service
public class PersonServiceImpl implements PersonService {
    
    PersonRepo personRepo;
    PersonMapper personMapper;

    @Autowired
    public PersonServiceImpl(PersonRepo personRepo, PersonMapper personMapper) {
        this.personRepo = personRepo; 
        this.personMapper = personMapper;
    }

    

    @Override
    public List<PersonDTO> getAll() {
        return personRepo.findAll().stream().map(personMapper::convertPersontoDTO).collect(Collectors.toList());
    }

    @Override 
    public PersonDTO getById(int id) {
        Optional<Person> res = personRepo.findById(id);
        Person person = null;

        if(res.isPresent()) {
            person = res.get();
        } else throw new PersonNotFoundException("Person not found:" + id);
        
        return personMapper.convertPersontoDTO(person);
    }

    @Override
    public PersonDTO save(PersonDTO persondDto) {
        Person person = personMapper.convertDTOtoPerson(persondDto);
        Person savedPerson = personRepo.save(person);
        return personMapper.convertPersontoDTO(savedPerson);
    }

    @Override
    public void deleteById(int id) {
        personRepo.deleteById(id);
    }
}