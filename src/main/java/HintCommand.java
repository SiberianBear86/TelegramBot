import org.telegram.telegrambots.api.methods.send.SendMessage;

import java.util.Map;

public class HintCommand implements Command {
    static Question hint;
    @Override
    public String getBotAnswer(Long id, GameQuiz game, Map<Long, GameQuiz> chatId) {
        game.correctAnswer("hint");
        if (!game.canGetHint)
            return "У тебя недостаточно очков";
        return "Вот твоя подсказка!";
    }

    @Override
    public SendMessage trySendMsg(Long id, GameQuiz game) {
        if (!game.canGetHint)
            return CreateButtons.questInline(id, game.question);
        hint = new Hint(game.question).getHint();
        return CreateButtons.questInline(id, hint);
    }
}
