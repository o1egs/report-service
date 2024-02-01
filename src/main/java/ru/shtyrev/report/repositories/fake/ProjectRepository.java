package ru.shtyrev.report.repositories.fake;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shtyrev.report.entities.fake.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
