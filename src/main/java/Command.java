import org.telegram.telegrambots.api.methods.send.SendMessage;
import java.util.Map;

public interface Command {
    String getBotAnswer(Long id, GameQuiz game, Map<Long, GameQuiz> chatId);
    SendMessage trySendMsg(Long id, GameQuiz game);
}
