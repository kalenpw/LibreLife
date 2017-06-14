package com.kalenpw.librelife;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by kalenpw on 6/14/17.
 */

public class LifeCounterView extends View {

    Paint paint = new Paint();

    public LifeCounterView(Context context){
        super(context);
    }

    @Override
    public void onDraw(Canvas canvas){
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(3);
        canvas.drawRect(0, 0, getScreenWidth(), getScreenHeight() /2, paint);

        paint.setColor(Color.RED);
        paint.setStrokeWidth(1);
        canvas.drawRect(getScreenWidth(), getScreenHeight() / 2, getScreenWidth(), getScreenHeight(), paint);




    }

    private int getScreenWidth(){
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    private int getScreenHeight(){
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }
}