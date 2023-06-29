package hackathon.uhtudy.domain.study.persistence;

import hackathon.uhtudy.domain.curriculum.persistence.Curriculum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    private String attendCode;
    private String place;
    private boolean isAttend;

    @OneToMany(mappedBy = "study")
    private List<Curriculum> curriculums = new ArrayList<>();


    public Study(
            final String title,
            final Integer people,
            final String goal,
            final String place,
            final boolean isAttend) {

        this.title = title;
        this.people = people;
        this.goal = goal;
        this.place = place;
        this.isAttend = isAttend;

        this.attendCode = UUID.randomUUID().toString();
    }
}
