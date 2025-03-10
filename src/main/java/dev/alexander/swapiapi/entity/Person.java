package dev.alexander.swapiapi.entity;

import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="person")
public class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="person_name")
    private String name;

    @Column(name="height")
    private int height;

    @Column(name="mass")
    private int mass;

    @Column(name="hair_color")
    private String hairColor;

    @Column(name="skin_color")
    private String skinColor;

    @Column(name="eye_color")
    private String eyeColor;

    @Column(name="birth_year")
    private String birthYear;

    @Column(name="gender")
    private String gender;

    public String getName() {
        return name;
    }

    @ManyToMany
    @JoinTable(
        name = "person_film",
        joinColumns = @JoinColumn(name = "person_id"),
        inverseJoinColumns = @JoinColumn(name = "film_id"))
    private Set<Film> films;

    @ManyToOne
    @JoinColumn(name = "planet_id")
    private Planet homeworld;
}

