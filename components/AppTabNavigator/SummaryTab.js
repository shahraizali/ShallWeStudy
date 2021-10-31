import React, { Component } from 'react';
import { View, Text, StyleSheet } from 'react-native';
 
export default class SummaryTab extends Component {
    render() {
        return (
            <View style={style.container}>
                <Text>SummaryTab</Text>
            </View>
        );
    }
}
 
const style = StyleSheet.create({
    container: {
        flex: 1,
        alignItems: 'center',
        justifyContent: 'center',
    }
});