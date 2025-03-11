package dev.alexander.swapiapi.errors;

public class PlanetNotFoundException extends RuntimeException {
    

    public PlanetNotFoundException() {
        super();
    }

    public PlanetNotFoundException(String message) {
        super(message);
    }
}
