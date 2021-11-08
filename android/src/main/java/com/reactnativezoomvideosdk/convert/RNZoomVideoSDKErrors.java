package com.reactnativezoomvideosdk.convert;

import com.facebook.react.bridge.NoSuchKeyException;

import java.util.HashMap;
import java.util.Map;

import us.zoom.sdk.ZoomVideoSDKErrors;

public class RNZoomVideoSDKErrors {

  private static final Map<Integer, String> errors =
    new HashMap<Integer, String>() {{
      put(ZoomVideoSDKErrors.Errors_Success, "ZoomVideoSDKError_Success");
      put(ZoomVideoSDKErrors.Errors_Wrong_Usage, "ZoomVideoSDKError_Wrong_Usage");
      put(ZoomVideoSDKErrors.Errors_Internal_Error, "ZoomVideoSDKError_Internal_Error");
      put(ZoomVideoSDKErrors.Errors_Uninitialize, "ZoomVideoSDKError_Uninitialize");
      put(ZoomVideoSDKErrors.Errors_Memory_Error, "ZoomVideoSDKError_Memory_Error");
      put(ZoomVideoSDKErrors.Errors_Load_Module_Error, "ZoomVideoSDKError_Load_Module_Error");
      put(ZoomVideoSDKErrors.Errors_UnLoad_Module_Error, "ZoomVideoSDKError_UnLoad_Module_Error");
      put(ZoomVideoSDKErrors.Errors_Invalid_Parameter, "ZoomVideoSDKError_Invalid_Parameter");
      put(ZoomVideoSDKErrors.Errors_Unknown, "ZoomVideoSDKError_Unknown");
      put(ZoomVideoSDKErrors.Errors_Auth_Error, "ZoomVideoSDKError_Auth_Error");
      put(ZoomVideoSDKErrors.Errors_Auth_Empty_Key_or_Secret, "ZoomVideoSDKError_Auth_Empty_Key_or_Secret");
      put(ZoomVideoSDKErrors.Errors_Auth_Wrong_Key_or_Secret, "ZoomVideoSDKError_Auth_Wrong_Key_or_Secret");
      put(ZoomVideoSDKErrors.Errors_Auth_DoesNot_Support_SDK, "ZoomVideoSDKError_Auth_DoesNot_Support_SDK");
      put(ZoomVideoSDKErrors.Errors_Auth_Disable_SDK, "ZoomVideoSDKError_Auth_Disable_SDK");
      put(ZoomVideoSDKErrors.Errors_SessionModule_Not_Found, "ZoomVideoSDKError_Session_Module_Not_Found");
      put(ZoomVideoSDKErrors.Errors_SessionService_Invaild, "ZoomVideoSDKError_Session_Service_Invaild");
      put(ZoomVideoSDKErrors.Errors_Session_Join_Failed, "ZoomVideoSDKError_Session_Join_Failed");
      put(ZoomVideoSDKErrors.Errors_Session_No_Rights, "ZoomVideoSDKError_Session_No_Rights");
      put(ZoomVideoSDKErrors.Errors_Session_Already_In_Progress, "ZoomVideoSDKError_Session_Already_In_Progress");
      put(ZoomVideoSDKErrors.Errors_Session_Dont_Support_SessionType, "ZoomVideoSDKError_Session_Dont_Support_SessionType");
      put(ZoomVideoSDKErrors.Errors_Session_Reconncting, "ZoomVideoSDKError_Session_Reconncting");
      put(ZoomVideoSDKErrors.Errors_Session_Disconnect, "ZoomVideoSDKError_Session_Disconncting");
      put(ZoomVideoSDKErrors.Errors_Session_Not_Started, "ZoomVideoSDKError_Session_Not_Started");
      put(ZoomVideoSDKErrors.Errors_Session_Need_Password, "ZoomVideoSDKError_Session_Need_Password");
      put(ZoomVideoSDKErrors.Errors_Session_Password_Wrong, "ZoomVideoSDKError_Session_Password_Wrong");
      put(ZoomVideoSDKErrors.Errors_Session_Remote_DB_Error, "ZoomVideoSDKError_Session_Remote_DB_Error");
      put(ZoomVideoSDKErrors.Errors_Session_Invalid_Param, "ZoomVideoSDKError_Session_Invalid_Param");
      put(ZoomVideoSDKErrors.Errors_Session_Audio_Error, "ZoomVideoSDKError_Session_Audio_Error");
      put(ZoomVideoSDKErrors.Errors_Session_Audio_No_Microphone, "ZoomVideoSDKError_Session_Audio_No_Microphone");
      put(ZoomVideoSDKErrors.Errors_Session_Video_Error, "ZoomVideoSDKError_Session_Video_Error");
      put(ZoomVideoSDKErrors.Errors_Session_Video_Device_Error, "ZoomVideoSDKError_Session_Video_Device_Error");
      put(ZoomVideoSDKErrors.Errors_Session_Live_Stream_Error, "ZoomVideoSDKError_Session_Live_Stream_Error");
      put(ZoomVideoSDKErrors.RawDataError_MALLOC_FAILED, "ZoomVideoSDKError_Malloc_Failed");
      put(ZoomVideoSDKErrors.RawDataError_NOT_IN_Session, "ZoomVideoSDKError_Not_In_Session");
      put(ZoomVideoSDKErrors.RawDataError_NO_LICENSE, "ZoomVideoSDKError_No_License");
      put(ZoomVideoSDKErrors.RawDataError_VIDEO_MODULE_NOT_READY, "ZoomVideoSDKError_Video_Module_Not_Ready");
      put(ZoomVideoSDKErrors.RawDataError_VIDEO_MODULE_ERROR, "ZoomVideoSDKError_Video_Module_Error");
      put(ZoomVideoSDKErrors.RawDataError_VIDEO_DEVICE_ERROR, "ZoomVideoSDKError_Video_device_error");
      put(ZoomVideoSDKErrors.RawDataError_NO_VIDEO_DATA, "ZoomVideoSDKError_No_Video_Data");
      put(ZoomVideoSDKErrors.RawDataError_SHARE_MODULE_NOT_READY, "ZoomVideoSDKError_Share_Module_Not_Ready");
      put(ZoomVideoSDKErrors.RawDataError_SHARE_MODULE_ERROR, "ZoomVideoSDKError_Share_Module_Error");
      put(ZoomVideoSDKErrors.RawDataError_NO_SHARE_DATA, "ZoomVideoSDKError_No_Share_Data");
      put(ZoomVideoSDKErrors.RawDataError_AUDIO_MODULE_NOT_READY, "ZoomVideoSDKError_Audio_Module_Not_Ready");
      put(ZoomVideoSDKErrors.RawDataError_AUDIO_MODULE_ERROR, "ZoomVideoSDKError_Audio_Module_Error");
      put(ZoomVideoSDKErrors.RawDataError_NO_AUDIO_DATA, "ZoomVideoSDKError_No_Audio_Data");
      put(ZoomVideoSDKErrors.RawDataError_NO_DEVICE_RUNNING, "ZoomVideoSDKError_Rawdata_No_Device_Running");
      put(ZoomVideoSDKErrors.RawDataError_INIT_DEVICE, "ZoomVideoSDKError_Rawdata_Init_Device");
      put(ZoomVideoSDKErrors.RawDataError_VIRTUAL_DEVICE, "ZoomVideoSDKError_Rawdata_Virtual_Device");
      put(ZoomVideoSDKErrors.RawDataError_CANNOT_CHANGE_VIRTUAL_DEVICE_IN_PREVIEW, "ZoomVideoSDKError_Rawdata_Cannot_Change_Virtual_Device_In_Preview");
      put(ZoomVideoSDKErrors.RawDataError_INTERNAL_ERROR, "ZoomVideoSDKError_Rawdata_Internal_Error");
      put(ZoomVideoSDKErrors.RawDataError_SEND_TOO_MUCH_DATA_IN_SINGLE_TIME, "ZoomVideoSDKError_Rawdata_Send_Too_Much_Data_In_Single_Time");
      put(ZoomVideoSDKErrors.RawDataError_SEND_TOO_FREQUENTLY, "ZoomVideoSDKError_Rawdata_Send_Too_Frequently");
      put(ZoomVideoSDKErrors.RawDataError_VIRTUAL_MIC_IS_TERMINATE, "ZoomVideoSDKError_Rawdata_Virtual_Mic_Is_Terminate");
      put(ZoomVideoSDKErrors.Errors_Meeting_Share_Error, "ZoomVideoSDKError_meeting_Share_Error");
      put(ZoomVideoSDKErrors.Errors_Meeting_Share_Module_Not_Ready, "ZoomVideoSDKError_meeting_Share_Module_Not_Ready");
      put(ZoomVideoSDKErrors.Errors_Meeting_Share_You_Are_Not_Sharing, "ZoomVideoSDKError_meeting_Share_You_Are_Not_Sharing");
      put(ZoomVideoSDKErrors.Errors_Meeting_Share_Type_Is_Not_Support, "ZoomVideoSDKError_meeting_Share_Type_Is_Not_Support");
      put(ZoomVideoSDKErrors.Errors_Meeting_Share_Internal_Error, "ZoomVideoSDKError_meeting_Share_Internal_Error");
    }};

  public static String valueOf(int status) {
    String result;
    try {
      result = errors.get(status);
    } catch (NoSuchKeyException e) {
      result = null;
    }
    return result;
  }

}
