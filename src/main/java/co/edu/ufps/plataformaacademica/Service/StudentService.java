package co.edu.ufps.plataformaacademica.Service;

import co.edu.ufps.plataformaacademica.Entity.Student;
import co.edu.ufps.plataformaacademica.Entity.Subject;
import co.edu.ufps.plataformaacademica.Entity.SubjectEnrollment;
import co.edu.ufps.plataformaacademica.Repository.StudentRepository;
import co.edu.ufps.plataformaacademica.Repository.SubjectEnrollmentRepository;
import co.edu.ufps.plataformaacademica.Repository.SubjectRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final SubjectEnrollmentRepository enrollmentRepository;

    public Student findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con ID: " + id));
    }

    public List<Subject> getEnrolledSubjects(Long studentId) {
        Student student = findById(studentId);
        return enrollmentRepository.findByStudent(studentId).stream()
                .map(SubjectEnrollment::getSubject)
                .collect(Collectors.toList());
    }

    public void enrollToSubject(Long studentId, Long subjectId) {
        Student student = findById(studentId);
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new EntityNotFoundException("Asignatura no encontrada con ID: " + subjectId));

        boolean alreadyEnrolled = enrollmentRepository.existsByStudentAndSubject(student, subject);
        if (alreadyEnrolled) {
            throw new IllegalArgumentException("El estudiante ya está inscrito en esta asignatura.");
        }

        SubjectEnrollment enrollment = new SubjectEnrollment();
        enrollment.setStudent(student);
        enrollment.setSubject(subject);
        enrollment.setEnrollmentDate(LocalDate.now());

        enrollmentRepository.save(enrollment);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }
}
