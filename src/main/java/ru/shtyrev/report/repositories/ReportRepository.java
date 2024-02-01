package ru.shtyrev.report.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shtyrev.report.entities.*;

import java.util.List;


@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findAllByType(ReportType type);
    List<Report> findAllByType(ReportType type, Pageable pageable);
    List<Report> findAllByStatus(ReportStatus status);
    List<Report> findAllByStatus(ReportStatus status, Pageable pageable);
}
