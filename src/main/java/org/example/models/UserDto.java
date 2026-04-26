package org.example.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserDto {

    @NonNull
    private String email;

    @NonNull
    private String name;

    private String education;

    private String phone;

    private String resume;

    private LocalDate dob;

    private String jobPreferenceFilter;
}
