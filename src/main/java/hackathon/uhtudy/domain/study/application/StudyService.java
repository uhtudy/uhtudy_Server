package hackathon.uhtudy.domain.study.application;

import hackathon.uhtudy.domain.curriculum.web.request.CurriculumRequestDto;
import hackathon.uhtudy.domain.curriculum.web.request.CurriculumSaveRequestDto;
import hackathon.uhtudy.domain.study.persistence.Study;
import hackathon.uhtudy.domain.study.persistence.StudyRepository;
import hackathon.uhtudy.domain.study.web.request.StudySaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
