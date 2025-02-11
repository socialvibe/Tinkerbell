package com.tinkerbell.tar;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.truex.adrenderer.TruexAdEvent;
import com.truex.adrenderer.TruexAdRenderer;

import java.util.Map;

public class TruexAdView extends FrameLayout {
  private final static String CLASS_NAME = TruexAdView.class.getSimpleName();

  private TruexAdRenderer truexAdRenderer;

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
    this.setBackgroundColor(Color.BLACK);
  }

  public void startAd(String vastConfigUrl) {
    Log.i(CLASS_NAME, "startAd: " + vastConfigUrl);
    if (truexAdRenderer != null) {
      truexAdRenderer.stop();
    }

    truexAdRenderer = new TruexAdRenderer(getContext());
    truexAdRenderer.addEventListener(null, this::onInternalAdEvent); // listen to all events

    truexAdRenderer.init(vastConfigUrl);
    truexAdRenderer.start(this);
  }

  private void onInternalAdEvent(TruexAdEvent event, Map<String, ?> data) {
    // We only care about event data for ad errors and web site popups.
    Object eventData = null;
    switch (event) {
      case AD_ERROR:
        eventData = data.get("errorMessage");
        break;

      case POPUP_WEBSITE:
        // Easier to just pop open the web site here.
        String url = (String) data.get("url");
        Context context = getContext();
        Activity activity = getActivity(context);
        activity.runOnUiThread(() -> {
          Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
          context.startActivity(browserIntent);
        });
        return;
    }
    emitAdEvent(event, eventData);
  }

  static public Activity getActivity(Context context) {
    if (context == null) {
      return null;
    } else if (context instanceof Activity) {
      return (Activity) context;
    } else if (context instanceof ContextWrapper) {
      return getActivity(((ContextWrapper) context).getBaseContext());
    }
    return null;
  }

  public void emitAdError(String message) {
    emitAdEvent(TruexAdEvent.AD_ERROR, message);
  }

  public void emitAdEvent(TruexAdEvent event, Object eventData) {
    ReactContext reactContext = (ReactContext) getContext();
    int surfaceId = UIManagerHelper.getSurfaceId(reactContext);

    EventDispatcher eventDispatcher = UIManagerHelper.getEventDispatcherForReactTag(reactContext, getId());
    WritableMap payload = Arguments.createMap();
    payload.putString("eventType", event.toString());

    // We only care about parameters for ad errors and web site popups.
    switch (event) {
      case AD_ERROR:
        payload.putString("errorMessage", (String) eventData);
        break;
      case POPUP_WEBSITE:
        payload.putString("url", (String) eventData);
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
