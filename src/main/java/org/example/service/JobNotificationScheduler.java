package org.example.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Job;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class JobNotificationScheduler {

    private final UserRepository userRepository;

    private final JobMatchService jobMatchService;

    private final EmailService emailService;

    @Scheduled(cron = "0 */1 * * * *")
    public void sendDailyJobRecommendations() {

        List<User> users = userRepository.findAll();

        for (User user : users) {

            List<Job> matchedJobs =
                    jobMatchService.getMatchingJobs(user.getId());

            if (matchedJobs.isEmpty()) {
                continue;
            }

            StringBuilder body = new StringBuilder();

            body.append("Recommended Jobs For You:\n\n");

            for (Job job : matchedJobs) {

                body.append("Job Title: ")
                        .append(job.getTitle())
                        .append("\n");

                body.append("Company: ")
                        .append(job.getCompanyName())
                        .append("\n");

                body.append("Salary: ")
                        .append(job.getMinSalary())
                        .append(" - ")
                        .append(job.getMaxSalary())
                        .append("\n\n");
            }
            //log.info("body:{},email:{}",body,user.getEmail());
            emailService.sendEmail(
                    user.getEmail(),
                    "Daily Job Recommendations",
                    body.toString()
            );
        }
    }
}
