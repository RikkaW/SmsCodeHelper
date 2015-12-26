package com.xiaomi.mms.transaction;

import android.content.Context;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.micloudsdk.micloudrichmedia.MiCloudRichMediaManager;

public abstract interface MiCloudRichMediaManagerFactory
{
  public abstract MiCloudRichMediaManager getMiCloudRichMediaManager(Context paramContext, String paramString1, ExtendedAuthToken paramExtendedAuthToken, String paramString2);
  
  public abstract MiCloudRichMediaManager getOtherIDCMiCloudRichMediaManager(Context paramContext, String paramString1, ExtendedAuthToken paramExtendedAuthToken, String paramString2);
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.transaction.MiCloudRichMediaManagerFactory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */