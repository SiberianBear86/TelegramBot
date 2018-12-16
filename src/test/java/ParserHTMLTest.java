import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ParserHTMLTest {

    @Test
    public void testParsePage() throws IOException {
        List<String> jokes = new ParserHTML("html.txt").parsePage();
        assertEquals("\t<div class=\"text\" id=\"98884\">Компьютер позволяет решать те проблемы, " +
                        "которые до его изобретения не существовали.</div>", jokes.get(0));
    }

    @Test
    public void testRemoveTags() throws IOException {
        ParserHTML parser = new ParserHTML("html.txt");
        List<String> jokes = parser.getText(parser.parsePage());
        assertEquals("Человек, который ставит детям диагнозы на основе информации из интернета - врач-википедиатр.", jokes.get(1));
    }

    @Test
    public void testRemoveSpace() throws IOException {
        ParserHTML parser = new ParserHTML("html.txt");
        List<String> jokes = parser.removeSpaces(parser.getText(parser.parsePage()));
        assertEquals("Будильник программиста всегда перед тем, как зазвонить, задает вопрос:\n" +
                "- А вы уверены в том, что хотите проснуться?", jokes.get(2));
    }
}