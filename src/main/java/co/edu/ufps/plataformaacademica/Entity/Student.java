package co.edu.ufps.plataformaacademica.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CascadeType;

import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
public class Student extends User{

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<SubjectEnrollment> subjects;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Grade> grades = new ArrayList<>();
}
