package com.example.tg_bot.services;

import com.example.tg_bot.commands.AddCar;
import com.example.tg_bot.commands.MainPage;
import com.example.tg_bot.commands.PhotoCommand;
import com.example.tg_bot.commands.helpers.Command;
import com.example.tg_bot.models.AdvertModel;
import com.example.tg_bot.models.UserModel;
import com.example.tg_bot.repos.AdvertRepo;
import com.example.tg_bot.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
public class Bot extends TelegramLongPollingBot {

    @Autowired
    MainPage mainPage;

    @Autowired
    AdvertRepo advertRepo;

    @Autowired
    AddCar addCar;

    @Autowired
    UserRepo userRepo;

    @Autowired
    PhotoCommand photoCommand;

    @Override
    public String getBotUsername() {
        return "carsaleoveronebot";
    }

    @Override
    public String getBotToken() {
        return "5940172311:AAF-33C1ZbRGY4rtlI7swp9cThwp1IBywpk";
    }

    @Override
    public void onUpdateReceived(Update update) {


        List<Command> list = new ArrayList<>();
        list.add(mainPage);
        list.add(addCar);
        SendMessage sendMessage = null;
        if (update.getMessage().hasPhoto()){
            sendMessage = photoCommand.execute(update);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }else {
            for (Command c : list) {
                if ((sendMessage = c.execute(update)) != null) {
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        if (sendMessage==null){
            String[] st = update.getMessage().getText().split("\n");
            AdvertModel advertModel = new AdvertModel();
            advertModel.setBrand(st[0]);
            advertModel.setModel(st[1]);
            advertModel.setDisc(st[2]);

            UserModel userModel = userRepo.findByTgId(update.getMessage().getFrom()
                    .getId().toString());
            advertModel.setUserModel(userModel);

            sendMessage = new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId());
            sendMessage.setText("Отправьте фотографии вашего автомобиля");
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            System.out.println(update.getMessage().getPhoto());
            advertRepo.save(advertModel);
        }


    }
}
