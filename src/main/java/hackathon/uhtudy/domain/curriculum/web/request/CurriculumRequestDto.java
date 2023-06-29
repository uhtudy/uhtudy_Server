package hackathon.uhtudy.domain.curriculum.web.request;

import hackathon.uhtudy.domain.study.persistence.Study;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CurriculumRequestDto {
    private Integer weekNum;
    private String title;
    private Study study;
    private String announcement;
}
