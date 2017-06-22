package com.kalenpw.librelife;

import android.graphics.Rect;

/**
 * Created by kalenpw on 6/14/17.
 */

public class Player {
    private Rect _Rectangle;
    private LifeCounter _LifeCounter;

    public Player(Rect playerRect){
        //TODO get life total from settings
        _LifeCounter = new LifeCounter(40);
        _Rectangle = playerRect;
    }

    /**
     * Gets the player's LifeCounter
     * @return LifeCounter - LifeCounter belonging to player
     */
    public LifeCounter getLifeCounter(){
        return _LifeCounter;
    }

    /**
     * Gets the player's Rect used to display life total
     * @return Rect - Rect that this player's life total is displayed in
     */
    public Rect getRectangle(){
        return _Rectangle;
    }

}
