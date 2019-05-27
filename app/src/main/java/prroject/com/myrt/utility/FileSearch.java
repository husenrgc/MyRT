package prroject.com.myrt.utility;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import id.zelory.compressor.Compressor;

public class FileSearch {

    public static ArrayList<String> getDirectoryPaths(String directory) {
        ArrayList<String> pathArray = new ArrayList<>();
        File file = new File(directory);
        File[] listfiles = file.listFiles();
        for (int i = 0; i < listfiles.length; i++) {

            if (listfiles[i].isDirectory()) {

                pathArray.add(listfiles[i].getAbsolutePath());
            }
        }
        return pathArray;
    }

    public static ArrayList<String> getFilePaths(String directory) {



        ArrayList<String> pathArray = new ArrayList<>();
        File file = new File(directory);

        File[] listfiles = file.listFiles();
        for (int i = 0; i < listfiles.length; i++) {

            if (listfiles[i].isFile()) {

                pathArray.add(listfiles[i].getAbsolutePath());
            }
        }

return pathArray;
    }
}