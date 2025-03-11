package dev.alexander.swapiapi.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import dev.alexander.swapiapi.dto.PersonDTO;
import dev.alexander.swapiapi.entity.Person;

@Component
public class PersonMapper {
    
    @Autowired
    ModelMapper modelMapper;
    
    public PersonDTO convertPersontoDTO (Person person) {
        PersonDTO personDTO = modelMapper.map(person, PersonDTO.class);
        
        return personDTO;
    }

    public Person convertDTOtoPerson(PersonDTO personDTO) {
        Person person = modelMapper.map(personDTO, Person.class);
        
        return person;
    }
    
}
