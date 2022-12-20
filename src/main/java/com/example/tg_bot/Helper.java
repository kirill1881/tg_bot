package com.example.tg_bot;

import com.example.tg_bot.models.UserModel;
import com.example.tg_bot.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Helper {
    @Autowired
    UserRepo userRepo;

    private static Helper helper = null;

    public Helper() {
        helper = this;
    }

    public static void saveUser(UserModel userModel){
        helper.userRepo.save(userModel);
    }
}