package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum FilterOperation {

    @JsonProperty("in")
    IN,

    @JsonProperty("notIn")
    NOT_IN,

    @JsonProperty("eq")
    EQUAL,

    @JsonProperty("neq")
    NOT_EQUAL,

    @JsonProperty("gt")
    GREATER_THAN,

    @JsonProperty("lt")
    LESS_THAN,

    @JsonProperty("between")
    BETWEEN,

    @JsonProperty("like")
    LIKE
}
