package com.tinkerbell.tar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.EventDispatcher;
//import com.truex.adrenderer.TruexAdEvent;
//import com.truex.adrenderer.TruexAdRenderer;

public class TruexAdView extends FrameLayout {

  //private TruexAdRenderer truexAdRenderer;

  public TruexAdView(Context context) {
    super(context);
    configureComponent();
  }

  public TruexAdView(Context context, AttributeSet attrs) {
    super(context, attrs);
    configureComponent();
  }

  public TruexAdView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    configureComponent();
  }

  private void configureComponent() {
    this.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
  }

  public void startAd(String vastConfigUrl) {
//    if (truexAdRenderer != null) {
//      truexAdRenderer.stop();
//    }
//
//    truexAdRenderer = new TruexAdRenderer(getContext());
//    truexAdRenderer.addEventListener(null, this::emitAdEvent); // listen to all events
//
//    truexAdRenderer.init(vastConfigUrl);
//    truexAdRenderer.start(this);
    TextView text = new TextView(getContext());
    text.setText("This is the TruexAdRenderer");
    text.setTextColor(255);
    this.addView(text);
  }

  public void emitAdError(String message) {
    emitAdEvent(TruexAdEvent.AD_ERROR, message);
  }

  public void emitAdEvent(TruexAdEvent event, Object data) {
    ReactContext reactContext = (ReactContext) getContext();
    int surfaceId = UIManagerHelper.getSurfaceId(reactContext);

    EventDispatcher eventDispatcher = UIManagerHelper.getEventDispatcherForReactTag(reactContext, getId());
    WritableMap payload = Arguments.createMap();
    payload.putString("eventType", event.toString());

    // We only care about parameters for ad errors and web site popups.
    switch (event) {
      case AD_ERROR:
        payload.putString("errorMessage", (String) data);
        break;
      case POPUP_WEBSITE:
        payload.putString("url", (String) data);
        break;
    }

    DispatchedAdEvent dispatchedEvent = new DispatchedAdEvent(surfaceId, getId(), payload);
    if (eventDispatcher != null) {
      eventDispatcher.dispatchEvent(dispatchedEvent);
    }
  }

  private static class DispatchedAdEvent extends Event<DispatchedAdEvent> {
    private final WritableMap payload;

    DispatchedAdEvent(int surfaceId, int viewId, WritableMap payload) {
      super(surfaceId, viewId);
      this.payload = payload;
    }

    @NonNull
    @Override
    public String getEventName() {
      return "onAdEvent";
    }

    @Override
    public WritableMap getEventData() {
      return payload;
    }
  }
}
