import {HostComponent, ViewProps} from 'react-native';
import type {DirectEventHandler} from 'react-native/Libraries/Types/CodegenTypes';
import codegenNativeComponent from 'react-native/Libraries/Utilities/codegenNativeComponent';

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

export function toAdEventType(name: string): TruexAdEventType | undefined {
  if (Object.values(TruexAdEventType).includes(name as any)) return name as TruexAdEventType;
  return undefined;
}

export interface TruexAdEvent {
  eventType: string;
  errorMessage?: string;
  url?: string;
}

export interface TruexAdViewProps extends ViewProps {
  vastConfigUrl: string;
  onAdEvent: DirectEventHandler<TruexAdEvent>;
}

export const TruexAdView = codegenNativeComponent<TruexAdViewProps>(
  'TruexAdView',
) as HostComponent<TruexAdViewProps>;

export default TruexAdView;
