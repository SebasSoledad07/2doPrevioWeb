package co.edu.ufps.plataformaacademica.Repository;

import co.edu.ufps.plataformaacademica.Entity.Evaluation;
import co.edu.ufps.plataformaacademica.Entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    List<Evaluation> findBySubject(Subject subject);
    List<Evaluation> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
