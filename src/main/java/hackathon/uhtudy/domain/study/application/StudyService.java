package hackathon.uhtudy.domain.study.application;

import hackathon.uhtudy.domain.curriculum.application.CurriculumService;
import hackathon.uhtudy.domain.study.persistence.Study;
import hackathon.uhtudy.domain.study.persistence.StudyRepository;
import hackathon.uhtudy.domain.study.web.request.StudySaveRequestDto;
import hackathon.uhtudy.domain.study.web.response.StudyMainResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class StudyService {

    private final StudyRepository studyRepository;
    private final CurriculumService curriculumService;

    @Transactional
    public String createStudy(final StudySaveRequestDto requestDto) {
        final Study study = new Study(
                requestDto.getTitle(),
                requestDto.getPeople(),
                requestDto.getGoal());
        studyRepository.save(study);

        curriculumService.createCurriculum(requestDto.getCurriculumList(), study);

        return study.getAttendCode();
    }


    @Transactional(readOnly = true)
    public Study getStudy(final Long studyId) {

        return studyRepository.findById(studyId).orElseThrow(IllegalArgumentException::new);
    }


    @Transactional(readOnly = true)
    public List<StudyMainResponseDto> getStudyList() {
        List<Study> studies = studyRepository.findAll();
        return studies.stream()
                .filter(study -> study.getIsMyStudy() == true)
                .map(StudyMainResponseDto::new)
                .toList();

    }

    public String getAttendCode(final Long studyId) {

        final Study study = studyRepository.findById(studyId)
                .orElseThrow(IllegalArgumentException::new);

        return study.getAttendCode();
    }

    @Transactional
    public void attendStudy(String attendCode) {
        final Study study = studyRepository.findByAttendCode(attendCode)
                .orElseThrow(IllegalArgumentException::new);
        study.update();
    }
}
