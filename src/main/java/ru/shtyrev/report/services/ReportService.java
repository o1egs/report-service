package ru.shtyrev.report.services;

import ru.shtyrev.report.dtos.ReportDTO;
import ru.shtyrev.report.entities.ReportStatus;
import ru.shtyrev.report.entities.ReportType;
import ru.shtyrev.report.entities.RightSideType;

import java.util.List;

public interface ReportService {
    ReportDTO save(ReportDTO reportDTO);

    ReportDTO findById(Long id);

    List<ReportDTO> findAll();

    List<ReportDTO> findAll(int page, int size);

    List<ReportDTO> findAllByType(ReportType type);

    List<ReportDTO> findAllByType(ReportType type, int page, int size);

    List<ReportDTO> findAllByStatus(ReportStatus status);

    List<ReportDTO> findAllByStatus(ReportStatus status, int page, int size);

    ReportDTO startWorkingOnReport(Long reportId, Long assignedAdminId);

    ReportDTO solveReport(Long reportId, RightSideType rightSide);
}
