import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.exceptions.TelegramApiException;

class WorkWithQuery {
    static void workWithQuest(CallbackQuery callback, Bot bot){
        Message message1 = callback.getMessage();
        Long id = message1.getChatId();
        Bot.chatId.computeIfAbsent(id, k -> {
            try {
                return new GameQuiz(callback.getData());
            } catch (FileException e) {
                e.printStackTrace();
            }return null;
        });
        GameQuiz game = Bot.chatId.get(id);
        if (Bot.quest != null) {
            game.correctAnswer(callback.getData());
            bot.sendMsg(message1, game.getBotAnswer());
        }
        if (game.getAmountQuest() != 0 && game.getPoint() >= 0) {
            Bot.quest = game.getQuest();
            try {
                bot.execute(CreateButtons.questInline(id, Bot.quest));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else {
            String answer;
            if (game.getPoint() < 0)
                answer = ", увы, но ты проиграл\n";
            else
                answer = ", молодец, ты победил!\n";
            bot.sendMsg(message1, message1.getChat().getFirstName() + answer);
            Bot.chatId.remove(id, game);
        }
    }
}