package com.kalenpw.librelife;

import android.content.res.Resources;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by kalenpw on 6/14/17.
 */

public class LayoutManager {

    private ArrayList<Rect> _Rectangles = new ArrayList<>();

    public LayoutManager(){

    }

    /**
     * Updates the ordering of rectangles for life counters
     */
    public void updateLayout(){
        int numberOfPlayers = Settings.NUMBER_OF_PLAYERS;
        Gameboard._Players.clear();

        //Delcare all variables for layout
        Rect pOneRect;
        Rect pTwoRect;
        Rect pThreeRect;
        Rect pFourRect;

        Player pOne;
        Player pTwo;
        Player pThree;
        Player pFour;

        switch(numberOfPlayers){
            case 3:
                pOneRect = new Rect(0, 0, getScreenWidth(), getScreenHeight() / 3);
                pTwoRect = new Rect(0, getScreenHeight() / 3, getScreenWidth(), (2 * (getScreenHeight() / 3)));
                pThreeRect = new Rect(0, (2 *(getScreenHeight() / 3)), getScreenWidth(), getScreenHeight());

                pOne = new Player(pOneRect);
                pTwo = new Player(pTwoRect);
                pThree = new Player(pThreeRect);

                Gameboard._Players.add(pOne);
                Gameboard._Players.add(pTwo);
                Gameboard._Players.add(pThree);

                break;

            case 4:
                pOneRect = new Rect(0, 0, getScreenWidth() / 2, getScreenHeight() / 2);
                pTwoRect = new Rect(getScreenWidth() / 2, 0, getScreenWidth(), getScreenHeight() / 2);
                pThreeRect = new Rect(0, getScreenHeight() / 2, getScreenWidth() / 2, getScreenHeight());
                pFourRect = new Rect(getScreenWidth() / 2, getScreenHeight() / 2, getScreenWidth(), getScreenHeight());

                pOne = new Player(pOneRect);
                pTwo = new Player(pTwoRect);
                pThree = new Player(pThreeRect);
                pFour = new Player(pFourRect);

                Gameboard._Players.add(pOne);
                Gameboard._Players.add(pTwo);
                Gameboard._Players.add(pThree);
                Gameboard._Players.add(pFour);

                break;
        }

    }

    /**
     * Gets the width of device screen
     * @return Int - width of screen
     */
    private int getScreenWidth(){
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    /**
     * Gets the height of device screen
     * @return Int - height of screen
     */
    private int getScreenHeight(){
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

}
