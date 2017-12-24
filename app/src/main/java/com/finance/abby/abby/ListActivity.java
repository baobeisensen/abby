package com.finance.abby.abby;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class ListActivity extends AppCompatActivity {
    private ListView listView;
    private ImageView headPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_list);
        SQLiteDatabase sq = openOrCreateDatabase("COST.db", MODE_PRIVATE, null);

        Cursor c = null;
        try {
            c = sq.query("costBill",null,"_id>?",new String[]{"0"} ,null,null,"inputTime Desc");
        } catch (Exception e) {
            Toast.makeText(ListActivity.this,"请先添加数据",Toast.LENGTH_LONG);
            //e.printStackTrace();
            return;

        }
        if (c==null){
            Toast.makeText(ListActivity.this,"没有记录",Toast.LENGTH_LONG);
        }else{
            String[] columns = c.getColumnNames();
            while (c.moveToNext()){
                for (String column :columns){
                    column = c.getString(c.getColumnIndex("cost_type"));
                    Log.i("TAG", "onCreate: column"+column);
                    Toast.makeText(ListActivity.this,column+".........................................................",Toast.LENGTH_LONG);
                }
            }
        }
    }
}

