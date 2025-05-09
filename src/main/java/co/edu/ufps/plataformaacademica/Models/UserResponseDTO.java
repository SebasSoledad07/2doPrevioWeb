package co.edu.ufps.plataformaacademica.Models;

import co.edu.ufps.plataformaacademica.Entity.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private RoleEnum role;
}
