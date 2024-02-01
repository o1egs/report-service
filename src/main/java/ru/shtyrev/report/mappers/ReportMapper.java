package ru.shtyrev.report.mappers;

import ru.shtyrev.report.dtos.ReportDTO;
import ru.shtyrev.report.entities.Report;
import ru.shtyrev.report.entities.ReportType;

public interface ReportMapper {
    ReportDTO toDTO(Report report);

    Report toReport(ReportDTO reportDTO);

    ReportType type();
}
