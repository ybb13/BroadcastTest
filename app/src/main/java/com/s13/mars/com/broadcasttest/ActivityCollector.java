package com.s13.mars.com.broadcasttest;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/26.
 */
public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<Activity>();

    public static void addactivity(Activity activity){
        activities.add(activity);
    }

    public static void removeactivity(Activity activity){
        activities.remove(activity);
    }

    public static void finishAll(){
        for (Activity activity : activities) {
            activity.finish();
        }
    }
}
