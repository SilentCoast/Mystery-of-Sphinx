package com.template;

import android.graphics.drawable.Drawable;

public class Item {
    private  int Id;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
    private Drawable Image;

    public Drawable getImage() {
        return Image;
    }

    public void setImage(Drawable image) {
        Image = image;
    }
    public Item(Drawable Image){
        this.Image = Image;
    }
}