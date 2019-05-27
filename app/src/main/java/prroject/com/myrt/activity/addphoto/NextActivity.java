package prroject.com.myrt.activity.addphoto;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import prroject.com.myrt.R;
import prroject.com.myrt.activity.home.Home;
import prroject.com.myrt.api.RegisterAPI;
import prroject.com.myrt.api.Value;
import prroject.com.myrt.utility.SharedPrefManager;
import prroject.com.myrt.utility.UniversalImageLoader;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NextActivity extends AppCompatActivity {
    private ImageView nextImage;
    private ProgressDialog mProgres;
    private TextView post;
    private EditText caption;
    SharedPrefManager sharedPrefManager;
    public static final String URL = "http://192.168.43.123/MyRT/api/";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_next);
        Log.d("NextActivity","Path imagenya ada di :"+getIntent().getStringExtra(getString(R.string.selected_image)));


    setupwidgets();
    setupnextphoto();

    post.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            postImage();
        }
    });

    }

    private void initImageLoader(){
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(NextActivity.this);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }
    private void setupnextphoto(){
        String imgUrl = getIntent().getStringExtra(getString(R.string.selected_image));
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage("file:/" + imgUrl, nextImage, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });

    }
    private  void setupwidgets(){


        nextImage= (ImageView)findViewById(R.id.imgNext);
        mProgres = new ProgressDialog(this);
        mProgres.setMessage("Uploading image...");
        sharedPrefManager = new SharedPrefManager(this);
        post = (TextView) findViewById(R.id.tvpost);
        caption =(EditText) findViewById(R.id.Caption);
    }

    private void postImage(){
        try{
        mProgres.show();
       final String urlbaru = random();
        String imgUrl = getIntent().getStringExtra(getString(R.string.selected_image));
        File imgFile = new File(imgUrl);
            File compressedImage = new Compressor(this)
                    .setMaxWidth(640)
                    .setMaxHeight(480)
                    .setQuality(90)

                    .setCompressFormat(Bitmap.CompressFormat.JPEG)
                    .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_PICTURES).getAbsolutePath())
                    .compressToFile(imgFile);

        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-file"),compressedImage);
        MultipartBody.Part partImg =  MultipartBody.Part.createFormData("imageupload",compressedImage.getName(),requestBody);
         MultipartBody.Part url = MultipartBody.Part.createFormData("urlname",urlbaru);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)

                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterAPI api = retrofit.create(RegisterAPI.class);
        Call<Value> call = api.uploadimg(partImg, url );
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();
                if (value.equals("1")) {
                    mProgres.dismiss();
                    Toast.makeText(NextActivity.this, message, Toast.LENGTH_SHORT).show();
                    String id_user = sharedPrefManager.getSPidUser();
                    Retrofit retrofits = new Retrofit.Builder()
                            .baseUrl(URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();


                    RegisterAPI api1 = retrofits.create(RegisterAPI.class);
                    Call<Value> call1 = api1.uploadimgdb(id_user,caption.getText().toString(),"img"+urlbaru+".jpg");
                    call1.enqueue(new Callback<Value>() {
                        @Override
                        public void onResponse(Call<Value> call, Response<Value> response) {
                            String value = response.body().getValue();
                            if (value.equals("1")) {


                                Intent home = new Intent(NextActivity.this,Home.class);
                                startActivity(home);
                                finish();
                            } else {

                            }
                        }

                        @Override
                        public void onFailure(Call<Value> call, Throwable t) {

                        }
                    });


                } else {
                    mProgres.dismiss();
                    Toast.makeText(NextActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
mProgres.dismiss();
            }
        });} catch (IOException e ){
            e.printStackTrace();
        }

    }
    public static String random(){
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomlenght = generator.nextInt(10);
        int tempChar;
        for (int i =0; i< randomlenght;i++){

            int random = (int )(Math.random() * 9999 + 1);
            if (random <1){} else{
            randomStringBuilder.append(random);}
        }
        return  randomStringBuilder.toString();
    }
}
