package br.com.apirest.gym.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

public class Exercise {

    private String name;

    private String muscleGroup;

    private String specificPart;

    private int sets;

    private int reps;

    private int estimatedTime;

    public Exercise(String name, String muscleGroup, String specificPart, int sets, int reps, int estimatedTime) {
        this.name = name;
        this.muscleGroup = muscleGroup;
        this.specificPart = specificPart;
        this.sets = sets;
        this.reps = reps;
        this.estimatedTime = estimatedTime;
    }

    public Exercise(){}


}
