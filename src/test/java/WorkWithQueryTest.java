import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class WorkWithQueryTest {

    @Test
    public void testGetQuest() throws IOException, FileException {
        GameQuiz gameQuiz = new GameQuiz("1");
        long id = 1234;
        gameQuiz.questList = Arrays.asList(new Question("На каком языке написана эта великолепная программа? :" +
                "1.C#; 2.java; 3.эльфийский; 4.Python", "2"), new Question("Какое имя у Демидовича? " +
                ":1.Игорь; 2.Вячеслав; 3.Борис; 4.Иван", "3"));
        gameQuiz.question = gameQuiz.questList.get(1);
        gameQuiz.amountQuest = 2;
        Map<Long, GameQuiz> chatId = new HashMap<>();
        chatId.put(id, gameQuiz);
        WorkWithQuery.workWithQuest("3", id, chatId);
        assertEquals(gameQuiz.question.text, WorkWithQuery.newQuest.getText());
        assertNotNull(WorkWithQuery.botAnswer);
    }
    @Test
    public void testLoseGame() throws IOException, FileException {
        GameQuiz gameQuiz = new GameQuiz("2");
        long id = 13434;
        Map<Long, GameQuiz> chatId = new HashMap<>();
        chatId.put(id, gameQuiz);
        gameQuiz.point = -500;
        WorkWithQuery.workWithQuest("2", id, chatId);
        assertEquals("Увы, но ты проиграл", WorkWithQuery.gameOver);
    }
    @Test
    public void testWinGame() throws IOException, FileException {
        GameQuiz gameQuiz = new GameQuiz("1");
        long id = 13554;
        Map<Long, GameQuiz> chatId = new HashMap<>();
        chatId.put(id, gameQuiz);
        gameQuiz.amountQuest = 0;
        WorkWithQuery.workWithQuest("2", id, chatId);
        assertEquals("Молодец, ты победил!", WorkWithQuery.gameOver);
    }
}