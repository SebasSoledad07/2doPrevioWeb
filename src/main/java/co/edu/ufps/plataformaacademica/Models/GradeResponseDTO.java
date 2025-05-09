package co.edu.ufps.plataformaacademica.Models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GradeResponseDTO {
    private Long id;
    private Long studentId;
    private String studentName;
    private Long evaluationId;
    private String evaluationTitle;
    private Double score;
    private LocalDateTime date;
    private String comments;
}
