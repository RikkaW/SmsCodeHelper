package com.meizu.common.widget;

import android.net.Uri;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Data;

public final class MzContactsContract$MzCommonDataKinds
{
  public static final class MzEmail
  {
    public static final Uri CONTENT_GROUP_URI = Uri.withAppendedPath(ContactsContract.CommonDataKinds.Email.CONTENT_URI, "group");
    public static final String USE_CUSTOM_ORDER = "use_custom_order";
  }
  
  public static final class MzEvent
  {
    public static final int TYPE_LUNAR_BIRTHDAY = 4;
    
    public static int getTypeResource(Integer paramInteger)
    {
      return ContactsContract.CommonDataKinds.Event.getTypeResource(paramInteger);
    }
  }
  
  public static final class MzGroupMembership
  {
    public static final String GROUP_TITLE = "group_title";
  }
  
  public static final class MzOrganization
  {
    public static final String IS_ORGANIZATION_NOTE = "data12";
  }
  
  public static final class MzPhone
  {
    public static final Uri CONTENT_GROUP_URI = Uri.withAppendedPath(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, "group");
    public static final String CONVERT_LETTERS = "convert_letters";
  }
  
  public static final class MzPhoneAndEmail
  {
    public static final Uri CONTENT_FILTER_URI = Uri.withAppendedPath(CONTENT_URI, "filter");
    public static final Uri CONTENT_GROUP_URI = Uri.withAppendedPath(CONTENT_URI, "group");
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/phone_email";
    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/phone_email";
    public static final Uri CONTENT_URI = Uri.withAppendedPath(ContactsContract.Data.CONTENT_URI, "phones_emails");
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.MzContactsContract.MzCommonDataKinds
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */