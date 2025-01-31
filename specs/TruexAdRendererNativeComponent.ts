import {Animated, HostComponent, ViewProps} from 'react-native';
import type {DirectEventHandler, Double} from 'react-native/Libraries/Types/CodegenTypes';
import codegenNativeComponent from 'react-native/Libraries/Utilities/codegenNativeComponent';
import Nullable = Animated.Nullable;

export enum TruexAdEventType {
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

export interface TruexAdEvent {
  eventType: string;
  timeout?: Double;
  userInitiated?: boolean;
  errorMessage?: string;
  url?: string;
}

export function isCompletionEvent(eventType: TruexAdEventType) {
  switch (eventType) {
    case TruexAdEventType.NoAdsAvailable:
    case TruexAdEventType.AdCompleted:
    case TruexAdEventType.AdError:
    case TruexAdEventType.UserCancelStream:
      return true;
  }
  return false;
}

export interface TruexAdRendererProps extends ViewProps {
  vastConfigUrl: string;
  onAdEvent: DirectEventHandler<TruexAdEvent>;
}

export default codegenNativeComponent<TruexAdRendererProps>(
  'TruexAdRenderer',
) as HostComponent<TruexAdRendererProps>;
