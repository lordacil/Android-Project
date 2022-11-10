package ujikom.rizkynugraha.com.tiket_kuy;

import com.google.gson.annotations.SerializedName;

public class user {

    @SerializedName("id_penumpang")
    public int id_penumpang;
    @SerializedName("username")
    private String username;
    @SerializedName("email")
    private String email;
    @SerializedName("nama_penumpang")
    private String nama_penumpang;
    @SerializedName("alamat_penumpang")
    private String alamat_penumpang;
    @SerializedName("tanggal_lahir")
    private String tanggal_lahir;
    @SerializedName("jenis_kelamin")
    private String jenis_kelamin;
    @SerializedName("telepon")
    private String telepon;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("picture")
    private String picture;
    @SerializedName("kode")
    private String kode;

    public int getId_penumpang() {
        return id_penumpang;
    }

    public void setId_penumpang(int id_penumpang) {
        this.id_penumpang = id_penumpang;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getNama_penumpang() {
        return nama_penumpang;
    }

    public void setNama_penumpang(String nama_penumpang) {
        this.nama_penumpang = nama_penumpang;
    }

    public String getAlamat_penumpang() {
        return alamat_penumpang;
    }

    public void setAlamat_penumpang(String alamat_penumpang) {
        this.alamat_penumpang = alamat_penumpang;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getKode() {
        return kode;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

}
