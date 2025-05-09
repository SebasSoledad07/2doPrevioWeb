package co.edu.ufps.plataformaacademica.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Getter
@Setter
public class GradeHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "grade_id", nullable = false)
    private Grade grade;

    @Column(nullable = false)
    private double oldScore;

    @Column(nullable = false)
    private double newScore;

    @ManyToOne
    @JoinColumn(name = "modified_by")
    private Teacher modifiedBy;

    private LocalDateTime modificationDate;
}
