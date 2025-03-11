package dev.alexander.swapiapi.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import dev.alexander.swapiapi.entity.Film;

public interface FilmRepo extends JpaRepository <Film,Integer> {}