package org.example.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FilterDto {

    private String field;

    private FilterOperation op;

    private List<String> value;
}
