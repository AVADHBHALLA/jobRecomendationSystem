package org.example.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PreferenceDetailsDto {

    private List<FilterDto> filters;
}