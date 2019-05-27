package prroject.com.myrt.activity.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import prroject.com.myrt.R;
import prroject.com.myrt.Result.ResultFeeds;
import prroject.com.myrt.api.RegisterAPI;
import prroject.com.myrt.api.Value;
import prroject.com.myrt.utility.GridImageAdapter;
import prroject.com.myrt.utility.SharedPrefManager;
import prroject.com.myrt.utility.UniversalImageLoader;
import prroject.com.myrt.utility.navHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Profile extends AppCompatActivity {
    private ImageView profilephoto ;
    public static final String URL = "http://192.168.43.123/MyRT/api/";
    private ProgressBar mProgressbar;
    private TextView namaLengkap , namUser, Description, Web;
     Context mContext = Profile.this;
     List<ResultFeeds> rs = new ArrayList<>();
    SharedPrefManager sharedPrefManager;
    private static final int NUM_GRID_COLUMNS=3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_profile);
        setupNav();
        setupToolbar();
        initImageLoader();
setupwidgets();
setupProfilephoto();
tempGridSetup();
    }
private void initImageLoader(){
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(this);
      ImageLoader.getInstance().init(universalImageLoader.getConfig());
}


    private void setupProfilephoto(){
        String imgUrl = "www.regmedia.co.uk/2015/08/18/android_6_marshmallow_logo.jpg";
        UniversalImageLoader.setImage(imgUrl, profilephoto, mProgressbar, "https://");

    }
    private  void setupwidgets(){

        mProgressbar = (ProgressBar) findViewById(R.id.profileProgresBar);
        mProgressbar.setVisibility(View.GONE);
profilephoto= (ImageView)findViewById(R.id.profil_image);
        sharedPrefManager = new SharedPrefManager(this);

        namaLengkap =(TextView) findViewById(R.id.profileName);
        namUser =(TextView) findViewById(R.id.display_name);
        namaLengkap.setText(sharedPrefManager.getNamaL());
        namUser.setText(sharedPrefManager.getSPNama());

    }
private void  tempGridSetup(){
        String username = sharedPrefManager.getSPidUser();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    RegisterAPI api = retrofit.create(RegisterAPI.class);
    Call<Value> call = api.readimageby(username);
    call.enqueue(new Callback<Value>() {
        @Override
        public void onResponse(Call<Value> call, Response<Value> response) {
            String value = response.body().getValue();

            String message = "Berhasil ";

            if (value.equals("1")) {
               rs = response.body().getResultFeed();

             ArrayList<String>imgURLs = new ArrayList<>();
             if (rs.size()>0){
             for (int i =0 ; i< rs.size(); i++){

                 imgURLs.add("http://192.168.43.123/MyRT/api/upload/"+rs.get(i).getGambar());

             }

                setupImgGrid(imgURLs);}

            }else {

                 Toast.makeText(Profile.this, "Gsgal", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<Value> call, Throwable t) {
            Toast.makeText(Profile.this, "Gsgal konek", Toast.LENGTH_SHORT).show();
        }
    });


}
private  void setupImgGrid(ArrayList<String> imgURLs){
    GridView gridView = (GridView)findViewById(R.id.gridview);
    int gridWidth = getResources().getDisplayMetrics().widthPixels;
    int imgWidth = gridWidth/NUM_GRID_COLUMNS;
    gridView.setColumnWidth(imgWidth);
    GridImageAdapter adapter = new GridImageAdapter(mContext, R.layout.layout_grid_image, "",imgURLs );
    gridView.setAdapter(adapter);
}


    private void setupToolbar(){

        ImageView profilemenu = (ImageView) findViewById(R.id.profile_menu);
        profilemenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this,SettingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupNav(){

        BottomNavigationView btv = (BottomNavigationView) findViewById(R.id.bottomNavViewBar);
        navHelper.enablenav(Profile.this,this, btv);
        Menu menu = btv.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.profile_menu,menu);
        return true;
    }


}
