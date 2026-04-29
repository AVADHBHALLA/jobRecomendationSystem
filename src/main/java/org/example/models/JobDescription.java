package org.example.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JobDescription {
    @NonNull
    private List<String> skills;

    private List<String> responsibilities;

    @NonNull
    private List<String> qualification;

    @NonNull
    private WorkSetup workSetup;

    private List<String> benefits;

    @NonNull
    private EmploymentType employmentType;
}
