package com.example.nevo.listviewadapter;

import android.graphics.Bitmap;

public class Item {
    private String name,size;
    private Bitmap image;

    public Item(String name, String size, Bitmap image){
        this.name = name;
        this.size = size;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getSize() {

        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
