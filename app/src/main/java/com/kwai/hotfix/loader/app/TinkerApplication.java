package com.kwai.hotfix.loader.app;

import android.app.Application;
import android.content.Context;

import com.smile.gifmaker.hookpms.ServiceManagerWraper;

public class TinkerApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        ServiceManagerWraper.hookPMS(base);
        super.attachBaseContext(base);
    }
}
