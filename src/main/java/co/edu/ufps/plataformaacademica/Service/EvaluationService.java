package co.edu.ufps.plataformaacademica.Service;

import co.edu.ufps.plataformaacademica.Entity.Evaluation;
import co.edu.ufps.plataformaacademica.Entity.Subject;
import co.edu.ufps.plataformaacademica.Models.EvaluationRequestDTO;
import co.edu.ufps.plataformaacademica.Models.EvaluationResponseDTO;
import co.edu.ufps.plataformaacademica.Repository.EvaluationRepository;
import co.edu.ufps.plataformaacademica.Repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EvaluationService {
    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public List<EvaluationResponseDTO> getAllEvaluations() {
        return evaluationRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public EvaluationResponseDTO getEvaluationById(Long id) {
        Evaluation evaluation = evaluationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluación no encontrada con id: " + id));
        return convertToResponseDTO(evaluation);
    }

    public List<EvaluationResponseDTO> getEvaluationsBySubject(Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada con id: " + subjectId));

        return evaluationRepository.findBySubject(subject).stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public EvaluationResponseDTO createEvaluation(EvaluationRequestDTO dto) {
        Subject subject = subjectRepository.findById(dto.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Materia no encontrada con id: " + dto.getSubjectId()));

        Evaluation evaluation = new Evaluation();
        evaluation.setTitle(dto.getTitle());
        evaluation.setDate(dto.getDate());
        evaluation.setSubject(subject);


        Evaluation savedEvaluation = evaluationRepository.save(evaluation);
        return convertToResponseDTO(savedEvaluation);
    }

    public EvaluationResponseDTO updateEvaluation(Long id, EvaluationRequestDTO dto) {
        Evaluation evaluation = evaluationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluación no encontrada con id: " + id));

        if (dto.getTitle() != null) {
            evaluation.setTitle(dto.getTitle());
        }

        if (dto.getDate() != null) {
            evaluation.setDate(dto.getDate());
        }

        if (dto.getSubjectId() != null && !dto.getSubjectId().equals(evaluation.getSubject().getId())) {
            Subject subject = subjectRepository.findById(dto.getSubjectId())
                    .orElseThrow(() -> new RuntimeException("Materia no encontrada con id: " + dto.getSubjectId()));
            evaluation.setSubject(subject);
        }

        Evaluation updatedEvaluation = evaluationRepository.save(evaluation);
        return convertToResponseDTO(updatedEvaluation);
    }

    public void deleteEvaluation(Long id) {
        if (!evaluationRepository.existsById(id)) {
            throw new RuntimeException("Evaluación no encontrada con id: " + id);
        }
        evaluationRepository.deleteById(id);
    }

    private EvaluationResponseDTO convertToResponseDTO(Evaluation evaluation) {
        EvaluationResponseDTO dto = new EvaluationResponseDTO();
        dto.setId(evaluation.getId());
        dto.setTitle(evaluation.getTitle());
        dto.setDate(evaluation.getDate());
        dto.setSubjectId(evaluation.getSubject().getId());
        return dto;
    }
}
