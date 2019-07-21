package model;

import lombok.Data;

@Data
public class QuizParameters {
    private QuizCategory category;
    private QuizDifficulty difficulty;
    private QuizType quizType;
    private Integer amountOfQuestions;
}
