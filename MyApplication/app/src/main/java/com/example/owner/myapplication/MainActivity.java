package com.example.owner.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView text1;
    TextView text2;
    Button table;
    Button map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        long now = System.currentTimeMillis();
        Date date = new Date(now);

        text1 = (TextView) findViewById(R.id.text1);
        text1.setText(date+"\n운행 시간이 아닙니다.");
        text2 = (TextView) findViewById(R.id.text2);
        table = (Button) findViewById(R.id.table);
        table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
            }
        });


        map = (Button) findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Map.class);
                startActivity(intent);
            }
        });
    }
    void show(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Shuttle Table");
        builder.setMessage("학 교  ↔ 삼선교\n" +
                "*학기중\n" +
                "오전:08:30 ~ 10:30(3대 수시운행)\n" +
                "점심:12:00 ~ 13:00(1대 수시운행)\n" +
                "저녁:17:00 ~ 19:00(2대 수시운행)\t" +
                "*방중\n" +
                "오전:09:30 ~ 10:30(2대 수시운행)\n" +
                "점심:12:00 ~ 13:00(1대 수시운행)\n" +
                "저녁:16:30 ~ 17:30(2대 수시운행)");
        builder.setNegativeButton("닫기",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.show();
    }

}