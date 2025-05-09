package co.edu.ufps.plataformaacademica.Repository;

import co.edu.ufps.plataformaacademica.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {}
