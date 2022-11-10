package ujikom.rizkynugraha.com.tiket_kuy;

import com.google.gson.annotations.SerializedName;

public class Bandara{
        @SerializedName("id")
        private int id;
        @SerializedName("id_destination")
        private String id_destination;
        @SerializedName("name")
        private String name;
        @SerializedName("iso")
        private String iso;
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

        public String getId_destination() {
            return id_destination;
        }

        public void setId_destination(String id_destination) {
            this.id_destination = id_destination;
        }

        public String getNameBandara() {
            return name;
        }

        public void setNameBandara(String name) {
            this.name = name;
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
