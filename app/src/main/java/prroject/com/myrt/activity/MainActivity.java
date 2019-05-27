package prroject.com.myrt.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.wang.avi.AVLoadingIndicatorView;

import prroject.com.myrt.R;
import prroject.com.myrt.activity.home.Home;
import prroject.com.myrt.utility.SharedPrefManager;


public class MainActivity extends AppCompatActivity {
AVLoadingIndicatorView avii;
Button bt;
    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
avii = (AVLoadingIndicatorView) findViewById(R.id.avi);
        avii.smoothToShow();
        bt = (Button) findViewById(R.id.toHome);
        sharedPrefManager = new SharedPrefManager(this);
        if (sharedPrefManager.getSPSudahLogin()){
            startActivity(new Intent(MainActivity.this, Home.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home  = new Intent(MainActivity.this, Login.class);
                startActivity(home);
                finish();
            }
        });
    }
}
