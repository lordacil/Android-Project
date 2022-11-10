package com.rizkynugraha.myquotes;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class Users implements Parcelable {
    private String login;
    private String avatar_url;
    private String type;
    private String id;

    protected Users(Parcel in) {
        login = in.readString();
        avatar_url = in.readString();
        type = in.readString();
        id = in.readString();
    }

    public static final Creator<Users> CREATOR = new Creator<Users>() {
        @Override
        public Users createFromParcel(Parcel in) {
            return new Users(in);
        }

        @Override
        public Users[] newArray(int size) {
            return new Users[size];
        }
    };

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(login);
        parcel.writeString(avatar_url);
        parcel.writeString(type);
        parcel.writeString(id);
    }

    public Users(JSONObject object){
        try {
            String login = object.getString("login");
            String avatar_url = object.getString("avatar_url");
            String type = object.getString("type");
            String id = object.getString("id");

            this.login = login;
            this.avatar_url = avatar_url;
            this.type = type;
            this.id = id;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
