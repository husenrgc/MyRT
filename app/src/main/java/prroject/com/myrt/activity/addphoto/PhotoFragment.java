package prroject.com.myrt.activity.addphoto;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import prroject.com.myrt.R;
import prroject.com.myrt.utility.Permissions;

public class PhotoFragment extends Fragment {
private static  final int camera_request_code =5;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_photo, container,false);
        Button   launcerCamera= (Button) view.findViewById(R.id.launcerCamera);
        launcerCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((addPhoto)getActivity()).gerCurrentTab() == 1){
                    if (((addPhoto)getActivity()).checkPermissions(Permissions.CAMERA_PERMISSION[0])){

                        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(camera, camera_request_code);
                    }else {

                        Intent intent = new Intent(getActivity(),addPhoto.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);

                    }


                }
            }
        });

        return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == camera_request_code){



        }
    }
}

