package hackathon.uhtudy.domain.assignment.persistence;

import hackathon.uhtudy.domain.comment.persistence.Comment;
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
public class Assignment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer weekNum;
    private String assignment;

    private boolean absent; //과제 수행 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curriculum_id")
    private Curriculum curriculum;


    @OneToMany(mappedBy = "assignment")
    private List<Comment> comments = new ArrayList<>();


    public Assignment(final Integer weekNum, final String assignment, final Curriculum curriculum) {
        this.weekNum = weekNum;
        this.assignment = assignment;
        this.setCurriculum(curriculum);
        this.absent = false;
    }

    private void setCurriculum(final Curriculum curriculum) {
        this.curriculum = curriculum;
        curriculum.getAssignments().add(this);
    }


    //TODO : 수행여부 true 로직 작성하기

}
