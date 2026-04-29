package com.example.Intenship_Project.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ScoreResponse {

    private Long id;
    private Integer score;
    private LocalDate scoreDate;
}
