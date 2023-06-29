package hackathon.uhtudy.domain.curriculum.web.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CurriculumSaveRequestDto {
    private Integer weekNum;
    private String title;
    private String announcement;
}
