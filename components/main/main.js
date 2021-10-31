import React, {PureComponent} from 'react';
import {
  SafeAreaView,
  StyleSheet,
  View,
  Text,
  TouchableOpacity,
  TextInput,
  Image,
  TouchableWithoutFeedback,
  Keyboard,
  Dimensions,
} from 'react-native';
import {withNavigation} from 'react-navigation';
import Swiper from 'react-native-swiper';
import {KeyboardAwareScrollView} from 'react-native-keyboard-aware-scroll-view';
import {getStatusBarHeight} from 'react-native-status-bar-height';
import {getBottomSpace} from 'react-native-iphone-x-helper';

class mainPage extends PureComponent {
  constructor(props) {
    super(props);
  }

  handleId = (e) => {
    this.setState({
      id: e,
    });
    // console.log(this.state.name1);
  };

  handlepasswd = (e) => {
    this.setState({
      passwd: e,
    });
    // console.log(this.state.name1);
  };

  onlogin = async (e) => {
    e.preventDefault();
    //로그인 로직 작성
    this.props.navigation.navigate('Main');
  };

  singupBtn = (e) => {
    e.preventDefault();
    this.props.navigation.navigate('signUpPage');
  };

  find_idpwBtn = (e) => {
    e.preventDefault();
    this.props.navigation.navigate('findAccount');
  };

  render() {
    let screenHeight =
      Dimensions.get('window').height - getStatusBarHeight() - getBottomSpace();
    return (
      <SafeAreaView style={styles.White_login}>
        <KeyboardAwareScrollView>
          <TouchableWithoutFeedback onPress={Keyboard.dismiss}>
            
            <View
              style={{
                display: 'flex',
                flexDirection: 'column',
                justifyContent: 'space-between',
                alignItems: 'center',
                height: screenHeight,
              }}>
               <Text>MainScreen</Text>
                
              
            </View>
          </TouchableWithoutFeedback>
        </KeyboardAwareScrollView>
      </SafeAreaView>
    );
  }
}

const styles = StyleSheet.create({
  contents: {
    display: 'flex',
    height: '100%',
    justifyContent: 'space-between',
  },
});
export default withNavigation(mainPage);
