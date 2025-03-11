package dev.alexander.swapiapi.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseError {
    
    private int statusCode;
    private String message;


    
}
