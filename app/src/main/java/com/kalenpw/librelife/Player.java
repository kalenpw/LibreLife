package com.kalenpw.librelife;

import android.graphics.Rect;

/**
 * Created by kalenpw on 6/14/17.
 */

public class Player {
    private Rect _Rectangle;
    private LifeCounter _LifeCounter;

    public Player(Rect playerRect){
        _LifeCounter = new LifeCounter(40);
        _Rectangle = playerRect;
    }

    public LifeCounter getLifeCounter(){
        return _LifeCounter;
    }

    public Rect getRectangle(){
        return _Rectangle;
    }




}
