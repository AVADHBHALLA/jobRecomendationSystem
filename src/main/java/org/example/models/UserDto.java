package org.example.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.example.entity.EducationLevel;

import java.time.LocalDate;

@Getter
@Setter
public class UserDto {

    @NonNull
    private String email;

    @NonNull
    private String name;

    @NonNull
    private EducationLevel education;

    private PreferenceDetailsDto jobPreferenceFilter;

    private boolean emailServiceEnabled;
}
