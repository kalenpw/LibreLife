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

    Paint paint = new Paint();
    Paint strokePaint = new Paint();
    int[] colors = new int[8];
    LayoutManager layoutManager = new LayoutManager();
    float fontSize = 196f;
    int textCenterOffset = 80;

    int primaryPointerId = -1;
    int secondaryPointerId = -1;

    int circleRadius = 100;
    int screenHeight = getScreenHeight();
    int screenWidth = getScreenWidth();

    Context context;

    public LifeCounterView(Context newContext){
        super(newContext);
        context = newContext;
        //TODO allow players to select their own color
        colors[0] = Color.BLUE;
        colors[1] = Color.rgb(244, 188, 66);
        colors[2] = Color.GREEN;
        colors[3] = Color.RED;

        Settings.defaultSettings();
        layoutManager.updateLayout();
    }

    private int getScreenWidth(){
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    private int getScreenHeight(){
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    @Override
    public void onDraw(Canvas canvas){

        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(4);
        strokePaint.setColor(Color.BLACK);
        strokePaint.setTextSize(fontSize);

        for(int i = 0; i < Gameboard.players.size(); i++){
            paint.setColor(colors[i]);
            canvas.drawRect(Gameboard.players.get(i).getRectangle(), paint);
            paint.setTextSize(fontSize);
            paint.setColor(Color.WHITE);

            Player currentPlayer = Gameboard.players.get(i);

            canvas.drawText(currentPlayer.getLifeCounter().getLifeTotalStr(),
                            currentPlayer.getRectangle().exactCenterX() - textCenterOffset,
                            currentPlayer.getRectangle().exactCenterY(), strokePaint);

            canvas.drawText(currentPlayer.getLifeCounter().getLifeTotalStr(),
                            currentPlayer.getRectangle().exactCenterX() - textCenterOffset,
                            currentPlayer.getRectangle().exactCenterY(), paint);


        }

        paint.setColor(Color.BLACK);
        canvas.drawCircle(screenWidth / 2, screenHeight / 2, circleRadius, paint);

    }

    private void updateLifeTotals(MotionEvent event){
        int touchX = (int) event.getX();
        int touchY = (int) event.getY();
        System.out.println("single finger down");
        for (int i = 0; i < Gameboard.players.size(); i++) {
            if (Gameboard.players.get(i).getRectangle().contains(touchX, touchY)) {
                if (touchY > Gameboard.players.get(i).getRectangle().centerY()) {
                    Gameboard.players.get(i).getLifeCounter().decreaseLifeBy(-1);
                    System.out.println("Decreased life new total is: " + Gameboard.players.get(i).getLifeCounter().getLifeTotal());
                } else {
                    Gameboard.players.get(i).getLifeCounter().decreaseLifeBy(1);
                    System.out.println("Decreased life new total is: " + Gameboard.players.get(i).getLifeCounter().getLifeTotal());
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        float touchX = event.getX();
        float touchY = event.getY();

        if (action == MotionEvent.ACTION_DOWN) {
            float touchRadius = (float) Math.sqrt(Math.pow(touchX - (screenWidth / 2), 2) + Math.pow(touchY - (screenHeight / 2), 2));

            //Settings
            if (touchRadius < circleRadius) {
                System.out.println("touched in circle");
                Intent intent = new Intent(context, PrefFragment.class);
                context.startActivity(intent);
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



}