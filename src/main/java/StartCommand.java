import org.telegram.telegrambots.api.methods.send.SendMessage;

import java.util.Map;

public class StartCommand implements Command {
    @Override
    public String getBotAnswer(Long id, GameQuiz game, Map<Long, GameQuiz> chatId) {
        chatId.remove(id, game);
        return "Привет! Давай сыграем!\nДля начала, выберите тематику вопросов:";
    }

    @Override
    public SendMessage trySendMsg(Long id, GameQuiz game) {
        { return CreateButtons.themeInline(id); }
    }
}
