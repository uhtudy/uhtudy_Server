package hackathon.uhtudy.domain.study.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, Long> {
    Study save(Study study);
}
