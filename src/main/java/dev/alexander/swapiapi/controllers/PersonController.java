package dev.alexander.swapiapi.controllers;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.alexander.swapiapi.dto.PersonDTO;
import dev.alexander.swapiapi.service.PersonService;





@RestController
@RequestMapping("/api")
public class PersonController {
    
    @Autowired
    PersonService personService;

    public PersonController(PersonService personService) {this.personService = personService;}



    @GetMapping("/people")
    public List<PersonDTO> getAllPeople() {
        return personService.getAll();
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable int id) {
        PersonDTO personDTO = personService.getById(id);
        return new ResponseEntity<>(personDTO, HttpStatus.FOUND);
    }
    

    @PostMapping("/people")
    public ResponseEntity<PersonDTO> postPerson(@RequestBody PersonDTO persondDto) {
        persondDto = personService.save(persondDto);
        return new ResponseEntity<>(persondDto, HttpStatus.CREATED);
    }
    
    @PutMapping("/planet/{id}/people")
    public ResponseEntity<PersonDTO> putPlanetId(@PathVariable int id, @RequestBody PersonDTO personDTO) {
        personDTO.setPlanetId(id);
        personDTO = personService.save(personDTO);
        
        return new ResponseEntity<>(personDTO,HttpStatus.OK);
    }

    @PutMapping("/film/{id}/people")
    public ResponseEntity<PersonDTO> putFilmIds(@PathVariable int id, @RequestBody Set<Integer> filmIds) {
        
        PersonDTO personDTO = personService.getById(id);
        personDTO.setFilmIds(filmIds);
        return new ResponseEntity<>(personDTO, HttpStatus.OK);
    }
}
