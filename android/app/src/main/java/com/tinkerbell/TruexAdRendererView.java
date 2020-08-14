package com.tinkerbell;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.views.view.ReactViewGroup;
import com.truex.adrenderer.IEventEmitter;
import com.truex.adrenderer.TruexAdRenderer;
import com.truex.adrenderer.TruexAdRendererConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class TruexAdRendererView extends ReactViewGroup {
    private static final String CLASSTAG = TruexAdRendererView.class.getSimpleName();

    private String networkUserID;
    private String placementHash;
    private String vastConfigURL;

    private TruexAdRenderer truexAdRenderer;

    public TruexAdRendererView(Context context) {
        super(context);

        // Set-up the true[X] ad renderer
        truexAdRenderer = new TruexAdRenderer(context);

        // Set-up the event listeners
        truexAdRenderer.addEventListener(TruexAdRendererConstants.AD_STARTED, this.adStarted);
        truexAdRenderer.addEventListener(TruexAdRendererConstants.AD_COMPLETED, this.adCompleted);
        truexAdRenderer.addEventListener(TruexAdRendererConstants.AD_ERROR, this.adError);
        truexAdRenderer.addEventListener(TruexAdRendererConstants.NO_ADS_AVAILABLE, this.noAds);
        truexAdRenderer.addEventListener(TruexAdRendererConstants.AD_FREE_POD, this.adFree);
        truexAdRenderer.addEventListener(TruexAdRendererConstants.POPUP_WEBSITE, this.popup);
        truexAdRenderer.addEventListener(TruexAdRendererConstants.USER_CANCEL, this.userCancel);
        truexAdRenderer.addEventListener(TruexAdRendererConstants.OPT_IN, this.optIn);
        truexAdRenderer.addEventListener(TruexAdRendererConstants.OPT_OUT, this.optOut);
        truexAdRenderer.addEventListener(TruexAdRendererConstants.SKIP_CARD_SHOWN, this.skipCardShown);
        truexAdRenderer.addEventListener(TruexAdRendererConstants.AD_FETCH_COMPLETED, this.adFetchCompleted);
        truexAdRenderer.addEventListener(TruexAdRendererConstants.VIDEO_STARTED, this.videoStarted);
        truexAdRenderer.addEventListener(TruexAdRendererConstants.VIDEO_FIRST_QUARTILE, this.videoFirstQuartile);
        truexAdRenderer.addEventListener(TruexAdRendererConstants.VIDEO_SECOND_QUARTILE, this.videoSecondQuartile);
        truexAdRenderer.addEventListener(TruexAdRendererConstants.VIDEO_THIRD_QUARTILE, this.videoThirdQuartile);
        truexAdRenderer.addEventListener(TruexAdRendererConstants.VIDEO_COMPLETED, this.videoCompleted);
    }

    public void setNetworkUserID(String networkUserID) {
        this.networkUserID = networkUserID;
    }

    public void setPlacementHash(String placementHash) {
        this.placementHash = placementHash;
    }

    public void setVastConfigURL(String vastConfigURL) {
        this.vastConfigURL = vastConfigURL;
    }

    public void startAd() {
        Assertions.assertNotNull(truexAdRenderer);
        Assertions.assertNotNull(networkUserID);
        Assertions.assertNotNull(placementHash);
        Assertions.assertNotNull(vastConfigURL);

        try {
            String json = String.format("{\"user_id\":\"%s\",\"placement_hash\":\"%s\",\"vast_config_url\":\"%s\"}\n", networkUserID, placementHash, vastConfigURL);
            JSONObject adParams = new JSONObject(json);

            truexAdRenderer.init(adParams, TruexAdRendererConstants.PREROLL);
            truexAdRenderer.start((ViewGroup) this.getRootView());
        } catch (JSONException e) {
            Log.e(CLASSTAG, "JSON ERROR");
        }
    }

    private void receiveEvent(String name, Map<String, ?> data) {
        WritableMap event = Arguments.createMap();
        event.putString("key", name);
        if (data != null) {
            for (Map.Entry<String, ?> entry : data.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value instanceof String) {
                    event.putString(key, (String) value);
                } else if (value instanceof Integer) {
                    event.putInt(key, (int) value);
                } else if (value instanceof Double) {
                    event.putDouble(key, (Double) value);
                } else if (value instanceof Boolean) {
                    event.putBoolean(key, (Boolean) value);
                }
            }
        }

        ReactContext reactContext = (ReactContext)getContext();
        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                getId(),
                "onEvent",
                event);
    }

    /*
   Note: This event is triggered when the ad starts
 */
    private IEventEmitter.IEventHandler adStarted = (String eventName, Map<String, ?> data) -> {
        receiveEvent(TruexAdRendererConstants.AD_STARTED, data);
    };

    /*
       Note: This event is triggered when the engagement is completed,
       either by the completion of the engagement or the user exiting the engagement
     */
    private IEventEmitter.IEventHandler adCompleted = (String eventName, Map<String, ?> data) -> {
        receiveEvent(TruexAdRendererConstants.AD_COMPLETED, data);
    };

    /*
       Note: This event is triggered when an error is encountered by the true[X] ad renderer
     */
    private IEventEmitter.IEventHandler adError = (String eventName, Map<String, ?> data) -> {
        receiveEvent(TruexAdRendererConstants.AD_ERROR, data);
    };

    /*
       Note: This event is triggered if the engagement fails to load,
       as a result of there being no engagements available
     */
    private IEventEmitter.IEventHandler noAds = (String eventName, Map<String, ?> data) -> {
        receiveEvent(TruexAdRendererConstants.NO_ADS_AVAILABLE, data);
    };

    /*
       Note: This event is not currently being used
     */
    private IEventEmitter.IEventHandler popup = (String eventName, Map<String, ?> data) -> {
        receiveEvent(TruexAdRendererConstants.POPUP_WEBSITE, data);
    };

    /*
       Note: This event is triggered when the viewer has earned their true[ATTENTION] credit. We
       could skip over the linear ads here, so that when the ad is complete, all we would need
       to do is resume the stream.
     */
    private IEventEmitter.IEventHandler adFree = (String eventName, Map<String, ?> data) -> {
        receiveEvent(TruexAdRendererConstants.AD_FREE_POD, data);
    };

    /*
       Note: This event is triggered when a user cancels an interactive engagement
     */
    private IEventEmitter.IEventHandler userCancel = (String eventName, Map<String, ?> data) -> {
        receiveEvent(TruexAdRendererConstants.USER_CANCEL, data);
    };

    /*
       Note: This event is triggered when a user opts-in to an interactive engagement
     */
    private IEventEmitter.IEventHandler optIn = (String eventName, Map<String, ?> data) -> {
        receiveEvent(TruexAdRendererConstants.OPT_IN, data);
    };

    /*
       Note: This event is triggered when a user opts-out of an interactive engagement,
       either by time-out, or by choice
     */
    private IEventEmitter.IEventHandler optOut = (String eventName, Map<String, ?> data) -> {
        receiveEvent(TruexAdRendererConstants.OPT_OUT, data);
    };

    /*
       Note: This event is triggered when a skip card is being displayed to the user
       This occurs when a user is able to skip ads
     */
    private IEventEmitter.IEventHandler skipCardShown = (String eventName, Map<String, ?> data) -> {
        receiveEvent(TruexAdRendererConstants.SKIP_CARD_SHOWN, data);
    };

    /*
       Note: This event is triggered when the ad has been fetched
       This event occurs before the following events: AD_STARTED, NO_ADS_AVAILABLE, and AD_ERROR.
     */
    private IEventEmitter.IEventHandler adFetchCompleted = (String eventName, Map<String, ?> data) -> {
        receiveEvent(TruexAdRendererConstants.AD_FETCH_COMPLETED, data);
    };

    /*
       Note: This event is triggered when a video starts playing within the true[X] Ad Renderer (TAR)
     */
    private IEventEmitter.IEventHandler videoStarted = (String eventName, Map<String, ?> data) -> {
        receiveEvent(TruexAdRendererConstants.VIDEO_STARTED, data);
    };

    /*
       Note: This event is triggered when a video reaches the first quartile within the true[X] Ad Renderer (TAR)
    */
    private IEventEmitter.IEventHandler videoFirstQuartile = (String eventName, Map<String, ?> data) -> {
        receiveEvent(TruexAdRendererConstants.VIDEO_FIRST_QUARTILE, data);
    };

    /*
       Note: This event is triggered when a video reaches the second quartile within the true[X] Ad Renderer (TAR)
    */
    private IEventEmitter.IEventHandler videoSecondQuartile = (String eventName, Map<String, ?> data) -> {
        receiveEvent(TruexAdRendererConstants.VIDEO_SECOND_QUARTILE, data);
    };

    /*
       Note: This event is triggered when a video reaches the third quartile within the true[X] Ad Renderer (TAR)
    */
    private IEventEmitter.IEventHandler videoThirdQuartile = (String eventName, Map<String, ?> data) -> {
        receiveEvent(TruexAdRendererConstants.VIDEO_THIRD_QUARTILE, data);
    };

    /*
       Note: This event is triggered when a video finishes playing within the true[X] Ad Renderer (TAR)
    */
    private IEventEmitter.IEventHandler videoCompleted = (String eventName, Map<String, ?> data) -> {
        receiveEvent(TruexAdRendererConstants.VIDEO_COMPLETED, data);
    };
}
