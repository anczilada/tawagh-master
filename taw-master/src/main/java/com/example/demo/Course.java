package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@JsonSerialize
@Data
public class Course {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("nazwa")
    private String name;

    @JsonProperty("prowadzący")
    private String professor;

    @JsonProperty("ocena")
    private Integer grade;

    @JsonProperty("test")
    private Boolean isTest;

    public Course(Integer id, String name, String professor, Integer grade, Boolean isTest) {
        this.id = id;
        this.name = name;
        this.professor = professor;
        this.grade = grade;
        this.isTest = isTest;
    }
}
