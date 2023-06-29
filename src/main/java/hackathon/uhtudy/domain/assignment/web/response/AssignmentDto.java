package hackathon.uhtudy.domain.assignment.web.response;

import hackathon.uhtudy.domain.assignment.persistence.Assignment;
import hackathon.uhtudy.domain.comment.web.response.CommentDto;

import java.util.List;

public class AssignmentDto {
    final String assignment;
    final List<CommentDto> comments;

    public AssignmentDto(final Assignment assignment) {
        this.assignment = assignment.getAssignment();
        this.comments = assignment.getComments()
                .stream().map(comment -> new CommentDto(comment.getBody()))
                .toList();
    }
}
