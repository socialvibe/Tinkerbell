package com.tinkerbell;

import androidx.annotation.Nullable;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.util.Map;

public class TruexAdRendererManager extends ViewGroupManager<TruexAdRendererView> {

    public TruexAdRendererManager(ReactApplicationContext reactContext) {
        super();
    }

    private static final int COMMAND_START_AD = 1;

    @Override
    public String getName() {
        return "TruexAdRenderer";
    }

    @Override
    public TruexAdRendererView createViewInstance(ThemedReactContext context) {
        return new TruexAdRendererView(context);
    }

    @Override
    public Map<String,Integer> getCommandsMap() {
        return MapBuilder.of("startAd", COMMAND_START_AD);
    }

    @Override
    public void receiveCommand(TruexAdRendererView view, int commandType, @Nullable ReadableArray args) {
        Assertions.assertNotNull(view);
        Assertions.assertNotNull(args);

        switch (commandType) {
            case COMMAND_START_AD: {
                view.startAd();
                return;
            }

            default:
                throw new IllegalArgumentException(String.format(
                        "Unsupported command %d received by %s.",
                        commandType,
                        getClass().getSimpleName()));
        }
    }

    @Override
    public @Nullable Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of("onEvent", MapBuilder.of("registrationName", "onAdEvent"));
    }

    @ReactProp(name="networkUserID")
    public void setNetworkUserID(TruexAdRendererView view, String networkUserID) {
        view.setNetworkUserID(networkUserID);
    }

    @ReactProp(name="placementHash")
    public void setPlacementHash(TruexAdRendererView view, String placementHash) {
        view.setPlacementHash(placementHash);
    }

    @ReactProp(name="vastConfigURL")
    public void setVastConfigURL(TruexAdRendererView view, String vastConfigURL) {
        view.setVastConfigURL(vastConfigURL);
    }
}
