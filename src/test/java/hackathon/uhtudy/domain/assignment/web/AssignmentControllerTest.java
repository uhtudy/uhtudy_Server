package hackathon.uhtudy.domain.assignment.web;

import hackathon.uhtudy.domain.assignment.web.request.SubmitAssignmentRequest;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import settings.ApiTest;

import static org.junit.jupiter.api.Assertions.*;

class AssignmentControllerTest extends ApiTest {


    @Nested
    @DisplayName("코드 리뷰 조회")
    public class GetCodeReview {

        @Test
        @DisplayName("요청이 정상적으로 수행되어, 코드 리뷰 조회에 성공해야 한다.")
        public void 성공() throws Exception {

            //given -- 조건
            final Long assignmentId = 1L;

            //when
            final var response = RestAssured
                    .given()
                        .log().all()
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .when()
                        .get("/assignments/{assignmentId}", assignmentId)
                    .then()
                        .log().all().extract();


            //then
            Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        }
    }


    @Nested
    @DisplayName("과제 제출")
    public class SubmitAssignment {

        @Test
        @DisplayName("요청이 정상적으로 수행되어, 과제 제출에 성공해야 한다.")
        public void 성공() throws Exception {

            //given -- 조건
            final Long studyId = 1L;
            final int weekNum = 1;
            final var request = new SubmitAssignmentRequest(
                    "github.com",
                    true
            ) ;

            //when
            final var response = RestAssured
                    .given()
                        .log().all()
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .body(request)
                    .when()
                        .post("/assignments/studies/{studyId}/{weekNum}", studyId, weekNum)
                    .then()
                        .log().all().extract();

            //then
            Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        }
    }

}