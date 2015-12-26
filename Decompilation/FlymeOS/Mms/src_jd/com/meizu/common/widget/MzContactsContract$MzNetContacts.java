package com.meizu.common.widget;

import android.content.ContentUris;
import android.net.Uri;

public final class MzContactsContract$MzNetContacts
{
  public static final String AUTHORITY = "com.meizu.netcontactservice.directory";
  public static final String ERROR_CODE_KEY = "error_code";
  public static final int ERROR_CODE_NETWORK_UNAVAILABLE = 1;
  public static final int ERROR_CODE_NO_ADDRESS = 2;
  public static final int ERROR_CODE_NUMBER_INVALIDATE = 3;
  public static final int ERROR_CODE_SUCCESS = 0;
  public static final int ERROR_CODE_UNKNOWN = 4;
  public static final String LINK_DISPLAY_NAME_AND_LABLE = "link_display_name_and_lable";
  public static final String NET_CONTACT_ACCOUNT_TYPE = "com.meizu.netcontactservice";
  public static final String NET_CONTACT_DIRECTORY_TYPE = "NetContact";
  public static final Uri PHONE_LOOKUP_URI = Uri.withAppendedPath(Uri.parse("content://com.meizu.netcontactservice.directory"), "phone_lookup");
  public static final String USE_YELLOW_PAGE_CONTACTS = "use_yellow_page_contacts";
  public static final long YELLOW_PAGE_MIN_ID = 9223372035781033983L;
  
  public static boolean isYPContact(long paramLong)
  {
    return paramLong >= 9223372035781033983L;
  }
  
  public static boolean isYPContact(Uri paramUri)
  {
    try
    {
      boolean bool = isYPContact(ContentUris.parseId(paramUri));
      return bool;
    }
    catch (Exception paramUri)
    {
      paramUri.printStackTrace();
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.MzContactsContract.MzNetContacts
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */