package com.kalenpw.librelife;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by kalenpw on 6/14/17.
 */

public class Settings {
    static private Context _Context;
    static public int NUMBER_OF_PLAYERS;
    static public int INITIAL_LIFE_TOTAL;

    public static void defaultSettings(){
        NUMBER_OF_PLAYERS = 4;
        INITIAL_LIFE_TOTAL = 40;
    }

    /**
     * Sets the context for this class used to for getDefaultSharedPreferences
     * @param Context value - the context to be set
     */
    public static void setContext(Context value){
        _Context = value;
    }

    /**
     * Updates the apps settings
     */
    public static void updateSettings(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(_Context);
    }

}
