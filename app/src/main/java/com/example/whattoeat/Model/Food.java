package com.example.whattoeat.Model;

import java.io.Serializable;

public class Food implements Serializable {

    public final int id;
    public final String name;
    public final String pictureFileName;

    public Food(int id, String name, String pictureFileName) {
        this.id = id;
        this.name = name;
        this.pictureFileName = pictureFileName;
    }

    public Food(String name, String pictureFileName) {
        this(-1, name, pictureFileName);
    }

    @Override
    public String toString() {
        return "#" + id + ": " + name;
    }

}
