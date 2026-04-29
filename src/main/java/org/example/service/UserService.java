package org.example.service;

import lombok.AllArgsConstructor;
import org.example.entity.User;
import org.example.models.PreferenceDetailsDto;
import org.example.models.UserDto;
import org.example.models.UserStatus;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User create(UserDto dto){
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        User user = createNewUser(dto);
        return userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User get(UUID userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("INVALID USER ID"));
    }

    public void update(UUID userId, UserDto dto){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setEducation(dto.getEducation());
        user.setEmailServiceEnabled(dto.isEmailServiceEnabled());
        user.setJobPreferenceFilter(dto.getJobPreferenceFilter());
        userRepository.save(user);
    }

    public void deactivate(UUID userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setStatus(UserStatus.INACTIVE);
    }

    private User createNewUser(UserDto dto) {
        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .education(dto.getEducation())
                .jobPreferenceFilter(
                        dto.getJobPreferenceFilter() != null
                                ? dto.getJobPreferenceFilter()
                                : new PreferenceDetailsDto()
                )
                .emailServiceEnabled(true)
                .build();
    }
}
