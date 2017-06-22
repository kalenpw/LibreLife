package com.kalenpw.librelife;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

    int[] _DigitOffSet = new int[3];

    int _CircleRadius = 100;
    int _ScreenHeight = getScreenHeight();
    int _ScreenWidth = getScreenWidth();

    int _HalfScreenWidth = _ScreenWidth / 2;
    int _HalfScreenHeight = _ScreenHeight / 2;

    private static boolean _numberOfPlayersChanged;
    private static int _ViewId;

    int _SettingsIconSize = 100;
    int _IconOffSet = _SettingsIconSize / 2;

    Context context;

    public LifeCounterView(Context newContext){
        super(newContext);
        context = newContext;
        //TODO allow players to select their own color
        _Colors[0] = Color.BLUE;
        _Colors[1] = Color.rgb(244, 188, 66);
        _Colors[2] = Color.GREEN;
        _Colors[3] = Color.RED;

        _DigitOffSet[0] = 20;
        _DigitOffSet[1] = 80;
        _DigitOffSet[2] = 160;

        Settings.setContext(context);
        Settings.updateSettings();

        _LayoutManager.updateLayout();

        _ViewId = this.generateViewId();
    }

    /**
     * Method called when screen is touched
     * @param MotionEvent - the Type of touching done
     * @return Boolean - True if the listener has consumed the event, false otherwise.
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //Not really necessary but here in case things change
        //Updates the view, but this is already done in onDraw()
        if(_numberOfPlayersChanged){
            _numberOfPlayersChanged = false;
            _LayoutManager.updateLayout();
            invalidate();
        }

        int action = event.getActionMasked();
        float touchX = event.getX();
        float touchY = event.getY();

        if (action == MotionEvent.ACTION_DOWN) {
            float touchRadius = (float) Math.sqrt(Math.pow(touchX - _HalfScreenWidth, 2) + Math.pow(touchY - _HalfScreenHeight, 2));

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
    public void onDraw(Canvas canvas) {

        if(_numberOfPlayersChanged){
            _numberOfPlayersChanged = false;
            _LayoutManager.updateLayout();
            invalidate();
        }

        //Clear canvas
        canvas.drawColor(Color.BLACK);

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

            int currentLifeTotal = currentPlayer.getLifeCounter().getLifeTotal();
            int numberOfDigits = String.valueOf(currentLifeTotal).length();
            numberOfDigits = numberOfDigits - 1; //Offest by one to match array index
            _TextCenterOffset = _DigitOffSet[numberOfDigits];

            canvas.drawText(currentPlayer.getLifeCounter().getLifeTotalStr(),
                    currentPlayer.getRectangle().exactCenterX() - _TextCenterOffset,
                    currentPlayer.getRectangle().exactCenterY(), _StrokePaint);

            canvas.drawText(currentPlayer.getLifeCounter().getLifeTotalStr(),
                    currentPlayer.getRectangle().exactCenterX() - _TextCenterOffset,
                    currentPlayer.getRectangle().exactCenterY(), _DefaultPaint);

        }

        _DefaultPaint.setColor(Color.WHITE);
        canvas.drawCircle(_HalfScreenWidth, _HalfScreenHeight, _CircleRadius, _DefaultPaint);
        _StrokePaint.setStrokeWidth(20);
        canvas.drawCircle(_HalfScreenWidth, _HalfScreenHeight, _CircleRadius, _StrokePaint);
        _StrokePaint.setStrokeWidth(4);


        Resources res = getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.settings_ion);
        bitmap = Bitmap.createScaledBitmap(bitmap, _SettingsIconSize, _SettingsIconSize, false);

        _DefaultPaint.setColor(Color.WHITE);
        canvas.drawBitmap(bitmap, _HalfScreenWidth - _IconOffSet, _HalfScreenHeight - _IconOffSet, _DefaultPaint);

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
                    Gameboard._Players.get(i).getLifeCounter().decreaseLifeBy(1);
                } else {
                    Gameboard._Players.get(i).getLifeCounter().decreaseLifeBy(-1);
                }
            }
        }
    }

    /**
     * Allows the canvas to be "redrawn" from other methods
     * @param value
     */
    public static void setNumberOfPlayersChanged(boolean value){
        _numberOfPlayersChanged = value;
    }

    public static int getViewId(){
        return _ViewId;
    }
}