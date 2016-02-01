package com.s13.mars.com.broadcasttest;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/1/26.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {

        if("com.s13.mars.com.broadcasttest.OFFILEN".equals(intent.getAction().toString())){
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
            dialogBuilder.setTitle("Warning");
            dialogBuilder.setMessage("You are forced to be offline. Please try to login again.");
                    dialogBuilder.setCancelable(false);
            dialogBuilder.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCollector.finishAll(); // 销毁所有活动
                            Intent intent = new Intent(context,LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent); // 重新启动LoginActivity
                        }
                    });
            AlertDialog alertDialog = dialogBuilder.create();
// 需要设置AlertDialog的类型，保证在广播接收器中可以正常弹出
            alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
            alertDialog.show();
        }else{
            Toast.makeText(context,"cuowu",Toast.LENGTH_SHORT).show();
        }

        };
    }

