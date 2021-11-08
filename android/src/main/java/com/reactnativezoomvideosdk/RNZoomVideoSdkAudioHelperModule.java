package com.reactnativezoomvideosdk;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import us.zoom.sdk.ZoomVideoSDK;
import us.zoom.sdk.ZoomVideoSDKAudioHelper;
import us.zoom.sdk.ZoomVideoSDKUser;

public class RNZoomVideoSdkAudioHelperModule  extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  RNZoomVideoSdkAudioHelperModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNZoomVideoSdkAudioHelper";
  }

  private ZoomVideoSDKAudioHelper getAudioHelper() {
    ZoomVideoSDKAudioHelper audioHelper = null;
    try {
      audioHelper = ZoomVideoSDK.getInstance().getAudioHelper();
      if (audioHelper == null) {
        throw new Exception("No Audio Helper Found");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return audioHelper;
  }

  @ReactMethod
  public void canSwitchSpeaker(Promise promise) {
     promise.resolve(getAudioHelper().canSwitchSpeaker());
  }

  @ReactMethod
  public void getSpeakerStatus(Promise promise) {
    promise.resolve(getAudioHelper().getSpeakerStatus());
  }

  @ReactMethod
  public void muteAudio(String userId, Promise promise) {
    ZoomVideoSDKUser user = RNZoomVideoSdkUserModule.getUser(userId);
    if (user != null) {
      promise.resolve(getAudioHelper().muteAudio(user));
    }
  }

  @ReactMethod
  public void unmuteAudio(String userId, Promise promise) {
    ZoomVideoSDKUser user = RNZoomVideoSdkUserModule.getUser(userId);
    if (user != null) {
      promise.resolve(getAudioHelper().unMuteAudio(user));
    }
  }
  @ReactMethod
  public void setSpeaker(boolean isOn, Promise promise) {
    promise.resolve(getAudioHelper().setSpeaker(isOn));
  }

  @ReactMethod
  public void startAudio(Promise promise) {
    promise.resolve(getAudioHelper().startAudio());
  }

  @ReactMethod
  public void stopAudio(Promise promise) {
    promise.resolve(getAudioHelper().stopAudio());
  }

  @ReactMethod
  public void subscribe(Promise promise) {
    promise.resolve(getAudioHelper().subscribe());
  }

  @ReactMethod
  public void unsubscribe(Promise promise) {
    promise.resolve(getAudioHelper().unSubscribe());
  }

}
