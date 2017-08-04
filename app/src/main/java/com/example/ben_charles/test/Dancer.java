package com.example.ben_charles.test;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Ben_Charles on 7/27/17.
 */

public class Dancer extends View {

    private int circleCol, labelCol;
    private String circleText;
    private Paint circlePaint;

    private float startingPointerX;
    private float startingPointerY;
    private float startingViewX;
    private float startingViewY;

    public Dancer(Context context, AttributeSet attrs){
        super(context, attrs);
        circlePaint = new Paint();
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Dancer, 0, 0);

        try {
            circleText = a.getString(R.styleable.Dancer_circleLabel);
            circleCol = a.getInteger(R.styleable.Dancer_circleColor, 0);
            labelCol = a.getInteger(R.styleable.Dancer_labelColor, 0);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int viewWidthHalf = this.getMeasuredWidth()/8;
        int viewHeightHalf = this.getMeasuredHeight()/8;
        int radius = 0;
        if(viewWidthHalf>viewHeightHalf)
            radius=viewHeightHalf-10;
        else
            radius=viewWidthHalf-10;

        circlePaint.setStyle(Style.FILL);
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(circleCol);
        canvas.drawCircle(viewWidthHalf, viewHeightHalf, radius, circlePaint);
        circlePaint.setColor(labelCol);
        circlePaint.setTextAlign(Paint.Align.CENTER);
        circlePaint.setTextSize(50);
        canvas.drawText(circleText, viewWidthHalf, viewHeightHalf, circlePaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(dm);

        //int screenHeight = dm.heightPixels;
        //int screenWidth = dm.widthPixels;

        int screenHeight = this.getMeasuredHeight();
        int screenWidth = this.getMeasuredWidth();

        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                startingViewX = getX();
                startingViewY = getY();
                startingPointerX = event.getRawX();
                startingPointerY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float pointerX = event.getRawX();
                float pointerY = event.getRawY();

                float dx = pointerX - startingPointerX;
                float dy = pointerY - startingPointerY;

                float viewX = startingViewX + dx;
                float viewY = startingViewY + dy;

                if (viewX > 0 && viewX < screenWidth)
                    setX(viewX);

                if (viewY > 0 && viewY < screenHeight)
                    setY(viewY);

                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
        }


        return true;
    }

}
