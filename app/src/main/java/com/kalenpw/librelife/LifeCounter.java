package com.kalenpw.librelife;

import android.graphics.Paint;

/**
 * Created by kalenpw on 6/14/17.
 */

public class LifeCounter {
    private Paint _Paint;
    private int _LifeTotal;
    private int _CommanaderDamage;

    public LifeCounter(int startingLifeTotal){
        _LifeTotal = startingLifeTotal;
    }

    /**
     * Gets the current life total amount
     * @return Int - remaining life
     */
    public int getLifeTotal(){
        return _LifeTotal;
    }

    /**
     * Gets the life total formatted as a String
     * @return String lifeTotal
     */
    public String getLifeTotalStr(){
        return "" + _LifeTotal;
    }

    /**
     * Takes away life from total. Pass in negative number to gain life
     * @param int amount - amount of life to be added/subtracted
     */
    public void decreaseLifeBy(int amount){
        _LifeTotal -= amount;
        if(_LifeTotal < 0){
            _LifeTotal = 0;
        }
    }

}
