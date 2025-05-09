package co.edu.ufps.plataformaacademica.Service;

import co.edu.ufps.plataformaacademica.Entity.Student;
import co.edu.ufps.plataformaacademica.Entity.Subject;
import co.edu.ufps.plataformaacademica.Entity.SubjectEnrollment;
import co.edu.ufps.plataformaacademica.Repository.StudentRepository;
import co.edu.ufps.plataformaacademica.Repository.SubjectEnrollmentRepository;
import co.edu.ufps.plataformaacademica.Repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;
    private final SubjectEnrollmentRepository enrollmentRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository,
                          StudentRepository studentRepository,
                          SubjectEnrollmentRepository enrollmentRepository) {
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public Subject createSubject(String teacher, Long teacherId) {
        Subject subject = new Subject();
        subject.setTeacher(teacher);
        subject.setTeacherId(teacherId);
        return subjectRepository.save(subject);
    }

    public void assignStudentToSubject(Long studentId, Long subjectId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada"));

        boolean alreadyEnrolled = enrollmentRepository.existsByStudentAndSubject(student, subject);
        if (alreadyEnrolled) {
            throw new RuntimeException("El estudiante ya está inscrito en esta materia");
        }

        SubjectEnrollment enrollment = new SubjectEnrollment();
        enrollment.setStudent(student);
        enrollment.setSubject(subject);
        enrollment.setEnrollmentDate(LocalDate.now());

        enrollmentRepository.save(enrollment);
    }

    public List<Subject> getSubjectsByTeacher(Long teacherId) {
        return subjectRepository.findByTeacherId(teacherId);
    }

    public List<Subject> getSubjectsByStudent(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId)
                .stream()
                .map(SubjectEnrollment::getSubject)
                .collect(Collectors.toList());
    }

    public void deleteSubject(Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada"));

        enrollmentRepository.deleteAllBySubject(subject);

        subjectRepository.delete(subject);
    }
}

