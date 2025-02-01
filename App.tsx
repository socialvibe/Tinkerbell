/**
 * Tinkerbell: Sample Truex React Native Demonstration App
 */

import React, {useCallback, useState} from "react";
import {Button, type NativeSyntheticEvent, SafeAreaView, StyleSheet, Text, View} from "react-native";

import Video from 'react-native-video';

import {
  isCompletionEvent,
  toAdEventType,
  TruexAdEvent,
  TruexAdEventType,
  TruexAdView
} from './specs/TruexAdRendererNativeComponent';

function App(): React.JSX.Element {
  const [isShowingTruex, setShowingTruex] = useState(false);

  const onAdEvent = useCallback((dispatchedEvent: NativeSyntheticEvent<TruexAdEvent>) => {
    const event = dispatchedEvent.nativeEvent;
    const data = event.url || event.errorMessage || '';
    const dataSuffix = data ? ': ' + data : '';
    console.log(`onAdEvent: ${event.eventType}${dataSuffix}`);

    const eventType = toAdEventType(event.eventType);
    if (isCompletionEvent(eventType)) {
      setShowingTruex(false);

    } else if (eventType == TruexAdEventType.PopupWebsite) {
      // TODO: open external site
    }
  }, []);

  return (
    <SafeAreaView style={styles.container}>
      <Video
        source={{uri: "https://media.truex.com/video_assets/2017-03-06/a9fbc895-6987-440e-b940-eeef8a714338_large.mp4"}}
        resizeMode="contain"
        paused={isShowingTruex}
        style={styles.backgroundVideo}/>
      {isShowingTruex ? (
        <TruexAdView
          vastConfigUrl='https://get.truex.com/6789e783ea2421ab2272794dbf8550ef2a9ace38/vast/config?dimension_5=confirmation-test&network_user_id=test-user-123&user_agent=Android'
          onAdEvent={onAdEvent}/>
      ) : (
        <View style={styles.mainView}>
          <Text style={styles.title}>
            Click "Start" to start the true[X] experience.
          </Text>
          <Button
            title="Start"
            hasTVPreferredFocus={true}
            onPress={() => {
              setShowingTruex(true);
            }}
          />
        </View>
      )}
    </SafeAreaView>
  );
}

export default App;

const styles = StyleSheet.create({
  container: {
    width: '100%',
    height: '100%'
  },
  adContainer: {
    position: 'absolute',
    top: 0,
    left: 0,
    right: 0,
    bottom: 0,
    color: 'white',
    backgroundColor: 'black'
  },
  backgroundVideo: {
    position: 'absolute',
    top: 0,
    left: 0,
    right: 0,
    bottom: 0
  },
  mainView: {
    position: 'absolute',
    top: 0,
    left: 0,
    right: 0,
    bottom: 0,
    padding: '10%',
    backgroundColor: 'rgba(0,0,0,.8)'
  },
  title: {
    textAlign: 'center',
    marginVertical: 8,
    color: '#ffffff'
  }
});
