package co.edu.ufps.plataformaacademica.Entity;

import java.time.LocalDate;

public class SubjectEnrollment {

    private Student student;
    private Subject subject;
    private LocalDate enrollmentDate;

    private enum enrollmentStatus{ACTIVE, DROPPED, COMPLETED}
}
