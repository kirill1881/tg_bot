package com.example.tg_bot.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ov1601_advert")
@Data
public class AdvertModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    UserModel userModel;

    @Column(name = "brand")
    String brand;

    @Column(name = "model")
    String model;

    @Column(name = "price")
    int price;

    @Column(name = "disc")
    String disc;

    @Column(name = "photos")
    String ids;

}
