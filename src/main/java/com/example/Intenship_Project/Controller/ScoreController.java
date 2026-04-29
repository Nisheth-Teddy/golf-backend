package com.example.Intenship_Project.Controller;

import com.example.Intenship_Project.Dto.ScoreRequest;
import com.example.Intenship_Project.Dto.ScoreResponse;
import com.example.Intenship_Project.Service.ScoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scores")
@RequiredArgsConstructor
public class ScoreController {

    private final ScoreService scoreService;

    @PostMapping
    public ResponseEntity<ScoreResponse> addScore(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody ScoreRequest request){
        return ResponseEntity.ok(
                scoreService.addScore(userDetails.getUsername(),request));
    }

    @GetMapping
    public ResponseEntity<List<ScoreResponse>> getScore(
            @AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(
                scoreService.getScores(userDetails.getUsername()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScoreResponse> editScore(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long id,
            @Valid @RequestBody ScoreRequest request){
        return ResponseEntity.ok(
                scoreService.editScore(userDetails.getUsername(), id,request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletescore(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long id){
        scoreService.deleteScore(userDetails.getUsername(),id);
        return ResponseEntity.ok("Score Delete Successfully");
    }


}
