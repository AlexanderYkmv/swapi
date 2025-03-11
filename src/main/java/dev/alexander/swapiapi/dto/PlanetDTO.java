package dev.alexander.swapiapi.dto;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanetDTO {
    
    private String name;
    private int rotationPeriod;
    private long diameter;
    private String climate;
    private String gravity;
    private String terrain;
    private int surfaceWater;
    private long population;
    private Set<Integer> people_ids;
    private Set<Integer> film_ids;
}
