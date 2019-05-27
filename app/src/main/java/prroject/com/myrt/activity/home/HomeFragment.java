package prroject.com.myrt.activity.home;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import prroject.com.myrt.R;
import prroject.com.myrt.Result.ResultFeeds;
import prroject.com.myrt.api.RegisterAPI;
import prroject.com.myrt.api.Value;
import prroject.com.myrt.utility.UniversalImageLoader;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {
    public static final String URL = "http://192.168.43.123/MyRT/api/";
    private List<ResultFeeds> results = new ArrayList<>();
    private RecycleFeed viewAdapter;
    ProgressBar mProgressbar;
  RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_home, container,false);
        mProgressbar = (ProgressBar) view.findViewById(R.id.homeProgresBar);
        mProgressbar.setVisibility(View.GONE);
    recyclerView = (RecyclerView) view.findViewById(R.id.recHome);
        initImageLoader();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(viewAdapter);

        loadDataproduk();

        return view;

    }
    private void initImageLoader(){
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(getActivity());
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }
    private void loadDataproduk() {
        mProgressbar.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterAPI api = retrofit.create(RegisterAPI.class);
        Call<Value> call = api.readimg();
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
              mProgressbar.setVisibility(View.GONE);
                String value = response.body().getValue();

                if (value.equals("1")) {

                    results = response.body().getResultFeed();
                    viewAdapter = new RecycleFeed(getActivity(), results);
                    recyclerView.setAdapter(viewAdapter);
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                mProgressbar.setVisibility(View.GONE);
            }
        });
    }



}
