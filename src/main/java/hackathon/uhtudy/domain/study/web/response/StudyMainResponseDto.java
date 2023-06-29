package hackathon.uhtudy.domain.study.web.response;

import hackathon.uhtudy.domain.study.persistence.Study;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.*;

@Getter
@AllArgsConstructor
public class StudyMainResponseDto {
//    @Getter
//    @AllArgsConstructor
//    public static class StudyListResponseDto{
//        List<StudyOverviewDto> overviewDtoList;
//    }
//
//    @Getter
//    @AllArgsConstructor
//    public static class StudyOverviewDto{
//        private Long studyId;
//        private String title;
//        private String subject;
//        private String place;
//    }

    private Long studyId;
    private String title;
    private String subject;
    private String place;


    public StudyMainResponseDto(Study study) {

        this.studyId = study.getId();
        this.title = study.getTitle();
        this.subject = study.getGoal();
        this.place = "신촌 사람인카페";
    }
}
