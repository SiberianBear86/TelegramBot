import org.telegram.telegrambots.api.methods.send.SendMessage;

import java.util.Map;

public class HelpCommand implements Command {
    @Override
    public String getBotAnswer(Long id, GameQuiz game, Map<Long, GameQuiz> chatId) {
        return "Правила очень простые: Я буду задавать вопросы, \n" +
                "а тебе нужно будет выбрать из четырёх вариантов ответа единственно верный. \n" +
                "За правильный ответ будет начислено 100 очков, за неправильный - отнято 300. \n" +
                "Так же я могу немного помочь. Но увы не бесплатно. Цена в 300 очков меня вполне устроит. \n" +
                "Как выиграть? Останься в плюсе до конца! Удачной игры!";
    }

    @Override
    public SendMessage trySendMsg(Long id, GameQuiz game) {
        return CreateButtons.questInline(id, game.question);
    }
}