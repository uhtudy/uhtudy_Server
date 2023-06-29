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

    private String title;
    private boolean absent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;

    @OneToMany(mappedBy = "curriculum")
    private List<Assignment> assignments = new ArrayList<>();

    public Curriculum(final String title, final boolean absent, final Study study) {
        this.title = title;
        this.absent = absent;
        this.setStudy(study);
    }


    private void setStudy(final Study study) {
        this.study = study;
        study.getCurriculums().add(this);
    }
}
