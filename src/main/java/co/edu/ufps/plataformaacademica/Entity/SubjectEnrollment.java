package co.edu.ufps.plataformaacademica.Entity;

import java.time.LocalDate;

public class SubjectEnrollment {

    private Student student;
    private Subject subject;
    private LocalDate enrollmentDate;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    private enum enrollmentStatus{ACTIVE, DROPPED, COMPLETED}
}
