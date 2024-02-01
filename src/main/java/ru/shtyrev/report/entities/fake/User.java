package ru.shtyrev.report.entities.fake;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.shtyrev.report.entities.Report;

import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "reporter")
//    @JsonBackReference
    private Set<Report> submittedReports;
    @OneToMany(mappedBy = "accused")
//    @JsonBackReference
    private Set<Report> receivedReports;
}
