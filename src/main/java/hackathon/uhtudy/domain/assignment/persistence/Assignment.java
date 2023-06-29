package hackathon.uhtudy.domain.assignment.persistence;

import hackathon.uhtudy.domain.comment.persistence.Comment;
import hackathon.uhtudy.domain.curriculum.persistence.Curriculum;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Assignment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer weekNum;
    private String assignment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curriculum_id")
    private Curriculum curriculum;


    @OneToMany(mappedBy = "assignment")
    private List<Comment> comments = new ArrayList<>();
}
