package com.example.tg_bot.repos;

import com.example.tg_bot.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserModel, Long> {
    UserModel findByTgId(String tgId);
}
