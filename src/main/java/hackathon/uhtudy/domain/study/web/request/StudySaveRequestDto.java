package hackathon.uhtudy.domain.study.web.request;

import hackathon.uhtudy.domain.curriculum.persistence.Curriculum;
import hackathon.uhtudy.domain.curriculum.web.request.CurriculumSaveRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.*;

@Getter
@AllArgsConstructor
public class StudySaveRequestDto {
    private String title;
    private Integer people;
    private String goal;
    List<CurriculumSaveRequestDto> curriculumList = new ArrayList<>();
}
