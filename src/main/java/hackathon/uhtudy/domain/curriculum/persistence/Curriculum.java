package hackathon.uhtudy.domain.curriculum.persistence;

import hackathon.uhtudy.domain.assignment.persistence.Assignment;
import hackathon.uhtudy.domain.study.persistence.Study;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Curriculum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer weekNum;
    private String title;
    private boolean absent; //해당 주차의 스터디 참석여부
    private String announcement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;

    @OneToMany(mappedBy = "curriculum")
    private List<Assignment> assignments = new ArrayList<>();

    public Curriculum(
            final Integer weekNum,
            final String title,
            final Study study,
            final String announcement) {

        this.weekNum = weekNum;
        this.title = title;
        this.announcement = announcement;
        this.setStudy(study);

        this.absent = false;
    }


    private void setStudy(final Study study) {
        this.study = study;
        study.getCurriculums().add(this);
    }


    @Override
    public String toString() {
        return "\t assignments : " + assignments;
    }
}
