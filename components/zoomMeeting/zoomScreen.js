//import { StatusBar } from 'expo-status-bar';
//this file was integrated with main.js

import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import ZoomMeeting from "./zoomMeeting";

export default function App() {
  return (
    <View style={styles.container}>
      <StatusBar style="auto" />
      <ZoomMeeting/>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});