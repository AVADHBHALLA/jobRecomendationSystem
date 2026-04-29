package org.example.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum EducationLevel {
    @JsonProperty("ug")
    UNDER_GRADUATE,

    @JsonProperty("pg")
    POST_GRADUATE
}
