import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class CreateButtons {
    static SendMessage themeInline(long chatId) {
        List <List<InlineKeyboardButton>> buttons = Collections.singletonList(Arrays.asList(
                new InlineKeyboardButton().setText("Матмех").setCallbackData("1"),
                new InlineKeyboardButton().setText("География").setCallbackData("2")));
        InlineKeyboardMarkup markupKeyboar = new InlineKeyboardMarkup();
        markupKeyboar.setKeyboard(buttons);
        return new SendMessage().setText("Матмех или География").setChatId(chatId).setReplyMarkup(markupKeyboar);
    }

    static SendMessage questInline(long chatId, Question quest) {
        List <List <InlineKeyboardButton>> buttons = Arrays.stream(quest.answers)
                .map(answer -> Collections.singletonList(
                        new InlineKeyboardButton().setText(answer.substring(2)).setCallbackData(answer.substring(0, 1))
                        ))
                .collect(Collectors.toList());
        InlineKeyboardMarkup markupKeyboar = new InlineKeyboardMarkup();
        markupKeyboar.setKeyboard(buttons);
        System.out.println(new SendMessage().setText(quest.text).setChatId(chatId).setReplyMarkup(markupKeyboar));
        return new SendMessage().setText(quest.text).setChatId(chatId).setReplyMarkup(markupKeyboar);
    }

    static void setButtons(SendMessage sendMessage) {
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
}