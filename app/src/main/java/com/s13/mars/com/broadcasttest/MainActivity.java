package com.s13.mars.com.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;


public class MainActivity extends BaseActivit {
    private IntentFilter intentFlter;
    private NetworkChangeReceiver networkChangeReceiver;


    private Button sendBroad;
    private Button offline;
    private Button login_out;

    private CheckBox autologin;
    ShareHelper shareHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     /*   //intentFlter 过滤Intent；白名单
        intentFlter = new IntentFilter();
        // android.net.conn.CONNECTIVITY_CHANGE 网络状态发生改变时,发出的广播
        intentFlter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        //registerReceiver 注册
        registerReceiver(networkChangeReceiver,intentFlter);
        */
        sendBroad = (Button)findViewById(R.id.sendBroadcast);
        offline = (Button)findViewById(R.id.force_offline);
        login_out = (Button)findViewById(R.id.login_out);
        autologin = (CheckBox)findViewById(R.id.autologin);

        shareHelper = new ShareHelper(getApplicationContext());


        String auto =  shareHelper.read("auto");
        if("y".equals(auto)){
            autologin.setChecked(true);
        }
        autologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(autologin.isChecked()){
                  shareHelper.save("auto","y");
              }else{
                  shareHelper.save("auto","n");
              }
            }
        });

        login_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCollector.finishAll();
            }
        });

        sendBroad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.s13.mars.com.broadcasttest.MY_BROADCAST");
                sendOrderedBroadcast(intent,null);
            }
        });

        offline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.s13.mars.com.broadcasttest.OFFILEN");
                sendBroadcast(intent);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

class NetworkChangeReceiver extends BroadcastReceiver {
    //NetworkChangeReceiver 每当网络变换时执行
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo!=null && networkInfo.isAvailable()){
            Toast.makeText(context, "net working", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "net no work", Toast.LENGTH_SHORT).show();
        }

    }
}
