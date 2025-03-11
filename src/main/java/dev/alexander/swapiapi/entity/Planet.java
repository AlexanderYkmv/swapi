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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="planet")
public class Planet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
   
    @Column(name="name")
    private String name;
   
    @Column(name="rotation_period")
    private int rotationPeriod;

    @Column(name="diameter")
    private long diameter;

    @Column(name="climate")
    private String climate;

    @Column(name="gravity")
    private String gravity;

    @Column(name="terrain")
    private String terrain;
    
    @Column(name="surface_water")
    private int surfaceWater;

    @Column(name="population")
    private long population;
                                        
    @OneToMany(mappedBy = "homeworld")
    private Set<Person> people;

    @ManyToMany
    @JoinTable(
        name = "planet_film",
        joinColumns = @JoinColumn(name="planet_id"),
        inverseJoinColumns = @JoinColumn(name="film_id"))
    private Set<Film> films;
}
