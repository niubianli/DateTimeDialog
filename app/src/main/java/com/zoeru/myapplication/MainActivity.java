package com.zoeru.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DateDialog dialo = new DateDialog(this, new DateDialog.IntefaceDateDialog() {
            @Override
            public void getTime(String dateTime) {
                Toast.makeText(MainActivity.this,dateTime,Toast.LENGTH_LONG).show();
            }
        });
        dialo.show();

        findViewById(R.id.btn_time).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialo.show();
            }
        });
    }
}
