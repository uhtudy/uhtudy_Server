package hackathon.uhtudy.domain.study.application;

import hackathon.uhtudy.domain.curriculum.web.request.CurriculumRequestDto;
import hackathon.uhtudy.domain.curriculum.web.request.CurriculumSaveRequestDto;
import hackathon.uhtudy.domain.study.persistence.Study;
import hackathon.uhtudy.domain.study.persistence.StudyRepository;
import hackathon.uhtudy.domain.study.web.request.StudySaveRequestDto;
import hackathon.uhtudy.domain.study.web.response.StudyMainResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
//@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudyService {

    private final StudyRepository studyRepository;

//    @Transactional
    public String createStudy(StudySaveRequestDto requestDto){
        Study study = new Study(requestDto.getTitle(), requestDto.getPeople(), requestDto.getGoal());
        studyRepository.save(study);
        for(CurriculumSaveRequestDto saveRequestDto : requestDto.getCurriculumList()){
            CurriculumRequestDto curriculumRequestDto = new CurriculumRequestDto(saveRequestDto.getWeekNum(), saveRequestDto.getTitle(), study, saveRequestDto.getAnnouncement());
        }
        return study.getAttendCode();
    }

    public Study getStudy(final Long studyId) {

        return studyRepository.findById(studyId).orElseThrow(IllegalArgumentException::new);
    }
    public List<StudyMainResponseDto> getStudyList(){
//        List<Study> studyList = studyRepository.getStudiesByIsMyStudy();
//        List<StudyMainResponseDto.StudyOverviewDto> studyOverviewDtos = new ArrayList<>();
//        for(Study study : studyList){
//            StudyMainResponseDto.StudyOverviewDto overviewDto = new StudyMainResponseDto.StudyOverviewDto(study.getId(), study.getTitle(), study.getGoal(), "홍대 할리스");
//            studyOverviewDtos.add(overviewDto);
//        }
//        StudyMainResponseDto.StudyListResponseDto listResponseDto = new StudyMainResponseDto.StudyListResponseDto(studyOverviewDtos);

        List<Study> studies = studyRepository.findAll();
        return studies.stream()
                .filter(study -> study.getIsMyStudy() == true)
                .map(StudyMainResponseDto::new)
                .toList();

//
    }

    public String getAttendCode(final Long studyId) {

        final Study study = studyRepository.findById(studyId)
                .orElseThrow(IllegalArgumentException::new);

        return study.getAttendCode();
    }
    @Transactional
    public void attendStudy(String attendCode){
        Study study = studyRepository.findByAttendCode(attendCode)
                .orElseThrow(() -> new IllegalArgumentException());
        study.update();
    }
}
