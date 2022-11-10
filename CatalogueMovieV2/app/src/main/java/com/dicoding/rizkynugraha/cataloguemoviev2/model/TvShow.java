package com.dicoding.rizkynugraha.cataloguemoviev2.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class TvShow implements Parcelable {
    private String name;
    private Double popularity;
    private String vote_count;
    private String first_air_date;
    private String original_language;
    private Double vote_average;
    private String overview;
    private String poster_path;
    private String backdrop_path;

    protected TvShow(Parcel in) {
        name = in.readString();
        if (in.readByte() == 0) {
            popularity = null;
        } else {
            popularity = in.readDouble();
        }
        vote_count = in.readString();
        first_air_date = in.readString();
        original_language = in.readString();
        if (in.readByte() == 0) {
            vote_average = null;
        } else {
            vote_average = in.readDouble();
        }
        overview = in.readString();
        poster_path = in.readString();
        backdrop_path = in.readString();
    }

    public static final Creator<TvShow> CREATOR = new Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel in) {
            return new TvShow(in);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }


    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeValue(this.popularity);
        dest.writeString(this.vote_count);
        dest.writeString(this.first_air_date);
        dest.writeString(this.original_language);
        dest.writeValue(this.vote_average);
        dest.writeString(this.overview);
        dest.writeString(this.poster_path);
        dest.writeString(this.backdrop_path);
    }

    public TvShow(JSONObject object) {
        try{
            String name = object.getString("name");
            String vote_count = object.getString("vote_count");
            String first_air_date = object.getString("first_air_date");
            String original_language = object.getString("original_language");
            String overview = object.getString("overview");
            String poster_path = object.getString("poster_path");
            Double popularity = object.getDouble("popularity");
            Double vote_average = object.getDouble("vote_average");
            String backdrop_path = object.getString("backdrop_path");

            this.name = name;
            this.vote_count = vote_count;
            this.first_air_date = first_air_date;
            this.original_language = original_language;
            this.overview = overview;
            this.poster_path = poster_path;
            this.backdrop_path = backdrop_path;

            this.popularity = popularity;
            this.vote_average = vote_average;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
