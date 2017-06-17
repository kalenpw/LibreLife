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

                Gameboard.players.add(playerOne);
                Gameboard.players.add(playerTwo);
                Gameboard.players.add(playerThree);
                Gameboard.players.add(playerFour);
        }

    }



    private int getScreenWidth(){
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    private int getScreenHeight(){
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

}
