package hackathon.uhtudy.domain.study.persistence;

import hackathon.uhtudy.domain.curriculum.persistence.Curriculum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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


    public Study(
            final String title,
            final Integer people,
            final String goal,
            final String announcement,
            final String attendCode,
            final String place,
            final boolean isAttend) {

        this.title = title;
        this.people = people;
        this.goal = goal;
        this.announcement = announcement;
        this.attendCode = attendCode;
        this.place = place;
        this.isAttend = isAttend;
    }
}
