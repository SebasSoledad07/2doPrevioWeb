package co.edu.ufps.plataformaacademica.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<Student> students;
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<SubjectSchedule> schedules;
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<Evaluation> evaluations;

}
