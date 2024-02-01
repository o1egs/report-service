package ru.shtyrev.report.dtos;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import ru.shtyrev.report.dtos.types.ProjectReportDTO;
import ru.shtyrev.report.dtos.types.UserReportDTO;
import ru.shtyrev.report.entities.ReportStatus;
import ru.shtyrev.report.entities.ReportType;
import ru.shtyrev.report.entities.RightSideType;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        visible = true
)
@JsonSubTypes(value = {
        @JsonSubTypes.Type(value = UserReportDTO.class, name = "USER"),
        @JsonSubTypes.Type(value = ProjectReportDTO.class, name = "PROJECT")
})
@NoArgsConstructor
@Getter
@Setter
public abstract class ReportDTO {
    private Long id;
    private ReportStatus status;
    private ReportType type;
    private String description;
    private Long reporterId;
    private Long accusedId;
    private Long assignedAdminId;
    private RightSideType rightSide;
}
