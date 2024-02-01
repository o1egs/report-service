package ru.shtyrev.report.repositories.fake;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shtyrev.report.entities.fake.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
