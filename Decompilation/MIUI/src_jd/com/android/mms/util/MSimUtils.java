package com.android.mms.util;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.SystemProperties;
import android.provider.MiuiSettings.VirtualSim;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.MmsApp;
import com.android.mms.data.Contact;
import com.android.mms.transaction.SmsReceiver;
import com.xiaomi.mms.utils.B2cMessageUtils;
import miui.telephony.CloudTelephonyManager;
import miui.telephony.SmsManager;
import miui.telephony.SubscriptionInfo;
import miui.telephony.SubscriptionManager;
import miui.telephony.SubscriptionManager.OnSubscriptionsChangedListener;
import miui.telephony.TelephonyManager;
import miui.telephony.exception.IllegalDeviceException;
import miui.util.FeatureParser;

public class MSimUtils
{
  public static final long NONE_SIM_ID = SubscriptionManager.INVALID_SUBSCRIPTION_ID;
  public static final String SLOT_ID = SubscriptionManager.SLOT_KEY;
  public static final int SLOT_ID_ALL = SubscriptionManager.DEFAULT_SLOT_ID;
  public static final int SLOT_ID_INVALID = SubscriptionManager.INVALID_SLOT_ID;
  
  public static Uri addMessageToUri(ContentResolver paramContentResolver, Uri paramUri, String paramString1, String paramString2, String paramString3, Long paramLong, boolean paramBoolean1, boolean paramBoolean2, long paramLong1, long paramLong2)
  {
    ContentValues localContentValues = new ContentValues(7);
    localContentValues.put("address", paramString1);
    if (paramLong != null) {
      localContentValues.put("date", paramLong);
    }
    if (paramBoolean1) {}
    for (paramString1 = Integer.valueOf(1);; paramString1 = Integer.valueOf(0))
    {
      localContentValues.put("read", paramString1);
      localContentValues.put("subject", paramString3);
      localContentValues.put("body", paramString2);
      if (paramBoolean2) {
        localContentValues.put("status", Integer.valueOf(32));
      }
      if (paramLong1 != -1L) {
        localContentValues.put("thread_id", Long.valueOf(paramLong1));
      }
      if (paramLong2 != -1L) {
        localContentValues.put("sim_id", Long.valueOf(paramLong2));
      }
      return paramContentResolver.insert(paramUri, localContentValues);
    }
  }
  
  public static Uri addMiMessageToUri(ContentResolver paramContentResolver, Uri paramUri, String paramString1, String paramString2, String paramString3, Long paramLong, boolean paramBoolean1, boolean paramBoolean2, long paramLong1, long paramLong2, String paramString4)
  {
    ContentValues localContentValues = new ContentValues(10);
    localContentValues.put("address", paramString1);
    if (paramLong != null) {
      localContentValues.put("date", paramLong);
    }
    if (paramBoolean1)
    {
      paramLong = Integer.valueOf(1);
      localContentValues.put("read", paramLong);
      localContentValues.put("subject", paramString3);
      localContentValues.put("body", paramString2);
      if (paramBoolean2) {
        localContentValues.put("status", Integer.valueOf(32));
      }
      if (paramLong1 != -1L) {
        localContentValues.put("thread_id", Long.valueOf(paramLong1));
      }
      localContentValues.put("mx_status", Integer.valueOf(1));
      if (paramLong2 != -1L) {
        localContentValues.put("sim_id", Long.valueOf(paramLong2));
      }
      if (TextUtils.isEmpty(paramString4)) {
        break label174;
      }
      localContentValues.put("b2c_numbers", paramString4);
    }
    for (;;)
    {
      return paramContentResolver.insert(paramUri, localContentValues);
      paramLong = Integer.valueOf(0);
      break;
      label174:
      if (B2cMessageUtils.isB2cNumber(Contact.get(paramString1))) {
        localContentValues.put("b2c_numbers", paramString1);
      }
    }
  }
  
  public static String blockingGetSimId(Context paramContext, int paramInt)
    throws IllegalDeviceException
  {
    return CloudTelephonyManager.blockingGetSimId(paramContext, paramInt);
  }
  
  public static String createApnSelectionBySlotId(String paramString, int paramInt)
  {
    String str = paramString;
    if (isMSim())
    {
      str = paramString;
      if (!isAndroidM())
      {
        str = paramString;
        if (isMSimSlotIdValid(paramInt))
        {
          str = "slot_id=" + String.valueOf(paramInt);
          if (!TextUtils.isEmpty(paramString)) {
            break label57;
          }
        }
      }
    }
    return str;
    label57:
    return paramString + " AND " + str;
  }
  
  public static String createKeyWithSimId(long paramLong, String paramString)
  {
    String str = paramString;
    if (isMSim()) {
      str = Long.toString(paramLong) + "_" + paramString;
    }
    return str;
  }
  
