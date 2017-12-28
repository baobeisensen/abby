package com.finance.abby.abby;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.security.auth.login.LoginException;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class MainActivity extends Activity {
    private Button buttonlogin;
    private EditText userName, passWord;
    private ImageView headPhoto;
    private static int REQ = 1;
    //文件保存路径
    private String mFilepath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_main);
        //初始化组件
        buttonlogin = findViewById(R.id.login);
        userName = findViewById(R.id.userName);
        passWord = findViewById(R.id.password);
        headPhoto = findViewById(R.id.headPhoto);
        //初始化路径;获取系统的保存路径
        mFilepath = Environment.getExternalStorageDirectory().getPath();
        Log.i("TAG", "onCreate:" + mFilepath);
        //设置自己的保存路径
        mFilepath = mFilepath + "/Abby.png";
        //登录监听
        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("1".equals(userName.getText().toString()) && "1".equals(passWord.getText().toString())) {
                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Bill_Activity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "账号或密码错误", Toast.LENGTH_LONG).show();
                }
            }
        });

        headPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "选择上传图片方式";
                String[] items = new String[]{"拍照缩略图", "拍照高清图", "从相册选择照片"};

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(title)
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                                switch (which) {
                                    case 0:
                                        //展示缩略图
                                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                        startActivityForResult(intent, REQ);
                                        Toast.makeText(MainActivity.this, "缩略图", Toast.LENGTH_LONG).show();
                                        break;
                                    case 1:
                                        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                        Uri uri = Uri.fromFile(new File(mFilepath));
                                        //将拍照后路径更改为自己设定的路径
                                        intent2.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                                        startActivityForResult(intent2, 2);
                                        Toast.makeText(MainActivity.this, "高清头像", Toast.LENGTH_LONG).show();
                                        break;
                                    case 2:
                                        Toast.makeText(MainActivity.this, "还未开发", Toast.LENGTH_LONG).show();
                                        break;
                                }
                            }
                        })
                        .show();
                //第三方引用
                //ImageUtils.showImagePickDialog(MainActivity.this);
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQ) {
                Bundle bundle = data.getExtras();
                Bitmap b = (Bitmap) bundle.get("data");
                headPhoto.setImageBitmap(b);
            } else if (requestCode == 2) {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(mFilepath);
                    Bitmap bitmap = BitmapFactory.decodeStream(fis);
                    headPhoto.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Log.e("tag", e.toString());
                } finally {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e("tag", e.toString());
                    }
                }

            }
        }
    }
}
