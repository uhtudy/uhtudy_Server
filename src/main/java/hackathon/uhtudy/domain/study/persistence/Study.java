package hackathon.uhtudy.domain.study.persistence;

import hackathon.uhtudy.domain.curriculum.persistence.Curriculum;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Study {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Integer people;
    private String goal;
    private String announcement;
    private String attendCode;
    private String place;
    private boolean isAttend;

    //TODO : 커리큘럼 추가
    @OneToMany(mappedBy = "study")
    private List<Curriculum> curriculums = new ArrayList<>();
}
