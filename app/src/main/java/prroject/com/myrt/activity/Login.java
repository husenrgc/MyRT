package prroject.com.myrt.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import prroject.com.myrt.R;
import prroject.com.myrt.Result.ResultUser;
import prroject.com.myrt.activity.home.Home;
import prroject.com.myrt.api.RegisterAPI;
import prroject.com.myrt.api.Value;
import prroject.com.myrt.utility.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {
    public static final String URL = "http://192.168.43.123/MyRT/api/";
    private EditText username ,password;
    private Button login, register;
    private List<ResultUser> results = new ArrayList<>();
    private ProgressDialog mprogres;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPrefManager = new SharedPrefManager(this);
        setupWidgets();
        if (sharedPrefManager.getSPSudahLogin()){
            startActivity(new Intent(Login.this, Home.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }
        btnClick();


    }

    private void RequestLogin(){
String usrnm = username.getText().toString();
String pwd = password.getText().toString();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterAPI api = retrofit.create(RegisterAPI.class);
        Call<Value> call = api.loginUser(usrnm,pwd);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {

                   String value = response.body().getValue();

                String message = "Berhasil Login";
                if (value.equals("1")) {
                    mprogres.dismiss();
                    results = response.body().getResultUsers();
                    Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
                    ResultUser rs = results.get(0);

                    String nama =  rs.getNm_user();
                    String iduser = rs.getId_user();
                    String nama_lengkap = rs.getNm_lengkap();
                    Intent intent = new Intent(Login.this,Home.class);
                    intent.putExtra(getString(R.string.iduser), iduser);
                    sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA,nama );
                    sharedPrefManager.saveSPString(SharedPrefManager.SP_ID_USER,iduser);
                    sharedPrefManager.saveSPString(sharedPrefManager.SP_NAMA_L,nama_lengkap);
                    sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
                    startActivity(intent);

                    finish(); }
                 else {
                    mprogres.dismiss();
                    Toast.makeText(Login.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                }}



            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                t.printStackTrace();
                mprogres.dismiss();
                Toast.makeText(Login.this, "Jaringan Error!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void setupWidgets(){
        username = (EditText) findViewById(R.id.username);
        password =(EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        register =(Button) findViewById(R.id.register);
        mprogres = new ProgressDialog(this);
        mprogres.setCancelable(false);
        mprogres.setMessage("mencocokan data");
    }
//    start event click
    private void btnClick(){

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mprogres.show();
                RequestLogin();

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerActv = new Intent(Login.this , Register.class);
                startActivity(registerActv);
            }
        });

    }
//    end event click
}