  public static int getInsertedSimCount()
  {
    int j;
    if (isMSim())
    {
      j = SubscriptionManager.getDefault().getSubscriptionInfoCount();
      i = j;
      if (j < 0) {
        Log.e("MSimUtils", "getInsertedSimCount count < 0");
      }
    }
    for (int i = j;; i = TelephonyManager.getDefault().getIccCardCount())
    {
      j = i;
      if (i > 0)
      {
        j = i;
        if (hasVirtualSim()) {
          j = i - 1;
        }
      }
      return j;
    }
  }
  
  public static int getInsertedSlotId()
  {
    int i = 0;
    SubscriptionInfo localSubscriptionInfo;
    if (isMSim())
    {
      int j = getMultiSimCount();
      i = 0;
      if (i >= j) {
        break label93;
      }
      localSubscriptionInfo = SubscriptionManager.getDefault().getSubscriptionInfoForSlot(i);
      if ((localSubscriptionInfo == null) || (!localSubscriptionInfo.isActivated()) || (isVirtualSimbySlotId(i))) {}
    }
    do
    {
      return i;
      i += 1;
      break;
      localSubscriptionInfo = SubscriptionManager.getDefault().getSubscriptionInfoForSlot(0);
    } while (((localSubscriptionInfo != null) || (!TelephonyManager.getDefault().hasIccCard())) && ((localSubscriptionInfo != null) && (localSubscriptionInfo.isActivated()) && (!isVirtualSimbySlotId(0))));
    label93:
    return SLOT_ID_INVALID;
  }
  
  public static int getMultiSimCount()
  {
    int i = TelephonyManager.getDefault().getPhoneCount();
    if (i < 0) {
      Log.e("MSimUtils", "getMultiSimCount count < 0");
    }
    return i;
  }
  
  public static String getNetworkOperator(int paramInt)
  {
    String str = "";
    if (!isMSim()) {
      str = SystemProperties.get("gsm.apn.sim.operator.numeric", "");
    }
    return str;
  }
  
  public static int getPhoneType(int paramInt)
  {
    return TelephonyManager.getDefault().getPhoneTypeForSlot(paramInt);
  }
  
  public static int getPreferredDataSlotId()
  {
    int i = 0;
    if (isMSim()) {
      i = SubscriptionManager.getDefault().getDefaultDataSlotId();
    }
    return i;
  }
  
  public static int getPreferredSmsSlotId()
  {
    int j = 0;
    int i = j;
    if (isMSim())
    {
      i = j;
      if (isMSimInserted()) {
        i = SubscriptionManager.getDefault().getDefaultVoiceSlotId();
      }
    }
    return i;
  }
  
  public static String getSimDisplayName(Context paramContext, int paramInt)
  {
    paramContext = null;
    SubscriptionInfo localSubscriptionInfo = SubscriptionManager.getDefault().getSubscriptionInfoForSlot(paramInt);
    if (localSubscriptionInfo != null) {
      paramContext = localSubscriptionInfo.getDisplayName().toString();
    }
    while (!isMSim()) {
      return paramContext;
    }
    Log.e("MSimUtils", "getSimDisplayName is empty");
    return null;
  }
  
  public static int getSimIconResId(int paramInt)
  {
    int j = 0;
    int i = j;
    if (isMSim())
    {
      if (paramInt != 0) {
        break label19;
      }
      i = 2130837972;
    }
    label19:
    do
    {
      return i;
      i = j;
    } while (paramInt != 1);
    return 2130837973;
  }
  
  public static String getSimId(Context paramContext, int paramInt)
  {
    return CloudTelephonyManager.getSimId(paramContext, paramInt);
  }
  
  public static long getSimIdBySlotId(int paramInt)
  {
    long l = 0L;
    SubscriptionInfo localSubscriptionInfo = SubscriptionManager.getDefault().getSubscriptionInfoForSlot(paramInt);
    if (localSubscriptionInfo != null) {
      l = localSubscriptionInfo.getSubscriptionId();
    }
    while (!isMSim()) {
      return l;
    }
    return NONE_SIM_ID;
  }
  
  public static long getSimIdFromIntent(Intent paramIntent)
  {
    long l1 = 0L;
    if (paramIntent == null) {
      Log.e("MSimUtils", "getSimIdFromIntent intent is null");
    }
    long l2;
    do
    {
      return l1;
      l2 = paramIntent.getLongExtra("sim_id", 0L);
      l1 = l2;
    } while (l2 >= 0L);
    Log.e("MSimUtils", "getSimIdFromIntent simId < DEFAULT_SIM_ID");
    return l2;
  }
  
