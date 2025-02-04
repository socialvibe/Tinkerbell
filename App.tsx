/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React from 'react';
import type {PropsWithChildren} from 'react';
import {
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  useColorScheme,
  View,
} from 'react-native';

import {
  Colors,
  DebugInstructions,
  Header,
  LearnMoreLinks,
  ReloadInstructions,
} from 'react-native/Libraries/NewAppScreen';

function App(): React.JSX.Element {
  const [isShowingTruex, setShowingTruex] = useState(false);

  const onAdEvent = useCallback((event: String, data: any) => {
    console.log(`onAdEvent: ${event}`);
  }, []);

  return (
    <SafeAreaView style={styles.container}>
      <Video
        source={{uri: "https://media.truex.com/video_assets/2017-03-06/a9fbc895-6987-440e-b940-eeef8a714338_large.mp4"}}
        resizeMode="contain"
        paused={isShowingTruex}
        style={styles.backgroundVideo}/>
      {isShowingTruex ? (
        // <TruexAdRendererView onAdEvent={onAdEvent} />
        <View style={styles.adContainer}>
          <Text style={styles.title}>
            TBD: TruexAdRenderer
          </Text>
        </View>
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
