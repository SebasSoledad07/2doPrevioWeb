package co.edu.ufps.plataformaacademica.Entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    public RoleEnum role;

    public enum RoleEnum{ ADMIN, TEACHER, STUDENT}
}
