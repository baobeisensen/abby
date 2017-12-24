package com.finance.abby.abby;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class MainActivity extends AppCompatActivity {
    private Button buttonlogin;
    private EditText userName,passWord;
    private ImageView headPhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_main);
        buttonlogin = findViewById(R.id.login);
        userName = findViewById(R.id.userName);
        passWord =findViewById(R.id.password);
        headPhoto =findViewById(R.id.headPhoto);
        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if("1".equals(userName.getText().toString())&&"1".equals(passWord.getText().toString())){
                    Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,Bill_Activity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this,"账号或密码错误",Toast.LENGTH_LONG).show();
                }
            }
        });
        headPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"点击换头像",Toast.LENGTH_LONG);

            }
        });
    }
}
