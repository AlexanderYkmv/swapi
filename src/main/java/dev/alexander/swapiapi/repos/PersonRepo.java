package dev.alexander.swapiapi.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dev.alexander.swapiapi.entity.Person;

@Repository
public interface PersonRepo extends JpaRepository <Person,Integer>  {

}
