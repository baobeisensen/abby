package com.finance.abby.abby;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    private Button buttonSave;
    private EditText costType,costNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        buttonSave= findViewById(R.id.save);
        costType= findViewById(R.id.costType);
        costNumber= findViewById(R.id.costNumber);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase sqldb = openOrCreateDatabase("COST.db",MODE_PRIVATE,null);
                sqldb.execSQL("Create Table if not exists costBill(_id integer primary key autoincrement,cost_type text not null,cost_number number not null,inputTime date default sysdate())");
            }
        });

    }
}
