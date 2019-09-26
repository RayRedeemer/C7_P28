package com.example.c7_p28;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, GestureDetector.OnGestureListener {

    private TextView textToFling;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textToFling = findViewById(R.id.textToFling);
        textToFling.setOnTouchListener(this);
        gestureDetector = new GestureDetector(this, this);
        DisplayMetrics displayMetrics = new DisplayMetrics();

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
        int xcoordinatePosition = (int) motionEvent1.getRawX();
        int ycoordinatePosition = (int) motionEvent1.getRawY() ;
        textToFling.setX(xcoordinatePosition);
        textToFling.setY(ycoordinatePosition);
        return true;
    }
}
