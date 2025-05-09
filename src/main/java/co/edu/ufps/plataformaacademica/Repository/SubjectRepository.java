package co.edu.ufps.plataformaacademica.Repository;

import co.edu.ufps.plataformaacademica.Entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findByTeacherId(Long teacherId);
}
