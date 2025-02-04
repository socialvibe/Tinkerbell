package com.tinkerbell.tar;

public enum TruexAdEvent {
  AD_FETCH_COMPLETED("adFetchCompleted"),
  AD_STARTED("adStarted"),
  AD_DISPLAYED("adDisplayed"),
  AD_COMPLETED("adCompleted"),
  AD_ERROR("adError"),
  NO_ADS_AVAILABLE("noAdsAvailable"),
  AD_FREE_POD("adFreePod"),
  USER_CANCEL_STREAM("userCancelStream"),
  OPT_IN("optIn"),
  OPT_OUT("optOut"),
  SKIP_CARD_SHOWN("skipCardShown"),
  USER_CANCEL("userCancel"),
  VIDEO_EVENT("videoEvent"),
  POPUP_WEBSITE("popupWebsite"),
  XTENDED_VIEW_STARTED("xtendedViewStarted");

  private final String name;

  TruexAdEvent(String name) {
    this.name = name;
  }

  public String toString() {
    return this.name;
  }

  static public TruexAdEvent toEvent(String eventName) {
    for(TruexAdEvent event : TruexAdEvent.values()) {
      if (event.name.equals(eventName)) return event;
    }
    throw new IllegalArgumentException("ad event not found: " + eventName);
  }
}
