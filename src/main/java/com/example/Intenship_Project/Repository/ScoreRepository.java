package com.example.Intenship_Project.Repository;

import com.example.Intenship_Project.Model.Score;
import com.example.Intenship_Project.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {

    List<Score> findByUserOrderByScoreDateDesc(User user);

    // to count the score
    long countByUser(User user);

    // to find the oldest score
    Optional<Score> findFirstByUserOrderByScoreDateAsc(User user);

    // to check exists for the same date
    boolean existsByUserAndScoreDate(User user, LocalDate scoreDate);

    // to find score by id and user
    Optional<Score> findByIdAndUser(Long id, User user);
}
