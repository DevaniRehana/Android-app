package edu.uta.eventapp.uta_event;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.annotation.VisibleForTesting;

/**
 * Created by Abhishek Sharma on 30/4/16.
 */
public class Session {


    private SharedPreferences prefs;

    public Session(Context context) {

        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setusename(String usename) {
        prefs.edit().putString("usename", usename).commit();

    }

    public void seteventdetail(String eventname) {
        System.out.println("String Value" + eventname);
        prefs.edit().putString("eventname", eventname).commit();
    }

    public String getusename() {
        String usename = prefs.getString("usename","");
        return usename;
    }
    public String geteventdetail() {
        String eventname = prefs.getString("eventname","");
        return eventname;
    }

}


