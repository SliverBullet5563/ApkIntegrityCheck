package com.yxcorp.gifshow;

import android.content.Context;
import android.util.Log;

import com.kwai.hotfix.loader.app.TinkerApplication;
import com.smile.gifmaker.Utils;

public class App extends TinkerApplication {
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        Log.d(Utils.TAG,"App attachBaseContext被调用");
    }

}
