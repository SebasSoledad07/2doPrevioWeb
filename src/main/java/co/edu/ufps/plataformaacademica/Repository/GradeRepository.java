package co.edu.ufps.plataformaacademica.Repository;

import co.edu.ufps.plataformaacademica.Entity.Evaluation;
import co.edu.ufps.plataformaacademica.Entity.Grade;
import co.edu.ufps.plataformaacademica.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByStudent(Student student);
    List<Grade> findByEvaluation(Evaluation evaluation);
    Optional<Grade> findByStudentAndEvaluation(Student student, Evaluation evaluation);
}
