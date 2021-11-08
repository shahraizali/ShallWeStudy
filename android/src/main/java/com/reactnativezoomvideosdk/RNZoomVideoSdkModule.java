package com.reactnativezoomvideosdk;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.reactnativezoomvideosdk.convert.RNZoomVideoSDKErrors;
import com.reactnativezoomvideosdk.convert.RNZoomVideoSdkLiveStreamStatus;
import com.reactnativezoomvideosdk.convert.RNZoomVideoSdkRawDataMemoryMode;
import com.reactnativezoomvideosdk.convert.RNZoomVideoSdkShareStatus;
import com.reactnativezoomvideosdk.convert.RNZoomVideoSdkVideoResolution;

import us.zoom.sdk.ZoomVideoSDK;
import us.zoom.sdk.ZoomVideoSDKAudioHelper;
import us.zoom.sdk.ZoomVideoSDKAudioOption;
import us.zoom.sdk.ZoomVideoSDKAudioRawData;
import us.zoom.sdk.ZoomVideoSDKAudioStatus;
import us.zoom.sdk.ZoomVideoSDKChatHelper;
import us.zoom.sdk.ZoomVideoSDKChatMessage;
import us.zoom.sdk.ZoomVideoSDKErrors;
import us.zoom.sdk.ZoomVideoSDKInitParams;
import us.zoom.sdk.ZoomVideoSDKLiveStreamHelper;
import us.zoom.sdk.ZoomVideoSDKLiveStreamStatus;
import us.zoom.sdk.ZoomVideoSDKPasswordHandler;
import us.zoom.sdk.ZoomVideoSDKSession;
import us.zoom.sdk.ZoomVideoSDKSessionContext;
import us.zoom.sdk.ZoomVideoSDKDelegate;
import us.zoom.sdk.ZoomVideoSDKShareHelper;
import us.zoom.sdk.ZoomVideoSDKShareStatus;
import us.zoom.sdk.ZoomVideoSDKUser;
import us.zoom.sdk.ZoomVideoSDKUserHelper;
import us.zoom.sdk.ZoomVideoSDKVideoHelper;
import us.zoom.sdk.ZoomVideoSDKVideoOption;
import us.zoom.sdk.ZoomVideoSDKVideoStatus;

import androidx.annotation.Nullable;

import android.app.Service;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import java.util.List;

public class RNZoomVideoSdkModule extends ReactContextBaseJavaModule implements ZoomVideoSDKDelegate, LifecycleEventListener {

  private final String DEBUG_TAG = "ZoomVideoSdkDebug";
  private final ReactApplicationContext reactContext;
  protected Display display;
  protected DisplayMetrics displayMetrics;

  RNZoomVideoSdkModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
    reactContext.addLifecycleEventListener(this);

