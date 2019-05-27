package prroject.com.myrt.activity.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import prroject.com.myrt.R;
import prroject.com.myrt.Result.ResultFeeds;
import prroject.com.myrt.utility.squareImageView;

public class RecycleFeed extends RecyclerView.Adapter<RecycleFeed.ViewHolder> {

    public static final String URL = "http://192.168.43.123/MyRT/api/";
    private Context context;
    private List<ResultFeeds> results;

    public RecycleFeed(Context context, List<ResultFeeds> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_home ,parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final ResultFeeds result = results.get(position);
        String imgurl =URL +"upload/"+ result.getGambar();
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage("" + imgurl, holder.img, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                if (holder.mprogres != null){

                    holder.mprogres.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                if (holder.mprogres != null){

                    holder.mprogres.setVisibility(View.GONE);

                }
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                if (holder.mprogres != null){

                    holder.mprogres.setVisibility(View.GONE);

                }
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                if (holder.mprogres != null){

                    holder.mprogres.setVisibility(View.GONE);

                }
            }
        });
        if (result.getCaption().equals("")){
            holder.caption.setVisibility(View.GONE);
        }
        else
        {
            holder.caption.setVisibility(View.VISIBLE);
        holder.caption.setText(result.getCaption());}



    }

    @Override
    public int getItemCount() {
       return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView img;
        CircleImageView cimg;
        TextView caption;
        TextView username;
        ProgressBar mprogres;

        public ViewHolder(View itemView) {
            super(itemView);

            img =(ImageView) itemView.findViewById(R.id.imgHomestatus);
            cimg =(CircleImageView) itemView.findViewById(R.id.imgHomeprofile);
            caption =(TextView) itemView.findViewById(R.id.captionHome);
            username =(TextView) itemView.findViewById(R.id.tv_name_home);
            mprogres = (ProgressBar) itemView.findViewById(R.id.progreshome);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

        }
    }
}
