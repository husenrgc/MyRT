package prroject.com.myrt.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Random;

import prroject.com.myrt.R;
import prroject.com.myrt.api.RegisterAPI;
import prroject.com.myrt.api.Value;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register extends AppCompatActivity {
      private EditText username , email, password, noktp,nokk,alamat ,nm_lengkap;
      private Button mRegister;
      private ProgressDialog mprogres;
    public static final String URL = "http://192.168.43.123/MyRT/api/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupWidgets();

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterUserAccount();
            }
        });

    }



    private  void setupWidgets(){
username = (EditText) findViewById(R.id.regUsername);
email = (EditText) findViewById(R.id.regEmail);
password = (EditText) findViewById(R.id.regPassword);
noktp   = (EditText) findViewById(R.id.regNoktp);
nokk = (EditText) findViewById(R.id.regNokk);
mRegister =(Button) findViewById(R.id.regUser);
alamat = (EditText) findViewById(R.id.regAlamat);
nm_lengkap =(EditText) findViewById(R.id.regnamalengkap);
mprogres = new ProgressDialog(this);
mprogres.setCancelable(false);
mprogres.setMessage("Sedang mendaftarkan user");


    }

    private   void RegisterUserAccount(){
        mprogres.show();
String user = username.getText().toString();
String nmlengkap = nm_lengkap.getText().toString();
String emailusr = email.getText().toString();
String pwd = password.getText().toString();
String alamatusr = alamat.getText().toString();
int noktpuser = Integer.parseInt(noktp.getText().toString());
int nokkuser = Integer.parseInt(nokk.getText().toString());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterAPI api = retrofit.create(RegisterAPI.class);
        Call<Value> call = api.registerUser(user,pwd,emailusr,noktpuser,nokkuser,alamatusr,nmlengkap);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();
                if (value.equals("1")) {
                    mprogres.dismiss();
                    Toast.makeText(Register.this, message, Toast.LENGTH_SHORT).show();
                    clear();
                    Intent login = new Intent(Register.this,Login.class);
                    startActivity(login);
                } else {
                    mprogres.dismiss();
                    Toast.makeText(Register.this, message, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                t.printStackTrace();
                mprogres.dismiss();
                Toast.makeText(Register.this, "Jaringan Error!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void  clear(){


        username.setText("");
        email.setText("");
        password.setText("");
        noktp.setText("");
        alamat.setText("");
        nokk.setText("");
    }

    public static String random(){
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomlenght = generator.nextInt(10);
        char tempChar;
        for (int i =0; i< randomlenght;i++){

            tempChar =(char)(generator.nextInt(96)+32);
            randomStringBuilder.append(tempChar);
        }
        return  randomStringBuilder.toString();
    }
}
