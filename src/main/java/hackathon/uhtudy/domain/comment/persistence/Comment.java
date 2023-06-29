package hackathon.uhtudy.domain.comment.persistence;

import hackathon.uhtudy.domain.assignment.persistence.Assignment;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;


    public Comment(final String body, final Assignment assignment) {
        this.body = body;
        this.setAssignment(assignment);
    }


    private void setAssignment(final Assignment assignment) {
        this.assignment = assignment;
        assignment.getComments().add(this);
    }
}
