package com.tinkerbell;

import android.app.Activity;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TruexAdRendererPackage implements ReactPackage {
    public TruexAdRendererPackage (Activity activity) {
    } // backwards compatibility

    public TruexAdRendererPackage () {
    }

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }

    // Deprecated RN 0.47
    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        TruexAdRendererManager truexAdRendererManager = new TruexAdRendererManager(reactContext);
        return Arrays.<ViewManager>asList(truexAdRendererManager);
    }
}
