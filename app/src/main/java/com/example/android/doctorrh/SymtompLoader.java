package com.example.android.doctorrh;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

/**
 * This class load the symptomps object on the background
 */
public class SymtompLoader extends AsyncTaskLoader<String> {

    SymtompLoader(Context context){
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.getSymtompInfo();
    }
}
