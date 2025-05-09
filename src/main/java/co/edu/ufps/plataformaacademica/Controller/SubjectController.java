package co.edu.ufps.plataformaacademica.Controller;

import co.edu.ufps.plataformaacademica.Service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import co.edu.ufps.plataformaacademica.Entity.Subject;
import co.edu.ufps.plataformaacademica.Service.SubjectService;
import co.edu.ufps.plataformaacademica.CreateSubjectRequest;
;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody CreateSubjectRequest request) {
        Subject subject = subjectService.createSubject(request.getTeacher(), request.getTeacherId());
        return ResponseEntity.ok(subject);
    }

    @PostMapping("/{subjectId}/assign/{studentId}")
    public ResponseEntity<Void> assignStudent(@PathVariable Long subjectId, @PathVariable Long studentId) {
        subjectService.assignStudentToSubject(studentId, subjectId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<Subject>> getSubjectsByTeacher(@PathVariable Long teacherId) {
        return ResponseEntity.ok(subjectService.getSubjectsByTeacher(teacherId));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Subject>> getSubjectsByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(subjectService.getSubjectsByStudent(studentId));
    }

    @DeleteMapping("/{subjectId}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long subjectId) {
        subjectService.deleteSubject(subjectId);
        return ResponseEntity.noContent().build();
    }
}

