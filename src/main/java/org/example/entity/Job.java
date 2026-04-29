package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "job")
public class Job {
    @Id
    @UuidGenerator
    @Column(name = "id")
    private UUID id;

    @Column(name = "title" , nullable = false)
    private String title;

    @Column(name = "description" , nullable = false,columnDefinition = "TEXT")
    private String description;

    @Column(name = "company_name" , nullable = false)
    private String companyName;

    @Column(name = "yoe")
    private int yearsOfExperience;

    @Column(name = "min_salary")
    private double minSalary;

    @Column(name = "max_salary")
    private double maxSalary;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id", nullable = false)
    private Address location;

}
