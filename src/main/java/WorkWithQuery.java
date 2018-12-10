import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.Map;

class WorkWithQuery {
    static void workWithQuest(CallbackQuery callback, Long id, Map<Long, GameQuiz> chatId, Bot bot){
        GameQuiz game = chatId.get(id);
        if (game.question != null) {
            game.correctAnswer(callback.getData());
            bot.sendMsg(id, game.getBotAnswer());
        }
        if (game.getAmountQuest() != 0 && game.getPoint() >= 0) {
            game.question = game.getQuest();
            try {
                bot.execute(CreateButtons.questInline(id, game.question));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else {
            String answer;
            if (game.getPoint() < 0)
                answer = ", увы, но ты проиграл\n";
            else
                answer = ", молодец, ты победил!\n";
            bot.sendMsg(id, callback.getMessage().getChat().getFirstName() + answer);
            chatId.remove(id, game);
        }
    }
}