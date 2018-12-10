import org.telegram.telegrambots.api.methods.send.SendMessage;

import java.util.Map;

public class JokeCommand implements Command {
    @Override
    public String getBotAnswer(Long id, GameQuiz game, Map<Long, GameQuiz> chatId) {
        game.correctAnswer("joke");
        if (game.getAmountJoke() != 0)
            return game.getJoke();
        return "Анекдоты кончились";
    }

    @Override
    public SendMessage trySendMsg(Long id, GameQuiz game) {
        return CreateButtons.questInline(id, game.question);
    }
}
