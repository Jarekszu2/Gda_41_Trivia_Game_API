package model.api;

import lombok.Data;

import java.util.List;

@Data
public class TriviaResponse {
    private int response_code;
    private List<TriviaQuestions> results;
}
