import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class HintCommandTest {

    @Test
    public void testGetHintWithoutPoint() throws FileException, IOException {
        HintCommand hintCommand = new HintCommand();
        GameQuiz gameQuiz = new GameQuiz("2");
        gameQuiz.question = new Question("Третья планета от Солнца :1.Меркурий; 2.Плутон; 3.Земля; 4.Марс","3");
        long id = 1234521;
        Map<Long, GameQuiz> chatId = new HashMap<>();
        chatId.put(id, gameQuiz);
        assertEquals("У тебя недостаточно очков", hintCommand.getBotAnswer(id, gameQuiz, chatId) );
    }

    @Test
    public void testGetHintWithPoint() throws FileException, IOException {
        HintCommand hintCommand = new HintCommand();
        GameQuiz gameQuiz = new GameQuiz("1");
        gameQuiz.question = new Question("Какое имя у Демидовича? :1.Игорь; 2.Вячеслав; 3.Борис; 4.Иван","3");
        gameQuiz.point = 500;
        long id = 34521;
        Map<Long, GameQuiz> chatId = new HashMap<>();
        chatId.put(id, gameQuiz);
        assertEquals("Вот твоя подсказка!", hintCommand.getBotAnswer(id, gameQuiz, chatId) );
    }

    @Test
    public void testTrySendMsg() throws IOException, FileException {
        HintCommand hintCommand = new HintCommand();
        GameQuiz gameQuiz = new GameQuiz("1");
        gameQuiz.question = new Question("Какое имя у Демидовича? :1.Игорь; 2.Вячеслав; 3.Борис; 4.Иван","3");
        long id = 1212;
        gameQuiz.canGetHint = false;
        assertEquals(gameQuiz.question.text, hintCommand.trySendMsg(id, gameQuiz).getText());
        assertEquals(4, gameQuiz.question.answers.length);
    }

    @Test
    public void testTrySendMsgWithHint() throws IOException, FileException {
        HintCommand hintCommand = new HintCommand();
        GameQuiz gameQuiz = new GameQuiz("2");
        gameQuiz.question = new Question("Третья планета от Солнца :1.Меркурий; 2.Плутон; 3.Земля; 4.Марс","3");
        long id = 123412;
        gameQuiz.canGetHint = true;
        assertEquals(gameQuiz.question.text, hintCommand.trySendMsg(id, gameQuiz).getText());
        assertEquals(2, HintCommand.hint.answers.length);
    }


}