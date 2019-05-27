package prroject.com.myrt.utility;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    public static final String MyRT = "spMyRT";

    public static final String SP_NAMA = "spNama";
    public static final String SP_ID_USER = "spIduser";
    public static final String SP_NAMA_L = "spNama_L";
    public static final String SP_SUDAH_LOGIN = "spSudahLogin";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context context){
        sp = context.getSharedPreferences(MyRT, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public String getNamaL(){ return  sp.getString(SP_NAMA_L,"");}

    public String getSPNama(){
        return sp.getString(SP_NAMA, "");
    }

    public String getSPidUser(){
        return sp.getString(SP_ID_USER, "");
    }


    public Boolean getSPSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }
}
