package hackathon.uhtudy.domain.study.web.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.*;

@Getter
@AllArgsConstructor
public class StudyMainResponseDto {
    @Getter
    @AllArgsConstructor
    public static class StudyListResponseDto{
        List<StudyOverviewDto> overviewDtoList;
    }

    @Getter
    @AllArgsConstructor
    public static class StudyOverviewDto{
        private Long studyId;
        private String title;
        private String subject;
        private String place;
    }
}
