package com.example.Intenship_Project.Service;

import com.example.Intenship_Project.Dto.ScoreRequest;
import com.example.Intenship_Project.Dto.ScoreResponse;
import com.example.Intenship_Project.Model.Score;
import com.example.Intenship_Project.Model.User;
import com.example.Intenship_Project.Repository.ScoreRepository;
import com.example.Intenship_Project.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScoreService {

    private final ScoreRepository scoreRepository;
    private final UserRepository userRepository;

    public ScoreResponse addScore(String email, ScoreRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        if (scoreRepository.existsByUserAndScoreDate(user, request.getScoreDate())) {
            throw new RuntimeException("A Score Already Exists For This Date");
        }
        if (scoreRepository.countByUser(user) >= 5){
            Score oldest = scoreRepository.findFirstByUserOrderByScoreDateAsc(user)
                    .orElseThrow(()-> new RuntimeException("Could not find oldest score"));
            scoreRepository.delete(oldest);
        }

        Score score = new Score();
        score.setUser(user);
        score.setScore(request.getScore());
        score.setScoreDate(request.getScoreDate());
        scoreRepository.save(score);

        return new ScoreResponse(score.getId(),score.getScore(),score.getScoreDate());
    }

    public List<ScoreResponse> getScores(String email){

        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("User Not Found"));

        return scoreRepository.findByUserOrderByScoreDateDesc(user)
                .stream()
                .map(s-> new ScoreResponse(s.getId(),s.getScore(),s.getScoreDate()))
                .collect(Collectors.toList());
    }

    public ScoreResponse editScore(String email, Long scoreId, ScoreRequest request){

        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("User Not Found"));

        Score score = scoreRepository.findByIdAndUser(scoreId,user)
                .orElseThrow(()-> new RuntimeException("Score Not Found"));

        if (!score.getScoreDate().equals(request.getScoreDate())){
            if (scoreRepository.existsByUserAndScoreDate(user,request.getScoreDate())){
                throw new RuntimeException("A Score Already Exists For This Date");
            }
        }
        score.setScore(request.getScore());
        score.setScoreDate(request.getScoreDate());
        scoreRepository.save(score);

        return new ScoreResponse(score.getId(), score.getScore(), score.getScoreDate());
    }

    public  void deleteScore(String email, Long scoreId){

        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("User Not Found"));

        Score score = scoreRepository.findByIdAndUser(scoreId,user)
                .orElseThrow(()-> new RuntimeException("Score Not Found"));

        scoreRepository.delete(score);
    }
}
