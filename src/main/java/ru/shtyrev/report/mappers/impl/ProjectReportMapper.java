package ru.shtyrev.report.mappers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.shtyrev.report.dtos.types.ProjectReportDTO;
import ru.shtyrev.report.dtos.ReportDTO;
import ru.shtyrev.report.entities.*;
import ru.shtyrev.report.entities.fake.Project;
import ru.shtyrev.report.entities.fake.User;
import ru.shtyrev.report.entities.types.ProjectReport;
import ru.shtyrev.report.mappers.ReportMapper;

import static ru.shtyrev.report.entities.ReportType.PROJECT;

@Component
@RequiredArgsConstructor
public class ProjectReportMapper implements ReportMapper {
    private final WebClient.Builder webClientBuilder;

    public ProjectReportDTO toDTO(Report report) {
        ProjectReport projectReport = (ProjectReport) report;
        var projectReportDTO = new ProjectReportDTO();
        projectReportDTO.setId(projectReport.getId());
        projectReportDTO.setType(projectReport.getType());
        projectReportDTO.setStatus(projectReport.getStatus());
        projectReportDTO.setReporterId(projectReport.getReporter().getId());
        projectReportDTO.setAccusedId(projectReport.getAccused().getId());
        projectReportDTO.setDescription(projectReport.getDescription());
        projectReportDTO.setReportedProjectId(projectReport.getReportedProject().getId());
        if (report.getAssignedAdmin() == null) {
            projectReportDTO.setAssignedAdminId(null);
        } else {
            projectReportDTO.setAssignedAdminId(report.getAssignedAdmin().getId());
        }
        if (report.getRightSide() == null) {
            projectReportDTO.setRightSide(null);
        } else {
            projectReportDTO.setRightSide(report.getRightSide());
        }
        return projectReportDTO;
    }
    public ProjectReport toReport(ReportDTO reportDTO) {
        ProjectReportDTO projectReportDTO = (ProjectReportDTO) reportDTO;
        User reporter = webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/users/" + projectReportDTO.getReporterId())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(User.class)
                .block();
        User accused = webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/users/" + projectReportDTO.getAccusedId())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(User.class)
                .block();
        User assignedAdmin = null;
        if (projectReportDTO.getAssignedAdminId() != null) {
            assignedAdmin = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8080/users/" + projectReportDTO.getAssignedAdminId())
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(User.class)
                    .block();
        }
        Project reportedProject = webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/projects/" + projectReportDTO.getReportedProjectId())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Project.class)
                .block();
        ReportStatus status;
        if (projectReportDTO.getStatus() == null) {
            status = null;
        } else {
            status = projectReportDTO.getStatus();
        }
        var projectReport = new ProjectReport();
        projectReport.setId(projectReportDTO.getId());
        projectReport.setType(projectReportDTO.getType());
        projectReport.setReporter(reporter);
        projectReport.setAccused(accused);
        projectReport.setAssignedAdmin(assignedAdmin);
        projectReport.setDescription(projectReportDTO.getDescription());
        projectReport.setStatus(status);
        projectReport.setReportedProject(reportedProject);
        return projectReport;
    }

    @Override
    public ReportType type() {
        return PROJECT;
    }
}
