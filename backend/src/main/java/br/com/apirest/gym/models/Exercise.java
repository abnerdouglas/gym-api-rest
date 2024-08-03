package br.com.apirest.gym.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

public class Exercise {

    @JsonProperty
    private String name;
    @JsonProperty
    private String type;
    @JsonProperty
    private String muscle;
    @JsonProperty
    private String equipment;
    @JsonProperty
    private String difficulty;
    @JsonProperty
    private String instructions;

}
