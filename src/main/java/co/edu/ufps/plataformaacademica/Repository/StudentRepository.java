package co.edu.ufps.plataformaacademica.Repository;

import co.edu.ufps.plataformaacademica.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.geom.QuadCurve2D;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {}
