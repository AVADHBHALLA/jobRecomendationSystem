package org.example.service;

import lombok.AllArgsConstructor;
import org.example.entity.Job;
import org.example.entity.User;
import org.example.models.EmploymentType;
import org.example.models.FilterDto;
import org.example.models.PreferenceDetailsDto;
import org.example.models.WorkSetup;
import org.example.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class JobMatchService {

    private final JobRepository jobRepository;

    public List<Job> getMatchingJobs(User user) {
        PreferenceDetailsDto prefs = user.getJobPreferenceFilter();

        List<Job> allJobs = jobRepository.findAll();
        List<Job> matchedJobs = new ArrayList<>();

        for (Job job : allJobs) {
            if (matches(job, prefs)) {
                matchedJobs.add(job);
            }
        }

        return matchedJobs;
    }

    private boolean matches(Job job, PreferenceDetailsDto prefs) {
        for (FilterDto filter : prefs.getFilters()) {

            switch (filter.getField()) {

                case "skills":
                    if (!matchSkills(job, filter)) return false;
                    break;

                case "company":
                    if (!matchCompany(job, filter)) return false;
                    break;

                case "location":
                    if (!matchLocation(job, filter)) return false;
                    break;

                case "salary":
                    if (!matchSalary(job, filter)) return false;
                    break;

                case "workSetup":
                    if (!matchWorkSetup(job, filter)) return false;
                    break;

                case "employmentType":
                    if (!matchEmploymentType(job, filter)) return false;
                    break;

                case "experience":
                    if (!matchExperience(job, filter)) return false;
                    break;
            }
        }
        return true;
    }

    private boolean matchSkills(Job job, FilterDto filter) {
        List<String> skills = filter.getValue();

        List<String> desc = job.getDescription().getSkills();

        return skills.stream()
                .anyMatch(skill -> desc.contains(skill.toLowerCase()));
    }

    private boolean matchCompany(Job job, FilterDto filter) {
        List<String> companies = filter.getValue();

        if (filter.getOp().equals("notIn")) {
            return companies.stream()
                    .noneMatch(c -> c.equalsIgnoreCase(job.getCompanyName()));
        }
        return true;
    }

    private boolean matchLocation(Job job, FilterDto filter) {
        List<String> locations = filter.getValue();

        String jobLocation = job.getLocation().getCity(); // assuming city exists

        if (filter.getOp().equals("notIn")) {
            return locations.stream()
                    .noneMatch(loc -> loc.equalsIgnoreCase(jobLocation));
        }
        return true;
    }

    private boolean matchSalary(Job job, FilterDto filter) {
        List<String> range = filter.getValue();

        double min = Double.parseDouble(range.get(0));
        double max = Double.parseDouble(range.get(1));

        return job.getMaxSalary() >= min && job.getMinSalary() <= max;
    }

    private boolean matchWorkSetup(Job job, FilterDto filter) {

        List<String> values = filter.getValue();

        WorkSetup jobSetup = job.getDescription().getWorkSetup();

        if (filter.getOp().equals("in")) {
            return values.stream()
                    .anyMatch(v -> jobSetup.name().equalsIgnoreCase(v));
        }

        if (filter.getOp().equals("notIn")) {
            return values.stream()
                    .noneMatch(v -> jobSetup.name().equalsIgnoreCase(v));
        }

        return true;
    }

    private boolean matchEmploymentType(Job job, FilterDto filter) {

        List<String> values = filter.getValue();

        EmploymentType type = job.getDescription().getEmploymentType();

        if (filter.getOp().equals("in")) {
            return values.stream()
                    .anyMatch(v -> type.name().equalsIgnoreCase(v));
        }

        if (filter.getOp().equals("notIn")) {
            return values.stream()
                    .noneMatch(v -> type.name().equalsIgnoreCase(v));
        }

        return true;
    }

    private boolean matchExperience(Job job, FilterDto filter) {

        int jobExp = job.getYearsOfExperience();

        int userExp = Integer.parseInt(filter.getValue().get(0));

        return jobExp<=userExp;
    }
}
