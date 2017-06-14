package com.kalenpw.librelife;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by kalenpw on 6/14/17.
 */

public class LifeCounterView extends View {

    Paint paint = new Paint();
    Paint strokePaint = new Paint();
    int[] colors = new int[8];
    LayoutManager layoutManager = new LayoutManager();

    public LifeCounterView(Context context){
        super(context);
        //TODO allow players to select their own color
        colors[0] = Color.BLUE;
        colors[1] = Color.YELLOW;
        colors[2] = Color.GREEN;
        colors[3] = Color.RED;

        Settings.defaultSettings();
        layoutManager.updateLayout();
    }

    @Override
    public void onDraw(Canvas canvas){

        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(4);
        strokePaint.setColor(Color.BLACK);
        strokePaint.setTextSize(96f);

        for(int i = 0; i < Gameboard.players.size(); i++){
            paint.setColor(colors[i]);
            canvas.drawRect(Gameboard.players.get(i).getRectangle(), paint);
            paint.setTextSize(96f);
            paint.setColor(Color.WHITE);

            Player currentPlayer = Gameboard.players.get(i);

            canvas.drawText(currentPlayer.getLifeCounter().getLifeTotalStr(),
                            currentPlayer.getRectangle().exactCenterX(),
                            currentPlayer.getRectangle().exactCenterY(), strokePaint);

            canvas.drawText(currentPlayer.getLifeCounter().getLifeTotalStr(),
                            currentPlayer.getRectangle().exactCenterX(),
                            currentPlayer.getRectangle().exactCenterY(), paint);

        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){


        int touchX = (int)event.getX();
        int touchY = (int)event.getY();

        int action = event.getAction();

        if(action == MotionEvent.ACTION_DOWN){
            System.out.println("Mouse down");
            for(int i = 0; i < Gameboard.players.size(); i++){
                if(Gameboard.players.get(i).getRectangle().contains(touchX, touchY)){
                    Gameboard.players.get(i).getLifeCounter().decreaseLifeBy(1);
                    System.out.println("Decreased life new total is: " + Gameboard.players.get(i).getLifeCounter().getLifeTotal());
                }
            }
            invalidate();
            return true;

        }
        else if(action == MotionEvent.ACTION_UP){
            System.out.println("clicked up");
            return true;

        }
        else{
            System.out.println("other");

        }

        return false;
    }


}