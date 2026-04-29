package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UserStatus {

    @JsonProperty("active")
    ACTIVE,

    @JsonProperty("inactive")
    INACTIVE
}
