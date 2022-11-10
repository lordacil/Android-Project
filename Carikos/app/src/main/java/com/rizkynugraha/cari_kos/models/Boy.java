package com.rizkynugraha.cari_kos.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Boy implements Parcelable {

    private String desc_kos;
    private String price_kos;
    private String name_kos;
    private String img_kos;
    private String cover_kos;
    private String owner_kos;
    private String no_kos;

    public Boy(Parcel in) {
        desc_kos = in.readString();
        price_kos = in.readString();
        name_kos = in.readString();
        img_kos = in.readString();
        cover_kos = in.readString();
        owner_kos = in.readString();
        no_kos = in.readString();
    }

    public static final Creator<Boy> CREATOR = new Creator<Boy>() {
        @Override
        public Boy createFromParcel(Parcel in) {
            return new Boy(in);
        }

        @Override
        public Boy[] newArray(int size) {
            return new Boy[size];
        }
    };

    public String getDesc_kos() {
        return desc_kos;
    }

    public void setDesc_kos(String desc_kos) {
        this.desc_kos = desc_kos;
    }

    public String getPrice_kos() {
        return price_kos;
    }

    public void setPrice_kos(String price_kos) {
        this.price_kos = price_kos;
    }

    public String getName_kos() {
        return name_kos;
    }

    public void setName_kos(String name_kos) {
        this.name_kos = name_kos;
    }

    public String getImg_kos() {
        return img_kos;
    }

    public void setImg_kos(String img_kos) {
        this.img_kos = img_kos;
    }

    public String getCover_kos() {
        return cover_kos;
    }

    public void setCover_kos(String cover_kos) {
        this.cover_kos = cover_kos;
    }

    public String getOwner_kos() {
        return owner_kos;
    }

    public void setOwner_kos(String owner_kos) {
        this.owner_kos = owner_kos;
    }

    public String getNo_kos() {
        return no_kos;
    }

    public void setNo_kos(String no_kos) {
        this.no_kos = no_kos;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(desc_kos);
        dest.writeString(price_kos);
        dest.writeString(name_kos);
        dest.writeString(img_kos);
        dest.writeString(cover_kos);
        dest.writeString(owner_kos);
        dest.writeString(no_kos);
    }
}
