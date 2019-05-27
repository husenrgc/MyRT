package prroject.com.myrt.utility;

import android.os.Environment;

public class FilePath {

    public  String ROOT_DIR= Environment.getExternalStorageDirectory().getPath();
    public  String PICTURES = ROOT_DIR + "/Pictures";
    public  String SCREENSHOOT = ROOT_DIR+"/DCIM/screenshots";
    public String CAMERA = ROOT_DIR + "/DCIM/camera";
    public  String WhatsApp = ROOT_DIR+"/WhatsApp/Media/WhatsApp Images";
}
