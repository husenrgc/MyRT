package prroject.com.myrt.activity.addphoto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.File;
import java.io.IOException;
import java.nio.LongBuffer;
import java.util.ArrayList;
import java.util.BitSet;

import id.zelory.compressor.Compressor;
import prroject.com.myrt.R;
import prroject.com.myrt.utility.FilePath;
import prroject.com.myrt.utility.FileSearch;
import prroject.com.myrt.utility.GridImageAdapter;
import prroject.com.myrt.utility.UniversalImageLoader;

public class GalleryFragment extends Fragment {
private GridView gridView;
private ImageView galleryImage,galleryImage2;
private ProgressBar progressBar;
private Spinner dirSpinner;
Button scale , center;
private ArrayList<String> directories;
private  String mSelectedImage;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_gallery, container,false);
        gridView =(GridView) view.findViewById(R.id.gridViewGallery);
        dirSpinner = (Spinner) view.findViewById(R.id.spinnerdirectory);
        galleryImage = (ImageView) view.findViewById(R.id.galleryImageView);
        galleryImage = (ImageView)view.findViewById(R.id.galleryImageView2);
        progressBar =(ProgressBar) view.findViewById(R.id.progressbarGallery);

        ImageView btclose  = (ImageView) view.findViewById(R.id.closegallery);
        TextView btnext = (TextView) view.findViewById(R.id.tvnext);
        progressBar.setVisibility(View.GONE);
        directories = new ArrayList<>();




        btnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),NextActivity.class);
                intent.putExtra(getString(R.string.selected_image), mSelectedImage);
                startActivity(intent);
            }
        });

        init();
        return view;
    }
    private void initImageLoader(){
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(getActivity());
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }
private void init(){

    FilePath filePath = new FilePath();

    if (FileSearch.getDirectoryPaths(filePath.PICTURES) != null){

        directories = FileSearch.getDirectoryPaths(filePath.PICTURES);
    }
    ArrayList<String> directoryName = new ArrayList<>();
    directories.add(filePath.CAMERA);
    directories.add(filePath.SCREENSHOOT);

    directories.add(filePath.WhatsApp);

    for (int i =0; i < directories.size(); i++){

        int index = directories.get(i).lastIndexOf("/");
        String string = directories.get(i).substring(index);
        directoryName.add(string);
    }

    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
            android.R.layout.simple_spinner_item, directoryName);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    dirSpinner.setAdapter(adapter);
    dirSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            setupGridGallery(directories.get(i));
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    });
}
private void setupGridGallery(String selected){
    try {
        final  ArrayList<String> imgURLs = FileSearch.getFilePaths(selected);

        int gridwidht = getResources().getDisplayMetrics().widthPixels;
        int imgwidth = gridwidht/3;
        gridView.setColumnWidth(imgwidth);

    GridImageAdapter adapter = new GridImageAdapter(getActivity(), R.layout.layout_grid_image,"file:/", imgURLs);
    gridView.setAdapter(adapter);
    File img = new File(imgURLs.get(0));
    if (img == null){}else {


            galleryImage.setImageBitmap(BitmapFactory.decodeFile(img.getAbsolutePath()));
            File compressedImage = new Compressor(getActivity())
                    .setMaxWidth(640)
                    .setMaxHeight(480)
                    .setQuality(75)
                    .setCompressFormat(Bitmap.CompressFormat.JPEG)
                    .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_PICTURES).getAbsolutePath())
                    .compressToFile(img);
            Bitmap compressedImageBitmap = new Compressor(getActivity()).compressToBitmap(compressedImage);

            galleryImage.setImageBitmap(compressedImageBitmap);

            setImage(imgURLs.get(0), galleryImage, "file:/");



    }



mSelectedImage = imgURLs.get(0);
    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {




               setImage(imgURLs.get(i),galleryImage,"file:/");
               mSelectedImage = imgURLs.get(i);

        }
    });
    } catch (IOException e) {
        e.printStackTrace();
    }
}

private  void setImage(String imgURL, ImageView image, String append){

        initImageLoader();
    ImageLoader imageLoader = ImageLoader.getInstance();

    imageLoader.displayImage(append + imgURL, image, new ImageLoadingListener() {
        @Override
        public void onLoadingStarted(String imageUri, View view) {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
            progressBar.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            progressBar.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onLoadingCancelled(String imageUri, View view) {
            progressBar.setVisibility(View.INVISIBLE);
        }
    });


    }
}
