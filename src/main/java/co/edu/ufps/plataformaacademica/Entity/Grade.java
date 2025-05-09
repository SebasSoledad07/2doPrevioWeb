package co.edu.ufps.plataformaacademica.Entity;

import java.time.LocalDateTime;
import java.util.List;

public class Grade {

    private Student student;
    private Evaluation evaluation;
    private double score;
    private LocalDateTime date;
    private List<GradeHistory> history;

}
