package ru.shtyrev.report.entities.types;

import jakarta.persistence.*;
import lombok.*;
import ru.shtyrev.report.entities.fake.Project;
import ru.shtyrev.report.entities.Report;


@Entity
@DiscriminatorValue("project_report")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProjectReport extends Report {
    @ManyToOne
    @JoinColumn(name = "reported_project_id")
//    @JsonManagedReference
    private Project reportedProject;
}
