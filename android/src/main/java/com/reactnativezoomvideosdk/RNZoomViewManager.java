package com.reactnativezoomvideosdk;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.reactnativezoomvideosdk.convert.RNZoomVideoSdkVideoAspect;

import us.zoom.sdk.ZoomVideoSDKUser;
import us.zoom.sdk.ZoomVideoSDKVideoAspect;
import us.zoom.sdk.ZoomVideoSDKVideoCanvas;
import us.zoom.sdk.ZoomVideoSDKVideoView;


public class RNZoomViewManager extends SimpleViewManager<ZoomVideoSDKVideoView>  {

  private final ReactApplicationContext reactContext;
  private ZoomVideoSDKVideoView videoView;
  private ZoomVideoSDKVideoCanvas currentCanvas;
  private String userId;
  private boolean sharing;
  private ZoomVideoSDKVideoAspect videoAspect;

  public RNZoomViewManager(ReactApplicationContext reactContext) {
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNZoomView";
  }

  @Override
  protected ZoomVideoSDKVideoView createViewInstance(ThemedReactContext reactContext) {
    userId = "";
    sharing = false;
    videoAspect = ZoomVideoSDKVideoAspect.ZoomVideoSDKVideoAspect_Original;
    videoView = new ZoomVideoSDKVideoView(reactContext);
    videoView.setZOrderMediaOverlay(true);
    return videoView;
  }

  @ReactProp(name = "userId")
  public void setUserId(ZoomVideoSDKVideoView videoView, String newUserId) {
    if (newUserId.equals(userId)) {
      return;
    }
    userId = newUserId;
    setViewingCanvas();
  }

  @ReactProp(name = "sharing")
  public void setSharing(ZoomVideoSDKVideoView videoView, boolean newSharing) {
    if (sharing == newSharing) {
      return;
    }
    sharing = newSharing;
    setViewingCanvas();
  }

  @ReactProp(name = "fullScreen")
  public void setFullScreen(ZoomVideoSDKVideoView videoView, Boolean fullScreen) {
    videoView.setZOrderOnTop(!fullScreen);
  }

  @ReactProp(name = "videoAspect")
  public void setAspect(ZoomVideoSDKVideoView videoView, String newVideoAspect) {
    ZoomVideoSDKVideoAspect aspect = RNZoomVideoSdkVideoAspect.valueOf(newVideoAspect);
    if (videoAspect == aspect) {
      return;
    }
    videoAspect = aspect;
    setViewingCanvas();
  }

  private void setViewingCanvas()
  {
    ZoomVideoSDKUser user = RNZoomVideoSdkUserModule.getUser(userId);
    if (user == null) return;
    if (currentCanvas != null) {
      currentCanvas.unSubscribe(videoView);
      currentCanvas = null;
    }
    currentCanvas = sharing ? user.getShareCanvas() : user.getVideoCanvas();
    currentCanvas.subscribe(videoView, videoAspect);
  }

}
