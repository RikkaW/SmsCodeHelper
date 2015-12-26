package com.android.mms.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.net.Uri.Builder;
import android.provider.Telephony.Mms;
import android.text.TextUtils;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.MiuiPduPersister;
import miui.telephony.PhoneNumberUtils;

public class AddressUtils
{
  public static String cutPhoneNumberTail(String paramString)
  {
    return PhoneNumberUtils.maskPhoneNumber(paramString, 1);
  }
  
  public static String getFrom(Context paramContext, Uri paramUri)
  {
    paramUri = paramUri.getLastPathSegment();
    Object localObject = Telephony.Mms.CONTENT_URI.buildUpon();
    ((Uri.Builder)localObject).appendPath(paramUri).appendPath("addr");
    paramUri = SqliteWrapper.query(paramContext, paramContext.getContentResolver(), ((Uri.Builder)localObject).build(), new String[] { "address", "charset" }, "type=137", null, null);
    if (paramUri != null)
    {
      try
      {
        if (paramUri.moveToFirst())
        {
          localObject = paramUri.getString(0);
          if (!TextUtils.isEmpty((CharSequence)localObject))
          {
            paramContext = MiuiPduPersister.getBytes((String)localObject);
            paramContext = new EncodedStringValue(paramUri.getInt(1), paramContext).getString();
            return paramContext;
          }
        }
      }
      finally
      {
        paramUri.close();
      }
      paramUri.close();
    }
    return paramContext.getString(2131361890);
  }
  
  public static String getTo(Context paramContext, Uri paramUri)
  {
    paramUri = paramUri.getLastPathSegment();
    Uri.Builder localBuilder = Telephony.Mms.CONTENT_URI.buildUpon();
    localBuilder.appendPath(paramUri).appendPath("addr");
    paramContext = SqliteWrapper.query(paramContext, paramContext.getContentResolver(), localBuilder.build(), new String[] { "address", "charset" }, "type=151", null, null);
    if (paramContext != null)
    {
      try
      {
        if (paramContext.moveToFirst())
        {
          paramUri = paramContext.getString(0);
          if (!TextUtils.isEmpty(paramUri))
          {
            paramUri = MiuiPduPersister.getBytes(paramUri);
            paramUri = new EncodedStringValue(paramContext.getInt(1), paramUri).getString();
            return paramUri;
          }
        }
      }
      finally
      {
        paramContext.close();
      }
      paramContext.close();
    }
    return "";
  }
  
  public static boolean isFetionNumber(String paramString)
  {
    return paramString.startsWith("12520");
  }
}

/* Location:
 * Qualified Name:     com.android.mms.util.AddressUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */