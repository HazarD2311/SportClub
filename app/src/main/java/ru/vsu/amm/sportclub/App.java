package ru.vsu.amm.sportclub;


import android.app.Application;

import ru.vsu.amm.sportclub.db.HelperFactory;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        HelperFactory.setHelper(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        HelperFactory.releaseHelper();
        super.onTerminate();
    }
}
