package org.example.entity;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.*;
import org.example.models.PreferenceDetailsDto;
import org.example.models.UserStatus;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name",nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "education")
    private EducationLevel education;

    @Column(name = "email", nullable = false, unique = true , updatable = false)
    private String email;

    @Type(JsonType.class)
    @Column(name = "job_preference_filter", columnDefinition = "jsonb")
    private PreferenceDetailsDto jobPreferenceFilter;

    @Column(name = "email_service_enabled")
    private boolean emailServiceEnabled;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserStatus status;
}