  public static String getSimNumber(Context paramContext, int paramInt)
  {
    paramContext = null;
    SubscriptionInfo localSubscriptionInfo = SubscriptionManager.getDefault().getSubscriptionInfoForSlot(paramInt);
    if (localSubscriptionInfo != null) {
      paramContext = localSubscriptionInfo.getDisplayNumber();
    }
    while (!isMSim()) {
      return paramContext;
    }
    Log.e("MSimUtils", "getSimNumber is empty");
    return null;
  }
  
  public static int getSlotIdBySimInfoId(long paramLong)
  {
    int i = 0;
    SubscriptionInfo localSubscriptionInfo;
    if (isMSim())
    {
      localSubscriptionInfo = SubscriptionManager.getDefault().getSubscriptionInfoForSubscription((int)paramLong);
      if ((localSubscriptionInfo == null) || (!localSubscriptionInfo.isActivated())) {
        i = SLOT_ID_INVALID;
      }
    }
    else
    {
      return i;
    }
    return localSubscriptionInfo.getSlotId();
  }
  
  public static int getSlotIdFromBundle(Bundle paramBundle)
  {
    int i = 0;
    if (isMSim())
    {
      int j = paramBundle.getInt(SLOT_ID, 0);
      i = j;
      if (j < 0)
      {
        Log.e("MSimUtils", "getSlotIdFromBundle slotId < 0");
        i = j;
      }
    }
    return i;
  }
  
  public static int getSlotIdFromIntent(Intent paramIntent)
  {
    int i = 0;
    if (paramIntent == null) {
      Log.e("MSimUtils", "getSlotIdFromIntent intent is null");
    }
    int j;
    do
    {
      do
      {
        return i;
      } while (!isMSim());
      j = paramIntent.getIntExtra(SLOT_ID, 0);
      i = j;
    } while (j >= 0);
    Log.e("MSimUtils", "getSlotIdFromIntent slotId < 0");
    return j;
  }
  
  public static SmsManager getSmsManager(int paramInt)
  {
    SmsManager localSmsManager = null;
    if (isMSim()) {
      if (isMSimSlotIdValid(paramInt)) {
        localSmsManager = SmsManager.getDefault(paramInt);
      }
    }
    for (;;)
    {
      if (localSmsManager == null) {
        Log.e("MSimUtils", "getSmsManager is null");
      }
      return localSmsManager;
      if (isSingleSimSlotIdValid(paramInt)) {
        localSmsManager = SmsManager.getDefault();
      }
    }
  }
  
  public static int getVirtualSimSlotId()
  {
    if ((MiuiSettings.VirtualSim.isVirtualSimEnabled(MmsApp.getApp())) && (MiuiSettings.VirtualSim.getVirtualSimType(MmsApp.getApp()) == 1)) {
      return MiuiSettings.VirtualSim.getVirtualSimSlotId(MmsApp.getApp());
    }
    return SLOT_ID_INVALID;
  }
  
  public static boolean hasVirtualSim()
  {
    return (MiuiSettings.VirtualSim.isVirtualSimEnabled(MmsApp.getApp())) && (MiuiSettings.VirtualSim.getVirtualSimType(MmsApp.getApp()) == 1);
  }
  
  public static boolean isAndroid50()
  {
    return Build.VERSION.SDK_INT >= 21;
  }
  
  public static boolean isAndroidM()
  {
    return Build.VERSION.SDK_INT >= 23;
  }
  
