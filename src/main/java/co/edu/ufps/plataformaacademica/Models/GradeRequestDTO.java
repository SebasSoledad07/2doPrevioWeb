package co.edu.ufps.plataformaacademica.Models;

import lombok.Data;

@Data
public class GradeRequestDTO {
    private Long studentId;
    private Long evaluationId;
    private Double score;
    private String comments;
}
