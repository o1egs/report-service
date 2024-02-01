package ru.shtyrev.report.services.impl;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.shtyrev.report.dtos.ReportDTO;
import ru.shtyrev.report.entities.*;
import ru.shtyrev.report.entities.fake.User;
import ru.shtyrev.report.exceptions.custom.*;
import ru.shtyrev.report.mappers.ReportMapper;
import ru.shtyrev.report.repositories.ReportRepository;
import ru.shtyrev.report.services.ReportService;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static ru.shtyrev.report.entities.ReportStatus.*;

@Service
public class ReportServiceImpl implements ReportService {
    private final Map<ReportType, ReportMapper> mappers;
    private final ReportRepository repository;
    private final WebClient.Builder webBuilder;

    public ReportServiceImpl(List<ReportMapper> mappers, ReportRepository repository, WebClient.Builder webBuilder) {
        this.mappers = mappers.stream()
                .collect(Collectors.toMap(ReportMapper::type, Function.identity()));
        this.repository = repository;
        this.webBuilder = webBuilder;
    }

    @Override
    public ReportDTO save(ReportDTO reportDTO) {
        ReportMapper mapper = mappers.get(reportDTO.getType());
        Report report = mapper.toReport(reportDTO);
        report = repository.save(report);
        User reporter = report.getReporter();
        User accused = report.getAccused();
        // TODO: 01.02.2024 Дописать добавление репорта для отпраляющего и осуждаемого
        return mapper.toDTO(report);
    }

    @Override
    public ReportDTO findById(Long id) {
        Report report = repository.findById(id)
                .orElseThrow(() -> new ReportNotFoundException(String.format("Report with id = \"%d\" not found", id)));
        ReportMapper mapper = mappers.get(report.getType());
        return mapper.toDTO(report);
    }

    @Override

    public List<ReportDTO> findAll() {
        return repository.findAll().stream()
                .map(report -> mappers.get(report.getType()).toDTO(report))
                .toList();
    }

    @Override
    public List<ReportDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable).stream()
                .map(report -> mappers.get(report.getType()).toDTO(report))
                .toList();
    }

    @Override
    public List<ReportDTO> findAllByType(ReportType type) {
        return repository.findAllByType(type).stream()
                .map(report -> mappers.get(report.getType()).toDTO(report))
                .toList();
    }

    @Override
    public List<ReportDTO> findAllByType(ReportType type, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAllByType(type, pageable).stream()
                .map(report -> mappers.get(report.getType()).toDTO(report))
                .toList();
    }

    @Override
    public List<ReportDTO> findAllByStatus(ReportStatus status) {
        return repository.findAllByStatus(status).stream()
                .map(report -> mappers.get(report.getType()).toDTO(report))
                .toList();
    }

    @Override
    public List<ReportDTO> findAllByStatus(ReportStatus status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAllByStatus(status, pageable).stream()
                .map(report -> mappers.get(report.getType()).toDTO(report))
                .toList();
    }

    @Override
    public ReportDTO startWorkingOnReport(Long reportId, Long assignedAdminId) {
        Report report = repository.findById(reportId)
                .orElseThrow(() -> new ReportNotFoundException(String.format("Report with id = \"%d\" not found", reportId)));
        if (report.getStatus().equals(ACTIVE)) {
            throw new ReportAlreadyStartedException(String.format("Report with id = \"%d\" has already been started", reportId));
        }
        if (report.getStatus().equals(SOLVED)) {
            throw new ReportAlreadySolvedException(String.format("Report with id = \"%d\" has already been solved", reportId));
        }
        var mapper = mappers.get(report.getType());
        var reportDTO = mapper.toDTO(report);
        reportDTO.setAssignedAdminId(assignedAdminId);
        reportDTO.setStatus(ACTIVE);
        report = repository.save(mapper.toReport(reportDTO));
        return mapper.toDTO(report);
    }

    @Override
    public ReportDTO solveReport(Long reportId, RightSideType rightSide) {
        Report report = repository.findById(reportId)
                .orElseThrow(() -> new ReportNotFoundException(String.format("Report with id = \"%d\" not found", reportId)));
        if (report.getStatus().equals(SENT)) {
            throw new ReportNotStartedException(String.format("Report with id = \"%d\" hasn't been started", reportId));
        }
        System.out.println(report.getStatus());
        if (report.getStatus().equals(SOLVED)) {
            throw new ReportAlreadySolvedException(String.format("Report with id = \"%d\" has already been solved", reportId));
        }
        report.setRightSide(rightSide);
        report.setStatus(SOLVED);
        repository.save(report);
        return mappers.get(report.getType()).toDTO(report);
    }
}
