package hackathon.uhtudy.domain.curriculum.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {
    Curriculum save(Curriculum curriculum);
}
