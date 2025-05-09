package co.edu.ufps.plataformaacademica.Entity;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
public class AuditTrail {
    private User user;
    private String action;
    private String entityAffected;
    private LocalDateTime timeStamp;

}
