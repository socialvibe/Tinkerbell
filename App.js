import React from "react";
import { StyleSheet, View } from "react-native";
  
import TruexAdRendererView from "./components/TruexAdRendererView";

const styles = StyleSheet.create({
  container: {
    width: '100%',
    height: '100%'
  }
});

export default class App extends React.Component {
  state = {
  };

  render() {
    return (
      <View style={styles.container}>
        <TruexAdRendererView style={styles.container} />
      </View>
    );
  }
}
