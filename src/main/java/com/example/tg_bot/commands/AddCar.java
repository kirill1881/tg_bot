package com.example.tg_bot.commands;

import com.example.tg_bot.commands.helpers.Command;
import com.example.tg_bot.models.AdvertModel;
import com.example.tg_bot.services.Bot;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Collections;

@Component
public class AddCar implements Command {
    @Override
    public SendMessage execute(Update update) {
        if (!update.getMessage().getText().equals("Добавить авто")){
            return null;
        }
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setText("Введите в одном сообщении через энтэр слудующие данные:\n" +
                "Марка авто,\n" +
                "Модель авто,\n" +
                "Описание авто");


        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(new KeyboardButton("Главная страница"));
        keyboardRow.add(new KeyboardButton("Просмотр объявлений"));

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(Collections.singletonList(keyboardRow));
        sendMessage.setReplyMarkup(replyKeyboardMarkup);


        return sendMessage;
    }
}
