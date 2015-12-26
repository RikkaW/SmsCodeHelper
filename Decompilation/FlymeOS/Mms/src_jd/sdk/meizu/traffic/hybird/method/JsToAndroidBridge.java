package sdk.meizu.traffic.hybird.method;

import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import sdk.meizu.traffic.hybird.exception.NativeMethodError;

public class JsToAndroidBridge
{
  private static final String TAG = JsToAndroidBridge.class.getSimpleName();
  private INativeInterface mNativeInterface;
  private Method[] mNativeMethods;
  private final HashMap<String, NativeMethodInfo> mNativeMethodsCache = new LinkedHashMap();
  
  public JsToAndroidBridge(INativeInterface paramINativeInterface)
  {
    mNativeInterface = paramINativeInterface;
  }
  
  private NativeMethodInfo generateNativeMethodInfo(String paramString1, String paramString2)
  {
    Object localObject2 = null;
    Method[] arrayOfMethod = mNativeMethods;
    int j = arrayOfMethod.length;
    int i = 0;
    for (;;)
    {
      Object localObject1 = localObject2;
      if (i < j)
      {
        localObject1 = arrayOfMethod[i];
        if (((Method)localObject1).getName().equals(paramString1))
        {
          validateNativeMethod((Method)localObject1);
          localObject1 = new NativeMethodInfo(mNativeInterface, (Method)localObject1, paramString2);
        }
      }
      else
      {
        if (localObject1 == null) {
          Log.e(TAG, paramString1 + " has no defined in native interface");
        }
        return (NativeMethodInfo)localObject1;
      }
      i += 1;
    }
  }
  
  private void initNativeMethods()
  {
    if (mNativeMethods == null) {
      mNativeMethods = mNativeInterface.getClass().getMethods();
    }
  }
  
  private void invokeMethod(String paramString1, String paramString2, String paramString3)
  {
    NativeMethodInfo localNativeMethodInfo2 = (NativeMethodInfo)mNativeMethodsCache.get(paramString1);
    NativeMethodInfo localNativeMethodInfo1 = localNativeMethodInfo2;
    if (localNativeMethodInfo2 == null)
    {
      initNativeMethods();
      localNativeMethodInfo1 = generateNativeMethodInfo(paramString1, paramString3);
      mNativeMethodsCache.put(paramString1, localNativeMethodInfo1);
    }
    localNativeMethodInfo1.invoke(paramString2);
  }
  
  private void validateNativeMethod(Method paramMethod)
  {
    if (paramMethod.getAnnotation(NativeMethod.class) == null) {
      throw new NativeMethodError(paramMethod.getName() + "can't be invoke by Javascript! @NativeMethod annotation couldn't be found!");
    }
  }
  
  @JavascriptInterface
  public void doAndroidAction(String paramString)
  {
    doAndroidAction(paramString, null, null);
  }
  
  @JavascriptInterface
  public void doAndroidAction(String paramString1, String paramString2)
  {
    doAndroidAction(paramString1, paramString2, null);
  }
  
  @JavascriptInterface
  public void doAndroidAction(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      if (TextUtils.isEmpty(paramString1)) {
        Log.e(TAG, "doAndroidAction fails! The function parameter couldn't be null");
      }
      invokeMethod(paramString1, paramString2, paramString3);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      Log.e(TAG, "function: " + paramString1 + " | data: " + paramString2 + " callback: " + paramString3 + " exception: " + localException.getMessage());
    }
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.hybird.method.JsToAndroidBridge
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */