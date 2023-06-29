package hackathon.uhtudy.domain.study.web;

import hackathon.uhtudy.domain.study.application.StudyService;
import hackathon.uhtudy.domain.study.web.request.StudySaveRequestDto;
import hackathon.uhtudy.domain.study.web.response.StudyCodeResponseDto;
import hackathon.uhtudy.domain.study.web.response.StudyMainResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class StudyController {

    @Autowired
    private StudyService studyService;

    @PostMapping("/studies")
    private StudyCodeResponseDto createStudy(@RequestBody StudySaveRequestDto requestDto){
        return new StudyCodeResponseDto(studyService.createStudy(requestDto));
    }
    @GetMapping("/studies")
    private StudyMainResponseDto.StudyListResponseDto getStudyList(){
        return studyService.getStudyList();
    }
}
