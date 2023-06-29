package hackathon.uhtudy.domain.curriculum.application;

import hackathon.uhtudy.domain.curriculum.persistence.Curriculum;
import hackathon.uhtudy.domain.curriculum.persistence.CurriculumRepository;
import hackathon.uhtudy.domain.curriculum.web.request.CurriculumSaveRequestDto;
import hackathon.uhtudy.domain.study.persistence.Study;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurriculumService {
    private final CurriculumRepository curriculumRepository;


    @Transactional
    public void createCurriculum(final List<CurriculumSaveRequestDto> requestDtos, final Study study) {

        final List<Curriculum> list = requestDtos.stream()
                .map(request -> new Curriculum(
                        request.getWeekNum(),
                        request.getTitle(),
                        study,
                        request.getAnnouncement()))
                .toList();

        curriculumRepository.saveAll(list);
    }
}
