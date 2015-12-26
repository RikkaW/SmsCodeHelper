package android.support.v4.media.routing;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Build.VERSION;
import android.os.Handler;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class MediaRouterJellybeanMr1$ActiveScanWorkaround
  implements Runnable
{
  private static final int WIFI_DISPLAY_SCAN_INTERVAL = 15000;
  private boolean mActivelyScanningWifiDisplays;
  private final DisplayManager mDisplayManager;
  private final Handler mHandler;
  private Method mScanWifiDisplaysMethod;
  
  public MediaRouterJellybeanMr1$ActiveScanWorkaround(Context paramContext, Handler paramHandler)
  {
    if (Build.VERSION.SDK_INT != 17) {
      throw new UnsupportedOperationException();
    }
    mDisplayManager = ((DisplayManager)paramContext.getSystemService("display"));
    mHandler = paramHandler;
    try
    {
      mScanWifiDisplaysMethod = DisplayManager.class.getMethod("scanWifiDisplays", new Class[0]);
      return;
    }
    catch (NoSuchMethodException paramContext) {}
  }
  
  public void run()
  {
    if (mActivelyScanningWifiDisplays) {}
    try
    {
      mScanWifiDisplaysMethod.invoke(mDisplayManager, new Object[0]);
      mHandler.postDelayed(this, 15000L);
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      for (;;)
      {
        Log.w("MediaRouterJellybeanMr1", "Cannot scan for wifi displays.", localIllegalAccessException);
      }
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      for (;;)
      {
        Log.w("MediaRouterJellybeanMr1", "Cannot scan for wifi displays.", localInvocationTargetException);
      }
    }
  }
  
  public void setActiveScanRouteTypes(int paramInt)
  {
    if ((paramInt & 0x2) != 0) {
      if (!mActivelyScanningWifiDisplays)
      {
        if (mScanWifiDisplaysMethod == null) {
          break label35;
        }
        mActivelyScanningWifiDisplays = true;
        mHandler.post(this);
      }
    }
    label35:
    while (!mActivelyScanningWifiDisplays)
    {
      return;
      Log.w("MediaRouterJellybeanMr1", "Cannot scan for wifi displays because the DisplayManager.scanWifiDisplays() method is not available on this device.");
      return;
    }
    mActivelyScanningWifiDisplays = false;
    mHandler.removeCallbacks(this);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.routing.MediaRouterJellybeanMr1.ActiveScanWorkaround
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */