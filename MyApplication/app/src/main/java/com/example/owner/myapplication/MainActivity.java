package com.example.owner.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView text1;
    TextView text2;
    Button table;
    Button map;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text1 = (TextView) findViewById(R.id.text1);
        final int[] images = {R.drawable.busstop0, R.drawable.busstop0, R.drawable.busstop0,
                R.drawable.busstop0, R.drawable.busstop1, R.drawable.busstop1,
                R.drawable.busstop2, R.drawable.busstop3, R.drawable.busstop4,
                R.drawable.busstop5, R.drawable.busstop6, R.drawable.busstop7,
                R.drawable.busstop8, R.drawable.busstop8, R.drawable.busstop8,
                R.drawable.busstop9, R.drawable.busstop10, R.drawable.busstop11,
                R.drawable.busstop12, R.drawable.busstop13, R.drawable.busstop14, R.drawable.busstop1};

        final Handler timeHandler = new Handler(){
            public void handleMessage(Message msg){
                long now = System.currentTimeMillis();
                Date date = new Date(now);
                SimpleDateFormat dayformat = new SimpleDateFormat("MM월\tdd일");
                SimpleDateFormat timeformat = new SimpleDateFormat("HH시\tmm분");
                String dayformatDate = dayformat.format(date);
                String timeformatDate = timeformat.format(date);
                text1.setText(dayformatDate+"\t"+ timeformatDate);
            }
        };
        Runnable timeRunnable = new Runnable() {
            @Override
            public void run() {
                while(true){
                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException e){
                        e.getStackTrace();
                    }
                    timeHandler.sendEmptyMessage(1);
                }
            }
        };
        Thread timeThread = new Thread(timeRunnable);
        timeThread.start();

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
        imageView = (ImageView) findViewById(R.id.animation);
        final Handler imageHandler = new Handler(){
            public void handleMessage(Message msg){
                imageView.setImageResource(images[msg.what]);
                if(msg.what == 0){
                    text2.setText("현재 운행 중이 아닙니다.");
                } else if (msg.what == 1){
                    text2.setText("현재 운행 중이 아닙니다.");
                }else if (msg.what == 2){
                    text2.setText("현재 운행 중이 아닙니다.");
                }else if (msg.what == 3){
                    text2.setText("현재 운행 중이 아닙니다.");
                }else if (msg.what == 4){
                    text2.setText("현재 출발 대기 중입니다.");
                }else if (msg.what == 5){
                    text2.setText("현재 출발 대기 중입니다.");
                }else if (msg.what == 6){
                    text2.setText("버스가 출발하였습니다");
                }else if (msg.what == 12){
                    text2.setText("버스가 학교 역에 도착하였습니다.");
                }else if (msg.what == 13){
                    text2.setText("버스가 학교 역에 도착하였습니다.");
                }else if (msg.what == 14){
                    text2.setText("버스가 학교 역에 도착하였습니다.");
                }else if (msg.what == 21){
                    text2.setText("현재 출발 대기 중입니다.");
                }else{
                    text2.setText("버스가 운행중입니다.");
                }
            }
        };
        Runnable imageRunnable = new Runnable() {
            @Override
            public void run() {
                int i=0;
                while (i < 22) {
                    try {
                        Thread.sleep(3000);
                        Message msg = Message.obtain();
                        msg.what = i;
                        imageHandler.sendMessage(msg);
                        i++;
                    } catch (InterruptedException e) {
                        e.getStackTrace();
                    }
                }
            }
        };
        Thread imageThread = new Thread(imageRunnable);
        imageThread.start();
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