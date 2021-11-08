package com.reactnativezoomvideosdk;

import android.content.Context;
import android.content.Intent;
import android.media.projection.MediaProjectionManager;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import us.zoom.sdk.ZoomVideoSDK;
import us.zoom.sdk.ZoomVideoSDKShareHelper;

public class RNZoomVideoSdkShareHelperModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  RNZoomVideoSdkShareHelperModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNZoomVideoSdkShareHelper";
  }

  private ZoomVideoSDKShareHelper getShareHelper() {
    ZoomVideoSDKShareHelper shareHelper = null;
    try {
      shareHelper = ZoomVideoSDK.getInstance().getShareHelper();
      if (shareHelper == null) {
        throw new Exception("No Share Found");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return shareHelper;
  }

  @ReactMethod
  public void shareScreen() throws Exception {
    MediaProjectionManager manager =
      (MediaProjectionManager) reactContext.getSystemService(Context.MEDIA_PROJECTION_SERVICE);
    if (manager != null) {
      Intent intent = manager.createScreenCaptureIntent();
      reactContext.startActivityForResult(intent, 0, null);
    } else {
      throw new Exception("Notification service must be implemented.");
    }
  }

  @ReactMethod
  public void shareView() {
    // TODO
  }

  @ReactMethod
  public void lockShare(boolean lock, Promise promise) {
    getShareHelper().lockShare(lock);
  }

  @ReactMethod
  public void stopShare() {
    getShareHelper().stopShare();
  }

  @ReactMethod
  public void isOtherSharing(Promise promise) {
    promise.resolve(getShareHelper().isOtherSharing());
  }

  @ReactMethod
  public void isScreenSharingOut(Promise promise) {
    promise.resolve(getShareHelper().isScreenSharingOut());
  }

  @ReactMethod
  public void isShareLocked(Promise promise) {
    promise.resolve(getShareHelper().isShareLocked());
  }

  @ReactMethod
  public void isSharingOut(Promise promise) {
    promise.resolve(getShareHelper().isSharingOut());
  }

}
