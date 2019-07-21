import model.QuizCategory;
import model.QuizDifficulty;
import model.QuizParameters;
import model.QuizType;

public class APITriviaURLBuilder {
    private static final String BASE_URL = "https://opentdb.com/api.php?amount={amount}";

    private final StringBuilder builder;

    /**
     * Tworzę builder który jako początkowy URL ma link z góry.
     */
    public APITriviaURLBuilder() {
        builder = new StringBuilder(BASE_URL);
    }

    private void appendNumberOfQuestions(int count) {
        if (builder.toString().contains("{amount}")) {
            int pozycjaAmount = builder.indexOf("{amount}");
            builder.replace(pozycjaAmount, pozycjaAmount + 8, String.valueOf(count));
        }
    }

    private void appendCategory(QuizCategory quizCategory) {
        // quizCategory.getId() == -1 to wartość ANY
        if (!builder.toString().contains("&cateogry=") && quizCategory.getId() != -1) {
            builder.append("&category=");
            builder.append(quizCategory.getId());
        } else if (builder.toString().contains("&cateogry=")) {
            System.err.println("Kategoria została już dopisana.");
        }
    }

    private void appendDifficulty(QuizDifficulty quizDifficulty) {
        // quizDifficulty.getId() == -1 to wartość ANY
        if (!builder.toString().contains("&difficulty=") && quizDifficulty != QuizDifficulty.ANY) {
            builder.append("&difficulty=");
            builder.append(quizDifficulty.toString().toLowerCase());
        } else if (builder.toString().contains("&difficulty=")) {
            System.err.println("Poziom trudności został już ustawiony.");
        }
    }

    private void appendType(QuizType quizType) {
        // quizType.getId() == -1 to wartość ANY
        if (!builder.toString().contains("&type=") && quizType != QuizType.ANY) {
            builder.append("&type=");
            builder.append(quizType.toString().toLowerCase());
        } else if (builder.toString().contains("&type=")) {
            System.err.println("Rodzaj pytań został już określony.");
        }
    }

    public String compileURL() {
        return builder.toString();
    }

    public void loadParameters(QuizParameters parameters) {
        appendNumberOfQuestions(parameters.getAmountOfQuestions());
        appendCategory(parameters.getCategory());
        appendDifficulty(parameters.getDifficulty());
        appendType(parameters.getQuizType());
    }

    @Override
    public String toString() {
        return "APITriviaURLBuilder{" +
                "builder=" + builder +
                '}';
    }
}
