package com.dicoding.rizkynugraha.cataloguemovie;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    private int photo,photo_sampul;
    private String judul,rate,popular,year,month,desc;

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public int getPhoto_sampul() {
        return photo_sampul;
    }

    public void setPhoto_sampul(int photo_sampul) {
        this.photo_sampul = photo_sampul;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getPopular() {
        return popular;
    }

    public void setPopular(String popular) {
        this.popular = popular;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    protected Movie(Parcel in) {
        photo = in.readInt();
        photo_sampul = in.readInt();
        judul = in.readString();
        rate = in.readString();
        popular = in.readString();
        year = in.readString();
        month = in.readString();
        desc = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(photo);
        dest.writeInt(photo_sampul);
        dest.writeString(judul);
        dest.writeString(rate);
        dest.writeString(popular);
        dest.writeString(year);
        dest.writeString(month);
        dest.writeString(desc);
    }

    @Override
    public int describeContents() {
        return 0;
    }


    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

}
