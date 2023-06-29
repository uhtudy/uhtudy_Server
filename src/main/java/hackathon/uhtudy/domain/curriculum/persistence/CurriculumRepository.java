package hackathon.uhtudy.domain.curriculum.persistence;

import hackathon.uhtudy.domain.study.persistence.Study;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {

    Optional<Curriculum> findByStudyAndWeekNum(final Study study, final int WeekNum);
}
