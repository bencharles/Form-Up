package com.example.ben_charles.test;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by Ben_Charles on 7/25/17.
 */

public class DraggableBox extends FrameLayout{

    private float startingPointerX;
    private float startingPointerY;
    private float startingViewX;
    private float startingViewY;

    public DraggableBox(@NonNull Context context) {
        super(context);
    }

    public DraggableBox(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DraggableBox(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(dm);

        int screenHeight = dm.heightPixels;
        int screenWidth = dm.widthPixels;

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

                if (viewX > 0 && viewX < screenHeight)
                    setX(viewX);

                if (viewY > 0 && viewY < screenWidth)
                    setY(viewY);

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
        }


        return true;
    }

}
