package br.com.apirest.gym.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

public class Exercise {

    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("muscle")
    private String muscle;
    @JsonProperty("equipment")
    private String equipment;
    @JsonProperty("difficulty")
    private String difficulty;
    @JsonProperty("instructions")
    private String instructions;

}
