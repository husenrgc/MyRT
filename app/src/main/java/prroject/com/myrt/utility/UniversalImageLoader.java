package prroject.com.myrt.utility;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.module.AppGlideModule;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.util.concurrent.Executor;

import prroject.com.myrt.R;

public class UniversalImageLoader  {

    private  static final int defaultImage = R.drawable.ic_android;
    private Context mContex;

    public UniversalImageLoader(Context mContex) {
        this.mContex = mContex;
    }

    public ImageLoaderConfiguration getConfig(){
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(defaultImage)
                .showImageForEmptyUri(defaultImage)
                .showImageOnFail(defaultImage)
                .cacheOnDisk(true).cacheInMemory(true)
                .cacheOnDisk(true).resetViewBeforeLoading(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();



    ImageLoaderConfiguration  configuration = new ImageLoaderConfiguration.Builder(mContex)
            .defaultDisplayImageOptions(defaultOptions)

            .imageDownloader(new CustomImageDownaloder(mContex))
            .memoryCache(new WeakMemoryCache())
            .diskCacheSize(100 * 100 *100).build();



    return configuration;
    }
            public static void setImage(String imgURL, ImageView image, final ProgressBar mProgressbar, String append) {

                ImageLoader imageLoader = ImageLoader.getInstance();
                imageLoader.displayImage(append + imgURL, image, new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {
                        if (mProgressbar != null) {

                            mProgressbar.setVisibility(View.VISIBLE);

                        }
                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                        if (mProgressbar != null) {

                            mProgressbar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        if (mProgressbar != null) {

                            mProgressbar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onLoadingCancelled(String imageUri, View view) {
                        if (mProgressbar != null) {

                            mProgressbar.setVisibility(View.GONE);
                        }
                    }
                });

    }

}
