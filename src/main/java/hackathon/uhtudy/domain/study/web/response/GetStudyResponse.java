package hackathon.uhtudy.domain.study.web.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
public class GetStudyResponse {

    String title;
    String goal;
    String announcement;
    int people;
    List<AssignmentDto> assignmentDtos;


    public GetStudyResponse(String title, String goal, String announcement, int people, List<AssignmentDto> assignmentDtos) {
        this.title = title;
        this.goal = goal;
        this.announcement = announcement;
        this.people = people;
        this.assignmentDtos = assignmentDtos;
    }

    @Getter
    @RequiredArgsConstructor
    public static class AssignmentDto {

        final int weekNum;
        final String title;
    }
}
