import org.telegram.telegrambots.api.methods.send.SendMessage;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public interface Command {
    String getBotAnswer(Long id, GameQuiz game, Map<Long, GameQuiz> chatId);
    SendMessage trySendMsg(Long id, GameQuiz game);
    default List<SendMessage> run(Long id, GameQuiz game) {
        return Arrays.asList(
                //getMessage(getBotAnswer(id, game, ???)),
                trySendMsg(id, game)
        );
    }
}
