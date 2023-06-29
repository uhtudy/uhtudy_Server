package hackathon.uhtudy.domain.curriculum.persistence;

import hackathon.uhtudy.domain.assignment.persistence.Assignment;
import hackathon.uhtudy.domain.study.persistence.Study;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Curriculum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private boolean absent;

    @OneToMany(mappedBy = "curriculum")
    private List<Assignment> assignments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;
}
