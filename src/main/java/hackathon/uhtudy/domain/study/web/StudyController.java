package hackathon.uhtudy.domain.study.web;

import hackathon.uhtudy.domain.assignment.persistence.Assignment;
import hackathon.uhtudy.domain.assignment.persistence.AssignmentRepository;
import hackathon.uhtudy.domain.study.application.StudyService;
import hackathon.uhtudy.domain.study.persistence.Study;
import hackathon.uhtudy.domain.study.web.request.StudySaveRequestDto;
import hackathon.uhtudy.domain.study.web.response.GetStudyResponse;
import hackathon.uhtudy.domain.study.web.response.StudyCodeResponseDto;
import hackathon.uhtudy.domain.study.web.response.StudyMainResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;
    private final AssignmentRepository assignmentRepository;

    @PostMapping("/studies")
    private StudyCodeResponseDto createStudy(@RequestBody StudySaveRequestDto requestDto){
        return new StudyCodeResponseDto(studyService.createStudy(requestDto));
    }

    @GetMapping("/studies")
    private List<StudyMainResponseDto> getStudyList() {
        return studyService.getStudyList();
    }

    @PostMapping("/attendCode/{attendCode}")
    private void attendStudy(@PathVariable String attendCode){
        studyService.attendStudy(attendCode);
    }

    @GetMapping("/studies/{studyId}")
    public GetStudyResponse getStudy(@PathVariable final Long studyId) {

        final Study study = studyService.getStudy(studyId);

        final int lastIndex = study.getCurriculums().size() - 1;

        final List<Assignment> assignments = assignmentRepository.findAssignmentsByStudy(study);

        return new GetStudyResponse(
                study.getTitle(),
                study.getGoal(),
                study.getCurriculums().get(lastIndex).getAnnouncement(), //가장 마지막주차 (= 가장 최신) 공지
                study.getPeople(),
                assignments.stream()
                        .map(assignment -> new GetStudyResponse.AssignmentDto(
                                assignment.getWeekNum(),
                                assignment.getAssignment()))
                        .toList()
        );
    }


    @GetMapping("/attendCode/{studyId}")
    public String getAttendCode(@PathVariable final Long studyId) {
        return studyService.getAttendCode(studyId);
    }
}
