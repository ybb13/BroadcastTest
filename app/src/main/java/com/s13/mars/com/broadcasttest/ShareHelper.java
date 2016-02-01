package com.s13.mars.com.broadcasttest;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/1.
 */
public class ShareHelper {

    private Context context;

    public ShareHelper(){}

    public ShareHelper(Context mcontext){
        this.context=mcontext;
    }

    public void save(String key , String value){
        SharedPreferences pre = context.getSharedPreferences("mysq",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();
        editor.putString(key,value);
        editor.commit();
    }

    public String read(String key) {
        SharedPreferences pre = context.getSharedPreferences("mysq",Context.MODE_PRIVATE);
        return pre.getString(key,"");
    }
}
