package hackathon.uhtudy.domain.assignment.application;

import hackathon.uhtudy.domain.assignment.persistence.Assignment;
import hackathon.uhtudy.domain.assignment.persistence.AssignmentRepository;
import hackathon.uhtudy.domain.assignment.web.request.SubmitAssignmentRequest;
import hackathon.uhtudy.domain.curriculum.persistence.CurriculumRepository;
import hackathon.uhtudy.domain.study.persistence.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final CurriculumRepository curriculumRepository;
    private final StudyRepository studyRepository;

    public Assignment getCodeReview(final Long assignmentId) {

        return assignmentRepository.findById(assignmentId)
                .orElseThrow(IllegalArgumentException::new);
    }

    public void submitAssignment(final Long studyId, final int weekNum, final SubmitAssignmentRequest request) {

        final var study = studyRepository.findById(studyId)
                .orElseThrow(IllegalArgumentException::new);

        final var curriculum = curriculumRepository.findByStudyAndWeekNum(study, weekNum)
                .orElseThrow(IllegalArgumentException::new);

        final var assignment = new Assignment(weekNum, request.assignment(), request.isAbsent(), curriculum);

        assignmentRepository.save(assignment);
    }
}
