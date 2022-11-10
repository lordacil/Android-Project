package ujikom.rizkynugraha.com.tiket_kuy;


import com.google.gson.annotations.SerializedName;

public class search {

    @SerializedName("id")
    private int id;
    @SerializedName("tujuan")
    private String tujuan;
    @SerializedName("harga")
    private String harga;
    @SerializedName("waktu_perjalanan")
    private String waktu_perjalanan;
    @SerializedName("type")
    private String type;
    @SerializedName("jadwal")
    private String jadwal;
    @SerializedName("message")
    private String massage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getWaktu_perjalanan() {
        return waktu_perjalanan;
    }

    public void setWaktu_perjalanan(String waktu_perjalanan) {
        this.waktu_perjalanan = waktu_perjalanan;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJadwal() {
        return jadwal;
    }

    public void setJadwal(String jadwal) {
        this.jadwal = jadwal;
    }


    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

}
