package com.reactnativezoomvideosdk;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.reactnativezoomvideosdk.convert.RNZoomVideoSDKErrors;
import com.reactnativezoomvideosdk.convert.RNZoomVideoSdkAudioType;

import us.zoom.sdk.ZoomVideoSDKUser;

public class RNZoomVideoSdkAudioStatusModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  RNZoomVideoSdkAudioStatusModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNZoomVideoSdkAudioStatus";
  }

  @ReactMethod
  public void isMuted(String userId, Promise promise) {
    ZoomVideoSDKUser user = RNZoomVideoSdkUserModule.getUser(userId);
    if (user != null) {
      promise.resolve(user.getAudioStatus().isMuted());
    }
  }

  @ReactMethod
  public void isTalking(String userId, Promise promise) {
    ZoomVideoSDKUser user = RNZoomVideoSdkUserModule.getUser(userId);
    if (user != null) {
      promise.resolve(user.getAudioStatus().isTalking());
    }
  }

  @ReactMethod
  public void getAudioType(String userId, Promise promise) {
    ZoomVideoSDKUser user = RNZoomVideoSdkUserModule.getUser(userId);
    if (user != null) {
      promise.resolve(RNZoomVideoSdkAudioType.valueOf(user.getAudioStatus().getAudioType()));
    }
  }

}

