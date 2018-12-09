import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Bot extends TelegramLongPollingBot {
    private String help = "Правила очень простые: Я буду задавать вопросы, \n" +
            "а тебе нужно будет выбрать из четырёх вариантов ответа единственно верный. \n" +
            "За правильный ответ будет начислено 100 очков, за неправильный - отнято 300. \n" +
            "Так же я могу немного помочь. Но увы не бесплатно. Цена в 300 очков меня вполне устроит. \n" +
            "Как выиграть? Останься в плюсе до конца! Удачной игры!";
    private Question s;
    private Map<Long, GameQuiz> chatId = new HashMap<>();

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
            setButtons(sendMessage);
            sendMessage(sendMessage);
        } catch (TelegramApiException e1) {
            e1.printStackTrace();
        }
    }

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (update.hasCallbackQuery()) {
            Message message1 = update.getCallbackQuery().getMessage();
            if (!chatId.containsKey(message1.getChatId())) {
                try {
                    chatId.put(message1.getChatId(), new GameQuiz(update.getCallbackQuery().getData()));
                    System.out.println(chatId);
                } catch (FileException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println(message1.getChatId());
                System.out.println(update.getCallbackQuery().getData().substring(0, 1));
                chatId.get(message1.getChatId()).correctAnswer(update.getCallbackQuery().getData());
                sendMsg(message1, chatId.get(message1.getChatId()).getBotAnswer());
            }
            if (chatId.get(message1.getChatId()).amountQuest != 0 && chatId.get(message1.getChatId()).point >= 0) {
                s = chatId.get(message1.getChatId()).getQuest();
                try {
                    execute(questInline(message1.getChatId(), s));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else {
                String answer;
                if (chatId.get(message1.getChatId()).point < 0)
                    answer = ", увы, но ты проиграл\n";
                else
                    answer = ", молодец, ты победил!\n";
                sendMsg(message1, message1.getChat().getFirstName() + answer);
                chatId.remove(message1.getChatId(), chatId.get(message1.getChatId()));
            }
        } else if (message != null && message.hasText()) {
            if (message.getText().toLowerCase().equals("start")) {
                sendMsg(message, "Привет, " + message.getChat().getFirstName() + "! Давай сыграем!");
                sendMsg(message, "Для начала, выберите тематику вопросов:");
                System.out.println(chatId);
                try {
                    execute(themeInline(message.getChatId()));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                chatId.remove(message.getChatId(), chatId.get(message.getChatId()));
            } else if (message.getText().toLowerCase().equals("help"))
                sendMsg(message, help);
            else if (message.getText().toLowerCase().equals("joke")) {
                if (chatId.get(message.getChatId()).amountJoke != 0) {
                    chatId.get(message.getChatId()).correctAnswer("joke");
                    sendMsg(message, chatId.get(message.getChatId()).getJoke());
                } else
                    sendMsg(message, "Анекдоты кончились");
            } else if (message.getText().toLowerCase().equals("hint")) {
                if (chatId.get(message.getChatId()).point < 300)
                    sendMsg(message, "У тебя недостаточно очков");
                else {
                    chatId.get(message.getChatId()).correctAnswer("hint");
                    try {
                        execute(questInline(message.getChatId(), chatId.get(message.getChatId()).getHint()));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                sendMsg(message, "Не знаю такую команду");
                try {
                    execute(questInline(message.getChatId(), s));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private SendMessage themeInline(long chatId) {
        List <List <InlineKeyboardButton>> buttons = new ArrayList <>();
        List <InlineKeyboardButton> buttons1 = new ArrayList <>();
        buttons1.add(new InlineKeyboardButton().setText("Матмех").setCallbackData("1"));
        buttons1.add(new InlineKeyboardButton().setText("География").setCallbackData("2"));
        buttons.add(buttons1);

        InlineKeyboardMarkup markupKeyboar = new InlineKeyboardMarkup();
        markupKeyboar.setKeyboard(buttons);
        return new SendMessage().setText("Матмех или География").setChatId(chatId).setReplyMarkup(markupKeyboar);
    }

    private SendMessage questInline(long chatId, Question quest) {
        List <List <InlineKeyboardButton>> buttons = new ArrayList <>();
        for (int i = 0; i < quest.answers.length; i++) {
            buttons.add(new ArrayList <>());
            buttons.get(i).add(new InlineKeyboardButton().setText(quest.answers[i].substring(2))
                    .setCallbackData(quest.answers[i].substring(0, 1)));
        }
        InlineKeyboardMarkup markupKeyboar = new InlineKeyboardMarkup();
        markupKeyboar.setKeyboard(buttons);
        return new SendMessage().setText(quest.text).setChatId(chatId).setReplyMarkup(markupKeyboar);
    }

    private void setButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(keyboardMarkup);
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("start"));
        keyboardFirstRow.add(new KeyboardButton("help"));
        keyboardFirstRow.add(new KeyboardButton("hint"));
        keyboardFirstRow.add(new KeyboardButton("joke"));

        keyboardRowList.add(keyboardFirstRow);
        keyboardMarkup.setKeyboard(keyboardRowList);
    }

    public String getBotUsername() {
        return "Quiz86Bot";
    }

    public String getBotToken() {
        return null;
    }
}
