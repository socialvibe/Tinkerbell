package com.tinkerbell.tar;

import androidx.annotation.NonNull;

import com.facebook.react.BaseReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.uimanager.ViewManager;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TruexAdViewPackage extends BaseReactPackage {

    @NonNull
    @Override
    public List<ViewManager> createViewManagers(@NonNull ReactApplicationContext reactContext) {
        return Collections.singletonList(new TruexAdViewManager());
    }

    @Override
    public NativeModule getModule(String s, ReactApplicationContext reactApplicationContext) {
        if (TruexAdViewManager.REACT_CLASS.equals(s)) {
            return new TruexAdViewManager();
        }
        return null;
    }

    @NonNull
    @Override
    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        return new ReactModuleInfoProvider() {
            @NonNull
            @Override
            public Map<String, ReactModuleInfo> getReactModuleInfos() {
                Map<String, ReactModuleInfo> map = new HashMap<>();
                map.put(TruexAdViewManager.REACT_CLASS, new ReactModuleInfo(
                        TruexAdViewManager.REACT_CLASS, // name
                        TruexAdViewManager.REACT_CLASS, // className
                        false,                           // canOverrideExistingModule
                        false,                           // needsEagerInit
                        false,                           // isCxxModule
                        true                             // isTurboModule
                ));
                return map;
            }
        };
    }
}
