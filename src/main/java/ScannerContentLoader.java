import model.QuizCategory;
import model.QuizDifficulty;
import model.QuizParameters;
import model.QuizType;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class ScannerContentLoader {
    Scanner scanner = new Scanner(System.in);

    public void loadAmount(QuizParameters quizParameters) {
        do {
            try {
                System.out.println("Podaj liczbę pytań:");
                String line = scanner.nextLine();
                int questionsCount = Integer.parseInt(line);
                if (questionsCount < 1 || questionsCount > 50) {
                    System.out.println("Ilość pytań musi być > 0, oraz mniejsza od 50.");
                    continue;
                }

                // ustawiono
                quizParameters.setAmountOfQuestions(questionsCount);
            } catch (NumberFormatException nfe) {
                System.err.println("Niepoprawna liczba!");
            }
        } while (quizParameters.getAmountOfQuestions() == null);
    }

    private QuizCategory sprawdzCzyIdZnajdujeSieWEnum(int categoryId) {
        return Arrays.asList(QuizCategory.values()).stream()
                .filter(quizCategory -> quizCategory.getId() == categoryId)
                .findFirst()
                .orElse(null);
    }

    public void loadCategory(QuizParameters quizParameters) {
        do {
            try {
                System.out.println("Podaj kategorię [wpisz identyfikator]:");
                Arrays.asList(QuizCategory.values()).stream()
                        .sorted(Comparator.comparingInt(value -> value.getId()))
//                        .sorted(Comparator.reverseOrder())
                        .forEach(quizCategory -> System.out.println(quizCategory.getId() + " -> " + quizCategory.getName()));

                String line = scanner.nextLine();
                int categoryId = Integer.parseInt(line);

                // ustawiono
                QuizCategory categoryFromScanner = sprawdzCzyIdZnajdujeSieWEnum(categoryId);
                quizParameters.setCategory(categoryFromScanner);
            } catch (NumberFormatException nfe) {
                System.err.println("Niepoprawna liczba!");
            }
        } while (quizParameters.getCategory() == null);
    }

    private QuizDifficulty sprawdzCzyPoprawneDifficulty(String difficulty) throws IllegalArgumentException {
        return QuizDifficulty.valueOf(difficulty.toUpperCase());
    }

    public void loadDifficulty(QuizParameters quizParameters) {
        do {
            try {
                System.out.println("Podaj poziom trudności [wpisz nazwę]:");
                Arrays.asList(QuizDifficulty.values()).stream()
                        .forEach(System.out::println);

                String line = scanner.nextLine();

                // ustawiono
                QuizDifficulty difficultyFromScanner = sprawdzCzyPoprawneDifficulty(line);
                quizParameters.setDifficulty(difficultyFromScanner);
            } catch (IllegalArgumentException iae) {
                System.err.println("Niepoprawne wejście!");
            }
        } while (quizParameters.getDifficulty() == null);
    }

    public void loadType(QuizParameters quizParameters) {
        do {
            try {
                System.out.println("Podaj typ pytania [wpisz nazwę]:");
                Arrays.asList(QuizType.values()).stream()
                        .forEach(System.out::println);

                String line = scanner.nextLine();

                // ustawiono
                quizParameters.setQuizType(QuizType.valueOf(line.toUpperCase()));
            } catch (IllegalArgumentException iae) {
                System.err.println("Niepoprawne wejście!");
            }
        } while (quizParameters.getQuizType() == null);
    }
}
