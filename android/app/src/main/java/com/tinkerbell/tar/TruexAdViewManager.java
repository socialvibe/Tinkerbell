package com.tinkerbell.tar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.viewmanagers.TruexAdViewManagerDelegate;
import com.facebook.react.viewmanagers.TruexAdViewManagerInterface;

import java.util.HashMap;
import java.util.Map;

@ReactModule(name = TruexAdViewManager.REACT_CLASS)
class TruexAdViewManager extends SimpleViewManager<TruexAdView> implements TruexAdViewManagerInterface<TruexAdView> {

    public static final String REACT_CLASS = "TruexAdView";

    private final TruexAdViewManagerDelegate<TruexAdView, TruexAdViewManager> delegate =
            new TruexAdViewManagerDelegate<>(this);

    @Override
    public ViewManagerDelegate<TruexAdView> getDelegate() {
        return delegate;
    }

    @NonNull
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    public TruexAdView createViewInstance(@NonNull ThemedReactContext context) {
        return new TruexAdView(context);
    }

    @ReactProp(name = "vastConfigUrl")
    @Override
    public void setVastConfigUrl(TruexAdView view, String vastConfigUrl) {
        if (vastConfigUrl == null || vastConfigUrl.isEmpty() || vastConfigUrl.isBlank()) {
            view.emitAdError("missing or invalid vast config url: " + vastConfigUrl);
            return;
        }
        view.startAd(vastConfigUrl);
    }

    @Nullable
    @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        Map<String, String> registrationNames = new HashMap<>();
        registrationNames.put("registrationName", "onAdEvent");

        Map<String, Object> eventTypeConstants = super.getExportedCustomDirectEventTypeConstants();
        assert eventTypeConstants != null;
        eventTypeConstants.put("onAdEvent", registrationNames);

        return eventTypeConstants;
    }
}