    display = ((WindowManager) reactContext.getSystemService(Service.WINDOW_SERVICE)).getDefaultDisplay();
    displayMetrics = new DisplayMetrics();
    display.getMetrics(displayMetrics);
  }

  @Override
  public String getName() {
    return "RNZoomVideoSdk";
  }

  public String[] supportedEvents() {
    return new String[] {
      "onSessionJoin",
      "onSessionLeave",
      "onUserJoin",
      "onUserLeave",
      "onUserShareStatusChanged",
      "onSessionNeedPassword",
      "onSessionPasswordWrong",
      "onError",
    };
  }

  @ReactMethod
  public void initSdk(ReadableMap config, Promise promise) {
    reactContext.getCurrentActivity().runOnUiThread(new Runnable() {
      @Override
      public void run() {
        ZoomVideoSDKInitParams params = new ZoomVideoSDKInitParams();
        params.domain = config.getString("domain");
        params.logFilePrefix = config.getString("logFilePrefix");
        params.enableLog = config.getBoolean("enableLog");

        params.videoRawDataMemoryMode = RNZoomVideoSdkRawDataMemoryMode.valueOf(config.getString("videoRawDataMemoryMode"));
        params.audioRawDataMemoryMode = RNZoomVideoSdkRawDataMemoryMode.valueOf(config.getString("audioRawDataMemoryMode"));
        params.shareRawDataMemoryMode = RNZoomVideoSdkRawDataMemoryMode.valueOf(config.getString("shareRawDataMemoryMode"));

        ZoomVideoSDK sdk = ZoomVideoSDK.getInstance();
        int initResult = sdk.initialize(reactContext.getCurrentActivity(), params);

        switch (initResult) {
          case ZoomVideoSDKErrors.Errors_Success:
            Log.d(DEBUG_TAG, "SDK initialized successfully");
            promise.resolve("SDK initialized successfully");
            break;
          default:
            Log.d(DEBUG_TAG, String.format("SDK failed to initialize with error code: %lu", initResult));
            promise.reject("sdkinit_failed", "Init SDK Failed", (WritableMap) null);
            break;
        }

        refreshRotation();
      }
    });
  }

  @ReactMethod
  public void joinSession(ReadableMap config, Promise promise) {
    ZoomVideoSDKAudioOption audioOption = new ZoomVideoSDKAudioOption();
    ReadableMap audioOptionConfig = config.getMap("audioOptions");
    audioOption.connect = audioOptionConfig.getBoolean("connect");
    audioOption.mute = audioOptionConfig.getBoolean("mute");

    ZoomVideoSDKVideoOption videoOption = new ZoomVideoSDKVideoOption();
    ReadableMap videoOptionConfig = config.getMap("videoOptions");
    videoOption.localVideoOn = videoOptionConfig.getBoolean("localVideoOn");

    ZoomVideoSDKSessionContext sessionContext = new ZoomVideoSDKSessionContext();
    sessionContext.sessionName = config.getString("sessionName");
    sessionContext.userName = config.getString("userName");
    sessionContext.token = config.getString("token");
    sessionContext.sessionPassword = config.getString("sessionPassword");
    sessionContext.audioOption = audioOption;
    sessionContext.videoOption = videoOption;

    ZoomVideoSDK.getInstance().addListener(this);

    try {
      ZoomVideoSDKSession session = ZoomVideoSDK.getInstance().joinSession(sessionContext);
      promise.resolve(null);
    } catch(Exception e) {
      promise.reject("joinSession_failure", "Join Session failed", (WritableMap) null);
    }
  }

  @ReactMethod
  public void leaveSession(boolean shouldEndSession) {
    ZoomVideoSDK.getInstance().removeListener(this);

    reactContext.getCurrentActivity().runOnUiThread(new Runnable() {
      @Override
      public void run() {
        ZoomVideoSDK.getInstance().leaveSession(shouldEndSession);
      }
    });
  }

  @ReactMethod
  public void getSdkVersion(Promise promise) {
    promise.resolve(ZoomVideoSDK.getInstance().getSDKVersion());
  }

  @ReactMethod
  public void isInSession(Promise promise) {
    promise.resolve(ZoomVideoSDK.getInstance().isInSession());
  }

  // -----------------------------------------------------------------------------------------------
  // region ZoomVideoSDKDelegate
  // -----------------------------------------------------------------------------------------------

  @Override
  public void onSessionJoin() {
    WritableMap params = Arguments.createMap();
    ReadableMap mySelf = RNZoomVideoSdkUserModule.mapUser(ZoomVideoSDK.getInstance().getSession().getMySelf());
    params.putMap("mySelf", mySelf);
    sendEvent(reactContext, "onSessionJoin", params);
  }

  @Override
  public void onSessionLeave() {
    sendEvent(reactContext, "onSessionLeave", Arguments.createMap());
  }

  @Override
  public void onError(int i) {
    WritableMap params = Arguments.createMap();
    params.putString("errorType", RNZoomVideoSDKErrors.valueOf(i));
    sendEvent(reactContext, "onError", params);
  }

  @Override
  public void onUserJoin(ZoomVideoSDKUserHelper userHelper, List<ZoomVideoSDKUser> userList) {
    List<ZoomVideoSDKUser> remoteUsers = ZoomVideoSDK.getInstance().getSession().getRemoteUsers();
    WritableMap params = Arguments.createMap();
    params.putArray("joinedUsers", RNZoomVideoSdkUserModule.mapUserArray(userList));
    params.putArray("remoteUsers", RNZoomVideoSdkUserModule.mapUserArray(remoteUsers));
    sendEvent(reactContext, "onUserJoin", params);
  }

  @Override
  public void onUserLeave(ZoomVideoSDKUserHelper userHelper, List<ZoomVideoSDKUser> userList) {
    List<ZoomVideoSDKUser> remoteUsers = ZoomVideoSDK.getInstance().getSession().getRemoteUsers();
    WritableMap params = Arguments.createMap();
    params.putArray("leftUsers", RNZoomVideoSdkUserModule.mapUserArray(userList));
    params.putArray("remoteUsers", RNZoomVideoSdkUserModule.mapUserArray(remoteUsers));
    sendEvent(reactContext, "onUserLeave", params);
  }

  @Override
  public void onUserVideoStatusChanged(ZoomVideoSDKVideoHelper videoHelper, List<ZoomVideoSDKUser> userList) {
    WritableMap params = Arguments.createMap();
    params.putArray("changedUsers", RNZoomVideoSdkUserModule.mapUserArray(userList));
    sendEvent(reactContext, "onUserVideoStatusChanged", params);
  }

  @Override
  public void onUserAudioStatusChanged(ZoomVideoSDKAudioHelper audioHelper, List<ZoomVideoSDKUser> userList) {
    WritableMap params = Arguments.createMap();
    params.putArray("changedUsers", RNZoomVideoSdkUserModule.mapUserArray(userList));
    sendEvent(reactContext, "onUserAudioStatusChanged", params);
  }

  @Override
  public void onUserShareStatusChanged(ZoomVideoSDKShareHelper shareHelper, ZoomVideoSDKUser user, ZoomVideoSDKShareStatus shareStatus) {
    WritableMap params = Arguments.createMap();
    params.putMap("user", RNZoomVideoSdkUserModule.mapUser(user));
    params.putString("status", RNZoomVideoSdkShareStatus.valueOf(shareStatus));
    sendEvent(reactContext, "onUserShareStatusChanged", params);
  }

  @Override
  public void onLiveStreamStatusChanged(ZoomVideoSDKLiveStreamHelper liveStreamHelper, ZoomVideoSDKLiveStreamStatus liveStreamStatus) {
    WritableMap params = Arguments.createMap();
    params.putString("status", RNZoomVideoSdkLiveStreamStatus.valueOf(liveStreamStatus));
    sendEvent(reactContext, "onLiveStreamStatusChanged", params);
  }

  @Override
  public void onChatNewMessageNotify(ZoomVideoSDKChatHelper chatHelper, ZoomVideoSDKChatMessage chatMessage) {
    String content = chatMessage.getContent();
    ZoomVideoSDKUser sender = chatMessage.getSenderUser();
    sendEvent(reactContext, "onChatNewMessageNotify", (WritableMap) RNZoomVideoSdkChatMessageModule.mapChatMessage(chatMessage));
  }

  @Override
  public void onUserNameChanged(ZoomVideoSDKUser user) {
    WritableMap params = Arguments.createMap();
    params.putMap("changedUser", RNZoomVideoSdkUserModule.mapUser(user));
    sendEvent(reactContext, "onUserNameChanged", params);
  }

  @Override
  public void onUserHostChanged(ZoomVideoSDKUserHelper userHelper, ZoomVideoSDKUser user) {
    WritableMap params = Arguments.createMap();
    params.putMap("changedUser", RNZoomVideoSdkUserModule.mapUser(user));
    sendEvent(reactContext, "onUserHostChanged", params);
  }

  @Override
  public void onUserManagerChanged(ZoomVideoSDKUser user) {
    WritableMap params = Arguments.createMap();
    params.putMap("changedUser", RNZoomVideoSdkUserModule.mapUser(user));
    sendEvent(reactContext, "onUserManagerChanged", params);
  }

  @Override
  public void onUserActiveAudioChanged(ZoomVideoSDKAudioHelper audioHelper, List<ZoomVideoSDKUser> userList) {
    WritableMap params = Arguments.createMap();
    params.putArray("changedUsers", RNZoomVideoSdkUserModule.mapUserArray(userList));
    sendEvent(reactContext, "onUserActiveAudioChanged", params);
  }

  @Override
  public void onSessionNeedPassword(ZoomVideoSDKPasswordHandler passwordHandler) {
    passwordHandler.leaveSessionIgnorePassword();
    sendEvent(reactContext, "onSessionNeedPassword", null);
  }

  @Override
  public void onSessionPasswordWrong(ZoomVideoSDKPasswordHandler passwordHandler) {
    passwordHandler.leaveSessionIgnorePassword();
    sendEvent(reactContext, "onSessionPasswordWrong", null);
  }

  @Override
  public void onMixedAudioRawDataReceived(ZoomVideoSDKAudioRawData audioRawData) {
    Log.d(DEBUG_TAG, "onMixedAudioRawDataReceived");
  }

  @Override
  public void onOneWayAudioRawDataReceived(ZoomVideoSDKAudioRawData audioRawData, ZoomVideoSDKUser user) {
    Log.d(DEBUG_TAG, "onOneWayAudioRawDataReceived");
  }

  @Override
  public void onShareAudioRawDataReceived(ZoomVideoSDKAudioRawData audioRawData) {
    Log.d(DEBUG_TAG, "onShareAudioRawDataReceived");
  }

  // -----------------------------------------------------------------------------------------------
  // endregion
  // -----------------------------------------------------------------------------------------------

  // -----------------------------------------------------------------------------------------------
  // region LifecycleEventListener
  // -----------------------------------------------------------------------------------------------

  @Override
  public void onHostResume() {
    refreshRotation();
    Log.d(DEBUG_TAG, "onHostResume");
  }

  @Override
  public void onHostPause() {
    Log.d(DEBUG_TAG, "onHostPause");
  }

  @Override
  public void onHostDestroy() {
    Log.d(DEBUG_TAG, "onHostDestroy");
  }

  // -----------------------------------------------------------------------------------------------
  // endregion
  // -----------------------------------------------------------------------------------------------

  // -----------------------------------------------------------------------------------------------
  // region Helper Methods
  // -----------------------------------------------------------------------------------------------

  protected void refreshRotation() {
    int displayRotation = display.getRotation();
    if (ZoomVideoSDK.getInstance().getVideoHelper() != null) {
      ZoomVideoSDK.getInstance().getVideoHelper().rotateMyVideo(displayRotation);
    }
  }

  private void sendEvent(ReactContext reactContext, String eventName, @Nullable WritableMap params) {
    reactContext
      .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
      .emit(eventName, params);
  }

  // -----------------------------------------------------------------------------------------------
  // endregion
  // -----------------------------------------------------------------------------------------------

}
