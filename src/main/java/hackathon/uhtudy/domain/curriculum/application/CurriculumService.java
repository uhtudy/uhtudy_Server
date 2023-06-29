package hackathon.uhtudy.domain.curriculum.application;

import hackathon.uhtudy.domain.curriculum.persistence.Curriculum;
import hackathon.uhtudy.domain.curriculum.persistence.CurriculumRepository;
import hackathon.uhtudy.domain.curriculum.web.request.CurriculumRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurriculumService {
    private final CurriculumRepository curriculumRepository;

    public void createCurriculum(CurriculumRequestDto requestDto){
        Curriculum curriculum = new Curriculum(requestDto.getWeekNum(), requestDto.getTitle(), requestDto.getStudy(), requestDto.getAnnouncement());
        curriculumRepository.save(curriculum);
    }
}
