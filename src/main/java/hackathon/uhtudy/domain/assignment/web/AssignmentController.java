package hackathon.uhtudy.domain.assignment.web;

import hackathon.uhtudy.domain.assignment.application.AssignmentService;
import hackathon.uhtudy.domain.assignment.persistence.Assignment;
import hackathon.uhtudy.domain.assignment.web.request.SubmitAssignmentRequest;
import hackathon.uhtudy.domain.assignment.web.response.AssignmentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;


    @GetMapping("/{assignmentId}")
    public ResponseEntity<AssignmentDto> getCodeReview(@PathVariable final Long assignmentId) {

        final Assignment codeReview = assignmentService.getCodeReview(assignmentId);
        return ResponseEntity.ok(new AssignmentDto(codeReview));
    }


    @PostMapping("/studies/{studyId}/{weekNum}")
    public ResponseEntity<?> submitAssignment(
            @PathVariable final Long studyId,
            @PathVariable final int weekNum,
            @RequestBody final SubmitAssignmentRequest request) {

        assignmentService.submitAssignment(studyId, weekNum, request);
        return ResponseEntity.ok().build();
    }
}
