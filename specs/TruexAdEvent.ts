/**
 * Describes events emitted from the truex ad renderer as the interactive ad progresses through its life cycle.
 */
export enum TruexAdEvent {
  AdStarted = 'adStarted',
  AdDisplayed = 'adDisplayed',
  AdCompleted = 'adCompleted',
  AdError = 'adError',
  NoAdsAvailable = 'noAdsAvailable',
  AdFreePod = 'adFreePod',
  AdFetchCompleted = 'adFetchCompleted',
  UserCancelStream = 'userCancelStream',
  OptIn = 'optIn',
  OptOut = 'optOut',
  SkipCardShown = 'skipCardShown',
  UserCancel = 'userCancel',
  XtendedViewStarted = 'xtendedViewStarted',
  PopupWebsite = "popupWebsite"
}

export type AdEventData = undefined | null | Record<string, null | boolean | number | string>;
export type AdEventHandler = (event: TruexAdEvent, data?: AdEventData) => void;

export function isCompletionEvent(event: TruexAdEvent) {
  switch (event) {
    case TruexAdEvent.NoAdsAvailable:
    case TruexAdEvent.AdCompleted:
    case TruexAdEvent.AdError:
    case TruexAdEvent.UserCancelStream:
      return true;
  }
  return false;
}
