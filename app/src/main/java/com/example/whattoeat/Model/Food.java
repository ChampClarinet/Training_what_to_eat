package com.example.whattoeat.Model;

import java.io.Serializable;

public class Food implements Serializable{

    public final String name;
    public final String pictureFileName;

    public Food(String name, String pictureFileName) {
        this.name = name;
        this.pictureFileName = pictureFileName;
    }

}
