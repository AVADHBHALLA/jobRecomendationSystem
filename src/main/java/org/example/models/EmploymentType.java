package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum EmploymentType {

    @JsonProperty("full_time")
    FULL_TIME,

    @JsonProperty("part_time")
    PART_TIME,

    @JsonProperty("intern")
    INTERN,

    @JsonProperty("contract")
    CONTRACT,

    @JsonProperty("freelance")
    FREELANCE
}
