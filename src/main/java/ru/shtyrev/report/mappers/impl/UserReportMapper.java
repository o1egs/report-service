package ru.shtyrev.report.mappers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.shtyrev.report.dtos.ReportDTO;
import ru.shtyrev.report.dtos.types.UserReportDTO;
import ru.shtyrev.report.entities.Report;
import ru.shtyrev.report.entities.ReportStatus;
import ru.shtyrev.report.entities.ReportType;
import ru.shtyrev.report.entities.fake.User;
import ru.shtyrev.report.entities.types.UserReport;
import ru.shtyrev.report.mappers.ReportMapper;

import static ru.shtyrev.report.entities.ReportType.USER;

@Component
@RequiredArgsConstructor
public class UserReportMapper implements ReportMapper {
    private final WebClient.Builder webClientBuilder;
    public UserReportDTO toDTO(Report report) {
        UserReport userReport = (UserReport) report;
        var userReportDTO = new UserReportDTO();
        userReportDTO.setId(userReport.getId());
        userReportDTO.setType(userReport.getType());
        userReportDTO.setStatus(userReport.getStatus());
        userReportDTO.setReporterId(userReport.getReporter().getId());
        userReportDTO.setAccusedId(userReport.getAccused().getId());
        userReportDTO.setDescription(userReport.getDescription());
        if ((report.getAssignedAdmin() == null)) {
            userReportDTO.setAssignedAdminId(null);
        } else {
            userReportDTO.setAssignedAdminId(report.getAssignedAdmin().getId());
        }
        if (report.getRightSide() == null) {
            userReportDTO.setRightSide(null);
        } else {
            userReportDTO.setRightSide(report.getRightSide());
        }
        return userReportDTO;
    }

    public UserReport toReport(ReportDTO reportDTO) {
        User reporter = webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/users/" + reportDTO.getReporterId())
                .retrieve()
                .bodyToMono(User.class)
                .block();
        User accused = webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/users/" + reportDTO.getAccusedId())
                .retrieve()
                .bodyToMono(User.class)
                .block();
        User assignedAdmin = null;
        if (reportDTO.getAssignedAdminId() != null) {
            assignedAdmin = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8080/users/" + reportDTO.getAssignedAdminId())
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(User.class)
                    .block();
        }
        ReportStatus status;
        if (reportDTO.getStatus() == null) {
            status = null;
        } else {
            status = reportDTO.getStatus();
        }
        var projectReport = new UserReport();
        projectReport.setId(reportDTO.getId());
        projectReport.setType(reportDTO.getType());
        projectReport.setReporter(reporter);
        projectReport.setAccused(accused);
        projectReport.setAssignedAdmin(assignedAdmin);
        projectReport.setDescription(reportDTO.getDescription());
        projectReport.setStatus(status);
        return projectReport;
    }

    @Override
    public ReportType type() {
        return USER;
    }
}
