package com.example.sandhya.android.smartbeings_foodorder.application;

import android.app.Application;

public class AppApplication extends Application {

    private static AppApplication sINSTANCE;

    //use getInstance() instead of getApplicationContext()
    public static AppApplication getInstance() {
        if (sINSTANCE == null)
            sINSTANCE = new AppApplication();

        return sINSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sINSTANCE = this;
    }
}
