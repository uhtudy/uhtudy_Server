package hackathon.uhtudy.domain.study.web;

import hackathon.uhtudy.domain.curriculum.web.request.CurriculumSaveRequestDto;
import hackathon.uhtudy.domain.study.application.StudyService;
import hackathon.uhtudy.domain.study.persistence.Study;
import hackathon.uhtudy.domain.study.persistence.StudyRepository;
import hackathon.uhtudy.domain.study.web.request.StudySaveRequestDto;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import settings.ApiTest;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class StudyControllerTest extends ApiTest {

    @Autowired
    private StudyService studyService;

    @Autowired
    private StudyRepository studyRepository;


    @Nested
    @DisplayName("스터디 생성")
    public class CreateStudy {

        @Test
        @DisplayName("요청이 성공적으로 수행되어, 스터기 생성에 성공한다.")
        public void 성공() throws Exception {

            //given -- 조건
            final var list = IntStream.range(0, 9)
                    .mapToObj(i -> new CurriculumSaveRequestDto(
                            i,
                            "title",
                            "announce"))
                    .toList();

            final var request = new StudySaveRequestDto(
                    "title",
                    7,
                    "goal",
                    list);


            //when -- 동작
            final var response = RestAssured
                    .given()
                        .log().all()
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .body(request)
                    .when()
                        .post("/studies")
                    .then()
                        .log().all().extract();

            //then -- 검증
            Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
            Assertions.assertThat((String) response.jsonPath().get("attendCode")).isNotNull();
        }
    }


    @Nested
    @DisplayName("스터디 목록 조회")
    public class GetStudyList {

        @Test
        @DisplayName("참여한 스터디가 있을 경우, 목록에 보여야 한다.")
        public void 성공() throws Exception {

            //given -- 조건
            final String attendCode = getAttendCode();

            studyService.attendStudy(attendCode);


            //when -- 동작
            final var response = RestAssured
                    .given()
                        .log().all()
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .when()
                        .get("/studies")
                    .then()
                        .log().all().extract();

            //then -- 검증
            Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        }
    }


    @Nested
    @DisplayName("스터디 참석")
    public class AttendStudy {

        @Test
        @DisplayName("참여 코드가 유효하여 스터디 참석에 성공해야 한다.")
        public void 성공() throws Exception {

            //given -- 조건
            final String attendCode = getAttendCode();

            //when -- 동작
            final var response = RestAssured
                    .given()
                        .log().all()
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .when()
                        .post("/attendCode/{attendCode}", attendCode)
                    .then()
                        .log().all().extract();

            //then -- 검증
            Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        }
    }


    @Nested
    @DisplayName("스터디 상세 조회")
    public class GetStudy {

        @Test
        @DisplayName("스터디 아이디가 유효하여, 상세 조회에 성공해야 한다.")
        public void 성공() throws Exception {

            //given -- 조건
            final Study study = createStudy();

            //when -- 동작
            final var response = RestAssured
                    .given()
                        .log().all()
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .when()
                        .get("/studies/{studyId}", study.getId())
                    .then()
                        .log().all().extract();

            //then -- 검증
            Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        }
    }


    @Nested
    @DisplayName("참여 코드 조회")
    public class GetAttendCode {

        @Test
        @DisplayName("참여 코드가 유효하여 스터디 참여에 성공한다.")
        public void 성공() throws Exception {

            //given -- 조건
            final Study study = createStudy();

            //when -- 동작
            final var response = RestAssured
                    .given()
                        .log().all()
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .when()
                        .get("/attendCode/{studyId}", study.getId())
                    .then()
                        .log().all().extract();

            //then -- 검증
            Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        }
    }

    private Study createStudy() {
        return studyRepository.save(new Study(
                "title", 7, "goal"
        ));
    }

    private String getAttendCode() {
        return studyService.createStudy(new StudySaveRequestDto(
                "title",
                7,
                "goal",
                IntStream.range(0, 9)
                        .mapToObj(i -> new CurriculumSaveRequestDto(
                                i,
                                "title",
                                "announce"))
                        .toList()
        ));
    }
}