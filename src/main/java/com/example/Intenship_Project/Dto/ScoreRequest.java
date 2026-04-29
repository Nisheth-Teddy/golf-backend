package com.example.Intenship_Project.Dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ScoreRequest {

    @NotNull(message = "Score is required")
    @Min(value = 1, message = "Score must be at least 1")
    @Max(value = 45, message = "Score must not exceed 45")
    private Integer score;

    @NotNull(message = "Date is required")
    private LocalDate scoreDate;
}
