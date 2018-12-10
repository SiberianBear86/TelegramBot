import org.telegram.telegrambots.api.methods.send.SendMessage;

import java.util.Map;

public class HintCommand implements Command {
    @Override
    public String getBotAnswer(Long id, GameQuiz game, Map<Long, GameQuiz> chatId) {
        game.correctAnswer("hint");
        if (game.getPoint() < 300)
            return "У тебя недостаточно очков";
        return null;
    }

    @Override
    public SendMessage trySendMsg(Long id, GameQuiz game) {
        return CreateButtons.questInline(id, !game.canGetHint ? game.question : new Hint(game.question).getHint());
    }
}
