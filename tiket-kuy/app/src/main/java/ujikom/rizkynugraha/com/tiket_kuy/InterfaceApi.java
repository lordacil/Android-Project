package ujikom.rizkynugraha.com.tiket_kuy;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface InterfaceApi {
    @FormUrlEncoded
    @POST("search.php")
    Call<List<search>> search(
            @Field("search") String search);
    // kenapa ga pake post aja ?
    // lah kan tdi disuruh pake get ;v , itu aku typo njerr

    // GET USER GAN........
    @GET("get_user.php")
    Call<List<user>> getUser();
    // Gw kasih catetan
    @POST("get_bandara.php")
    Call<List<Bandara>> getBandara();
    /*
    Kalo POST Methodnya field
    kalo Get Methodnya Path
    dah tau ?

    ashiaap uhh sep


    *
     */
    // GET DESTINATION gan.......
    // ga ada FormUrlEncoder :v
    @FormUrlEncoded
    @POST("add_bandara.php")
    Call<Bandara> insertBandara(
            @Field("key") String key,
            @Field("id_destination") String idDesti,
            @Field("name") String bandara,
            @Field("iso") String iso);

    @FormUrlEncoded
    @POST("update_bandara.php")
    Call<Bandara> updateBandara(
            @Field("key") String key,
            @Field("id") int id,
            @Field("id_destination") String idDesti,
            @Field("name") String bandara,
            @Field("iso") String iso);

    @FormUrlEncoded
    @POST("delete_bandara.php")
    Call<Bandara> deleteBandara(
            @Field("id") int id);

    @POST("get_destination.php")
    Call<List<Destination>> getDestination();


    @FormUrlEncoded
    @POST("add_destination.php")
    Call<Destination> insertDestination(
            @Field("key") String key,
            @Field("destination") String destination,
            @Field("iso") String iso);


    @FormUrlEncoded
    @POST("update_destination.php")
    Call<Destination> updateDestination(
            @Field("key") String key,
            @Field("id") int id,
            @Field("destination") String destination,
            @Field("iso") String iso);

    @FormUrlEncoded
    @POST("delete_destination.php")
    Call<Destination> deleteDestination(
            @Field("id") int id);

    // GET TRANSPORTASI GAN.....
    @POST("get_trans.php")
    Call<List<Transportasi>> getTrans();
    // post api buat search mana ?

    @FormUrlEncoded
    @POST("add_trans.php")
    Call<Transportasi> insertTransportasi(
            @Field("kode") String kode,
            @Field("jumlah_kursi") String jumlah_kursi,
            @Field("nama_type") String nama_type,
            @Field("keterangan") String keterangan,
            @Field("id_type_transportasi") String id_type_transportasi,
            @Field("picture") String picture);

    @FormUrlEncoded
    @POST("update_trans.php")
    Call<Transportasi> updateTransportasi(
            @Field("key") String key,
            @Field("id_transportasi") int id_transportasi,
            @Field("kode") String kode,
            @Field("jumlah_kursi") String jumlah_kursi,
            @Field("nama_type") String nama_type,
            @Field("keterangan") String keterangan,
            @Field("id_type_transportasi") String id_type_transportasi,
            @Field("picture") String picture);

    @FormUrlEncoded
    @POST("delete_trans.php")
    Call<Transportasi> deletePet(
            @Field("id_transportasi") int id_transportasi);

}
