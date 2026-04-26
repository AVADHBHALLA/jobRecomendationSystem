package org.example.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.example.entity.Address;

@Getter
@Setter
public class JobDto {
    @NonNull
    private String title;

    @NonNull
    private JobDescription jobDescription;

    @NonNull
    private String companyName;

    private int yearOfExperience;

    private double minSalary;

    private double maxSalary;

    @NonNull
    private Address location;
}
