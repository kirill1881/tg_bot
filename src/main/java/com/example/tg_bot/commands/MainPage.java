package com.example.tg_bot.commands;

import com.example.tg_bot.commands.helpers.Command;
import com.example.tg_bot.models.UserModel;
import com.example.tg_bot.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Collections;

@Component
public class MainPage implements Command {

    @Autowired
    UserRepo userRepo;

    @Override
    public SendMessage execute(Update update) {
        if (!update.getMessage().getText().equals("/start")&&
                !update.getMessage().getText().equals("Главная страница")){
            return null;
        }

        if (update.getMessage().getText().equals("/start")){
            UserModel userModel = new UserModel();
            userModel.setChatId(update.getMessage().getChatId().toString());
            userModel.setTgId(update.getMessage().getFrom().getId().toString());
            userModel.setName(update.getMessage().getFrom().getFirstName());

            userRepo.save(userModel);
        }
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setText("Выберите команду");

        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(new KeyboardButton("Добавить авто"));
        keyboardRow.add(new KeyboardButton("Просмотр объявлений"));

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(Collections.singletonList(keyboardRow));
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        return sendMessage;
    }
}
