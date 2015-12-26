package com.xiaomi.mms.transaction;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.ui.MessageUtils;
import com.android.mms.util.MSimUtils;
import com.xiaomi.accountsdk.activate.ActivateManager;
import com.xiaomi.accountsdk.activate.ActivateManager.ActivateManagerFuture;
import com.xiaomi.accountsdk.activate.ActivateStatusReceiver;
import com.xiaomi.mms.utils.MxCapabilityText;
import com.xiaomi.mms.utils.PrefUtils;
import com.xiaomi.mms.utils.logger.MyLog;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import miui.push.ServiceClient;
import miui.util.DropBoxLog;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MxActivateService
  extends IntentService
{
  private static final Object BUSY_FLAG_LOCK = new Object();
  private static final long[] INTS = { 2000L, 4000L, 8000L, 15000L, 30000L, 60000L };
  private static final Object MX_STATUS_INFO_LOCK = new Object();
  private static int mIsEnabling = 0;
  private static Map<String, MxStatusInfo> mMxStatusInfo;
  private static boolean[] sEnableAfterActivation = new boolean[2];
  
  public MxActivateService()
  {
    super("MxActivateService");
  }
  
  private static void checkMxLoginInfo(MxStatusInfo paramMxStatusInfo)
  {
    if (paramMxStatusInfo == null) {}
    do
    {
      return;
      if (TextUtils.isEmpty(serviceToken)) {
        Log.d("MxActivateService", "saved service token is empty");
      }
      if (TextUtils.isEmpty(security)) {
        Log.d("MxActivateService", "saved security is empty");
      }
    } while (!TextUtils.isEmpty(simId));
    Log.d("MxActivateService", "sim if for saved info is empty");
  }
  
  public static void clearLastFailureCode(Context paramContext)
  {
    setLastFailureCode(paramContext, -1);
  }
  
  private void closeChannel(int paramInt)
  {
    String str2 = PushSession.getInstance(this).getMyMid(paramInt);
    Object localObject = str2;
    if (str2 == null)
    {
      localObject = str2;
      if (MSimUtils.getSimId(this, paramInt) != null) {
        localObject = ActivateManager.get(this).getActivateInfo(paramInt);
      }
    }
    try
    {
      localObject = ((Bundle)((ActivateManager.ActivateManagerFuture)localObject).getResult()).getString("activate_sim_user_id");
      if (localObject == null)
      {
        Log.e("MxActivateService", "failed to get sim user, close channel aborted");
        return;
      }
    }
    catch (Exception localException)
    {
      String str1;
      for (;;)
      {
        Log.e("MxActivateService", "error when get sim user", localException);
        str1 = str2;
      }
      PushSession.getInstance(this).setStatus(this, paramInt, PushSession.Status.DISCONNECTED);
      ServiceClient.getInstance(this).closeChannel("3", str1);
    }
  }
  
  public static void closeChannel(Context paramContext, int paramInt)
  {
    Intent localIntent = new Intent("com.xiaomi.mms.action.CLOSE_CHANNEL");
    localIntent.setPackage(paramContext.getPackageName());
    localIntent.putExtra("sim_index", paramInt);
    paramContext.startService(localIntent);
  }
  
  private void disableOldMxEnabled()
  {
    Object localObject = PreferenceManager.getDefaultSharedPreferences(this);
    if (((SharedPreferences)localObject).getBoolean("pref_key_enable_mx", false))
    {
      localObject = ((SharedPreferences)localObject).edit();
      ((SharedPreferences.Editor)localObject).putBoolean("pref_key_enable_mx", false);
      ((SharedPreferences.Editor)localObject).commit();
    }
  }
  
  public static void enableAll(Context paramContext, boolean paramBoolean)
  {
    if (MSimUtils.isSimInserted(0)) {
      enableMx(paramContext, 0, true, paramBoolean);
    }
    if (MSimUtils.isSimInserted(1)) {
      enableMx(paramContext, 1, true, paramBoolean);
    }
  }
  
  public static void enableMx(Context paramContext, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    boolean bool1 = false;
    boolean bool2 = false;
    if (paramInt == 0)
    {
      bool1 = paramBoolean1;
      bool2 = isMxEnabled(paramContext, 1);
      if ((!bool1) && (!bool2)) {
        MxMessageService.resetMxCount(paramContext);
      }
      setIsEnabling(paramInt, paramBoolean1);
      if (paramBoolean2) {
        setMxEnabled(paramContext, paramInt, paramBoolean1);
      }
      if (!paramBoolean1) {
        break label122;
      }
    }
    label122:
    for (Object localObject = "com.xiaomi.mms.action.ENABLE_MX";; localObject = "com.xiaomi.mms.action.DISABLE_MX")
    {
      localObject = new Intent((String)localObject);
      ((Intent)localObject).putExtra("extra_manually", paramBoolean2);
      ((Intent)localObject).putExtra("sim_index", paramInt);
      ((Intent)localObject).setPackage(paramContext.getPackageName());
      paramContext.startService((Intent)localObject);
      return;
      if (paramInt != 1) {
        break;
      }
      bool1 = isMxEnabled(paramContext, 0);
      bool2 = paramBoolean1;
      break;
    }
  }
  
  public static String generateRandomString(int paramInt)
  {
    Random localRandom = new Random();
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    while (i < paramInt)
    {
      localStringBuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".charAt(localRandom.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".length())));
      i += 1;
    }
    return localStringBuffer.toString();
  }
  
  private static int getActivateStatus(int paramInt)
  {
    if (paramInt < 0) {}
    Bundle localBundle;
    do
    {
      return -1;
      localBundle = ActivateStatusReceiver.getActivateInfo(paramInt);
    } while (localBundle == null);
    return localBundle.getInt("activate_status", -1);
  }
  
  public static boolean getEnableAfterActivation(int paramInt)
  {
    synchronized (BUSY_FLAG_LOCK)
    {
      int i = sEnableAfterActivation[paramInt];
      return i;
    }
  }
  
  public static int getLastFailureCode(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getInt("pref_key_last_failure_code", -1);
  }
  
  public static String getMxActivateServiceFeedback(Context paramContext)
  {
    paramContext = null;
    DropBoxLog localDropBoxLog;
    int i;
    MxStatusInfo localMxStatusInfo;
    for (;;)
    {
      synchronized (MX_STATUS_INFO_LOCK)
      {
        if (mMxStatusInfo != null) {
          paramContext = mMxStatusInfo;
        }
        localDropBoxLog = new DropBoxLog();
        if (paramContext == null)
        {
          localDropBoxLog.v("MxActivateService", "no activate info is valid");
          return localDropBoxLog.getMessage();
        }
      }
      int j = mMxStatusInfo.size();
      localDropBoxLog.v("MxActivateService", "activate info size is " + j);
      Iterator localIterator = mMxStatusInfo.values().iterator();
      i = 0;
      while (i < j)
      {
        localMxStatusInfo = (MxStatusInfo)localIterator.next();
        if (localMxStatusInfo != null) {
          break label172;
        }
        localDropBoxLog.v("MxActivateService", "sim " + i + " activate info is null");
        i += 1;
      }
    }
    label172:
    ??? = mid;
    paramContext = (Context)???;
    if (TextUtils.isEmpty((CharSequence)???)) {
      paramContext = "null";
    }
    localDropBoxLog.v("MxActivateService", "sim " + i + " mid = " + paramContext);
    ??? = simId;
    paramContext = (Context)???;
    if (TextUtils.isEmpty((CharSequence)???)) {
      paramContext = "null";
    }
    localDropBoxLog.v("MxActivateService", "sim " + i + " simId = " + paramContext);
    ??? = serviceToken;
    paramContext = (Context)???;
    if (TextUtils.isEmpty((CharSequence)???)) {
      paramContext = "null";
    }
    localDropBoxLog.v("MxActivateService", "sim " + i + " serviceToken = " + paramContext);
    ??? = security;
    paramContext = (Context)???;
    if (TextUtils.isEmpty((CharSequence)???)) {
      paramContext = "null";
    }
    localDropBoxLog.v("MxActivateService", "sim " + i + " security = " + paramContext);
    if (enabled) {}
    for (paramContext = "true";; paramContext = "false")
    {
      localDropBoxLog.v("MxActivateService", "sim " + i + " enabled = " + paramContext);
      break;
    }
  }
  
  public static int getSimActivateStatus(Context paramContext)
  {
    int j = -1;
    int k = MSimUtils.getMultiSimCount();
    paramContext = new int[k];
    int i = 0;
    while (i < k)
    {
      paramContext[i] = -1;
      i += 1;
    }
    i = 0;
    while (i < k)
    {
      Bundle localBundle = ActivateStatusReceiver.getActivateInfo(i);
      if (localBundle.getBoolean("sim_inserted")) {
        paramContext[i] = localBundle.getInt("activate_status");
      }
      i += 1;
    }
    if (k > 1) {
      if ((paramContext[0] == 3) || (paramContext[1] == 3)) {
        i = 3;
      }
    }
    do
    {
      do
      {
        do
        {
          do
          {
            return i;
            if (((paramContext[0] == 1) && (paramContext[1] == 1)) || ((paramContext[0] == -1) && (paramContext[1] == 1))) {
              break;
            }
            i = j;
          } while (paramContext[0] != 1);
          i = j;
        } while (paramContext[1] != -1);
        return 1;
        i = j;
      } while (k <= 0);
      if (paramContext[0] == 3) {
        return 3;
      }
      i = j;
    } while (paramContext[0] != 1);
    return 1;
  }
  
  public static void invalidMxToken(Context paramContext, int paramInt)
  {
    Object localObject2 = MSimUtils.getSimId(paramContext, paramInt);
    if (!TextUtils.isEmpty((CharSequence)localObject2)) {
      synchronized (MX_STATUS_INFO_LOCK)
      {
        localObject2 = (MxStatusInfo)mMxStatusInfo.get(localObject2);
        if (localObject2 != null)
        {
          security = null;
          serviceToken = null;
          persistMxStatusInfo(paramContext, mMxStatusInfo);
        }
        return;
      }
    }
  }
  
  public static boolean isActivateStatusInitialized()
  {
    boolean bool = false;
    if ((getActivateStatus(0) != -1) || (getActivateStatus(1) != -1)) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean isActivating()
  {
    boolean bool = false;
    if ((getActivateStatus(0) == 2) || (getActivateStatus(1) == 2)) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean isBusy(int paramInt)
  {
    boolean bool = false;
    if (paramInt < 0) {
      return false;
    }
    Bundle localBundle = ActivateStatusReceiver.getActivateInfo(paramInt);
    for (;;)
    {
      synchronized (BUSY_FLAG_LOCK)
      {
        if (((mIsEnabling & 1 << paramInt) <= 0) && (localBundle.getInt("activate_status") != 2)) {
          return bool;
        }
      }
      bool = true;
    }
  }
  
  public static boolean isMxEnabled(Context paramContext)
  {
    boolean bool = false;
    if ((isMxEnabled(paramContext, 0, false)) || (isMxEnabled(paramContext, 1, false))) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean isMxEnabled(Context paramContext, int paramInt)
  {
    return isMxEnabled(paramContext, paramInt, false);
  }
  
  public static boolean isMxEnabled(Context paramContext, int paramInt, boolean paramBoolean)
  {
    if (paramInt < 0) {}
    boolean bool;
    int i;
    do
    {
      do
      {
        return paramBoolean;
        ??? = ActivateStatusReceiver.getActivateInfo(paramInt);
      } while (??? == null);
      bool = ((Bundle)???).getBoolean("sim_inserted");
      i = ((Bundle)???).getInt("activate_status", -1);
    } while ((!bool) || (i != 3));
    synchronized (MX_STATUS_INFO_LOCK)
    {
      if (mMxStatusInfo == null) {
        mMxStatusInfo = readMxLoginInfo(paramContext);
      }
      Object localObject2 = MSimUtils.getSimId(paramContext, paramInt);
      localObject2 = (MxStatusInfo)mMxStatusInfo.get(localObject2);
      if (localObject2 != null)
      {
        paramBoolean = enabled;
        return paramBoolean;
      }
    }
    if (mMxStatusInfo.isEmpty())
    {
      paramBoolean = PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("pref_key_enable_mx", paramBoolean);
      Log.d("MxActivateService", "cached old mx toggle preference, enabled: " + paramBoolean);
      return paramBoolean;
    }
    return paramBoolean;
  }
  
  private void onDisable(int paramInt)
  {
    setMxEnabled(this, paramInt, false);
    setIsEnabling(paramInt, false);
    Intent localIntent = new Intent("com.xiaomi.mms.action.DISABLE_RESULT");
    localIntent.setPackage(getPackageName());
    localIntent.putExtra("sim_index", paramInt);
    sendBroadcast(localIntent);
  }
  
  private void onEnableFailure(int paramInt, boolean paramBoolean)
  {
    if (!paramBoolean) {}
    for (boolean bool = true;; bool = false)
    {
      setMxEnabled(this, paramInt, bool);
      if (paramBoolean) {
        disableOldMxEnabled();
      }
      setIsEnabling(paramInt, false);
      Intent localIntent = new Intent("com.xiaomi.mms.action.ENBALE_RESULT");
      localIntent.setPackage(getPackageName());
      localIntent.putExtra("success", false);
      localIntent.putExtra("sim_index", paramInt);
      sendBroadcast(localIntent);
      return;
    }
  }
  
  private void onEnableSuccess(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    openChannel(paramInt, paramString1, paramString2, paramString3, paramString4);
    setMxEnabled(this, paramInt, true);
    setIsEnabling(paramInt, false);
    paramString1 = new Intent("com.xiaomi.mms.action.ENBALE_RESULT");
    paramString1.putExtra("success", true);
    paramString1.putExtra("sim_index", paramInt);
    sendBroadcast(paramString1);
  }
  
  private void onSevereFailure(int paramInt1, int paramInt2)
  {
    setMxEnabled(this, paramInt1, false);
    disableOldMxEnabled();
    setIsEnabling(paramInt1, false);
    Intent localIntent = new Intent("com.xiaomi.mms.action.ENBALE_RESULT");
    localIntent.putExtra("success", false);
    localIntent.setPackage(getPackageName());
    sendBroadcast(localIntent);
    setLastFailureCode(this, paramInt2);
  }
  
  private void onStartActivate(int paramInt)
  {
    Intent localIntent = new Intent("com.xiaomi.mms.action.STATUS_START");
    localIntent.setPackage(getPackageName());
    localIntent.putExtra("sim_index", paramInt);
    sendBroadcast(localIntent);
  }
  
  private void openChannel(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    PushSession localPushSession = PushSession.getInstance(this);
    String str = Settings.System.getString(getContentResolver(), "pref_mx_res");
    Object localObject = str;
    if (TextUtils.isEmpty(str))
    {
      localObject = generateRandomString(16);
      Settings.System.putString(getContentResolver(), "pref_mx_res", (String)localObject);
    }
    localPushSession.setMyFullMidAndPhone(paramInt, paramString1, (String)localObject, paramString4);
    paramString1 = ServiceClient.getInstance(this);
    localObject = new ArrayList();
    paramString4 = MessageUtils.getEncryptedPhone(paramString3, paramString4);
    if (!TextUtils.isEmpty(paramString4)) {
      ((ArrayList)localObject).add(new BasicNameValuePair("pid", paramString4));
    }
    for (;;)
    {
      ((ArrayList)localObject).add(new BasicNameValuePair("v", "1"));
      paramString4 = new ArrayList();
      paramString4.add(new BasicNameValuePair("cap", MxCapabilityText.combine(new String[] { "sms", "mms", "mx2audio", "mxV2mms2", "mxV3" })));
      try
      {
        paramString1.openChannel(localPushSession.getMyFullMid(paramInt), "3", paramString2, "XIAOMI-PASS", paramString3, false, paramString4, (List)localObject);
        return;
      }
      catch (Exception paramString1)
      {
        MyLog.e("MxActivateService", "openChanel faild", paramString1);
      }
      Log.e("MxActivateService", "pid is null");
    }
  }
  
  private static void persistMxStatusInfo(Context paramContext, Map<String, MxStatusInfo> paramMap)
  {
    JSONObject localJSONObject1 = new JSONObject();
    JSONArray localJSONArray = new JSONArray();
    paramMap = paramMap.values().iterator();
    for (;;)
    {
      if (paramMap.hasNext())
      {
        MxStatusInfo localMxStatusInfo = (MxStatusInfo)paramMap.next();
        JSONObject localJSONObject2 = new JSONObject();
        try
        {
          localJSONObject2.put("mid", mid);
          localJSONObject2.put("sim_id", simId);
          localJSONObject2.put("st", serviceToken);
          localJSONObject2.put("sec", security);
          localJSONObject2.put("enabled", enabled);
          localJSONArray.put(localJSONObject2);
        }
        catch (JSONException localJSONException)
        {
          for (;;)
          {
            Log.e("MxActivateService", "error when persist login infos", localJSONException);
          }
        }
      }
    }
    try
    {
      localJSONObject1.put("logins", localJSONArray);
      PrefUtils.saveString(paramContext, "pref_mx_account_info", localJSONObject1.toString());
      return;
    }
    catch (JSONException paramMap)
    {
      for (;;)
      {
        Log.e("MxActivateService", "error when persist mx account infos", paramMap);
      }
    }
  }
  
  private static Map<String, MxStatusInfo> readMxLoginInfo(Context paramContext)
  {
    Object localObject1 = PrefUtils.getString(paramContext, "pref_mx_account_info");
    paramContext = new HashMap();
    if (!TextUtils.isEmpty((CharSequence)localObject1)) {
      try
      {
        localObject1 = new JSONObject((String)localObject1).getJSONArray("logins");
        int i = 0;
        while (i < ((JSONArray)localObject1).length())
        {
          Object localObject2 = ((JSONArray)localObject1).getJSONObject(i);
          String str1 = ((JSONObject)localObject2).optString("mid", null);
          String str2 = ((JSONObject)localObject2).getString("sim_id");
          String str3 = ((JSONObject)localObject2).optString("st", null);
          String str4 = ((JSONObject)localObject2).optString("sec", null);
          boolean bool = ((JSONObject)localObject2).getBoolean("enabled");
          localObject2 = new MxStatusInfo();
          mid = str1;
          serviceToken = str3;
          security = str4;
          enabled = bool;
          simId = str2;
          paramContext.put(str2, localObject2);
          i += 1;
        }
        return paramContext;
      }
      catch (JSONException localJSONException)
      {
        Log.e("MxActivateService", "error when parse mx account info", localJSONException);
      }
    }
  }
  
  public static void setEnableAfterActivation(int paramInt, boolean paramBoolean)
  {
    synchronized (BUSY_FLAG_LOCK)
    {
      sEnableAfterActivation[paramInt] = paramBoolean;
      return;
    }
  }
  
  private static void setIsEnabling(int paramInt, boolean paramBoolean)
  {
    localObject1 = BUSY_FLAG_LOCK;
    if (paramBoolean) {}
    for (;;)
    {
      try
      {
        mIsEnabling |= 1 << paramInt;
        return;
      }
      finally {}
      mIsEnabling &= (1 << paramInt ^ 0xFFFFFFFF);
    }
  }
  
  private static void setLastFailureCode(Context paramContext, int paramInt)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    paramContext.putInt("pref_key_last_failure_code", paramInt);
    paramContext.commit();
  }
  
  private static void setMxEnabled(Context paramContext, int paramInt, boolean paramBoolean)
  {
    boolean bool = false;
    Object localObject2 = MSimUtils.getSimId(paramContext, paramInt);
    if (!TextUtils.isEmpty((CharSequence)localObject2)) {}
    synchronized (MX_STATUS_INFO_LOCK)
    {
      if (mMxStatusInfo == null) {
        return;
      }
      localObject2 = (MxStatusInfo)mMxStatusInfo.get(localObject2);
      if (localObject2 != null)
      {
        enabled = paramBoolean;
        persistMxStatusInfo(paramContext, mMxStatusInfo);
      }
      if (!isMxEnabled(paramContext, 0))
      {
        paramBoolean = bool;
        if (!isMxEnabled(paramContext, 1)) {}
      }
      else
      {
        paramBoolean = true;
      }
      writeStatusToSettings(paramContext, paramBoolean);
      return;
    }
  }
  
  private static void writeStatusToSettings(Context paramContext, boolean paramBoolean)
  {
    paramContext = paramContext.getContentResolver();
    if (paramBoolean) {}
    for (int i = 1;; i = 0)
    {
      Settings.Secure.putInt(paramContext, "cloud_messaging_on", i);
      return;
    }
  }
  
  /* Error */
  protected void onHandleIntent(Intent paramIntent)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 10
    //   3: iconst_1
    //   4: istore_2
    //   5: aload_1
    //   6: ifnonnull +4 -> 10
    //   9: return
    //   10: aload_1
    //   11: invokevirtual 621	android/content/Intent:getAction	()Ljava/lang/String;
    //   14: astore 8
    //   16: ldc -9
    //   18: aload 8
    //   20: invokevirtual 624	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   23: ifeq +651 -> 674
    //   26: aload_1
    //   27: ldc -75
    //   29: iconst_m1
    //   30: invokevirtual 627	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
    //   33: istore_3
    //   34: iload_3
    //   35: ifge +14 -> 49
    //   38: new 629	java/lang/IllegalStateException
    //   41: dup
    //   42: ldc_w 631
    //   45: invokespecial 632	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   48: athrow
    //   49: iload_3
    //   50: invokestatic 290	com/xiaomi/accountsdk/activate/ActivateStatusReceiver:getActivateInfo	(I)Landroid/os/Bundle;
    //   53: astore 11
    //   55: aload 11
    //   57: ldc_w 383
    //   60: invokevirtual 386	android/os/Bundle:getBoolean	(Ljava/lang/String;)Z
    //   63: istore 5
    //   65: aload 11
    //   67: ldc_w 292
    //   70: iconst_m1
    //   71: invokevirtual 296	android/os/Bundle:getInt	(Ljava/lang/String;I)I
    //   74: istore 4
    //   76: aload_1
    //   77: ldc -7
    //   79: iconst_0
    //   80: invokevirtual 635	android/content/Intent:getBooleanExtra	(Ljava/lang/String;Z)Z
    //   83: istore 7
    //   85: iload 5
    //   87: ifne +28 -> 115
    //   90: ldc 51
    //   92: ldc_w 637
    //   95: invokestatic 640	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   98: pop
    //   99: iload_3
    //   100: iconst_0
    //   101: invokestatic 241	com/xiaomi/mms/transaction/MxActivateService:setIsEnabling	(IZ)V
    //   104: iload 7
    //   106: ifeq -97 -> 9
    //   109: iload_3
    //   110: iconst_1
    //   111: invokestatic 642	com/xiaomi/mms/transaction/MxActivateService:setEnableAfterActivation	(IZ)V
    //   114: return
    //   115: iload 4
    //   117: iconst_3
    //   118: if_icmpeq +28 -> 146
    //   121: ldc 51
    //   123: ldc_w 644
    //   126: invokestatic 640	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   129: pop
    //   130: iload_3
    //   131: iconst_0
    //   132: invokestatic 241	com/xiaomi/mms/transaction/MxActivateService:setIsEnabling	(IZ)V
    //   135: iload 7
    //   137: ifeq -128 -> 9
    //   140: iload_3
    //   141: iconst_1
    //   142: invokestatic 642	com/xiaomi/mms/transaction/MxActivateService:setEnableAfterActivation	(IZ)V
    //   145: return
    //   146: getstatic 48	com/xiaomi/mms/transaction/MxActivateService:MX_STATUS_INFO_LOCK	Ljava/lang/Object;
    //   149: astore 8
    //   151: aload 8
    //   153: monitorenter
    //   154: getstatic 306	com/xiaomi/mms/transaction/MxActivateService:mMxStatusInfo	Ljava/util/Map;
    //   157: ifnonnull +10 -> 167
    //   160: aload_0
    //   161: invokestatic 410	com/xiaomi/mms/transaction/MxActivateService:readMxLoginInfo	(Landroid/content/Context;)Ljava/util/Map;
    //   164: putstatic 306	com/xiaomi/mms/transaction/MxActivateService:mMxStatusInfo	Ljava/util/Map;
    //   167: aload_0
    //   168: invokevirtual 648	com/xiaomi/mms/transaction/MxActivateService:getApplicationContext	()Landroid/content/Context;
    //   171: iload_3
    //   172: invokestatic 110	com/android/mms/util/MSimUtils:getSimId	(Landroid/content/Context;I)Ljava/lang/String;
    //   175: astore_1
    //   176: aload_1
    //   177: ifnull +146 -> 323
    //   180: getstatic 306	com/xiaomi/mms/transaction/MxActivateService:mMxStatusInfo	Ljava/util/Map;
    //   183: aload_1
    //   184: invokeinterface 393 2 0
    //   189: checkcast 6	com/xiaomi/mms/transaction/MxActivateService$MxStatusInfo
    //   192: astore_1
    //   193: aload_1
    //   194: ifnull +1300 -> 1494
    //   197: new 6	com/xiaomi/mms/transaction/MxActivateService$MxStatusInfo
    //   200: dup
    //   201: aload_1
    //   202: invokespecial 650	com/xiaomi/mms/transaction/MxActivateService$MxStatusInfo:<init>	(Lcom/xiaomi/mms/transaction/MxActivateService$MxStatusInfo;)V
    //   205: astore_1
    //   206: ldc 51
    //   208: ldc_w 652
    //   211: invokestatic 74	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   214: pop
    //   215: aload_1
    //   216: invokestatic 654	com/xiaomi/mms/transaction/MxActivateService:checkMxLoginInfo	(Lcom/xiaomi/mms/transaction/MxActivateService$MxStatusInfo;)V
    //   219: aload 8
    //   221: monitorexit
    //   222: aload_1
    //   223: ifnull +111 -> 334
    //   226: aload_1
    //   227: getfield 371	com/xiaomi/mms/transaction/MxActivateService$MxStatusInfo:enabled	Z
    //   230: ifeq +104 -> 334
    //   233: iconst_1
    //   234: istore 5
    //   236: iload 5
    //   238: istore 6
    //   240: iload 5
    //   242: ifne +41 -> 283
    //   245: iload 5
    //   247: istore 6
    //   249: getstatic 306	com/xiaomi/mms/transaction/MxActivateService:mMxStatusInfo	Ljava/util/Map;
    //   252: invokeinterface 412 1 0
    //   257: ifeq +26 -> 283
    //   260: ldc 51
    //   262: ldc_w 656
    //   265: invokestatic 74	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   268: pop
    //   269: aload_0
    //   270: invokestatic 196	android/preference/PreferenceManager:getDefaultSharedPreferences	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   273: ldc -58
    //   275: iconst_0
    //   276: invokeinterface 204 3 0
    //   281: istore 6
    //   283: aload_1
    //   284: ifnonnull +56 -> 340
    //   287: iload 7
    //   289: ifne +51 -> 340
    //   292: iload 6
    //   294: ifne +46 -> 340
    //   297: aload_0
    //   298: iload_3
    //   299: iload 7
    //   301: invokespecial 658	com/xiaomi/mms/transaction/MxActivateService:onEnableFailure	(IZ)V
    //   304: iload_2
    //   305: ifeq -296 -> 9
    //   308: ldc 51
    //   310: ldc_w 660
    //   313: invokestatic 74	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   316: pop
    //   317: aload_0
    //   318: iload_3
    //   319: invokespecial 662	com/xiaomi/mms/transaction/MxActivateService:closeChannel	(I)V
    //   322: return
    //   323: aconst_null
    //   324: astore_1
    //   325: goto -132 -> 193
    //   328: astore_1
    //   329: aload 8
    //   331: monitorexit
    //   332: aload_1
    //   333: athrow
    //   334: iconst_0
    //   335: istore 5
    //   337: goto -101 -> 236
    //   340: iload 7
    //   342: ifne +8 -> 350
    //   345: iload 6
    //   347: ifeq +270 -> 617
    //   350: iload 7
    //   352: ifeq +18 -> 370
    //   355: ldc 51
    //   357: ldc_w 664
    //   360: invokestatic 74	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   363: pop
    //   364: aload_0
    //   365: iload_3
    //   366: iconst_1
    //   367: invokestatic 245	com/xiaomi/mms/transaction/MxActivateService:setMxEnabled	(Landroid/content/Context;IZ)V
    //   370: aload_0
    //   371: iconst_1
    //   372: invokestatic 603	com/xiaomi/mms/transaction/MxActivateService:writeStatusToSettings	(Landroid/content/Context;Z)V
    //   375: aload_0
    //   376: iload_3
    //   377: invokespecial 666	com/xiaomi/mms/transaction/MxActivateService:onStartActivate	(I)V
    //   380: aload_0
    //   381: invokestatic 668	com/xiaomi/mms/transaction/MxActivateService:clearLastFailureCode	(Landroid/content/Context;)V
    //   384: aload_1
    //   385: ifnull +1098 -> 1483
    //   388: aload_1
    //   389: getfield 357	com/xiaomi/mms/transaction/MxActivateService$MxStatusInfo:mid	Ljava/lang/String;
    //   392: astore 9
    //   394: aload_1
    //   395: getfield 60	com/xiaomi/mms/transaction/MxActivateService$MxStatusInfo:serviceToken	Ljava/lang/String;
    //   398: astore 8
    //   400: aload_1
    //   401: getfield 77	com/xiaomi/mms/transaction/MxActivateService$MxStatusInfo:security	Ljava/lang/String;
    //   404: astore_1
    //   405: aload 11
    //   407: ldc -126
    //   409: invokevirtual 134	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   412: astore 12
    //   414: aload 11
    //   416: ldc_w 670
    //   419: invokevirtual 134	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   422: astore 11
    //   424: aload 12
    //   426: aload 9
    //   428: invokestatic 673	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   431: ifne +1039 -> 1470
    //   434: ldc 51
    //   436: new 325	java/lang/StringBuilder
    //   439: dup
    //   440: invokespecial 326	java/lang/StringBuilder:<init>	()V
    //   443: ldc_w 675
    //   446: invokevirtual 331	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   449: iload_3
    //   450: invokevirtual 334	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   453: invokevirtual 335	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   456: invokestatic 678	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   459: pop
    //   460: aconst_null
    //   461: astore 8
    //   463: aload 10
    //   465: astore_1
    //   466: aload 12
    //   468: invokestatic 66	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   471: ifne +43 -> 514
    //   474: aload 11
    //   476: invokestatic 66	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   479: ifne +35 -> 514
    //   482: aload_1
    //   483: invokestatic 66	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   486: ifne +28 -> 514
    //   489: aload 8
    //   491: invokestatic 66	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   494: ifne +20 -> 514
    //   497: aload_0
    //   498: iload_3
    //   499: aload 12
    //   501: aload_1
    //   502: aload 8
    //   504: aload 11
    //   506: invokespecial 680	com/xiaomi/mms/transaction/MxActivateService:onEnableSuccess	(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   509: iconst_0
    //   510: istore_2
    //   511: goto -207 -> 304
    //   514: aload 12
    //   516: invokestatic 66	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   519: ifne +78 -> 597
    //   522: aload 11
    //   524: invokestatic 66	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   527: ifne +70 -> 597
    //   530: new 166	android/content/Intent
    //   533: dup
    //   534: aload_0
    //   535: ldc 2
    //   537: invokespecial 683	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   540: astore_1
    //   541: aload_1
    //   542: ldc_w 685
    //   545: invokevirtual 688	android/content/Intent:setAction	(Ljava/lang/String;)Landroid/content/Intent;
    //   548: pop
    //   549: aload_1
    //   550: ldc -7
    //   552: iload 7
    //   554: invokevirtual 252	android/content/Intent:putExtra	(Ljava/lang/String;Z)Landroid/content/Intent;
    //   557: pop
    //   558: aload_1
    //   559: ldc_w 533
    //   562: aload 12
    //   564: invokevirtual 691	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   567: pop
    //   568: aload_1
    //   569: ldc_w 693
    //   572: aload 11
    //   574: invokevirtual 691	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   577: pop
    //   578: aload_1
    //   579: ldc -75
    //   581: iload_3
    //   582: invokevirtual 185	android/content/Intent:putExtra	(Ljava/lang/String;I)Landroid/content/Intent;
    //   585: pop
    //   586: aload_0
    //   587: aload_1
    //   588: invokevirtual 694	com/xiaomi/mms/transaction/MxActivateService:startService	(Landroid/content/Intent;)Landroid/content/ComponentName;
    //   591: pop
    //   592: iconst_1
    //   593: istore_2
    //   594: goto -83 -> 511
    //   597: ldc 51
    //   599: ldc_w 696
    //   602: invokestatic 139	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   605: pop
    //   606: aload_0
    //   607: iload_3
    //   608: iconst_2
    //   609: invokespecial 698	com/xiaomi/mms/transaction/MxActivateService:onSevereFailure	(II)V
    //   612: iconst_1
    //   613: istore_2
    //   614: goto -103 -> 511
    //   617: aload_0
    //   618: invokestatic 700	com/xiaomi/mms/transaction/MxActivateService:getLastFailureCode	(Landroid/content/Context;)I
    //   621: istore 4
    //   623: iload 4
    //   625: ifle +40 -> 665
    //   628: ldc 51
    //   630: new 325	java/lang/StringBuilder
    //   633: dup
    //   634: invokespecial 326	java/lang/StringBuilder:<init>	()V
    //   637: ldc_w 702
    //   640: invokevirtual 331	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   643: iload 4
    //   645: invokevirtual 334	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   648: invokevirtual 335	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   651: invokestatic 74	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   654: pop
    //   655: aload_0
    //   656: iload_3
    //   657: iload 4
    //   659: invokespecial 698	com/xiaomi/mms/transaction/MxActivateService:onSevereFailure	(II)V
    //   662: goto -358 -> 304
    //   665: aload_0
    //   666: iload_3
    //   667: iconst_1
    //   668: invokespecial 658	com/xiaomi/mms/transaction/MxActivateService:onEnableFailure	(IZ)V
    //   671: goto -367 -> 304
    //   674: ldc_w 685
    //   677: aload 8
    //   679: invokevirtual 624	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   682: ifeq +523 -> 1205
    //   685: aload_1
    //   686: ldc_w 533
    //   689: invokevirtual 705	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   692: astore 13
    //   694: aload_1
    //   695: ldc_w 693
    //   698: invokevirtual 705	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   701: astore 14
    //   703: aload_1
    //   704: ldc -75
    //   706: iconst_m1
    //   707: invokevirtual 627	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
    //   710: istore 4
    //   712: iload 4
    //   714: ifge +41 -> 755
    //   717: ldc 51
    //   719: new 325	java/lang/StringBuilder
    //   722: dup
    //   723: invokespecial 326	java/lang/StringBuilder:<init>	()V
    //   726: ldc_w 707
    //   729: invokevirtual 331	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   732: iload 4
    //   734: invokevirtual 334	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   737: invokevirtual 335	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   740: invokestatic 74	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   743: pop
    //   744: new 629	java/lang/IllegalStateException
    //   747: dup
    //   748: ldc_w 709
    //   751: invokespecial 632	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   754: athrow
    //   755: aload_0
    //   756: iload 4
    //   758: invokestatic 712	com/android/mms/util/MSimUtils:blockingGetSimId	(Landroid/content/Context;I)Ljava/lang/String;
    //   761: astore 11
    //   763: aload 11
    //   765: ifnonnull +45 -> 810
    //   768: ldc 51
    //   770: new 325	java/lang/StringBuilder
    //   773: dup
    //   774: invokespecial 326	java/lang/StringBuilder:<init>	()V
    //   777: ldc_w 714
    //   780: invokevirtual 331	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   783: iload 4
    //   785: invokevirtual 334	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   788: invokevirtual 335	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   791: invokestatic 640	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   794: pop
    //   795: aload_0
    //   796: iload 4
    //   798: iconst_0
    //   799: invokespecial 658	com/xiaomi/mms/transaction/MxActivateService:onEnableFailure	(IZ)V
    //   802: return
    //   803: astore_1
    //   804: aconst_null
    //   805: astore 11
    //   807: goto -44 -> 763
    //   810: aload_0
    //   811: invokestatic 116	com/xiaomi/accountsdk/activate/ActivateManager:get	(Landroid/content/Context;)Lcom/xiaomi/accountsdk/activate/ActivateManager;
    //   814: astore 15
    //   816: aconst_null
    //   817: astore 8
    //   819: aconst_null
    //   820: astore_1
    //   821: iconst_0
    //   822: istore_3
    //   823: invokestatic 720	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   826: invokevirtual 723	java/lang/Thread:isInterrupted	()Z
    //   829: ifne +638 -> 1467
    //   832: iload_3
    //   833: getstatic 35	com/xiaomi/mms/transaction/MxActivateService:INTS	[J
    //   836: arraylength
    //   837: if_icmpge +630 -> 1467
    //   840: ldc 51
    //   842: new 325	java/lang/StringBuilder
    //   845: dup
    //   846: invokespecial 326	java/lang/StringBuilder:<init>	()V
    //   849: ldc_w 725
    //   852: invokevirtual 331	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   855: iload_3
    //   856: invokevirtual 334	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   859: invokevirtual 335	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   862: invokestatic 74	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   865: pop
    //   866: aload 15
    //   868: iload 4
    //   870: ldc_w 727
    //   873: invokevirtual 731	com/xiaomi/accountsdk/activate/ActivateManager:getSimAuthToken	(ILjava/lang/String;)Lcom/xiaomi/accountsdk/activate/ActivateManager$ActivateManagerFuture;
    //   876: astore 12
    //   878: aload_1
    //   879: astore 9
    //   881: aload_1
    //   882: astore 10
    //   884: aload 12
    //   886: invokeinterface 126 1 0
    //   891: checkcast 128	android/os/Bundle
    //   894: astore 12
    //   896: aload_1
    //   897: astore 9
    //   899: aload_1
    //   900: astore 10
    //   902: aload 12
    //   904: ldc_w 733
    //   907: invokevirtual 134	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   910: astore_1
    //   911: aload_1
    //   912: astore 9
    //   914: aload_1
    //   915: astore 10
    //   917: aload 12
    //   919: ldc_w 735
    //   922: invokevirtual 134	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   925: astore 12
    //   927: aload 12
    //   929: astore 8
    //   931: iconst_0
    //   932: istore_2
    //   933: aload_1
    //   934: ifnull +155 -> 1089
    //   937: aload 8
    //   939: ifnull +150 -> 1089
    //   942: aload_1
    //   943: ifnull +245 -> 1188
    //   946: aload 8
    //   948: ifnull +240 -> 1188
    //   951: getstatic 48	com/xiaomi/mms/transaction/MxActivateService:MX_STATUS_INFO_LOCK	Ljava/lang/Object;
    //   954: astore 12
    //   956: aload 12
    //   958: monitorenter
    //   959: getstatic 306	com/xiaomi/mms/transaction/MxActivateService:mMxStatusInfo	Ljava/util/Map;
    //   962: aload 11
    //   964: invokeinterface 393 2 0
    //   969: checkcast 6	com/xiaomi/mms/transaction/MxActivateService$MxStatusInfo
    //   972: astore 10
    //   974: aload 10
    //   976: astore 9
    //   978: aload 10
    //   980: ifnonnull +25 -> 1005
    //   983: new 6	com/xiaomi/mms/transaction/MxActivateService$MxStatusInfo
    //   986: dup
    //   987: invokespecial 589	com/xiaomi/mms/transaction/MxActivateService$MxStatusInfo:<init>	()V
    //   990: astore 9
    //   992: getstatic 306	com/xiaomi/mms/transaction/MxActivateService:mMxStatusInfo	Ljava/util/Map;
    //   995: aload 11
    //   997: aload 9
    //   999: invokeinterface 736 3 0
    //   1004: pop
    //   1005: aload 9
    //   1007: aload 13
    //   1009: putfield 357	com/xiaomi/mms/transaction/MxActivateService$MxStatusInfo:mid	Ljava/lang/String;
    //   1012: aload 9
    //   1014: aload 11
    //   1016: putfield 82	com/xiaomi/mms/transaction/MxActivateService$MxStatusInfo:simId	Ljava/lang/String;
    //   1019: aload 9
    //   1021: aload_1
    //   1022: putfield 60	com/xiaomi/mms/transaction/MxActivateService$MxStatusInfo:serviceToken	Ljava/lang/String;
    //   1025: aload 9
    //   1027: aload 8
    //   1029: putfield 77	com/xiaomi/mms/transaction/MxActivateService$MxStatusInfo:security	Ljava/lang/String;
    //   1032: aload 9
    //   1034: iconst_1
    //   1035: putfield 371	com/xiaomi/mms/transaction/MxActivateService$MxStatusInfo:enabled	Z
    //   1038: aload_0
    //   1039: getstatic 306	com/xiaomi/mms/transaction/MxActivateService:mMxStatusInfo	Ljava/util/Map;
    //   1042: invokestatic 397	com/xiaomi/mms/transaction/MxActivateService:persistMxStatusInfo	(Landroid/content/Context;Ljava/util/Map;)V
    //   1045: aload 12
    //   1047: monitorexit
    //   1048: aload_0
    //   1049: iload 4
    //   1051: aload 13
    //   1053: aload_1
    //   1054: aload 8
    //   1056: aload 14
    //   1058: invokespecial 680	com/xiaomi/mms/transaction/MxActivateService:onEnableSuccess	(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1061: return
    //   1062: astore_1
    //   1063: iconst_1
    //   1064: istore_2
    //   1065: aload 9
    //   1067: astore_1
    //   1068: goto -135 -> 933
    //   1071: astore_1
    //   1072: ldc 51
    //   1074: ldc_w 738
    //   1077: invokestatic 139	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   1080: pop
    //   1081: iconst_0
    //   1082: istore_2
    //   1083: aload 10
    //   1085: astore_1
    //   1086: goto -153 -> 933
    //   1089: iload_2
    //   1090: ifeq +34 -> 1124
    //   1093: ldc 51
    //   1095: ldc_w 740
    //   1098: invokestatic 74	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   1101: pop
    //   1102: aload_0
    //   1103: invokestatic 745	miui/cloud/CloudManager:waitUntilNetworkConnected	(Landroid/content/Context;)V
    //   1106: iload_3
    //   1107: iconst_1
    //   1108: iadd
    //   1109: istore_3
    //   1110: goto -287 -> 823
    //   1113: astore 9
    //   1115: invokestatic 720	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   1118: invokevirtual 748	java/lang/Thread:interrupt	()V
    //   1121: goto -15 -> 1106
    //   1124: ldc 51
    //   1126: new 325	java/lang/StringBuilder
    //   1129: dup
    //   1130: invokespecial 326	java/lang/StringBuilder:<init>	()V
    //   1133: ldc_w 750
    //   1136: invokevirtual 331	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1139: getstatic 35	com/xiaomi/mms/transaction/MxActivateService:INTS	[J
    //   1142: iload_3
    //   1143: laload
    //   1144: invokevirtual 753	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1147: ldc_w 755
    //   1150: invokevirtual 331	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1153: invokevirtual 335	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1156: invokestatic 74	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   1159: pop
    //   1160: getstatic 35	com/xiaomi/mms/transaction/MxActivateService:INTS	[J
    //   1163: iload_3
    //   1164: laload
    //   1165: invokestatic 759	java/lang/Thread:sleep	(J)V
    //   1168: goto -62 -> 1106
    //   1171: astore 9
    //   1173: invokestatic 720	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   1176: invokevirtual 748	java/lang/Thread:interrupt	()V
    //   1179: goto -73 -> 1106
    //   1182: astore_1
    //   1183: aload 12
    //   1185: monitorexit
    //   1186: aload_1
    //   1187: athrow
    //   1188: ldc 51
    //   1190: ldc_w 761
    //   1193: invokestatic 139	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   1196: pop
    //   1197: aload_0
    //   1198: iload 4
    //   1200: iconst_0
    //   1201: invokespecial 658	com/xiaomi/mms/transaction/MxActivateService:onEnableFailure	(IZ)V
    //   1204: return
    //   1205: ldc -88
    //   1207: aload 8
    //   1209: invokevirtual 624	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1212: ifeq +36 -> 1248
    //   1215: aload_1
    //   1216: ldc -75
    //   1218: iconst_m1
    //   1219: invokevirtual 627	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
    //   1222: istore_2
    //   1223: iload_2
    //   1224: ifge +14 -> 1238
    //   1227: new 629	java/lang/IllegalStateException
    //   1230: dup
    //   1231: ldc_w 631
    //   1234: invokespecial 632	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   1237: athrow
    //   1238: aload_0
    //   1239: invokestatic 668	com/xiaomi/mms/transaction/MxActivateService:clearLastFailureCode	(Landroid/content/Context;)V
    //   1242: aload_0
    //   1243: iload_2
    //   1244: invokespecial 662	com/xiaomi/mms/transaction/MxActivateService:closeChannel	(I)V
    //   1247: return
    //   1248: ldc -2
    //   1250: aload 8
    //   1252: invokevirtual 624	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1255: ifeq +41 -> 1296
    //   1258: aload_1
    //   1259: ldc -75
    //   1261: iconst_m1
    //   1262: invokevirtual 627	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
    //   1265: istore_2
    //   1266: iload_2
    //   1267: ifge +14 -> 1281
    //   1270: new 629	java/lang/IllegalStateException
    //   1273: dup
    //   1274: ldc_w 631
    //   1277: invokespecial 632	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   1280: athrow
    //   1281: aload_0
    //   1282: invokestatic 668	com/xiaomi/mms/transaction/MxActivateService:clearLastFailureCode	(Landroid/content/Context;)V
    //   1285: aload_0
    //   1286: iload_2
    //   1287: invokespecial 662	com/xiaomi/mms/transaction/MxActivateService:closeChannel	(I)V
    //   1290: aload_0
    //   1291: iload_2
    //   1292: invokespecial 763	com/xiaomi/mms/transaction/MxActivateService:onDisable	(I)V
    //   1295: return
    //   1296: ldc_w 765
    //   1299: aload 8
    //   1301: invokevirtual 624	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1304: ifeq -1295 -> 9
    //   1307: aload_1
    //   1308: ldc_w 693
    //   1311: invokevirtual 705	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   1314: astore 8
    //   1316: aload_1
    //   1317: ldc_w 533
    //   1320: invokevirtual 705	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   1323: astore 9
    //   1325: aload_1
    //   1326: ldc -75
    //   1328: iconst_m1
    //   1329: invokevirtual 627	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
    //   1332: istore_2
    //   1333: iload_2
    //   1334: iconst_m1
    //   1335: if_icmpne +13 -> 1348
    //   1338: ldc 51
    //   1340: ldc_w 767
    //   1343: invokestatic 640	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   1346: pop
    //   1347: return
    //   1348: aload 8
    //   1350: invokestatic 66	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1353: ifne +11 -> 1364
    //   1356: aload 9
    //   1358: invokestatic 66	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1361: ifeq +13 -> 1374
    //   1364: ldc 51
    //   1366: ldc_w 769
    //   1369: invokestatic 640	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   1372: pop
    //   1373: return
    //   1374: aload_0
    //   1375: iload_2
    //   1376: invokestatic 232	com/xiaomi/mms/transaction/MxActivateService:isMxEnabled	(Landroid/content/Context;I)Z
    //   1379: ifeq +29 -> 1408
    //   1382: aload_0
    //   1383: invokestatic 100	com/xiaomi/mms/transaction/PushSession:getInstance	(Landroid/content/Context;)Lcom/xiaomi/mms/transaction/PushSession;
    //   1386: iload_2
    //   1387: invokevirtual 772	com/xiaomi/mms/transaction/PushSession:isConnected	(I)Z
    //   1390: ifeq +18 -> 1408
    //   1393: ldc 51
    //   1395: ldc_w 774
    //   1398: invokestatic 678	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   1401: pop
    //   1402: iload_2
    //   1403: iconst_0
    //   1404: invokestatic 241	com/xiaomi/mms/transaction/MxActivateService:setIsEnabling	(IZ)V
    //   1407: return
    //   1408: aload_0
    //   1409: iload_2
    //   1410: invokespecial 666	com/xiaomi/mms/transaction/MxActivateService:onStartActivate	(I)V
    //   1413: new 166	android/content/Intent
    //   1416: dup
    //   1417: aload_0
    //   1418: ldc 2
    //   1420: invokespecial 683	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   1423: astore_1
    //   1424: aload_1
    //   1425: ldc_w 685
    //   1428: invokevirtual 688	android/content/Intent:setAction	(Ljava/lang/String;)Landroid/content/Intent;
    //   1431: pop
    //   1432: aload_1
    //   1433: ldc_w 533
    //   1436: aload 9
    //   1438: invokevirtual 691	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   1441: pop
    //   1442: aload_1
    //   1443: ldc_w 693
    //   1446: aload 8
    //   1448: invokevirtual 691	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   1451: pop
    //   1452: aload_1
    //   1453: ldc -75
    //   1455: iload_2
    //   1456: invokevirtual 185	android/content/Intent:putExtra	(Ljava/lang/String;I)Landroid/content/Intent;
    //   1459: pop
    //   1460: aload_0
    //   1461: aload_1
    //   1462: invokevirtual 694	com/xiaomi/mms/transaction/MxActivateService:startService	(Landroid/content/Intent;)Landroid/content/ComponentName;
    //   1465: pop
    //   1466: return
    //   1467: goto -525 -> 942
    //   1470: aload_1
    //   1471: astore 9
    //   1473: aload 8
    //   1475: astore_1
    //   1476: aload 9
    //   1478: astore 8
    //   1480: goto -1014 -> 466
    //   1483: aconst_null
    //   1484: astore_1
    //   1485: aconst_null
    //   1486: astore 8
    //   1488: aconst_null
    //   1489: astore 9
    //   1491: goto -1086 -> 405
    //   1494: aconst_null
    //   1495: astore_1
    //   1496: goto -1277 -> 219
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1499	0	this	MxActivateService
    //   0	1499	1	paramIntent	Intent
    //   4	1452	2	i	int
    //   33	1131	3	j	int
    //   74	1125	4	k	int
    //   63	273	5	bool1	boolean
    //   238	108	6	bool2	boolean
    //   83	470	7	bool3	boolean
    //   14	1473	8	localObject1	Object
    //   392	674	9	localObject2	Object
    //   1113	1	9	localInterruptedException1	InterruptedException
    //   1171	1	9	localInterruptedException2	InterruptedException
    //   1323	167	9	localObject3	Object
    //   1	1083	10	localObject4	Object
    //   53	962	11	localObject5	Object
    //   692	360	13	str1	String
    //   701	356	14	str2	String
    //   814	53	15	localActivateManager	ActivateManager
    // Exception table:
    //   from	to	target	type
    //   154	167	328	finally
    //   167	176	328	finally
    //   180	193	328	finally
    //   197	219	328	finally
    //   219	222	328	finally
    //   329	332	328	finally
    //   755	763	803	miui/telephony/exception/IllegalDeviceException
    //   884	896	1062	java/io/IOException
    //   902	911	1062	java/io/IOException
    //   917	927	1062	java/io/IOException
    //   884	896	1071	java/lang/Exception
    //   902	911	1071	java/lang/Exception
    //   917	927	1071	java/lang/Exception
    //   1093	1106	1113	java/lang/InterruptedException
    //   1124	1168	1171	java/lang/InterruptedException
    //   959	974	1182	finally
    //   983	1005	1182	finally
    //   1005	1048	1182	finally
    //   1183	1186	1182	finally
  }
  
  public static class MxStatusInfo
  {
    public boolean enabled;
    public String mid;
    public String security;
    public String serviceToken;
    public String simId;
    
    MxStatusInfo() {}
    
    MxStatusInfo(MxStatusInfo paramMxStatusInfo)
    {
      mid = mid;
      simId = simId;
      serviceToken = serviceToken;
      security = security;
      enabled = enabled;
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.transaction.MxActivateService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */