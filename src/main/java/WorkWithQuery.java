import org.telegram.telegrambots.api.methods.send.SendMessage;

import java.util.Map;

class WorkWithQuery {
    static String botAnswer = "Начнём!";
    static String gameOver;
    static SendMessage newQuest;
    static void workWithQuest(String answer, Long id, Map<Long, GameQuiz> chatId){
        GameQuiz game = chatId.get(id);
        if (game.question != null) {
            game.correctAnswer(answer);
            botAnswer = game.getBotAnswer();
        }
        if (game.getAmountQuest() != 0 && game.getPoint() >= 0) {
            game.question = game.getQuest();
            newQuest = CreateButtons.questInline(id, game.question);
        } else {
            if (game.getPoint() < 0)
                gameOver = "Увы, но ты проиграл";
            else
                gameOver = "Молодец, ты победил!";
        }
    }
}