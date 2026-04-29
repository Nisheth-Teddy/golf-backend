package com.example.Intenship_Project.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "scores", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id","score_date"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Integer score;

    @Column(name = "score_date", nullable = false)
    private LocalDate scoreDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
