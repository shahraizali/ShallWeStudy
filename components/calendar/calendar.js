import React, {Component} from "react";
import {View} from "react-native";
import { 
  Calendar,
  CalendarList,
  Agenda,
} from "react-native-calendars";
import { LocaleConfig } from "react-native-calendars";

LocaleConfig.locales['fr'] = {
  monthNames: ['Janvier','Février','Mars','Avril','Mai','Juin','Juillet','Août','Septembre','Octobre','Novembre','Décembre'],
  monthNamesShort: ['Janv.','Févr.','Mars','Avril','Mai','Juin','Juil.','Août','Sept.','Oct.','Nov.','Déc.'],
  dayNames: ['Dimanche','Lundi','Mardi','Mercredi','Jeudi','Vendredi','Samedi'],
  dayNamesShort: ['Dim.','Lun.','Mar.','Mer.','Jeu.','Ven.','Sam.'],
  today: 'Aujourd\'hui'
};
LocaleConfig.defaultLocale = 'fr';

class calendar extends Component {
  render() {
    return (
      <View style={{ paddingTop: 50, flex: 1}}>
        <Calendar
        // Initially visible month. Default = Date()
        current={Date()}
        // Minimum date that can be selected, dates before minDate will be grayed out. Default = undefined
        minDate={'2021-01-01'}
        // Maximum date that can be selected, dates after maxDate will be grayed out. Default = undefined
        maxDate={'2021-12-31'}
        // Handler which gets executed on day press. Default = undefined
        onDayPress={(day) => {console.log('selected day', day)}}
        // Handler which gets executed on day long press. Default = undefined
        onDayLongPress={(day) => {console.log('selected day', day)}}
        // Month format in calendar title. Formatting values: http://arshaw.com/xdate/#Formatting
        monthFormat={'yyyy MM'}
        // Handler which gets executed when visible month changes in calendar. Default = undefined
        onMonthChange={(month) => {console.log('month changed', month)}}
        // Hide month navigation arrows. Default = false
        hideArrows={true}
        // Replace default arrows with custom ones (direction can be 'left' or 'right')
        renderArrow={(direction) => (<Arrow/>)}
        // Do not show days of other months in month page. Default = false
        hideExtraDays={true}
        // If hideArrows = false and hideExtraDays = false do not switch month when tapping on greyed out
        // day from another month that is visible in calendar page. Default = false
        disableMonthChange={true}
        // If firstDay=1 week starts from Monday. Note that dayNames and dayNamesShort should still start from Sunday
        firstDay={1}
        // Hide day names. Default = false
        hideDayNames={false}
        // Show week numbers to the left. Default = false
        showWeekNumbers={false}
        // Handler which gets executed when press arrow icon left. It receive a callback can go back month
        onPressArrowLeft={subtractMonth => subtractMonth()}
        // Handler which gets executed when press arrow icon right. It receive a callback can go next month
        onPressArrowRight={addMonth => addMonth()}
        // Disable left arrow. Default = false
        disableArrowLeft={true}
        // Disable right arrow. Default = false
        disableArrowRight={true}
        // Disable all touch events for disabled days. can be override with disableTouchEvent in markedDates
        disableAllTouchEventsForDisabledDays={true}
        // Replace default month and year title with custom one. the function receive a date as parameter
        renderHeader={(date) => {/*Return JSX*/}}
        // Enable the option to swipe between months. Default = false
        enableSwipeMonths={true}
        />

        <CalendarList
        // Enable horizontal scroliing, default = false
        horizontal={true}
        // Enable paging on horizontal, default = false
        pagingEnabled={true}
        // Set custom calendarWidth ... other Params
        />

        <Agenda
        // The list of items that have to be displayed in agenda. If you want to render item as empty date
        // the value of date key has to be an empty array []. If there exists no value for date key it is
        // considered that the date in question is not yet loaded
        items={{
          '2021-11-22': [{name: 'item 1 - any js object'}],
          '2021-11-23': [{name: 'item 2 - any js object', height: 80}],
          '2021-11-24': [],
          '2021-05-25': [{name: 'item 3 - any js object'}, {name: 'any js object'}]
        }}
        // Callback that gets called when items for a certain month should be loaded (month became visible)
        loadItemsForMonth={(month) => {console.log('trigger items loading')}}
        // Callback that fires when the calendar is opened or closed
        onCalendarToggled={(calendarOpened) => {console.log(calendarOpened)}}
        // Callback that gets called on day press
        onDayPress={(day) => {console.log('day pressed')}}
        // Callback that gets called when day changes while scrolling agenda list
        onDayChange={(day) => {console.log('day changed')}}
        // Initially selected day
        selected={date()}
        // Minimum date that can be selected, dates before minDate will be grayed out. Default = undefined
        minDate={'2021-01-01'}
        // Maximum date that can be selected, dates after maxDate will be grayed out. Default = undefined
        maxDate={'2021-12-31'}
        // Max amount of months allowed to scroll to the past. Default = 50
        pastScrollRange={50}
        // Max amount of months allowed to scroll to the future. Default = 50
        futureScrollRange={50}
        // Specify how each item should be rendered in agenda
        renderItem={(item, firstItemInDay) => {return (<View />);}}
        // Specify how each date should be rendered. day can be undefined if the item is not first in that day
        renderDay={(day, item) => {return (<View />);}}
        // Specify how empty date content with no items should be rendered
        renderEmptyDate={() => {return (<View />);}}
        // Specify how agenda knob should look like
        renderKnob={() => {return (<View />);}}
        // Specify what should be rendered instead of ActivityIndicator
        renderEmptyData = {() => {return (<View />);}}
        // Specify your item comparison function for increased performance
        rowHasChanged={(r1, r2) => {return r1.text !== r2.text}}
        // Hide knob button. Default = false
        hideKnob={true}
        // When `true` and `hideKnob` prop is `false`, the knob will always be visible and the user will be able to drag the knob up and close the calendar. Default = false
        showClosingKnob={false}
        // By default, agenda dates are marked if they have at least one item, but you can override this if needed
        markedDates={{
          '2012-05-16': {selected: true, marked: true},
          '2012-05-17': {marked: true},
          '2012-05-18': {disabled: true}
        }}
        // If disabledByDefault={true} dates flagged as not disabled will be enabled. Default = false
        disabledByDefault={true}
        // If provided, a standard RefreshControl will be added for "Pull to Refresh" functionality. Make sure to also set the refreshing prop correctly
        onRefresh={() => console.log('refreshing...')}
        // Set this true while waiting for new data from a refresh
        refreshing={false}
        // Add a custom RefreshControl component, used to provide pull-to-refresh functionality for the ScrollView
        refreshControl={null}
        // Agenda theme
        theme={{
          ...calendarTheme,
          agendaDayTextColor: 'yellow',
          agendaDayNumColor: 'green',
          agendaTodayColor: 'red',
          agendaKnobColor: 'blue'
        }}
        // Agenda container style
        style={{}}
        />
      </View>
    )
  }
}
export default calendar;