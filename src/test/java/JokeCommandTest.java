import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class JokeCommandTest {

    @Test
    public void testGetJoke() throws IOException, FileException {
        JokeCommand jokeCommand = new JokeCommand();
        GameQuiz gameQuiz = new GameQuiz("1");
        gameQuiz.jokes = Arrays.asList("Отличие плохого сисадмина от хорошего в том," +
                " что плохой не появляется на работе, а хорошему нет нужды появляться на работе.");
        gameQuiz.question = new Question("Третья планета от Солнца :1.Меркурий; 2.Плутон; 3.Земля; 4.Марс","3");
        gameQuiz.amountJoke = 1;
        long id = 12321;
        Map<Long, GameQuiz> chatId = new HashMap<>();
        chatId.put(id, gameQuiz);
        assertEquals(gameQuiz.jokes.get(0), jokeCommand.getBotAnswer(id, gameQuiz, chatId));
    }

    @Test
    public void testJokeEnds() throws IOException, FileException {
        JokeCommand jokeCommand = new JokeCommand();
        GameQuiz gameQuiz = new GameQuiz("1");
        gameQuiz.question = new Question("Третья планета от Солнца :1.Меркурий; 2.Плутон; 3.Земля; 4.Марс","3");
        gameQuiz.amountJoke = 0;
        long id = 12321;
        Map<Long, GameQuiz> chatId = new HashMap<>();
        chatId.put(id, gameQuiz);
        assertEquals("Анекдоты кончились", jokeCommand.getBotAnswer(id, gameQuiz, chatId));
    }

    @Test
    public void testTrySendMsg() throws IOException, FileException {
        JokeCommand jokeCommand = new JokeCommand();
        GameQuiz gameQuiz = new GameQuiz("1");
        gameQuiz.question = new Question("Третья планета от Солнца :1.Меркурий; 2.Плутон; 3.Земля; 4.Марс","3");
        long id = 12321;
        assertEquals(gameQuiz.question.text, jokeCommand.trySendMsg(id, gameQuiz).getText());
        assertEquals(Long.toString(id), jokeCommand.trySendMsg(id, gameQuiz).getChatId());
    }
}