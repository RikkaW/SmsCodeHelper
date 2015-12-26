package com.android.mms.transaction;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SqliteWrapper;
import android.net.NetworkUtils;
import android.provider.Telephony.Carriers;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.util.MSimUtils;
import com.xiaomi.mms.utils.logger.MyLog;
import miui.telephony.TelephonyManager;

public class TransactionSettings
{
  private static final String[] APN_PROJECTION = { "type", "mmsc", "mmsproxy", "mmsport" };
  private String mProxyAddress;
  private int mProxyPort = -1;
  private String mServiceCenter;
  
  public TransactionSettings(Context paramContext, String paramString, int paramInt)
  {
    Object localObject1 = MSimUtils.getNetworkOperator(paramInt);
    Object localObject2;
    if (TextUtils.isEmpty((CharSequence)localObject1))
    {
      Log.v("aaaaaa", "TransactionSettings getNetworkOperator oper is null");
      localObject1 = TelephonyManager.getDefault().getSimOperatorForSlot(paramInt);
      Log.v("aaaaaa", "TransactionSettings getSimOperator oper is " + (String)localObject1);
      localObject2 = null;
      if (TextUtils.isEmpty((CharSequence)localObject1)) {
        break label287;
      }
      localObject2 = "numeric='" + (String)localObject1 + "'";
      localObject1 = localObject2;
      if (!TextUtils.isEmpty(paramString)) {
        localObject1 = (String)localObject2 + " AND " + "apn" + "='" + paramString.trim() + "'";
      }
      label159:
      paramString = null;
      if (paramInt != 0) {
        break label330;
      }
      localObject2 = MSimUtils.createApnSelectionBySlotId((String)localObject1, paramInt);
      paramString = SqliteWrapper.query(paramContext, paramContext.getContentResolver(), Telephony.Carriers.CONTENT_URI, APN_PROJECTION, (String)localObject2, null, null);
      label192:
      if (Log.isLoggable("Mms:transaction", 2))
      {
        localObject1 = new StringBuilder().append("TransactionSettings looking for apn: ").append((String)localObject2).append(" returned: ");
        if (paramString != null) {
          break label369;
        }
        paramContext = "null cursor";
        label232:
        MyLog.v("TransactionSettings", paramContext);
      }
      if (paramString != null) {
        break label397;
      }
      MyLog.e("TransactionSettings", "Apn is not found in Database!");
    }
    label287:
    label330:
    label369:
    label397:
    label603:
    do
    {
      return;
      Log.v("aaaaaa", "TransactionSettings getNetworkOperator oper is " + (String)localObject1);
      break;
      localObject1 = localObject2;
      if (TextUtils.isEmpty(paramString)) {
        break label159;
      }
      localObject1 = "apn='" + paramString.trim() + "'";
      break label159;
      localObject2 = localObject1;
      if (1 != paramInt) {
        break label192;
      }
      localObject2 = MSimUtils.createApnSelectionBySlotId((String)localObject1, paramInt);
      paramString = SqliteWrapper.query(paramContext, paramContext.getContentResolver(), Telephony.Carriers.CONTENT_URI, APN_PROJECTION, (String)localObject2, null, null);
      break label192;
      paramContext = paramString.getCount() + " hits";
      break label232;
      int i;
      for (paramInt = 0;; paramInt = i) {
        try
        {
          for (;;)
          {
            if ((!paramString.moveToNext()) || (!TextUtils.isEmpty(mServiceCenter))) {
              break label603;
            }
            if (isValidApnType(paramString.getString(0), "mms"))
            {
              i = 1;
              localObject1 = paramString.getString(1);
              paramContext = (Context)localObject1;
              if (localObject1 == null) {
                paramContext = "";
              }
              localObject2 = paramString.getString(2);
              localObject1 = localObject2;
              if (localObject2 == null) {
                localObject1 = "";
              }
              mServiceCenter = NetworkUtils.trimV4AddrZeros(paramContext.trim());
              mProxyAddress = NetworkUtils.trimV4AddrZeros(((String)localObject1).trim());
              paramInt = i;
              if (isProxySet())
              {
                paramContext = paramString.getString(3);
                paramInt = i;
                if (paramContext != null)
                {
                  try
                  {
                    mProxyPort = Integer.parseInt(paramContext);
                    paramInt = i;
                  }
                  catch (NumberFormatException localNumberFormatException)
                  {
                    if (!TextUtils.isEmpty(paramContext)) {
                      break;
                    }
                  }
                  MyLog.w("TransactionSettings", "mms port not set!");
                  paramInt = i;
                }
              }
            }
          }
          MyLog.e("TransactionSettings", "Bad port number format: " + paramContext, localNumberFormatException);
        }
        finally
        {
          paramString.close();
        }
      }
      paramString.close();
    } while ((paramInt == 0) || (!TextUtils.isEmpty(mServiceCenter)));
    MyLog.e("TransactionSettings", "Invalid APN setting: MMSC is empty");
  }
  
  public TransactionSettings(String paramString1, String paramString2, int paramInt)
  {
    if (paramString1 != null) {}
    for (paramString1 = paramString1.trim();; paramString1 = null)
    {
      mServiceCenter = paramString1;
      mProxyAddress = paramString2;
      mProxyPort = paramInt;
      return;
    }
  }
  
  private static boolean isValidApnType(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1)) {
      return true;
    }
    paramString1 = paramString1.split(",");
    int j = paramString1.length;
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label57;
      }
      Object localObject = paramString1[i];
      if ((((String)localObject).equals(paramString2)) || (((String)localObject).equals("*"))) {
        break;
      }
      i += 1;
    }
    label57:
    return false;
  }
  
  public String getMmscUrl()
  {
    return mServiceCenter;
  }
  
  public String getProxyAddress()
  {
    return mProxyAddress;
  }
  
  public int getProxyPort()
  {
    return mProxyPort;
  }
  
  public boolean isProxySet()
  {
    return (mProxyAddress != null) && (mProxyAddress.trim().length() != 0);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.TransactionSettings
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */