import React from "react";
import { View, StyleSheet, requireNativeComponent, UIManager, Event, findNodeHandle } from "react-native";
import PropTypes from 'prop-types';

var TruexAdRenderer = requireNativeComponent(`TruexAdRenderer`);

export default class TruexAdRendererView extends React.Component {

  constructor(props) {
    super(props);
    this._onAdEvent = this._onAdEvent.bind(this);
  }

  _onAdEvent(event: Event) {
    console.log('Received event: ' + event.nativeEvent.key);
    if (!this.props.onAdEvent) {
      return;
    }
    this.props.onAdEvent(event.nativeEvent.key, event.nativeEvent);
  };

  componentDidMount() {
    UIManager.dispatchViewManagerCommand(
        findNodeHandle(this),
        UIManager.TruexAdRenderer.Commands.startAd,
        [],
    );
  }

  render() {
    return (
      <TruexAdRenderer
        networkUserID="3e47e82244f7aa7ac3fa60364a7ede8453f3f9fe"
        placementHash="81551ffa2b851abc5372ab9ed9f1f58adabe5203"
        vastConfigURL="https://qa-get.truex.com/81551ffa2b851abc5372ab9ed9f1f58adabe5203/vast/config?asnw=&flag=%2Bamcb%2Bemcr%2Bslcb%2Bvicb%2Baeti-exvt&fw_key_values=&metr=0&prof=g_as3_truex&ptgt=a&pvrn=&resp=vmap1&slid=fw_truex&ssnw=&vdur=&vprn="
        onAdEvent={this._onAdEvent}
      />
    );
  }
}

TruexAdRendererView.propTypes = {
  onAdEvent: PropTypes.func
};
