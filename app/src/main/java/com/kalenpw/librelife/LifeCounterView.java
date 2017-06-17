package com.kalenpw.librelife;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.sql.SQLOutput;

/**
 * Created by kalenpw on 6/14/17.
 */

public class LifeCounterView extends View {

    Paint _DefaultPaint = new Paint();
    Paint _StrokePaint = new Paint();
    int[] _Colors = new int[8];
    LayoutManager _LayoutManager = new LayoutManager();
    float _FontSize = 196f;
    int _TextCenterOffset = 80;

    int _CircleRadius = 100;
    int _ScreenHeight = getScreenHeight();
    int _ScreenWidth = getScreenWidth();

    Context context;

    public LifeCounterView(Context newContext){
        super(newContext);
        context = newContext;
        //TODO allow players to select their own color
        _Colors[0] = Color.BLUE;
        _Colors[1] = Color.rgb(244, 188, 66);
        _Colors[2] = Color.GREEN;
        _Colors[3] = Color.RED;

        Settings.defaultSettings();
        _LayoutManager.updateLayout();
    }

    /**
     * Method called when screen is touched
     * @param MotionEvent - the Type of touching done
     * @return Boolean - True if the listener has consumed the event, false otherwise.
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        float touchX = event.getX();
        float touchY = event.getY();

        if (action == MotionEvent.ACTION_DOWN) {
            float touchRadius = (float) Math.sqrt(Math.pow(touchX - (_ScreenWidth / 2), 2) + Math.pow(touchY - (_ScreenHeight / 2), 2));

            //Settings
            if (touchRadius < _CircleRadius) {
                System.out.println("touched in circle");
                Intent intent = new Intent(context, PrefFragment.class);
                context.startActivity(intent);
                return true;
            }
            //Life total click
            else {
                updateLifeTotals(event);
                invalidate();
                return true;
            }
        }

        return false;
    }

    /**
     * Method called to draw the canvase
     * @param Canvas canvas - canvas on which to be drawn
     */
    @Override
    public void onDraw(Canvas canvas){

        _StrokePaint.setStyle(Paint.Style.STROKE);
        _StrokePaint.setStrokeWidth(4);
        _StrokePaint.setColor(Color.BLACK);
        _StrokePaint.setTextSize(_FontSize);

        for(int i = 0; i < Gameboard._Players.size(); i++){
            _DefaultPaint.setColor(_Colors[i]);
            canvas.drawRect(Gameboard._Players.get(i).getRectangle(), _DefaultPaint);
            _DefaultPaint.setTextSize(_FontSize);
            _DefaultPaint.setColor(Color.WHITE);

            Player currentPlayer = Gameboard._Players.get(i);

            canvas.drawText(currentPlayer.getLifeCounter().getLifeTotalStr(),
                    currentPlayer.getRectangle().exactCenterX() - _TextCenterOffset,
                    currentPlayer.getRectangle().exactCenterY(), _StrokePaint);

            canvas.drawText(currentPlayer.getLifeCounter().getLifeTotalStr(),
                    currentPlayer.getRectangle().exactCenterX() - _TextCenterOffset,
                    currentPlayer.getRectangle().exactCenterY(), _DefaultPaint);

        }

        _DefaultPaint.setColor(Color.BLACK);
        canvas.drawCircle(_ScreenWidth / 2, _ScreenHeight / 2, _CircleRadius, _DefaultPaint);

    }

    /**
     * Gets screen width of device
     * @return - Int screen width
     */
    private int getScreenWidth(){
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    /**
     * Gets screen height of device
     * @return - Int screen height
     */
    private int getScreenHeight(){
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }


    /**
     * Updates the life total amount when screen is clicked
     * @param MotionEvent event - the onClick event
     */
    private void updateLifeTotals(MotionEvent event){
        int touchX = (int) event.getX();
        int touchY = (int) event.getY();
        System.out.println("single finger down");
        for (int i = 0; i < Gameboard._Players.size(); i++) {
            if (Gameboard._Players.get(i).getRectangle().contains(touchX, touchY)) {
                if (touchY > Gameboard._Players.get(i).getRectangle().centerY()) {
                    Gameboard._Players.get(i).getLifeCounter().decreaseLifeBy(-1);
                } else {
                    Gameboard._Players.get(i).getLifeCounter().decreaseLifeBy(1);
                }
            }
        }
    }
}