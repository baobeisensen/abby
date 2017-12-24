package com.finance.abby.abby;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import static com.finance.abby.abby.AddActivity.DataBaseName;

public class ListActivity extends AppCompatActivity {
    private ListView lv;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        //初始化
        lv = findViewById(R.id.AListView);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
       // adapter = new ArrayAdapter<String>(this,R.layout.layout_cell);

        SQLiteDatabase sq = openOrCreateDatabase("COST.db", MODE_PRIVATE, null);
        sq.execSQL("Create Table if not exists " + DataBaseName + "(_id integer primary key autoincrement,cost_type text not null,cost_number number not null,inputTime TIMESTAMP default (datetime('now', 'localtime')))");
        Cursor c = null;
        try {
            c = sq.query("costBill",null,"_id>?",new String[]{"0"} ,null,null,"inputTime Desc");
        } catch (Exception e) {
            Toast.makeText(ListActivity.this,"请先添加数据",Toast.LENGTH_LONG);
            e.printStackTrace();
            return;
        }
        if (c==null){
            Toast.makeText(ListActivity.this,"没有记录",Toast.LENGTH_LONG);
        }else{
             Long sum = 0L;
            while (c.moveToNext()){
                String type = c.getString(c.getColumnIndex("cost_type"));//类型
                Long number = c.getLong(c.getColumnIndex("cost_number"));//金额
                sum = number+sum;
                Log.i("tag", "onCreate）））））））））））））））: "+number);
                adapter.add(type+" 花费金额为："+number);
            }
            adapter.add("收支总金额为："+sum);
        }
        lv.setAdapter(adapter);
        c.close();
    }
}
