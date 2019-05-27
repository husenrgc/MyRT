package prroject.com.myrt.utility;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;

import prroject.com.myrt.R;

public class GridImageAdapter extends ArrayAdapter<String>{
    private Context mContex;
    private LayoutInflater mInflater;
    private  int layoutResource;
    private String mAppend;
    private ArrayList<String> imgURLs;

    public GridImageAdapter(Context mContex,  int layoutResource, String mAppend, ArrayList<String> imgURLs) {

       super(mContex, layoutResource, imgURLs);
      mInflater = (LayoutInflater)mContex.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mContex = mContex;

        this.layoutResource = layoutResource;
        this.mAppend = mAppend;
        this.imgURLs = imgURLs;
    }

    private static  class ViewHolder{
        squareImageView profilImage;
        ProgressBar mProgressbar;
        CheckBox cek;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       final ViewHolder holder;
       if (convertView == null){
           convertView = mInflater.inflate(layoutResource, parent, false);
           holder = new ViewHolder();
           holder.mProgressbar =(ProgressBar) convertView.findViewById(R.id.gridimageProgressbar);
           holder.profilImage = (squareImageView) convertView.findViewById(R.id.gridImageView);

           convertView.setTag(holder);

       }
       else {

           holder =(ViewHolder) convertView.getTag();

       }
       String imgURL= getItem(position);
       
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(mAppend + imgURL, holder.profilImage, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                if (holder.mProgressbar != null){

                    holder.mProgressbar.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                if (holder.mProgressbar != null){

                    holder.mProgressbar.setVisibility(View.GONE);}
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                if (holder.mProgressbar != null){

                    holder.mProgressbar.setVisibility(View.GONE);}
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                if (holder.mProgressbar != null){

                   holder.mProgressbar.setVisibility(View.GONE);}
            }
        });
        return convertView;
    }
}
