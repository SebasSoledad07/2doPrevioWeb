package co.edu.ufps.plataformaacademica.Models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EvaluationResponseDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDate date;
    private Long subjectId;
    private String subjectName;
    private Double maxScore;
    private Double weight;
}
