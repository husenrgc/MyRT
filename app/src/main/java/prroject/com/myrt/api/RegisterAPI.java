package prroject.com.myrt.api;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RegisterAPI {


    @FormUrlEncoded
    @POST("register.php")
    Call<Value> registerUser(

            @Field("nm_user") String nm_user,
            @Field("pass") String pass,
            @Field("email") String email,
            @Field("no_ktp") int no_ktp,
            @Field("no_kk") int no_kk,
            @Field("alamat") String alamat,
            @Field("nm_lengkap") String nm_lengkap);

    @FormUrlEncoded
    @POST("useruploaDb.php")
    Call<Value> uploadimgdb(

            @Field("id_user") String id_user,
            @Field("caption") String caption,
            @Field("gambar") String gambar);

    @FormUrlEncoded
    @POST("direktori.php")
    Call<Value> dir(
            @Field("name") String name);

    @GET("read_produk.php")
    Call<Value> read();
    @GET("readAllimg.php")
    Call<Value> readimg();

    @FormUrlEncoded
    @POST("login.php")
    Call<Value> loginUser(@Field("nm_user") String nm_user,
                          @Field("pass") String pass);

    @GET("read_pesanan.php")
    Call<Value> readpesanan();


    @Multipart
    @POST("uploadimage.php")
    Call<Value> uploadimg(@Part MultipartBody.Part image,
                          @Part MultipartBody.Part  url);

    @FormUrlEncoded
    @POST("readimageprofile.php")
    Call<Value> readimageby(

            @Field("id_user") String id_user);


}
