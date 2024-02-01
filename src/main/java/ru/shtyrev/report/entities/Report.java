package ru.shtyrev.report.entities;

import jakarta.persistence.*;
import lombok.*;
import ru.shtyrev.report.entities.fake.User;

import java.io.Serializable;

import static ru.shtyrev.report.entities.ReportStatus.*;

@Entity
@Table(name = "reports")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "report_type", discriminatorType = DiscriminatorType.STRING)
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Report implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private ReportType type;
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private ReportStatus status;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "reporter_id",
                nullable = false)
//    @JsonManagedReference
    private User reporter;
    @ManyToOne
    @JoinColumn(name = "accused_id",
            nullable = false)
//    @JsonManagedReference
    private User accused;
    @ManyToOne
    @JoinColumn(name = "assignedAdmin")
//    @JsonManagedReference
    private User assignedAdmin;
    @Column(name = "rightSide")
    @Enumerated(value = EnumType.STRING)
    private RightSideType rightSide;

    @PrePersist
    public void init() {
        if (status == null) {
            this.setStatus(SENT);
        }
        if (assignedAdmin == null) {
            this.setAssignedAdmin(null);
        }
        this.setRightSide(null);
    }
}
