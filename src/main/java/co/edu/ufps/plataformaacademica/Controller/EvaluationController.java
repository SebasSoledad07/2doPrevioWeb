package co.edu.ufps.plataformaacademica.Controller;

import co.edu.ufps.plataformaacademica.Models.EvaluationRequestDTO;
import co.edu.ufps.plataformaacademica.Models.EvaluationResponseDTO;
import co.edu.ufps.plataformaacademica.Service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluations")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @GetMapping
    public ResponseEntity<List<EvaluationResponseDTO>> getAllEvaluations() {
        return ResponseEntity.ok(evaluationService.getAllEvaluations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluationResponseDTO> getEvaluationById(@PathVariable Long id) {
        return ResponseEntity.ok(evaluationService.getEvaluationById(id));
    }

    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<EvaluationResponseDTO>> getEvaluationsBySubject(@PathVariable Long subjectId) {
        return ResponseEntity.ok(evaluationService.getEvaluationsBySubject(subjectId));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
    public ResponseEntity<EvaluationResponseDTO> createEvaluation(@RequestBody EvaluationRequestDTO evaluationDTO) {
        return new ResponseEntity<>(evaluationService.createEvaluation(evaluationDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
    public ResponseEntity<EvaluationResponseDTO> updateEvaluation(
            @PathVariable Long id,
            @RequestBody EvaluationRequestDTO evaluationDTO) {
        return ResponseEntity.ok(evaluationService.updateEvaluation(id, evaluationDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
    public ResponseEntity<Void> deleteEvaluation(@PathVariable Long id) {
        evaluationService.deleteEvaluation(id);
        return ResponseEntity.noContent().build();
    }
}
