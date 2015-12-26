package com.xiaomi.mms.transaction;

import android.net.Uri;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import java.io.IOException;

public abstract interface MiCloudMxMmsTransactionHandler$MiCloudMediaManagerCallback
{
  public abstract ExtendedAuthToken getMediaManagerToken(String paramString)
    throws IOException;
  
  public abstract boolean onDownloadMediaSuccess(Uri paramUri, String paramString, byte[] paramArrayOfByte);
  
  public abstract void onMediaManagerTokenInvalid(String paramString);
  
  public abstract boolean onUploadMediaSuccess(Uri paramUri, long paramLong1, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, long paramLong2, long paramLong3, String paramString6);
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.transaction.MiCloudMxMmsTransactionHandler.MiCloudMediaManagerCallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */