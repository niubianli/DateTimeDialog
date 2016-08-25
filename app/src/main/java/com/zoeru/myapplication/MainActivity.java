package com.zoeru.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btn_time1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             DateDialog dateDialog=   new DateDialog(MainActivity.this,"获取当前时间日期", DateDialog.MODE_1, new DateDialog.InterfaceDateDialog() {
                    @Override
                    public void getTime(String dateTime) {
                        Toast.makeText(MainActivity.this,dateTime,Toast.LENGTH_LONG).show();
                    }
                } );
                dateDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dateDialog.show();
            }
        });



        findViewById(R.id.btn_time2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateDialog dateDialog=    new DateDialog(MainActivity.this,"获取当前时间日期", DateDialog.MODE_2, new DateDialog.InterfaceDateDialog() {
                    @Override
                    public void getTime(String dateTime) {
                        Toast.makeText(MainActivity.this,dateTime,Toast.LENGTH_LONG).show();
                    }
                } );
                dateDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dateDialog.show();
            }
        });

        findViewById(R.id.btn_time3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateDialog dateDialog=     new DateDialog(MainActivity.this,"获取当前时间日期", DateDialog.MODE_3, new DateDialog.InterfaceDateDialog() {
                    @Override
                    public void getTime(String dateTime) {
                        Toast.makeText(MainActivity.this,dateTime,Toast.LENGTH_LONG).show();
                    }
                } );
                dateDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dateDialog.show();
            }
        });

    }
}
