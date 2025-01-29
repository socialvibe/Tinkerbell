import { useCallback } from "react";
import type {PropsWithChildren} from 'react';
import { View, StyleSheet, requireNativeComponent, UIManager, findNodeHandle } from "react-native";
import PropTypes from 'prop-types';


//var TruexAdRenderer = requireNativeComponent(`TruexAdRenderer`);

type TruexAdRendererProps = PropsWithChildren<{
  vastConfigUrl: string;
  onAdEvent: (event: String, data: any) => void;
}>;

function TruexAdRender(props: TruexAdRendererProps) {
}

const vastConfigUrl = "https://qa-get.truex.com/81551ffa2b851abc5372ab9ed9f1f58adabe5203/vast/config?asnw=&flag=%2Bamcb%2Bemcr%2Bslcb%2Bvicb%2Baeti-exvt&fw_key_values=&metr=0&prof=g_as3_truex&ptgt=a&pvrn=&resp=vmap1&slid=fw_truex&ssnw=&vdur=&vprn=";

const adEventHandler = useCallback((event: String, data: any) => {
  console.log("ad event received: " + event);
}, []);

return (
  <TruexAdRender vastConfigUrl={vastConfigUrl} onAdEvent={adEventHandler} />
);

TruexAdRendererView.propTypes = {
  onAdEvent: PropTypes.func
};
