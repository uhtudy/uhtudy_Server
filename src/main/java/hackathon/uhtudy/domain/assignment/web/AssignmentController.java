package hackathon.uhtudy.domain.assignment.web;

import hackathon.uhtudy.domain.assignment.application.AssignmentService;
import hackathon.uhtudy.domain.assignment.persistence.Assignment;
import hackathon.uhtudy.domain.assignment.web.response.AssignmentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;


    @GetMapping("/{assignmentId}")
    public AssignmentDto getCodeReview(@PathVariable final Long assignmentId) {

        final Assignment codeReview = assignmentService.getCodeReview(assignmentId);
        return new AssignmentDto(codeReview);
    }
}
