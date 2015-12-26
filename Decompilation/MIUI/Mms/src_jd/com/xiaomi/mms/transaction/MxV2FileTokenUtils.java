package com.xiaomi.mms.transaction;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.accountsdk.activate.ActivateManager;
import com.xiaomi.accountsdk.activate.ActivateManager.ActivateManagerFuture;
import com.xiaomi.mms.utils.MxConfigCompat;
import com.xiaomi.mms.utils.PrefUtils;
import com.xiaomi.mms.utils.logger.MyLog;

public class MxV2FileTokenUtils
{
  public static ExtendedAuthToken getFileTokenbysimIndex(Context paramContext, int paramInt)
  {
    Object localObject1 = ExtendedAuthToken.parse(PrefUtils.getString(paramContext, "pref_mx2_file_token" + paramInt));
    if (localObject1 != null) {
      return (ExtendedAuthToken)localObject1;
    }
    localObject1 = PushSession.getInstance(paramContext).getMyMid(paramInt);
    localObject1 = ActivateManager.get(paramContext).getSimAuthToken(paramInt, MxConfigCompat.getMxV2Sid(paramContext, (String)localObject1));
    try
    {
      Object localObject2 = (Bundle)((ActivateManager.ActivateManagerFuture)localObject1).getResult();
      localObject1 = ((Bundle)localObject2).getString("user_token");
      localObject2 = ((Bundle)localObject2).getString("user_security");
      if ((!TextUtils.isEmpty((CharSequence)localObject1)) && (!TextUtils.isEmpty((CharSequence)localObject2)))
      {
        localObject1 = ExtendedAuthToken.build((String)localObject1, (String)localObject2);
        PrefUtils.saveString(paramContext, "pref_mx2_file_token" + paramInt, ((ExtendedAuthToken)localObject1).toPlain());
        return (ExtendedAuthToken)localObject1;
      }
    }
    catch (Exception paramContext)
    {
      MyLog.e("failed to get file upload token");
    }
    return null;
  }
  
  public static ExtendedAuthToken getOtherIDCFileTokenbysimIndex(Context paramContext, int paramInt)
  {
    Object localObject1 = ExtendedAuthToken.parse(PrefUtils.getString(paramContext, "pref_mx2_file_token" + paramInt + "_idc"));
    if (localObject1 != null) {
      return (ExtendedAuthToken)localObject1;
    }
    localObject1 = PushSession.getInstance(paramContext).getMyMid(paramInt);
    localObject1 = ActivateManager.get(paramContext).getSimAuthToken(paramInt, MxConfigCompat.getOtherIDCMxV2Sid(paramContext, (String)localObject1));
    try
    {
      Object localObject2 = (Bundle)((ActivateManager.ActivateManagerFuture)localObject1).getResult();
      localObject1 = ((Bundle)localObject2).getString("user_token");
      localObject2 = ((Bundle)localObject2).getString("user_security");
      if ((!TextUtils.isEmpty((CharSequence)localObject1)) && (!TextUtils.isEmpty((CharSequence)localObject2)))
      {
        localObject1 = ExtendedAuthToken.build((String)localObject1, (String)localObject2);
        PrefUtils.saveString(paramContext, "pref_mx2_file_token" + paramInt + "_idc", ((ExtendedAuthToken)localObject1).toPlain());
        return (ExtendedAuthToken)localObject1;
      }
    }
    catch (Exception paramContext)
    {
      MyLog.e("failed to get other idc file auth token");
    }
    return null;
  }
  
  private static void invalidFileToken(Context paramContext, int paramInt)
  {
    if (paramInt == -1)
    {
      MyLog.v("failed to get sim index when invalid file token  simIndex == -1");
      return;
    }
    PrefUtils.remove(paramContext, "pref_mx2_file_token" + paramInt);
  }
  
  public static void invalidFileToken(Context paramContext, String paramString)
  {
    invalidFileToken(paramContext, PushSession.getInstance(paramContext).getSimIdByMid(paramString));
  }
  
  private static void invalidOtherIDCFileToken(Context paramContext, int paramInt)
  {
    if (paramInt == -1)
    {
      MyLog.v("failed to get sim index when invalid file token  simIndex == -1");
      return;
    }
    PrefUtils.remove(paramContext, "pref_mx2_file_token" + paramInt + "_idc");
  }
  
  public static void invalidOtherIDCFileToken(Context paramContext, String paramString)
  {
    invalidOtherIDCFileToken(paramContext, PushSession.getInstance(paramContext).getSimIdByMid(paramString));
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.transaction.MxV2FileTokenUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */