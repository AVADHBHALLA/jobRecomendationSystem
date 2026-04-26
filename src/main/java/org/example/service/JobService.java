package org.example.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.entity.Job;
import org.example.models.JobDescription;
import org.example.models.JobDto;
import org.example.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class JobService {

    private final JobRepository jobRepository;

    public Job create(JobDto dto){
        String description = formDescription(dto.getJobDescription());
        Job job = createNewJob(dto,description);
        return jobRepository.save(job);
    }

    public List<Job> getAll(){
        return jobRepository.findAll();
    }

    public Job get(UUID jobId){
        return jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("INVALID JOB ID"));
    }

    public void update(UUID jobId , JobDto dto){
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        String description = formDescription(dto.getJobDescription());
        job.setDescription(description);
        job.setTitle(dto.getTitle());
        job.setYearsOfExperience(dto.getYearOfExperience());
        job.setLocation(dto.getLocation());
        job.setCompanyName(dto.getCompanyName());
        job.setMinSalary(dto.getMinSalary());
        job.setMaxSalary(dto.getMaxSalary());
    }

    private String formDescription(JobDescription description) {
        StringBuilder sb = new StringBuilder();

        sb.append("Skills: ")
                .append(String.join(", ", description.getSkills()))
                .append("\n\n");

        if (description.getResponsibilities() != null && !description.getResponsibilities().isEmpty()) {
            sb.append("Responsibilities:\n")
                    .append(" - ")
                    .append(String.join("\n - ", description.getResponsibilities()))
                    .append("\n\n");
        }

        sb.append("Qualification:\n")
                .append(" - ")
                .append(String.join("\n - ", description.getQualification()))
                .append("\n\n");

        sb.append("Work Setup: ")
                .append(description.getWorkSetup())
                .append("\n\n");

        if (description.getBenefits() != null && !description.getBenefits().isEmpty()) {
            sb.append("Benefits:\n")
                    .append(" - ")
                    .append(String.join("\n - ", description.getBenefits()))
                    .append("\n\n");
        }

        sb.append("Employment Type: ")
                .append(description.getEmploymentType());

        return sb.toString();
    }

    private Job createNewJob(JobDto dto,String description){
        return Job.builder()
                .title(dto.getTitle())
                .description(description)
                .companyName(dto.getCompanyName())
                .yearsOfExperience(dto.getYearOfExperience())
                .minSalary(dto.getMinSalary())
                .maxSalary(dto.getMaxSalary())
                .location(dto.getLocation())
                .build();
    }
}
