package co.edu.ufps.plataformaacademica.Models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EvaluationRequestDTO {
    private String title;
    private String description;
    private LocalDate date;
    private Long subjectId;
    private Double maxScore;
    private Double weight;
}
