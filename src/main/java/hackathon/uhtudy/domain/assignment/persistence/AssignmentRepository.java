package hackathon.uhtudy.domain.assignment.persistence;

import hackathon.uhtudy.domain.study.persistence.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {


    @Query(value = "select a from Assignment a" +
            " join fetch a.curriculum c" +
            " join fetch c.study s" +
            " where s =:study"
            , countQuery = "select count(a) from Assignment a")
    List<Assignment> findAssignmentsByStudy(@Param("study")final Study study);
}
