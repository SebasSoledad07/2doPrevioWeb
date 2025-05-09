package co.edu.ufps.plataformaacademica.Models;

import co.edu.ufps.plataformaacademica.Entity.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequestDTO {
    private String name;
    private String email;
    private String password;
    private RoleEnum role;
}
