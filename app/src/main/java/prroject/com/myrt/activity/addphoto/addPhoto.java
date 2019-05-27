package prroject.com.myrt.activity.addphoto;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import prroject.com.myrt.R;
import prroject.com.myrt.activity.home.SectionPagerAdapter;
import prroject.com.myrt.utility.Permissions;
import prroject.com.myrt.utility.navHelper;

public class addPhoto  extends AppCompatActivity{

    private ViewPager mViewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_addphoto);

        setupViewPager();
        if (CheckPermissionsArray(Permissions.PERMISSIONS)){

            setupViewPager();
        }else {

            verfyPermissions(Permissions.PERMISSIONS);
        }
setupViewPager();
    }

public  int gerCurrentTab(){
        return mViewPager.getCurrentItem();

}
    private  void setupViewPager(){

        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager());


        adapter.addFragment(new GalleryFragment());

        adapter.addFragment(new PhotoFragment());
        mViewPager =(ViewPager)findViewById(R.id.container);
        mViewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs_bottom);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.getTabAt(0).setText("Gallery");
        tabLayout.getTabAt(1).setText("Photo");
    }

    public  void  verfyPermissions(String[] permissions) {

        ActivityCompat.requestPermissions(
                addPhoto.this,
                permissions,
                1
        );
    }
    public  boolean CheckPermissionsArray(String [] permissions){

        for (int i =0; i< permissions.length; i++){

            String check = permissions[i];
            if (!checkPermissions(check)){

                return false;
            }
        }
        return true;
    }
    public  boolean checkPermissions(String permission){

        int permissionRequest = ActivityCompat.checkSelfPermission(addPhoto.this,permission);
        if (permissionRequest != PackageManager.PERMISSION_GRANTED){
            return false;
        }else {
            return true;
        }
    }
    private void setupNav(){

        BottomNavigationView btv = (BottomNavigationView) findViewById(R.id.bottomNavViewBar);
        navHelper.enablenav(addPhoto.this,this, btv);
        Menu menu = btv.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
    }
}
