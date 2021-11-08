import * as React from 'react';
import { ZoomVideoSdkProvider } from 'react-native-zoom-video-sdk';
import { NavigationContainer } from '@react-navigation/native';

import { Navigation } from './navigation';

export default function App() {
  return (
    <NavigationContainer>
      <ZoomVideoSdkProvider
        config={{
          appGroupId: '',
          domain: 'zoom.us',
          enableLog: true,
        }}
      >
        <Navigation />
      </ZoomVideoSdkProvider>
    </NavigationContainer>
  );
}