package hackathon.uhtudy.domain.assignment.application;

import hackathon.uhtudy.domain.assignment.persistence.Assignment;
import hackathon.uhtudy.domain.assignment.persistence.AssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

    public Assignment getCodeReview(final Long assignmentId) {

        return assignmentRepository.findById(assignmentId)
                .orElseThrow(IllegalArgumentException::new);
    }
}
