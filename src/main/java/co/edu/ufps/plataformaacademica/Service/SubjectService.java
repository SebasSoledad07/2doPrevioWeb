package co.edu.ufps.plataformaacademica.Service;

import co.edu.ufps.plataformaacademica.Entity.Subject;
import co.edu.ufps.plataformaacademica.Entity.Teacher;

import java.util.List;

public interface SubjectService {

    Subject createSubject(String title, Long teacherId);

    Subject updateSubject(Long subjectId, String newTitle, Long newTeacherId);

    void deleteSubject(Long subjectId);

    Subject getSubjectById(Long subjectId);

    List<Subject> getAllSubjects();

    List<Subject> getSubjectsByTeacher(Long teacherId);

}