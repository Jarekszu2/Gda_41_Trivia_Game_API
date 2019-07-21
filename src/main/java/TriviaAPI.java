import com.google.gson.Gson;
import model.api.TriviaResponse;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class TriviaAPI {
    private final static Gson GSON = new Gson();

    public TriviaResponse loadURLbyContent(String requestURL) {
        StringBuilder bilder = new StringBuilder();
        try {
            URL url = new URL(requestURL);

            // wczytujemy treść z URL'a
            try(BufferedReader reader = new BufferedReader( // czyta linia po linii co mu InputStreamReader załaduje
                    new InputStreamReader(  // pomaga czytać, bo buffered reader - pomaga obsłużyć dane
                            url.openStream()))) {   // otwiera żródło (stronę)

//                String liniaZeStrony;

                // przeczytaj linię (liniaZeStrony) i upewnij się, że źródło się nie skończyło (!= null)
//                while ((liniaZeStrony = reader.readLine()) != null) {
//                    bilder.append(liniaZeStrony);
//                }

                // albo to samo za pomocą stream'u - wtedy nie potrzebujemy String liniaZeStrony i można to zakomentować

                // to należy rozumieć:
                // załaduj całą treść strony do obiektu bilder
                // List<String> treść // treścią są kolejne linie, które są zapisywane do listy
                // i dalej zwykłe operacje na stream'ie // traktujemy rader.lines().forEach... jako treść.stream().forEach...
                reader.lines().forEach(bilder::append);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        TriviaResponse triviaResponse = GSON.fromJson(bilder.toString(), TriviaResponse.class);
//        return triviaResponse;

        //Uwaga
        // Jeżeli zrobimy wydruk triviaResponse i pojawią się dodatkowe znaki zaciągnięte skądś,
        // to można wydruk przeformatować za pomocą biblioteki Apache commons z Mavena,
        // trzeba wgrać odpowiednie dependencje do pom.xml i sformatować tekst.

        triviaResponse.getResults()
                .forEach(triviaQuestions ->
                        triviaQuestions.setQuestion(StringEscapeUtils.unescapeHtml4(triviaQuestions.getQuestion())));

        return triviaResponse;
    }

    public TriviaResponse loadURLbyInputStream(String requestURL) {
        TriviaResponse triviaResponse = null;
        try {

            URL url = new URL(requestURL);
            InputStream inputStream = url.openStream();
            triviaResponse = GSON.fromJson(new InputStreamReader(inputStream), TriviaResponse.class);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        triviaResponse.getResults()
                .forEach(triviaQuestions ->
                        triviaQuestions.setQuestion(StringEscapeUtils.unescapeHtml4(triviaQuestions.getQuestion())));

        return triviaResponse;
    }
}
