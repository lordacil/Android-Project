package com.dicoding.rizkynugraha.cataloguemoviev2.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class Movies implements Parcelable {
    private String vote_count;
    private Double vote_average;
    private String title;
    private Double popularity;
    private String original_language;
    private String overview;
    private String release_date;
    private String photo;
    private String banner;

    protected Movies(Parcel in) {
        vote_count = in.readString();
        if (in.readByte() == 0) {
            vote_average = null;
        } else {
            vote_average = in.readDouble();
        }
        title = in.readString();
        if (in.readByte() == 0) {
            popularity = null;
        } else {
            popularity = in.readDouble();
        }
        original_language = in.readString();
        overview = in.readString();
        release_date = in.readString();
        photo = in.readString();
        banner = in.readString();
    }

    public static final Creator<Movies> CREATOR = new Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel in) {
            return new Movies(in);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.vote_count);
        dest.writeValue(this.vote_average);
        dest.writeString(this.title);
        dest.writeValue(this.popularity);
        dest.writeString(this.original_language);
        dest.writeString(this.overview);
        dest.writeString(this.release_date);
        dest.writeString(this.photo);
        dest.writeString(this.banner);
    }

    public Movies(JSONObject object){
        try {
            String vote_count = object.getString("vote_count");
            Double vote_average = object.getDouble("vote_average");
            String title = object.getString("title");
            Double popularity = object.getDouble("popularity");
            String original_language = object.getString("original_language");
            String overview = object.getString("overview");
            String release_date = object.getString("release_date");
            String poster_path = object.getString("poster_path");
            String backdrop_path = object.getString("backdrop_path");

            this.vote_count = vote_count;
            this.vote_average = vote_average;
            this.title = title;
            this.popularity = popularity;
            this.original_language = original_language;
            this.overview = overview;
            this.release_date = release_date;
            this.photo = poster_path;
            this.banner = backdrop_path;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
