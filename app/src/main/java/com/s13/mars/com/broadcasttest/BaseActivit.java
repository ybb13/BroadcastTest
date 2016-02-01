package com.s13.mars.com.broadcasttest;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Administrator on 2016/1/26.
 */
public class BaseActivit extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addactivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeactivity(this);
    }
}
