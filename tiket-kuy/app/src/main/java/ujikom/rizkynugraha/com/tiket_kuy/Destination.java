package ujikom.rizkynugraha.com.tiket_kuy;

import com.google.gson.annotations.SerializedName;

public class Destination {

    @SerializedName("id")
    private int id;
    @SerializedName("destination")
    private String destination;
    @SerializedName("iso")
    private String iso;
    @SerializedName("picture")
    private String picture;
    @SerializedName("kode")
    private String kode;
    @SerializedName("message")
    private String massage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

}
