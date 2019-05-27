package prroject.com.myrt.utility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import prroject.com.myrt.R;
import prroject.com.myrt.activity.home.Home;
import prroject.com.myrt.activity.profile.Profile;
import prroject.com.myrt.activity.addphoto.addPhoto;

public class navHelper {

public static void enablenav(final Context context, final Activity call, BottomNavigationView view){

    view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.ic_house:
                    Intent intent1 = new Intent(context, Home.class);
                    context.startActivity(intent1);
                    call.overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

                    break;
                case R.id.ic_add:

                    Intent intent2 = new Intent(context,addPhoto.class);
                    context.startActivity(intent2);
                    call.overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                    break;
                case R.id.ic_profile:
                    Intent intent3 = new Intent(context, Profile.class);
                    context.startActivity(intent3);
                    call.overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                    break;


            }

            return false;
        }
    });

}

}


