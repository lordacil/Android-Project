package com.rizkynugraha.cari_kos.models;

import android.os.Parcelable;

public class Model {

//    private String desc;
    private int image;

    public Model(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
