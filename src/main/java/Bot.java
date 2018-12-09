import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Bot extends TelegramLongPollingBot {
    static Question quest;
    static Map<Long, GameQuiz> chatId = new HashMap<>();

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e1) {
            e1.printStackTrace();
        }
    }

    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);
        try {
            CreateButtons.setButtons(sendMessage);
            sendMessage(sendMessage);
        } catch (TelegramApiException e1) {
            e1.printStackTrace();
        }
    }

    private void workWithQuest(CallbackQuery callback){
        Message message1 = callback.getMessage();
        Long id = message1.getChatId();
        chatId.computeIfAbsent(id, k -> {
            try {
                return new GameQuiz(callback.getData());
            } catch (FileException e) {
                e.printStackTrace();
            }return null;
        });
        GameQuiz game = chatId.get(id);
        if (quest != null) {
            game.correctAnswer(callback.getData());
            sendMsg(message1, game.getBotAnswer());
        }
        if (game.getAmountQuest() != 0 && game.getPoint() >= 0) {
            quest = game.getQuest();
            try {
                execute(CreateButtons.questInline(id, quest));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else {
            String answer;
            if (game.getPoint() < 0)
                answer = ", увы, но ты проиграл\n";
            else
                answer = ", молодец, ты победил!\n";
            sendMsg(message1, message1.getChat().getFirstName() + answer);
            chatId.remove(id, game);
        }
    }

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (update.hasCallbackQuery()) {
            workWithQuest(update.getCallbackQuery());
        } else if (message != null && message.hasText()) {
            if (Arrays.toString(Commands.values()).contains(message.getText().toUpperCase())) {
                Commands com = Commands.valueOf(message.getText().toUpperCase());
                Commands.id = message.getChatId();
                com.botReaction();
                sendMsg(message, com.getBotAnswer());
                try {
                    execute(com.trySendMsg());
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else
                sendMsg(message, "Не знаю такую команду");
        }
    }

    public String getBotUsername() {
        return "Quiz86Bot";
    }
    public String getBotToken() {
        return "778550876:AAF_5E6G0pYVi_58cEdaTz9BuuoFkpsEQnY";
    }
}