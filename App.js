import React from 'react';
import {SafeAreaView, StyleSheet} from 'react-native';
import logInPage from './components/sign/logIn';
import signUpPage from './components/sign/signUp';
import findAccount from './components/sign/findAccount';
import 'react-native-gesture-handler';
import {NavigationContainer} from '@react-navigation/native';
import {createStackNavigator} from '@react-navigation/stack';
const Stack = createStackNavigator();

const App: () => React$Node = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen
          name="logInPage"
          component={logInPage}
          options={{headerShown: false}}
        />
        <Stack.Screen
          name="signUpPage"
          component={signUpPage}
          options={{headerShown: false}}
        />

        <Stack.Screen
          name="findAccount"
          component={findAccount}
          options={{headerShown: false}}
        />

      </Stack.Navigator>
    </NavigationContainer>
  );
};

const styles = StyleSheet.create({});

export default App;
