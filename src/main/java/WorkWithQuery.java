import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.CallbackQuery;

import java.util.Map;

class WorkWithQuery {
    static String botAnswer = "Начнём!";
    static String gameOver;
    static SendMessage newQuest;
    static void workWithQuest(CallbackQuery callback, Long id, Map<Long, GameQuiz> chatId){
        GameQuiz game = chatId.get(id);
        if (game.question != null) {
            game.correctAnswer(callback.getData());
            botAnswer = game.getBotAnswer();
        }
        if (game.getAmountQuest() != 0 && game.getPoint() >= 0) {
            game.question = game.getQuest();
            newQuest = CreateButtons.questInline(id, game.question);
        } else {
            String answer;
            if (game.getPoint() < 0)
                answer = ", увы, но ты проиграл\n";
            else
                answer = ", молодец, ты победил!\n";
            gameOver = callback.getMessage().getChat().getFirstName() + answer;
            chatId.remove(id, game);
        }
    }
}