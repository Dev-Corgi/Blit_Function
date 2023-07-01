// MyNativeModule.java
package com.blit3;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import android.content.Intent;
import android.util.Log;
import com.blit3.HceService;

public class MyNativeModule extends ReactContextBaseJavaModule {
    private static final String TAG = "MyNativeModule";

    public MyNativeModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "MyNativeModule";
    }

    @ReactMethod
    public void startHceService() {
        Log.d(TAG, "HceService called");
        ReactApplicationContext context = getReactApplicationContext();
        Intent serviceIntent = new Intent(context, HceService.class);
        context.startService(serviceIntent);
        Log.d(TAG, "HceService started");
    }
}
