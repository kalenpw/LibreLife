package com.kalenpw.librelife;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

/**
 * Created by kalenpw on 6/17/17.
 */

public class PrefFragment extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key){
        //TODO update stuff when settings are changed
        Settings.setContext(this);
        Settings.updateSettings();

        System.out.println("Number of players is: " + Settings.NUMBER_OF_PLAYERS);
        // LayoutManager layoutManager = new LayoutManager();
        LifeCounterView.setNumberOfPlayersChanged(true);

//        LifeCounterView  view = (LifeCounterView) findViewById(LifeCounterView._ViewId);
//        view.invalidate();

    }
}
