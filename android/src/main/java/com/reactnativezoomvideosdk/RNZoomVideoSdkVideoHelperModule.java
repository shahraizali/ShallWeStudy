package com.reactnativezoomvideosdk;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;

import us.zoom.sdk.ZoomVideoSDK;
import us.zoom.sdk.ZoomVideoSDKCameraDevice;
import us.zoom.sdk.ZoomVideoSDKVideoHelper;

public class RNZoomVideoSdkVideoHelperModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  RNZoomVideoSdkVideoHelperModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNZoomVideoSdkVideoHelper";
  }

  private ZoomVideoSDKVideoHelper getVideoHelper() {
    ZoomVideoSDKVideoHelper videoHelper = null;
    try {
      videoHelper = ZoomVideoSDK.getInstance().getVideoHelper();
      if (videoHelper == null) {
        throw new Exception("No Video Helper Found");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return videoHelper;
  }

  @ReactMethod
  public void getCameraList(Promise promise) {
    WritableArray cameraList = new WritableNativeArray();
    for (ZoomVideoSDKCameraDevice device : getVideoHelper().getCameraList()) {
      WritableMap camera = new WritableNativeMap();
      camera.putString("deviceId", device.getDeviceId());
      camera.putString("deviceName", device.getDeviceName());
      cameraList.pushMap(camera);
    }

    promise.resolve(cameraList);
  }

  @ReactMethod
  public void getNumberOfCameras(Promise promise) {
    promise.resolve(getVideoHelper().getNumberOfCameras());
  }

  @ReactMethod
  public void rotateMyVideo(double rotation, Promise promise) {
    promise.resolve(getVideoHelper().rotateMyVideo((int) rotation));
  }

  @ReactMethod
  public void startVideo(Promise promise) {
    promise.resolve(getVideoHelper().startVideo());
  }

  @ReactMethod
  public void stopVideo(Promise promise) {
    promise.resolve(getVideoHelper().stopVideo());
  }

  @ReactMethod
  public void switchCamera(String deviceId, Promise promise) {
    if (deviceId != null) {
      ZoomVideoSDKCameraDevice device = getVideoHelper().getCameraList()
        .stream()
        .filter(c -> c.getDeviceId().equals(deviceId))
        .findAny()
        .orElse(null);
      if (device != null) {
        promise.resolve(getVideoHelper().switchCamera(device));
      }
    }
    promise.resolve(getVideoHelper().switchCamera());
  }

}
