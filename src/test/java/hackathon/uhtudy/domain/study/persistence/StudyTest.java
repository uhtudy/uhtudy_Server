package hackathon.uhtudy.domain.study.persistence;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudyTest {

    @Nested
    @DisplayName("스터디 참석")
    public class UpdateIsMyStudy {

        @Test
        @DisplayName("요청이 정상적으로 수행되어, 스터디에 참석되어야 한다.")
        public void 성공() throws Exception {

            //given -- 조건
            final String title = "title";
            final Integer people = 7;
            final String goal = "goal";

            final Study study = new Study(title, people, goal);

            //when -- 동작
            study.update();

            //then -- 검증
            Assertions.assertThat(study.getIsMyStudy()).isTrue();
        }
    }
}