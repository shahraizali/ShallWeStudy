import React, { useEffect, useState } from 'react';
import ZoomUs from "react-native-zoom-us";
import { Button, Alert } from "react-native";


const CLIENT_KEY = 'Nqy1bu9w3VjL3A0YaJq8whaW8NVSHHxKMhQ2';
const CLIENT_SECRET = 'IbTsCfBoawIUP1bIsSBTRqWwoAvQS3zRsY8q';
const MEETING_NUMBER = '82984654639';
const PASSWORD = 'dmZJcGh0cHNmWldxL1p3bm9JYWk3dz09';

const ZoomMeeting = () => {
  const [isInitialized, setIsInitialized] = useState(false);
   useEffect(() => {
     (async () => {
       try{
         const message = await ZoomUs.initialize({
           clientKey: CLIENT_KEY,
           clientSecret: CLIENT_SECRET,
         });
         console.log('message is ', message);
         setIsInitialized(true);
       }catch(error){
         Alert.alert('error is ', error.toString());
       }

     })();
   },[]);

   const joinAMeeting = async() => {
       const meeting = await ZoomUs.joinMeeting({
         userName: 'Songi',
         meetingNumber: MEETING_NUMBER,
         password: PASSWORD
       });

       console.log('meetng joined ', meeting);
     };

   return (
       <Button title={"Join a meeting"} disabled={!isInitialized} onPress ={joinAMeeting}/>
   )
};

export default ZoomMeeting;