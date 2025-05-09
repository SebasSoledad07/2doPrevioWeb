package co.edu.ufps.plataformaacademica.Entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class Teacher extends User {
    private List<TeacherSchedule> availableSchedules ;
    private List<UnavailableTime> unavailableTime ;
    private List<Subject> subjects ;

}
