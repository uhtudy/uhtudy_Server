package hackathon.uhtudy.domain.curriculum.application;

import hackathon.uhtudy.domain.curriculum.persistence.Curriculum;
import hackathon.uhtudy.domain.curriculum.persistence.CurriculumRepository;
import hackathon.uhtudy.domain.curriculum.web.request.CurriculumSaveRequestDto;
import hackathon.uhtudy.domain.study.persistence.Study;
import hackathon.uhtudy.domain.study.persistence.StudyRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import settings.ServiceTest;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class CurriculumServiceTest extends ServiceTest {

    @Autowired
    private CurriculumService curriculumService;

    @Autowired
    private CurriculumRepository curriculumRepository;

    @Autowired
    private StudyRepository studyRepository;


    @Nested
    @DisplayName("커리큘럼 등록")
    public class CreateCurriculum {

        @Test
        @DisplayName("요청이 성공적으로 수행되어, 커리큘럼이 등록되어야 한다.")
        public void 성공() throws Exception {

            //given -- 조건
            final Study study = studyRepository.save(new Study("title", 7, "win!"));


            final var list = IntStream.range(0, 10)
                    .mapToObj(i -> new CurriculumSaveRequestDto(
                            i,
                            "title" + i,
                            "announcement" + i))
                    .toList();


            //when -- 동작
            curriculumService.createCurriculum(list, study);

            //then -- 검증
            Assertions.assertThat(curriculumRepository.count()).isGreaterThan(9);
        }
    }
}