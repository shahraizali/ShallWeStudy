import React from 'react';
import { IntroScreen } from './screens/intro-screen';
import { JoinScreen } from './screens/join-screen';
import { CallScreen } from './screens/call-screen';
import { MenuDrawer } from './components/menu-drawer';
import { createDrawerNavigator } from '@react-navigation/drawer';
import { createStackNavigator } from '@react-navigation/stack';

const Stack = createStackNavigator();
const Drawer = createDrawerNavigator();

function MainNavigation() {
  return (
    <Stack.Navigator initialRouteName="Intro">
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
      <Stack.Screen
        name="Intro"
        component={IntroScreen}
        options={{ headerShown: false }}
      />
      <Stack.Screen
        name="Join"
        component={JoinScreen}
        options={{ title: '' }}
      />
      <Stack.Screen
        name="Call"
        component={CallScreen}
        options={{ headerShown: false }}
      />
      
    </Stack.Navigator>
  );
}


const styles = StyleSheet.create({});

const Tab = createBottomTabNavigator();

function Bottom() {
  return (
    <Tab.Navigator>
      <Tab.Screen
        name="Main"
        component={Intro}
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

export function Navigation() {
  return (
    <Drawer.Navigator initialRouteName="Main" drawerContent={MenuDrawer}>
      <Drawer.Screen name="Main" component={MainNavigation} />
    </Drawer.Navigator>
  );
}