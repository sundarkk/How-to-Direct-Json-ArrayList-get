package com.sundar.assignola.language;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;

import com.sundar.assignola.classes.CheckConnectivity;
import com.zookey.universalpreferences.UniversalPreferences;

import java.util.Locale;


public class Ezetap extends Application {
    public static final String TAG = Application.class
            .getSimpleName();
    private static Ezetap mInstance;

    public static synchronized Ezetap getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // instance of the class
        mInstance = this;

        //initialize shared prefs
        UniversalPreferences.initialize(this);


        // call method to set selected locale
        setResourceLocale(new Locale(UniversalPreferences.getInstance().get("SELECTED_LOCALE", "en").toString()));
        // call method to locale on the basis of language selection
        setLocale(UniversalPreferences.getInstance().get("SELECTED_LOCALE", "en").toString());

    }

    // setting locale for the whole application
    public void setResourceLocale(Locale locale) {
        if (Build.VERSION.SDK_INT >= 17) {
            getBaseContext().getResources().getConfiguration().setLocale(locale);
        } else {
            Configuration config = getResources().getConfiguration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        }
    }

    //for setting locale on the basis of language selection
    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }

    public void setConnectivityListener(CheckConnectivity.ConnectivityReceiverListener listener) {
        CheckConnectivity.connectivityReceiverListener = listener;
    }


}
