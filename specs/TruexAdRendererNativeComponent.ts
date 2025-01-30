import type {HostComponent, ViewProps} from 'react-native';
import type {BubblingEventHandler} from 'react-native/Libraries/Types/CodegenTypes';
import codegenNativeComponent from 'react-native/Libraries/Utilities/codegenNativeComponent';
import {AdEventHandler, TruexAdEvent} from './TruexAdEvent';

export interface TruexAdRendererProps extends ViewProps {
  vastConfigUrl: string;
  onAdEvent: AdEventHandler;
}

export default codegenNativeComponent<TruexAdRendererProps>(
  'TruexAdRenderer',
) as HostComponent<TruexAdRendererProps>;
