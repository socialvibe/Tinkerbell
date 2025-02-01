package com.tinkerbell.tar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.truex.adrenderer.TruexAdEvent;

import java.util.Map;

public class TruexAdView extends WebView {
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

  public void emitAdEvent(TruexAdEvent event, Map<String, ?> data) {
    ReactContext reactContext = (ReactContext) getContext();
    int surfaceId = UIManagerHelper.getSurfaceId(reactContext);

    EventDispatcher eventDispatcher = UIManagerHelper.getEventDispatcherForReactTag(reactContext, getId());
    WritableMap payload = Arguments.createMap();
    payload.putString("eventType", event.toString());
    if (data != null) {
      for (Map.Entry<String, ?> entry : data.entrySet()) {
        String key = entry.getKey();
        Object value = entry.getValue();
        if (value instanceof Integer) payload.putInt(key, (Integer) value);
        else if (value instanceof Number) payload.putDouble(key, ((Number) value).doubleValue());
        else if (value instanceof Boolean) payload.putBoolean(key, (Boolean) value);
        else if (value instanceof String) payload.putString(key, (String) value);
      }
    }

    DispatchedAdEvent dispatchedEvent = new DispatchedAdEvent(surfaceId, getId(), payload);
    if (eventDispatcher != null) {
      eventDispatcher.dispatchEvent(dispatchedEvent);
    }
  }

  private class DispatchedAdEvent extends Event<DispatchedAdEvent> {
    private final WritableMap payload;

    DispatchedAdEvent(int surfaceId, int viewId, WritableMap payload) {
      super(surfaceId, viewId);
      this.payload = payload;
    }

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
