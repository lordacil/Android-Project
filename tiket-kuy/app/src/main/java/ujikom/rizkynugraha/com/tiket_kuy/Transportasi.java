package ujikom.rizkynugraha.com.tiket_kuy;

import com.google.gson.annotations.SerializedName;

public class Transportasi {

    @SerializedName("id_transportasi")
    private int id_transportasi;
    @SerializedName("kode")
    private String kode;
    @SerializedName("jumlah_kursi")
    private String jumlah_kursi;
    @SerializedName("picture")
    private String picture;
    @SerializedName("nama_type")
    private String nama_type;
    @SerializedName("keterangan")
    private String keterangan;
    @SerializedName("id_type_transportasi")
    private String id_type_transportasi;
    @SerializedName("kodena")
    private String kodena;
    @SerializedName("message")
    private String massage;

    public int getId_transportasi() {
        return id_transportasi;
    }

    public void setId_transportasi(int id) {
        this.id_transportasi = id;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getJumlah_kursi() {
        return jumlah_kursi;
    }

    public void setJumlah_kursi(String jumlah_kursi) {
        this.jumlah_kursi = jumlah_kursi;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getNama_type() {
        return nama_type;
    }

    public void setNama_type(String nama_type) {
        this.nama_type = nama_type;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getId_type_transportasi() {
        return id_type_transportasi;
    }

    public void setId_type_transportasi(String id_type_transportasi) {
        this.id_type_transportasi = id_type_transportasi;
    }

    public String getKodena() {
        return kodena;
    }

    public void setKodena(String kodena) {
        this.kodena = kodena;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

}
