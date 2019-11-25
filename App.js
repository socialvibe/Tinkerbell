import React from "react";
import { StyleSheet, TouchableOpacity, Text, View, Button, ToastAndroid } from "react-native";
  
import TruexAdRendererView from "./components/TruexAdRendererView";

const styles = StyleSheet.create({
  container: {
    width: '100%',
    height: '100%'
  }
});

export default class App extends React.Component {
  state = { truexStarted: false };

  constructor(props) {
    super(props);
    this._onAdEvent = this._onAdEvent.bind(this);
  }

  _onAdEvent(key, event) {
    ToastAndroid.show(key, ToastAndroid.SHORT);
    if ((key == "AD_COMPLETED") || (key == "NO_ADS_AVAILABLE") || (key == "AD_ERROR") || (key=="OPT_OUT") ) {
      this.setState({
        truexStarted: false
      })
    }
    var Platform = require('Platform');
    var running_on_android_tv = Platform.isTV;
    ToastAndroid.show(running_on_android_tv? "true":"false", ToastAndroid.SHORT);
  };

  render() {
    // if (this.state.truexStarted) {
    //   return (
    //     <View style={styles.container}>
    //       <TruexAdRendererView style={styles.container} onAdEvent={this._onAdEvent} />
    //     </View>
    //   );
    // }

    return (
      <View style={styles.container}>
        <Button
          title="Start"
          onPress={() => {
            this.setState({
              truexStarted: true 
            });
          }}
        />
      </View>
    )
  }
}
