package com.reactnativezoomvideosdk;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.reactnativezoomvideosdk.convert.RNZoomVideoSdkShareStatus;

import java.util.List;

import us.zoom.sdk.ZoomVideoSDK;
import us.zoom.sdk.ZoomVideoSDKSession;
import us.zoom.sdk.ZoomVideoSDKUser;

public class RNZoomVideoSdkUserModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  RNZoomVideoSdkUserModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNZoomVideoSdkUser";
  }

  public static ZoomVideoSDKUser getUser(String userId) {
    ZoomVideoSDKSession session = ZoomVideoSDK.getInstance().getSession();
    ZoomVideoSDKUser myUser = session.getMySelf();

    if (myUser.getUserID().equals(userId)) {
      return myUser;
    }

    return session.getRemoteUsers()
      .stream()
      .filter(u -> u.getUserID().equals(userId))
      .findAny()
      .orElse(null);
  }

  public static ReadableArray mapUserArray(List<ZoomVideoSDKUser> userList) {
    WritableArray mappedUserArray = new WritableNativeArray();
    for (ZoomVideoSDKUser user : userList) {
      mappedUserArray.pushMap(mapUser(user));
    }
    return mappedUserArray;
  }

  public static ReadableMap mapUser(ZoomVideoSDKUser user) {
    WritableMap mappedUser = Arguments.createMap();
    mappedUser.putString("userId", user.getUserID());
    mappedUser.putString("customUserId", user.getCustomIdentity());
    mappedUser.putString("userName", user.getUserName());
    mappedUser.putBoolean("isHost", user.isHost());
    mappedUser.putBoolean("isManager", user.isManager());
    return mappedUser;
  }

  @ReactMethod
  public void getUserName(String userId, Promise promise) {
    ZoomVideoSDKUser user = RNZoomVideoSdkUserModule.getUser(userId);
    if (user != null) {
      promise.resolve(user.getUserName());
    }
  }

  @ReactMethod
  public void getShareStatus(String userId, Promise promise) {
    ZoomVideoSDKUser user = RNZoomVideoSdkUserModule.getUser(userId);
    if (user != null) {
      promise.resolve(RNZoomVideoSdkShareStatus.valueOf(user.getShareStatus()));
    }
  }

  @ReactMethod
  public void isHost(String userId, Promise promise) {
    ZoomVideoSDKUser user = RNZoomVideoSdkUserModule.getUser(userId);
    if (user != null) {
      promise.resolve(user.isHost());
    }
  }

  @ReactMethod
  public void isManager(String userId, Promise promise) {
    ZoomVideoSDKUser user = RNZoomVideoSdkUserModule.getUser(userId);
    if (user != null) {
      promise.resolve(user.isManager());
    }
  }

}
