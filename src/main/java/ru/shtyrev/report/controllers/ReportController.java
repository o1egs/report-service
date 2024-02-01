package ru.shtyrev.report.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shtyrev.report.dtos.ReportDTO;
import ru.shtyrev.report.entities.ReportStatus;
import ru.shtyrev.report.entities.ReportType;
import ru.shtyrev.report.entities.RightSideType;
import ru.shtyrev.report.services.impl.ReportServiceImpl;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@CrossOrigin
@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportServiceImpl service;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ReportDTO reportDTO) {
        ReportDTO saved = service.save(reportDTO);
        return new ResponseEntity<>(saved, CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        ReportDTO reportDTO = service.findById(id);
        return new ResponseEntity<>(reportDTO, OK);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<ReportDTO> reportDTOs = service.findAll();
        return new ResponseEntity<>(reportDTOs, OK);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<?> findAllByType(@PathVariable ReportType type) {
        List<ReportDTO> reportDTOs = service.findAllByType(type);
        return new ResponseEntity<>(reportDTOs, OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<?> findAllByStatus(@PathVariable ReportStatus status) {
        List<ReportDTO> reportDTOs = service.findAllByStatus(status);
        return new ResponseEntity<>(reportDTOs, OK);
    }

    @PutMapping("/start")
    public ResponseEntity<?> startWorkingOnReport(@RequestParam Long reportId, @RequestParam Long assignedAdminId) {
        ReportDTO reportDTO = service.startWorkingOnReport(reportId, assignedAdminId);
        return new ResponseEntity<>(reportDTO, OK);
    }

    @PutMapping("/solve")
    public ResponseEntity<?> solveReport(@RequestParam Long reportId, @RequestParam RightSideType rightSide) {
        ReportDTO reportDTO = service.solveReport(reportId, rightSide);
        return new ResponseEntity<>(reportDTO, OK);
    }


}
