package com.finance.abby.abby;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    private Button buttonSave;
    private EditText costType, costNumber;
    public static final String DataBaseName = "costBill";
    private Long resulta=0L;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        buttonSave = findViewById(R.id.save);
        costType = findViewById(R.id.costType);
        costNumber = findViewById(R.id.costNumber);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = costType.getText().toString();
                Double number = 0-Double.parseDouble(costNumber.getText().toString());
                SQLiteDatabase sqldb = openOrCreateDatabase("COST.db", MODE_PRIVATE, null);
                sqldb.execSQL("Create Table if not exists " + DataBaseName + "(_id integer primary key autoincrement,cost_type text not null,cost_number number not null,inputTime TIMESTAMP default (datetime('now', 'localtime')))");
                ContentValues contentValues = new ContentValues();
                contentValues.put("cost_type", type);
                contentValues.put("cost_number", number);
                Long resuleLine = sqldb.insert(DataBaseName, null, contentValues);
                sqldb.close();
                contentValues.clear();
                if (resuleLine != 0) {
                    resulta = resuleLine;
                    Toast.makeText(AddActivity.this, "保存成功", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AddActivity.this, ListActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(AddActivity.this, "保存异常", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
