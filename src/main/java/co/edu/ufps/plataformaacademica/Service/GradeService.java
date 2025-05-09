package co.edu.ufps.plataformaacademica.Service;

import co.edu.ufps.plataformaacademica.Entity.Evaluation;
import co.edu.ufps.plataformaacademica.Entity.Grade;
import co.edu.ufps.plataformaacademica.Entity.GradeHistory;
import co.edu.ufps.plataformaacademica.Entity.Student;
import co.edu.ufps.plataformaacademica.Models.GradeRequestDTO;
import co.edu.ufps.plataformaacademica.Models.GradeResponseDTO;
import co.edu.ufps.plataformaacademica.Repository.EvaluationRepository;
import co.edu.ufps.plataformaacademica.Repository.GradeRepository;
import co.edu.ufps.plataformaacademica.Repository.StudentRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class GradeService {
    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private UserService userService;

    public List<GradeResponseDTO> getAllGrades() {
        return gradeRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public GradeResponseDTO getGradeById(Long id) {
        Grade grade = gradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Calificación no encontrada con id: " + id));
        return convertToResponseDTO(grade);
    }

    public List<GradeResponseDTO> getGradesByStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con id: " + studentId));

        return gradeRepository.findByStudent(student).stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<GradeResponseDTO> getGradesByEvaluation(Long evaluationId) {
        Evaluation evaluation = evaluationRepository.findById(evaluationId)
                .orElseThrow(() -> new RuntimeException("Evaluación no encontrada con id: " + evaluationId));

        return gradeRepository.findByEvaluation(evaluation).stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public GradeResponseDTO createOrUpdateGrade(GradeRequestDTO dto) {
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con id: " + dto.getStudentId()));

        Evaluation evaluation = evaluationRepository.findById(dto.getEvaluationId())
                .orElseThrow(() -> new RuntimeException("Evaluación no encontrada con id: " + dto.getEvaluationId()));


        Optional<Grade> existingGrade = gradeRepository.findByStudentAndEvaluation(student, evaluation);

        if (existingGrade.isPresent()) {
            // Actualizar calificación existente
            Grade grade = existingGrade.get();

            // Crear entrada en el historial
            createGradeHistory(grade, dto.getScore());

            // Actualizar la calificación
            grade.setScore(dto.getScore());
            grade.setDate(LocalDateTime.now());

            Grade updatedGrade = gradeRepository.save(grade);
            return convertToResponseDTO(updatedGrade);
        } else {
            // Crear nueva calificación
            Grade grade = new Grade();
            grade.setStudent(student);
            grade.setEvaluation(evaluation);
            grade.setScore(dto.getScore());
            grade.setDate(LocalDateTime.now());

            Grade savedGrade = gradeRepository.save(grade);
            return convertToResponseDTO(savedGrade);
        }
    }

    private void createGradeHistory(Grade grade, Double newScore) {
        GradeHistory history = new GradeHistory();
        history.setGrade(grade);
        history.setPreviousScore(grade.getScore());
        history.setNewScore(newScore);
        history.setModificationDate(LocalDateTime.now());


    public void deleteGrade(Long id) {
        if (!gradeRepository.existsById(id)) {
            throw new RuntimeException("Calificación no encontrada con id: " + id);
        }
        gradeRepository.deleteById(id);
    }

    private GradeResponseDTO convertToResponseDTO(Grade grade) {
        GradeResponseDTO dto = new GradeResponseDTO();
        dto.setId(grade.getId());
        dto.setStudentId(grade.getStudent().getId());
        dto.setStudentName(grade.getStudent().getName());
        dto.setEvaluationId(grade.getEvaluation().getId());
        dto.setEvaluationTitle(grade.getEvaluation().getTitle());
        dto.setScore(grade.getScore());
        dto.setDate(grade.getDate());
        return dto;
    }
}
}
