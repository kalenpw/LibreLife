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

        switch(numberOfPlayers){
            case 3:


            case 4:
                Rect pOne = new Rect(0, 0, getScreenWidth() / 2, getScreenHeight() / 2);
                Rect pTwo = new Rect(getScreenWidth() / 2, 0, getScreenWidth(), getScreenHeight() / 2);
                Rect pThree = new Rect(0, getScreenHeight() / 2, getScreenWidth() / 2, getScreenHeight());
                Rect pFour = new Rect(getScreenWidth() / 2, getScreenHeight() / 2, getScreenWidth(), getScreenHeight());

                Player playerOne = new Player(pOne);
                Player playerTwo = new Player(pTwo);
                Player playerThree = new Player(pThree);
                Player playerFour = new Player(pFour);

                Gameboard._Players.add(playerOne);
                Gameboard._Players.add(playerTwo);
                Gameboard._Players.add(playerThree);
                Gameboard._Players.add(playerFour);
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
