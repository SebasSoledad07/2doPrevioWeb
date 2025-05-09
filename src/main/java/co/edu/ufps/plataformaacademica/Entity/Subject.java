package co.edu.ufps.plataformaacademica.Entity;

import java.util.List;

public class Subject {

    private String title;
    private Teacher teacher;
    private List<Student> students;
    private List<SubjectSchedule> schedules;
    private List<Evaluation> evaluations;

    public void setTeacher(String teacher) {
    }

    public void setTeacherId(Long teacherId) {

    }
}
