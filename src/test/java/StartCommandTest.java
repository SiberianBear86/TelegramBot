import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class StartCommandTest {

    @Test
    public void testGetBotAnswer() throws IOException, FileException {
        StartCommand startCommand = new StartCommand();
        GameQuiz gameQuiz = new GameQuiz("1");
        long id = 44034;
        Map<Long, GameQuiz> chatId = new HashMap<>();
        chatId.put(id, gameQuiz);
        assertEquals("Привет! Давай сыграем!\n" +
                "Для начала, выберите тематику вопросов:", startCommand.getBotAnswer(id, gameQuiz, chatId));
        assertTrue(chatId.isEmpty());
    }

    @Test
    public void testTrySendMsg() throws IOException, FileException {
        StartCommand startCommand = new StartCommand();
        GameQuiz gameQuiz = new GameQuiz("2");
        long id = 44034;
        assertEquals("Матмех или География", startCommand.trySendMsg(id, gameQuiz).getText());
        assertEquals(Long.toString(id), startCommand.trySendMsg(id, gameQuiz).getChatId());
    }
}