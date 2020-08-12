import React from "react";
import { StyleSheet, SafeAreaView, Text, View, Button, ToastAndroid, TextInput } from "react-native";
  
import Video from 'react-native-video';
import TruexAdRendererView from "./components/TruexAdRendererView";

const styles = StyleSheet.create({
  container: {
    width: '100%',
    height: '100%'
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

export default class App extends React.Component {
  state = { truexStarted: false };

  constructor(props) {
    super(props);
    this._onAdEvent = this._onAdEvent.bind(this);
  }

  _onAdEvent(key, event) {
    ToastAndroid.show("_onAdEvent: " + key, ToastAndroid.SHORT);
    if ((key == "AD_COMPLETED") || (key == "NO_ADS_AVAILABLE") || (key == "AD_ERROR") || (key=="OPT_OUT") ) {
      this.setState({
        truexStarted: false
      })
    }
  };

  render() {
    return (
      <SafeAreaView style={styles.container}>
        <Video source={{uri: "https://media.truex.com/video_assets/2017-03-06/a9fbc895-6987-440e-b940-eeef8a714338_large.mp4"}}
              ref={(ref) => {
                this.player = ref
              }}
              resizeMode="contain"
              paused={this.state.truexStarted}
              style={styles.backgroundVideo} /> 
        {(this.state.truexStarted) ? (
          <TruexAdRendererView style={styles.container} onAdEvent={this._onAdEvent} />
        ) : (
          <View style={styles.mainView}>
            <Text style={styles.title}>
              Click "Start" to start the true[X] experience.
            </Text>
            <Button
              title="Start"
              onPress={() => {
                this.setState({
                  truexStarted: true 
                });
              }}
            />
          </View>
        )}
      </SafeAreaView>
    )
  }
}
