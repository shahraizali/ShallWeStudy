import React, { useEffect, useState } from 'react';
import { View, TouchableOpacity, StyleSheet, Text } from 'react-native';
import { TextInputRow } from '../../components/text-input-row';

const DEFAULT_SESSION_NAMES = [
  'grand-canyon',
  'yosemite',
  'yellowstone',
  'disneyland',
  'golden-gate-bridge',
  'monument-valley',
  'death-valley',
  'brooklyn-bridge',
  'hoover-dam',
  'lake-tahoe',
];

type JoinScreenProps = {
  route: any;
  navigation: any;
};

export function JoinScreen({ route, navigation }: JoinScreenProps) {
  const [sessionName, setSessionName] = useState('');
  const [sessionPassword, setSessionPassword] = useState('');
  const [displayName, setDisplayName] = useState('');

  const isJoin = route?.params?.isJoin;

  useEffect(() => {
    navigation.setOptions({
      title: !isJoin ? 'Create a Session' : 'Join a Session',
    });
    if (!isJoin) {
      const defaultSessionName =
        DEFAULT_SESSION_NAMES[
          Math.floor(Math.random() * DEFAULT_SESSION_NAMES.length)
        ] +
        '-' +
        Math.floor(Math.random() * 1000);
      setSessionName(defaultSessionName.trim());
    }
  }, [navigation, isJoin]);

  return (
    <View style={styles.container}>
      <TextInputRow
        label="Session Name"
        placeholder="Required"
        value={sessionName}
        onChangeText={setSessionName}
      />
      <TextInputRow
        label="Display Name"
        placeholder="Required"
        value={displayName}
        onChangeText={setDisplayName}
      />
      <TextInputRow
        label="Password"
        placeholder="Optional"
        value={sessionPassword}
        onChangeText={setSessionPassword}
        secureTextEntry
      />
      <TouchableOpacity
        onPress={() =>
          navigation.navigate('Call', {
            sessionName,
            displayName,
            sessionPassword,
          })
        }
        style={styles.button}
      >
        <Text style={styles.buttonText}>{!isJoin ? 'Create' : 'Join'}</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'white',
  },
  button: {
    backgroundColor: '#0e71eb',
    alignItems: 'center',
    marginTop: 15,
    marginHorizontal: 15,
    paddingVertical: 10,
    borderRadius: 5,
  },
  buttonText: {
    color: 'white',
  },
});
