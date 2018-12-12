import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ParserHTMLTest {

    @Test
    public void parsePage() throws IOException {
        List<String> jokes = new ParserHTML("html.txt").parsePage();
        assertEquals(jokes.get(0), "Компьютер позволяет решать те проблемы, которые до его изобретения не существовали.");
    }

    @Test
    public void parser() throws IOException {
//        List<String> jokes = new ParserHTML("html.txt").getText(Arrays.asList(
//                "	<div class=\"text\" id=\"98884\">Компьютер позволяет решать те проблемы, " +
//                        "которые до его изобретения не существовали.</div>",
//                " 	<div class=\"text\" id=\"98734\">Человек, который ставит детям диагнозы на основе" +
//                        " информации из интернета - врач-википедиатр.</div>",
//                "<div class=\"text\" id=\"98678\">Будильник программиста всегда перед тем, как зазвонить," +
//                        " задает вопрос:<br>- А вы уверены в том, что хотите проснуться?</div>"));
        ParserHTML parser = new ParserHTML("html.txt");
        parser.parsePage();
        List<String> jokes = parser.getText(parser.lines1);
        assertEquals("Человек, который ставит детям диагнозы на основе информации из интернета - врач-википедиатр.", jokes.get(1));
    }

    @Test
    public void parser1() throws IOException {
        ParserHTML parser = new ParserHTML("html.txt");
        parser.parsePage();
        List<String> jokes = parser.removeSpaces(parser.lines2);
        assertEquals("Будильник программиста всегда перед тем, как зазвонить, задает вопрос:\n" +
                "- А вы уверены в том, что хотите проснуться?", jokes.get(2));
    }

}