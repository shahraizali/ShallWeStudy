import React from 'react';
import {SafeAreaView, StyleSheet} from 'react-native';

import 'react-native-gesture-handler';
import {NavigationContainer} from '@react-navigation/native';
import {createStackNavigator} from '@react-navigation/stack';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';

import logInPage from './components/sign/logIn';
import signUpPage from './components/sign/signUp';
import findAccount from './components/sign/findAccount';
import mainPage from './components/main/main'

import HomeTab from './components/AppTabNavigator/HomeTab'
import GroupTab from './components/AppTabNavigator/GroupTab'
import PlannerTab from './components/AppTabNavigator/PlannerTab'
import rankingTab from './components/AppTabNavigator/rankingTab'
import SummaryTab from './components/AppTabNavigator/SummaryTab'


import {
  Image,
} from 'react-native';
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
        
        <Stack.Screen
          name="bottomtab"
          component={Bottom}
          options={{headerShown: false}}
        />
        <Stack.Screen
        name="mainPage"
        component={mainPage}
        options={{headerShown: false}}
      />



      </Stack.Navigator>
    </NavigationContainer>
  );
};

const styles = StyleSheet.create({});

const Tab = createBottomTabNavigator();

function Bottom() {
  return (
    <Tab.Navigator>
      <Tab.Screen
        name="Main"
        component={mainPage}
        options={{
          headerShown: false,
          unmountOnBlur: true,
          tabBarLabel: '',
          tabBarIcon: ({focused, color, size}) => {
            let icon;
            if (focused) {
              icon = require('./src/image/mainimo_full.png');
            } else {
              icon = require('./src/image/homeimo.png');
            }
            return (
              <Image
                name="homeimo"
                color={color}
                source={icon}
                style={{marginTop: 15, width: size * 1.1, height: size * 1.1}}
              />
            );
          },
        }}
        listeners={({navigation}) => ({
          blur: () => navigation.setParams({screen: undefined}),
        })}
      />
      {/* 친구들 찾기 */}
      <Tab.Screen
        name="Group"
        component={GroupTab}
        options={{
          headerShown: false,
          tabBarLabel: '',
          tabBarIcon: ({focused, color, size}) => {
            let icon;
            if (focused) {
              icon = require('./src/image/mainimo_full.png');
            } else {
              icon = require('./src/image/mainmo3.png');
            }
            return (
              <Image
                name="homeimo"
                color={color}
                source={icon}
                style={{marginTop: 15, width: size * 1.6, height: size * 1.1}}
              />
            );
          },
        }}
        listeners={({navigation}) => ({
          blur: () => navigation.setParams({screen: undefined}),
        })}
      />
      {/* 메세지함 */}
      <Tab.Screen
        name="PlannerTab"
        component={PlannerTab}
        options={{
          headerShown: false,
          unmountOnBlur: true,
          tabBarLabel: '',
          tabBarIcon: ({focused, color, size}) => {
            let icon;
            if (focused) {
              icon = require('./src/image/msgimo_full.png');
            } else {
              icon = require('./src/image/msgimo2.png');
            }
            return (
              <Image
                name="homeimo"
                color={color}
                source={icon}
                style={{marginTop: 15, width: size * 1.1, height: size * 1.1}}
              />
            );
          },
        }}
        listeners={({navigation}) => ({
          blur: () => navigation.setParams({screen: undefined}),
        })}
      />
      
      <Tab.Screen
        name="rankingTab"
        component={rankingTab}
        options={{
          headerShown: false,
          tabBarLabel: '',
          tabBarIcon: ({focused, color, size}) => {
            let icon;
            if (focused) {
              icon = require('./src/image/setting.png');
            } else {
              icon = require('./src/image/gear.png');
              //setting이미지 만드는중..
            }
            return (
              <Image
                name="homeimo"
                color={color}
                source={icon}
                style={{marginTop: 15, width: size * 1.2, height: size * 1.2}}
              />
            );
          },
        }}
      />
    </Tab.Navigator>
  );
}




export default App;
