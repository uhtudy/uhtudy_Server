package hackathon.uhtudy.domain.assignment.application;

import hackathon.uhtudy.domain.assignment.persistence.Assignment;
import hackathon.uhtudy.domain.assignment.persistence.AssignmentRepository;
import hackathon.uhtudy.domain.assignment.web.request.SubmitAssignmentRequest;
import hackathon.uhtudy.domain.curriculum.persistence.Curriculum;
import hackathon.uhtudy.domain.curriculum.persistence.CurriculumRepository;
import hackathon.uhtudy.domain.study.persistence.Study;
import hackathon.uhtudy.domain.study.persistence.StudyRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import settings.ServiceTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AssignmentServiceTest extends ServiceTest {

    @Autowired AssignmentService assignmentService;
    @Autowired AssignmentRepository assignmentRepository;
    @Autowired CurriculumRepository curriculumRepository;
    @Autowired StudyRepository studyRepository;

    @Nested
    @DisplayName("코드리뷰 조회")
    public class GetCodeReview {

        @Test
        @DisplayName("요청이 정상적으로 수행되어, 코드리뷰 조회에 성공해야 한다.")
        public void 성공() throws Exception {

            //given -- 조건
            
            //스터디 생성
            final Study study = getDefaulStudy();
            final Curriculum curriculum = getDefaultStudy(study);
            final Assignment assignment = getDefaultAssignment(curriculum);

            final Long assignmentId = assignment.getId();

            //when -- 동작
            final Assignment codeReview = assignmentService.getCodeReview(assignmentId);

            //then -- 검증
            assertThat(codeReview.getAssignment()).isEqualTo("github.com");
        }


        @Test
        @DisplayName("존재하지 않는 과제 정보를 조회하려 하면, 조회에 실패한다.")
        public void 실패1() throws Exception {

            //given -- 조건

            // do nothing

            //expected
            assertThrows(IllegalArgumentException.class, () -> {

                assignmentService.getCodeReview(1000L);
            });
        }
    }


    @Nested
    @DisplayName("과제 제출")
    public class SubmitAssignment {

        @Test
        @DisplayName("요청이 성공적으로 수행되어, 과제 제출에 성공해야 한다.")
        public void 성공1() throws Exception {

            //given -- 조건
            final Study study = getDefaulStudy();
            final Curriculum curriculum = getDefaultStudy(study);
            final Assignment assignment = getDefaultAssignment(curriculum);

            final var request = new SubmitAssignmentRequest(
                    "github.com",
                    true
            );

            //when -- 동작
            assignmentService.submitAssignment(study.getId(), 1, request);

            //then -- 검증
            final List<Assignment> assignments = assignmentRepository.findAll();
            Assertions.assertThat(assignments).isNotNull();
            Assertions.assertThat(assignments.size()).isGreaterThan(1);
        }

        @Test
        @DisplayName("존재하지 않는 스터디 정보로 요청할 경우, 과제 제출에 실패한다.")
        public void 실패1() throws Exception {

            //given -- 조건

            final var request = new SubmitAssignmentRequest(
                    "github.com",
                    true
            );

            //expected
            assertThrows(IllegalArgumentException.class, () -> {
                assignmentService.submitAssignment(1000L, 1, request);
            });
        }


        @Test
        @DisplayName("존재하지 않는 주차의 커리큘럼 정보로 요청할 경우, 과제 제출에 실패한다.")
        public void 실패2() throws Exception {

            //given -- 조건

            final Study study = getDefaulStudy();
            final Curriculum curriculum = getDefaultStudy(study);
            final Assignment assignment = getDefaultAssignment(curriculum);

            final var request = new SubmitAssignmentRequest(
                    "github.com",
                    true
            );

            //expected
            assertThrows(IllegalArgumentException.class, () -> {
                assignmentService.submitAssignment(study.getId() , 1000, request);
            });
        }
    }


    private Assignment getDefaultAssignment(final Curriculum curriculum) {
        return assignmentRepository.save(new Assignment(
                1,
                "github.com",
                true,
                curriculum
        ));
    }

    private Curriculum getDefaultStudy(final Study study) {
        return curriculumRepository.save(new Curriculum(
                1,
                "title",
                study,
                "공지1"
        ));
    }

    private Study getDefaulStudy() {
        return studyRepository.save(new Study(
                "title",
                7,
                "취업해보자~!"
        ));
    }
}