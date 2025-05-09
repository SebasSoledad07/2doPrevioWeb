package co.edu.ufps.plataformaacademica.Repository;

import co.edu.ufps.plataformaacademica.Entity.Student;
import co.edu.ufps.plataformaacademica.Entity.Subject;
import co.edu.ufps.plataformaacademica.Entity.SubjectEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectEnrollmentRepository extends JpaRepository<SubjectEnrollment, Long> {

    List<SubjectEnrollment> findByStudent(Student student);

    boolean existsByStudentAndSubject(Student student, Subject subject);
}
