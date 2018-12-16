import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class GameQuizTest {

    @org.junit.Test
    public void testGetQuestAtZeroAmount() throws FileException, IOException {
        GameQuiz gameQuiz = new GameQuiz("1");
        gameQuiz.amountQuest = 0;
        assertNull(gameQuiz.getQuest());
    }

    @org.junit.Test
    public void testGetQuestAtHintLine() throws FileException, IOException {
        GameQuiz gameQuiz = new GameQuiz("1");
        gameQuiz.lastAns = "hint";
        gameQuiz.lastQuest = new Question("Третья планета от Солнца :1.Меркурий; 2.Плутон; 3.Земля; 4.Марс", "3");
        assertEquals(gameQuiz.lastQuest, gameQuiz.getQuest());
    }

    @org.junit.Test
    public void testGetQuestAtNotLine() throws FileException, IOException {
        GameQuiz gameQuiz = new GameQuiz("1");
        gameQuiz.questList = Collections.singletonList(new Question("Самый мелкий океан? :" +
                "1.Тихий; 2.Атлантический; 3.Индийский; 4.Северный ледовитый", "4"));
        gameQuiz.amountQuest = 1;
        assertEquals(gameQuiz.questList.get(0), gameQuiz.getQuest());
    }

    @org.junit.Test
    public void testCorrectAnswerAtRightAnswer() throws FileException, IOException {
        GameQuiz gameQuiz = new GameQuiz("2");
        gameQuiz.question = new Question("Третья планета от Солнца :1.Меркурий; 2.Плутон; 3.Земля; 4.Марс","3");
        gameQuiz.questList = Arrays.asList(gameQuiz.question);
        gameQuiz.amountQuest = 1;
        gameQuiz.correctAnswer("3");
        assertEquals(100, gameQuiz.point);
    }

    @org.junit.Test
    public void testCorrectAnswerAtFailAnswer() throws FileException, IOException {
        GameQuiz gameQuiz = new GameQuiz("1");
        gameQuiz.question = new Question("Какое имя у Демидовича? :1.Игорь; 2.Вячеслав; 3.Борис; 4.Иван","3");
        gameQuiz.questList = Arrays.asList(gameQuiz.question);
        gameQuiz.amountQuest = 1;
        gameQuiz.correctAnswer("1");
        assertEquals(-300, gameQuiz.point);
    }

    @org.junit.Test
    public void testCorrectAnswerAtJokeLine() throws FileException, IOException {
        GameQuiz gameQuiz = new GameQuiz("1");
        gameQuiz.question = new Question("Какое имя у Демидовича? :1.Игорь; 2.Вячеслав; 3.Борис; 4.Иван","3");
        gameQuiz.jokes = Arrays.asList("У шефа ВКонтакте статус: \"Заболел\" и на кнопку \"Мне нравится\" нажало уже 160 человек.");
        gameQuiz.amountJoke = 1;
        gameQuiz.correctAnswer("hint");
        assertEquals(gameQuiz.question, gameQuiz.lastQuest);
    }

    @org.junit.Test
    public void testCorrectAnswerAtHintLine() throws FileException, IOException {
        GameQuiz gameQuiz = new GameQuiz("1");
        gameQuiz.question = new Question("Третья планета от Солнца :1.Меркурий; 2.Плутон; 3.Земля; 4.Марс","3");
        gameQuiz.correctAnswer("hint");
        assertEquals(gameQuiz.question, gameQuiz.lastQuest);
        assertFalse(gameQuiz.canGetHint);
    }
}