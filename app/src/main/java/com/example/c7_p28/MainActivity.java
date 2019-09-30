package com.example.c7_p28;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, GestureDetector.OnGestureListener {

    private TextView textToFling;
    private GestureDetector gestureDetector;
    private String MA = "MainActivity";
    private int width;
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textToFling = findViewById(R.id.textToFling);
        textToFling.setOnTouchListener(this);
        gestureDetector = new GestureDetector(this, this);
        WindowManager manager = this.getWindowManager();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(displayMetrics);
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (view.getId() == R.id.textToFling){
            gestureDetector.onTouchEvent(motionEvent);
            return true;
        }
        return true;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        int xNew;
        int yNew;
        float xOrigin = textToFling.getX();
        float yOrigin = textToFling.getY();
        int txtHeight = 367;
        int txtWidth = 363;
        int valve = 10000;
        if(v >= 0 && v1 >= 0){
            float xOffset = width - xOrigin - txtWidth;
            float yOffset = height - yOrigin - txtHeight;
            if(xOffset / v < yOffset / v1){
                xNew = width - txtHeight;
                yNew = (int) (yOrigin + xOffset * v1 / v);
            }
            else{
                xNew = (int)(xOrigin + yOffset * v / v1);
                yNew = height - txtHeight;
            }
        }
        else if(v >= 0 && v1 <0){
            float xOffset = width - xOrigin - txtWidth;
            float yOffset = yOrigin;
            if(xOffset / v < yOffset / Math.abs(v1)){
                xNew = width - txtWidth;
                yNew = (int) (yOrigin - xOffset * Math.abs(v1) / v);
            }
            else{
                xNew = (int)(xOrigin + yOffset * v / Math.abs(v1));
                yNew = 0;
            }
        }
        else if(v < 0 && v1 >=0){
            float xOffset = xOrigin;
            float yOffset = height - yOrigin - txtHeight;
            if(xOffset / Math.abs(v) < yOffset / v1){
                xNew = 0;
                yNew = (int) (yOrigin + xOffset * v1 / Math.abs(v));
            }
            else{
                xNew = (int)(xOrigin - yOffset * Math.abs(v) / v1);
                yNew = height - txtHeight;
            }
        }
        else{
            float xOffset = xOrigin;
            float yOffset = yOrigin;
            if(xOffset / Math.abs(v) < yOffset / Math.abs(v1)){
                xNew = 0;
                yNew = (int) (yOrigin - xOffset * Math.abs(v1 / v));
            }
            else{
                xNew = (int)(xOrigin - yOffset * Math.abs(v / v1));
                yNew = 0;
            }
        }
        double speed = Math.sqrt(v * v + v1 * v1);

        textToFling.setX(xNew);
        textToFling.setY(yNew);
        if (speed > valve){
            textToFling.setVisibility(View.GONE);
            Random rand = new Random();
            int newX = rand.nextInt(width - txtWidth);
            int newY = rand.nextInt(height - txtHeight);
            textToFling.setX(newX);
            textToFling.setY(newY);
            textToFling.setVisibility(View.VISIBLE);
        }
        return true;
    }
}
