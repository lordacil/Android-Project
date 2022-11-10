package com.rizkynugraha.submission2.Model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class User implements Parcelable {
    private String login;
    private String avatar_url;
    private String name;
    private String location;
    private int id;

    protected User(Parcel in) {
        login = in.readString();
        avatar_url = in.readString();
        name = in.readString();
        location = in.readString();
        id = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
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
        parcel.writeString(name);
        parcel.writeString(location);
        parcel.writeInt(id);
    }

    public User(JSONObject object){
        try {
            String login = object.getString("login");
            String avatar_url = object.getString("avatar_url");
            String name = object.getString("name");
            String location = object.getString("location");
            int id = object.getInt("id");

            this.login = login;
            this.avatar_url = avatar_url;
            this.name = name;
            this.location = location;
            this.id = id;

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
