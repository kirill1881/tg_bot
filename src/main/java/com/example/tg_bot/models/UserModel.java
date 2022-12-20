package com.example.tg_bot.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ov1601_user")
@Data
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    String name;

    @Column(name = "tg_id")
    String tgId;

    @Column(name = "chatId")
    String chatId;
}
