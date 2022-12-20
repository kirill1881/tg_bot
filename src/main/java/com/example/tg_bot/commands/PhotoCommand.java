package com.example.tg_bot.commands;

import com.example.tg_bot.models.AdvertModel;
import com.example.tg_bot.repos.AdvertRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PhotoCommand {

    @Autowired
    AdvertRepo advertRepo;

    public SendMessage execute(Update update){
        List<AdvertModel> list = advertRepo.findAllByUserModelTgId(
                update.getMessage().getFrom().getId().toString()
        );

        list = list.stream().filter(a -> a.getIds() == null).collect(Collectors.toList());
        list = list.stream().sorted(Comparator.comparingLong(AdvertModel::getId)).collect(Collectors.toList());

        AdvertModel advertModel = list.get(list.size()-1);
        advertModel.setIds(update.getMessage().getPhoto().get(0).getFileId());
        advertRepo.save(advertModel);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setText("Объявление сохранено");

        return sendMessage;
    }
}
