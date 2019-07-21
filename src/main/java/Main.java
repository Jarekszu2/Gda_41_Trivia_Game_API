import model.QuizParameters;
import model.api.TriviaResponse;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        APITriviaURLBuilder bilder = new APITriviaURLBuilder();
        QuizParameters quizParameters = new QuizParameters();
        ScannerContentLoader scannerContentLoader = new ScannerContentLoader();

        scannerContentLoader.loadAmount(quizParameters);
        scannerContentLoader.loadCategory(quizParameters);
        scannerContentLoader.loadDifficulty(quizParameters);
        scannerContentLoader.loadType(quizParameters);

        System.out.println(quizParameters);

        bilder.loadParameters(quizParameters);

        String requestURL = bilder.compileURL();

        System.out.println(requestURL);

        TriviaAPI triviaAPI = new TriviaAPI();

//        TriviaResponse triviaResponse = triviaAPI.loadURLbyContent(requestURL);
        TriviaResponse triviaResponse = triviaAPI.loadURLbyInputStream(requestURL);

//        System.out.println(triviaResponse);

        triviaResponse.getResults().stream()
                .forEach(triviaQuestions -> System.out.println(triviaQuestions.getQuestion() + " " + triviaQuestions.getCorrect_answer()));
    }
}
