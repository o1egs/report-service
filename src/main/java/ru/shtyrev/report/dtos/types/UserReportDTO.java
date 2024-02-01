package ru.shtyrev.report.dtos.types;


import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;
import ru.shtyrev.report.dtos.ReportDTO;

@Getter
@Setter
@NoArgsConstructor
@JsonTypeName(value = "user")
@EqualsAndHashCode(callSuper = false)
public class UserReportDTO extends ReportDTO {

}