  public static boolean isCallStateIdle()
  {
    int i = 0;
    while (i < TelephonyManager.getDefault().getPhoneCount())
    {
      if (TelephonyManager.getDefault().getCallStateForSlot(i) != 0) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  public static boolean isLeadcore()
  {
    return "leadcore".equals(FeatureParser.getString("vendor"));
  }
  
  public static boolean isMSim()
  {
    return getMultiSimCount() > 1;
  }
  
  public static boolean isMSimInserted()
  {
    return getInsertedSimCount() > 1;
  }
  
  public static boolean isMSimSlotIdValid(int paramInt)
  {
    return (paramInt >= 0) && (paramInt < getMultiSimCount());
  }
  
  public static boolean isMtk()
  {
    return "mediatek".equals(FeatureParser.getString("vendor"));
  }
  
  public static boolean isSimInserted()
  {
    if (isSimInserted(0)) {}
    while (isSimInserted(1)) {
      return true;
    }
    return false;
  }
  
  public static boolean isSimInserted(int paramInt)
  {
    boolean bool = false;
    SubscriptionInfo localSubscriptionInfo;
    if (isMSimSlotIdValid(paramInt))
    {
      localSubscriptionInfo = SubscriptionManager.getDefault().getSubscriptionInfoForSlot(paramInt);
      if ((localSubscriptionInfo == null) && (!isMSim())) {
        bool = TelephonyManager.getDefault().hasIccCard();
      }
    }
    else
    {
      if ((!bool) || (isVirtualSimbySlotId(paramInt))) {
        break label68;
      }
      return true;
    }
    if ((localSubscriptionInfo != null) && (localSubscriptionInfo.isActivated())) {}
    for (bool = true;; bool = false) {
      break;
    }
    label68:
    return false;
  }
  
  public static boolean isSingleSimSlotIdValid(int paramInt)
  {
    return paramInt == 0;
  }
  
  public static boolean isVirtualSimbySlotId(int paramInt)
  {
    return (paramInt != SLOT_ID_INVALID) && (getVirtualSimSlotId() == paramInt);
  }
  
  public static boolean moveMessageToFolder(Context paramContext, Uri paramUri, int paramInt1, int paramInt2, Long paramLong, Integer paramInteger, int paramInt3)
  {
    return moveMessageToFolder(paramContext, paramUri, paramInt1, paramInt2, paramLong, paramInteger, paramInt3, null);
  }
  
  public static boolean moveMessageToFolder(Context paramContext, Uri paramUri, int paramInt1, int paramInt2, Long paramLong1, Integer paramInteger, int paramInt3, Long paramLong2)
  {
    if (paramUri == null) {
      return false;
    }
    int k = 0;
    int m = 0;
    int i = m;
    int j = k;
    ContentValues localContentValues;
    switch (paramInt1)
    {
    default: 
      return false;
    case 2: 
    case 4: 
      i = 1;
      j = k;
    case 1: 
    case 3: 
      localContentValues = new ContentValues();
      localContentValues.put("type", Integer.valueOf(paramInt1));
      if (j != 0) {
        localContentValues.put("read", Integer.valueOf(0));
      }
      break;
    }
    for (;;)
    {
      if (paramLong1 != null) {
        localContentValues.put("out_time", paramLong1);
      }
      if (paramInteger != null) {
        localContentValues.put("status", paramInteger);
      }
      localContentValues.put("error_code", Integer.valueOf(paramInt2));
      localContentValues.put("mx_status", Integer.valueOf(paramInt3));
      if (paramLong2 != null) {
        localContentValues.put("mx_id", paramLong2);
      }
      if (1 != SqliteWrapper.update(paramContext, paramContext.getContentResolver(), paramUri, localContentValues, null, null)) {
        break label222;
      }
      return true;
      j = 1;
      i = m;
      break;
      if (i != 0) {
        localContentValues.put("read", Integer.valueOf(1));
      }
    }
    label222:
    return false;
  }
  
  public static void registerChangeListener(Context paramContext, SubscriptionManager.OnSubscriptionsChangedListener paramOnSubscriptionsChangedListener)
  {
    if (isMSim()) {
      SubscriptionManager.getDefault().addOnSubscriptionsChangedListener(paramOnSubscriptionsChangedListener);
    }
  }
  
  public static void sendQueuedMessage(Context paramContext, int paramInt)
  {
    if (paramInt == SLOT_ID_ALL)
    {
      int i = getMultiSimCount();
      paramInt = 0;
      while (paramInt < i)
      {
        sendQueuedMessageInt(paramContext, paramInt, true);
        paramInt += 1;
      }
    }
    sendQueuedMessageInt(paramContext, paramInt, true);
  }
  
  public static void sendQueuedMessageInt(Context paramContext, int paramInt, boolean paramBoolean)
  {
    Intent localIntent = new Intent("com.android.mms.transaction.SEND_MESSAGE", null, paramContext, SmsReceiver.class);
    localIntent.putExtra(SLOT_ID, paramInt);
    localIntent.putExtra("show_toast_when_offline", paramBoolean);
    Log.d("MSimUtils", "send queued message with broadcast with slotId " + paramInt);
    paramContext.sendBroadcast(localIntent);
  }
  
  public static void sendQueuedMessageNoToast(Context paramContext, int paramInt)
  {
    if (paramInt == SLOT_ID_ALL)
    {
      int i = getMultiSimCount();
      paramInt = 0;
      while (paramInt < i)
      {
        sendQueuedMessageInt(paramContext, paramInt, false);
        paramInt += 1;
      }
    }
    sendQueuedMessageInt(paramContext, paramInt, false);
  }
  
  public static void startSmscAddressActivity(Context paramContext, int paramInt)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.setClassName("com.android.phone", "com.android.phone.settings.SmscAddressSettingActivity");
    localIntent.putExtra(SLOT_ID, paramInt);
    paramContext.startActivity(localIntent);
  }
  
  public static void unregisterChangeListener(Context paramContext, SubscriptionManager.OnSubscriptionsChangedListener paramOnSubscriptionsChangedListener)
  {
    if (isMSim()) {
      SubscriptionManager.getDefault().removeOnSubscriptionsChangedListener(paramOnSubscriptionsChangedListener);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.util.MSimUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */