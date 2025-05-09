package co.edu.ufps.plataformaacademica.Entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class AuditTrail {
    private User user;
    private String action;
    private String entityAffected;
    private LocalDateTime timeStamp;

}
