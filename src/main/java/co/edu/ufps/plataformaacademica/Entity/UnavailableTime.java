package co.edu.ufps.plataformaacademica.Entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class UnavailableTime {

    private Teacher teacher;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String reason;
}
