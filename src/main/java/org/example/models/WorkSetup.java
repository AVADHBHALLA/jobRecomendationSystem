package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum WorkSetup {

    @JsonProperty("remote")
    REMOTE,

    @JsonProperty("hybrid")
    HYBRID,

    @JsonProperty("onsite")
    ONSITE
}
