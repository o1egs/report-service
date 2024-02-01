package ru.shtyrev.report.entities.types;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.shtyrev.report.entities.Report;

import static ru.shtyrev.report.entities.ReportType.USER;

@Entity
@DiscriminatorValue("user_report")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserReport extends Report {

}
