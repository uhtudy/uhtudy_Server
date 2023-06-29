package hackathon.uhtudy.domain.study.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

public interface StudyRepository extends JpaRepository<Study, Long> {
    @Query("select Study from Study s where s.isMyStudy = true")
    Optional<List<Study>> getStudiesByIsMyStudy();

    Optional<Study> findByAttendCode(String attendCode);
}
