package ru.shtyrev.report.dtos.types;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;
import ru.shtyrev.report.dtos.ReportDTO;


@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonTypeName(value = "project")
@EqualsAndHashCode(callSuper = false)
public class ProjectReportDTO extends ReportDTO {
    private Long reportedProjectId;
}
