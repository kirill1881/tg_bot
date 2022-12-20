package com.example.tg_bot.repos;

import com.example.tg_bot.models.AdvertModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvertRepo extends JpaRepository<AdvertModel, Long> {
    List<AdvertModel> findAllByUserModelTgId(String tgId);
}
