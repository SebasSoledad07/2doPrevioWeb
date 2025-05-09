package co.edu.ufps.plataformaacademica.Entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class SubjectSchedule {

    private Subject subject;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
}
