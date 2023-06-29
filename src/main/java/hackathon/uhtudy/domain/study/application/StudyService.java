package hackathon.uhtudy.domain.study.application;

import hackathon.uhtudy.domain.curriculum.web.request.CurriculumRequestDto;
import hackathon.uhtudy.domain.curriculum.web.request.CurriculumSaveRequestDto;
import hackathon.uhtudy.domain.study.persistence.Study;
import hackathon.uhtudy.domain.study.persistence.StudyRepository;
import hackathon.uhtudy.domain.study.web.request.StudySaveRequestDto;
import hackathon.uhtudy.domain.study.web.response.StudyMainResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class StudyService {

    private final StudyRepository studyRepository;

    public String createStudy(StudySaveRequestDto requestDto){
        Study study = new Study(requestDto.getTitle(), requestDto.getPeople(), requestDto.getGoal());
        studyRepository.save(study);
        for(CurriculumSaveRequestDto saveRequestDto : requestDto.getCurriculumList()){
            CurriculumRequestDto curriculumRequestDto = new CurriculumRequestDto(saveRequestDto.getWeekNum(), saveRequestDto.getTitle(), study, saveRequestDto.getAnnouncement());
        }
        return study.getAttendCode();
    }

    public StudyMainResponseDto.StudyListResponseDto getStudyList() {
        List<Study> studyList = studyRepository.findAll();
        List<StudyMainResponseDto.StudyOverviewDto> studyOverviewDtos = new ArrayList<>();
        for (Study study : studyList) {
            StudyMainResponseDto.StudyOverviewDto overviewDto = new StudyMainResponseDto.StudyOverviewDto(study.getId(), study.getTitle(), study.getGoal(), "홍대 할리스");
            studyOverviewDtos.add(overviewDto);
        }
        StudyMainResponseDto.StudyListResponseDto listResponseDto = new StudyMainResponseDto.StudyListResponseDto(studyOverviewDtos);
        return listResponseDto;
    }


    public Study getStudy(final Long studyId) {

        return studyRepository.findById(studyId).orElseThrow(IllegalArgumentException::new);
    }

    public String getAttendCode(final Long studyId) {

        final Study study = studyRepository.findById(studyId)
                .orElseThrow(IllegalArgumentException::new);

        return study.getAttendCode();
    }
}
