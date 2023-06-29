package hackathon.uhtudy.domain.comment.persistence;

import hackathon.uhtudy.domain.assignment.persistence.Assignment;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;
}
