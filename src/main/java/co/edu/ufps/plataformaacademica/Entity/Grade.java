package co.edu.ufps.plataformaacademica.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "evaluation_id", nullable = false)
    private Evaluation evaluation;

    @Column(nullable = false)
    private double score;


    @Column(nullable = false)
    private LocalDateTime date;

    @OneToMany(mappedBy = "grade", cascade = CascadeType.ALL)
    private List<GradeHistory> history = new ArrayList<>();


}
