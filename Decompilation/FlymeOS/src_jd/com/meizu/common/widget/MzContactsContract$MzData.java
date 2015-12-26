package com.meizu.common.widget;

import android.net.Uri;
import android.net.Uri.Builder;
import android.provider.ContactsContract.Data;
import android.text.TextUtils;

public final class MzContactsContract$MzData
{
  public static final Uri CONTENT_FILTER_URI = Uri.withAppendedPath(ContactsContract.Data.CONTENT_URI, "filter");
  
  public static Uri buildUriWithRequestMimetypes(Uri paramUri, String[] paramArrayOfString)
  {
    if ((paramArrayOfString == null) || (paramArrayOfString.length < 1)) {}
    StringBuilder localStringBuilder;
    do
    {
      return paramUri;
      localStringBuilder = new StringBuilder();
      int j = paramArrayOfString.length;
      int i = 0;
      if (i < j)
      {
        String str = paramArrayOfString[i];
        if (localStringBuilder.length() > 0) {
          localStringBuilder.append(';');
        }
        if (TextUtils.equals("vnd.android.cursor.item/phone_v2", str)) {
          localStringBuilder.append("phone");
        }
        for (;;)
        {
          i += 1;
          break;
          if (TextUtils.equals("vnd.android.cursor.item/email_v2", str)) {
            localStringBuilder.append("email");
          }
        }
      }
    } while (localStringBuilder.length() <= 0);
    return paramUri.buildUpon().appendQueryParameter("request_mimes", localStringBuilder.toString()).build();
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.MzContactsContract.MzData
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */