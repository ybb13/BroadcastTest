package com.s13.mars.com.broadcasttest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/1/26.
 */
public class LoginActivity extends BaseActivit {
    private EditText accuntEdit;
    private EditText passEdit;
    private Button login;

    private CheckBox remenber;
    private CheckBox autologin;
    ShareHelper shelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        accuntEdit = (EditText)findViewById(R.id.account);
        passEdit = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.login);

        remenber = (CheckBox)findViewById(R.id.remenber_pass);
        autologin = (CheckBox)findViewById(R.id.autologin);

        shelper = new ShareHelper(getApplicationContext());

        String name = shelper.read("username");
        accuntEdit.setText(name);

        String pass = shelper.read("password");
        if(pass!=""){
            remenber.setChecked(true);
            passEdit.setText(pass);
        }
        String auto = shelper.read("auto");
        if("y".equals(auto)){
            Intent intent =new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

        Log.d("login",remenber.getText().toString());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String accunt = accuntEdit.getText().toString();
                String pass = passEdit.getText().toString();

                if("admin".equals(accunt)&&"123456".equals(pass)){
                    shelper.save("username",accunt);
                    if(remenber.isChecked()){
                        shelper.save("password",pass);
                    }else{
                        shelper.save("password","");
                    }
                    if(autologin.isChecked()){
                        shelper.save("auto","y");
                    }else{
                        shelper.save("auto","n");
                    }
                    Intent intent =new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
