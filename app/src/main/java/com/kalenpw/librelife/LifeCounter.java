package com.kalenpw.librelife;

import android.graphics.Paint;

/**
 * Created by kalenpw on 6/14/17.
 */

public class LifeCounter {
    private Paint _paint;
    private int _lifeTotal;
    private int _commanaderDamage;

    public LifeCounter(int startingLifeTotal){
        _lifeTotal = startingLifeTotal;
    }

    public int getLifeTotal(){
        return _lifeTotal;
    }

    /**
     * Gets the life total formatted as a String
     * @return String lifeTotal
     */
    public String getLifeTotalStr(){
        return "" + _lifeTotal;
    }

    /**
     * Takes away life from total. Pass in negative number to gain life
     * @param int amount - amount of life to be added/subtracted
     */
    public void decreaseLifeBy(int amount){
        _lifeTotal -= amount;
    }



}
