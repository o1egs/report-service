package ru.shtyrev.report.controllers.fake;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.shtyrev.report.entities.fake.Project;
import ru.shtyrev.report.entities.fake.User;
import ru.shtyrev.report.repositories.fake.ProjectRepository;
import ru.shtyrev.report.repositories.fake.UserRepository;

@RestController
@RequiredArgsConstructor
public class ProjectAndUserController {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @PostMapping("/projects")
    public Project saveProject() {
        return projectRepository.save(new Project());
    }

    @PostMapping("/users")
    public User saveUser() {
        return userRepository.save(new User());
    }

    @GetMapping("/projects/{id}")
    public Project findProjectById(@PathVariable Long id) {
        return projectRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @GetMapping("/users/{id}")
    public User findUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}
