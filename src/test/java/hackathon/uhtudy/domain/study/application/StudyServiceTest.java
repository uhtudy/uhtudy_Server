package hackathon.uhtudy.domain.study.application;

import hackathon.uhtudy.domain.curriculum.web.request.CurriculumSaveRequestDto;
import hackathon.uhtudy.domain.study.persistence.Study;
import hackathon.uhtudy.domain.study.persistence.StudyRepository;
import hackathon.uhtudy.domain.study.web.request.StudySaveRequestDto;
import hackathon.uhtudy.domain.study.web.response.StudyMainResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import settings.ServiceTest;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class StudyServiceTest extends ServiceTest {

    @Autowired
    private StudyService studyService;

    @Autowired
    private StudyRepository studyRepository;


    @Nested
    @DisplayName("스터디 생성")
    public class CreateStudy {

        @Test
        @DisplayName("요청이 정상적으로 수행되어, 스터디가 생성되어야 한다.")
        public void 성공() throws Exception {

            //given -- 조건
            final var list = IntStream.range(0, 10)
                    .mapToObj(i -> new CurriculumSaveRequestDto(
                            i,
                            "title" + i,
                            "announcement" + i))
                    .toList();

            final var request = new StudySaveRequestDto(
                    "title",
                    7,
                    "goal",
                    list
            );

            //when -- 동작
            final String attendCode = studyService.createStudy(request);

            //then -- 검증
            Assertions.assertThat(attendCode).isNotNull();
        }
    }


    @Nested
    @DisplayName("스터디 상세 조회")
    public class GetStudy {

        @Test
        @DisplayName("요청이 성공적으로 수행되어, 스터디 상세 조회에 성공해야 한다.")
        public void 성공() throws Exception {

            //given -- 조건
            final Study study = studyRepository.save(new Study("title", 7, "goal"));

            //when -- 동작
            final Study findStudy = studyService.getStudy(study.getId());

            //then -- 검증
            Assertions.assertThat(findStudy.getTitle()).isEqualTo("title");
            Assertions.assertThat(findStudy.getPeople()).isEqualTo(7);
            Assertions.assertThat(findStudy.getGoal()).isEqualTo("goal");
        }


        @Test
        @DisplayName("존재하지 않는 스터디를 조회하려 할 경우, 조회에 실패한다.")
        public void 실패1() throws Exception {

            //given -- 조건

            //do nothing

            //expected
            assertThrows(IllegalArgumentException.class, () -> {
                studyService.getStudy(1000L);
            });
        }
    }


    @Nested
    @DisplayName("스터디 목록 조회")
    public class GetStudyList {

        @Test
        @DisplayName("참여한 스터디가 있을 경우, 조회 결과에 참여한 스터디만 나와야 한다.")
        public void 성공1() throws Exception {

            //given -- 조건
            final var list = IntStream.range(0, 10)
                    .mapToObj(i -> new CurriculumSaveRequestDto(i, "title", "announce"))
                    .toList();

            final var requestDto = new StudySaveRequestDto(
                    "title",
                    7,
                    "goal",
                    list);

            final String attendCode = studyService.createStudy(requestDto);
            studyService.attendStudy(attendCode);

            //when -- 동작
            final var attendStudyList = studyService.getStudyList();

            //then -- 검증
            Assertions.assertThat(attendStudyList).isNotNull();
            Assertions.assertThat(attendStudyList.size()).isEqualTo(1);
        }


        @Test
        @DisplayName("참여한 스터디가 없을 경우, 스터디 목록 조회 결과가 없어야 한다.")
        public void 성공2() throws Exception {

            //when -- 동작
            final var attendStudyList = studyService.getStudyList();

            //then -- 검증
            Assertions.assertThat(attendStudyList).isEqualTo(List.of());
            Assertions.assertThat(attendStudyList.size()).isEqualTo(0);
        }
    }


    @Nested
    @DisplayName("참여코드 조회")
    public class GetAttendCode {

        @Test
        @DisplayName("요청이 정상적으로 수행되어, 참여코드 조회에 성공해야 한다.")
        public void 성공() throws Exception {

            //given -- 조건
            final var list = IntStream.range(0, 10)
                    .mapToObj(i -> new CurriculumSaveRequestDto(i, "title", "announce"))
                    .toList();

            final var requestDto = new StudySaveRequestDto(
                    "title",
                    7,
                    "goal",
                    list);

            studyService.createStudy(requestDto);

            //when -- 동작
            String attendCode = studyService.getAttendCode(1L);

            //then -- 검증
            Assertions.assertThat(attendCode).isNotNull();
        }


        @Test
        @DisplayName("존재하지 않는 스터디의 참여코드를 조회하려 할 경우, 조회에 실패한다.")
        public void 실패1() throws Exception {

            //given -- 조건

            //do nothing

            //expected
            assertThrows(IllegalArgumentException.class, () -> {
                studyService.getAttendCode(1000L);
            });
        }
    }


    @Nested
    @DisplayName("스터디 참석")
    public class AttendStudy {

        @Test
        @DisplayName("참여코드가 유효하여 스터디 참석에 성공한다.")
        public void 성공() throws Exception {

            //given -- 조건
            final var list = IntStream.range(0, 10)
                    .mapToObj(i -> new CurriculumSaveRequestDto(i, "title", "announce"))
                    .toList();

            final var requestDto = new StudySaveRequestDto(
                    "title",
                    7,
                    "goal",
                    list);

            String attendCode = studyService.createStudy(requestDto);

            //when -- 동작
            studyService.attendStudy(attendCode);

            //then -- 검증
            final var studyList = studyService.getStudyList();
            Assertions.assertThat(studyList.size()).isGreaterThan(0);
        }


        @Test
        @DisplayName("참여 코드가 유효하지 않아, 스터디 참석에 실패한다.")
        public void 실패1() throws Exception {

            //given -- 조건

            //do nothing

            //expected
            assertThrows(IllegalArgumentException.class, () -> {
                studyService.attendStudy("invalid-code");
            });
        }
    }
}