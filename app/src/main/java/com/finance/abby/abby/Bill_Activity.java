package com.finance.abby.abby;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Bill_Activity extends AppCompatActivity implements View.OnClickListener {
    private ImageView addView, listView, serachView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_);
        addView = findViewById(R.id.addView);
        listView = findViewById(R.id.listView);
        serachView = findViewById(R.id.serachView);

        addView.setOnClickListener(this);
        listView.setOnClickListener(this);
        serachView.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view.getId()==addView.getId()){
            Intent intent = new Intent(Bill_Activity.this,AddActivity.class);
            startActivity(intent);
        }else if(view.getId()==listView.getId()){
            Intent intent = new Intent(Bill_Activity.this,ListActivity.class);
            startActivity(intent);
        }else if(view.getId()==serachView.getId()){
            Intent intent = new Intent(Bill_Activity.this,IncomeActivity.class);
            startActivity(intent);
        }

    }
}
