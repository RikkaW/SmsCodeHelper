package com.meizu.common.widget;

import android.net.Uri;
import android.provider.ContactsContract.Contacts;

public class MzContactsContract$MzContacts
  implements MzContactsContract.MzContactColumns
{
  public static final Uri CONTENT_EX_GROUP_URI = Uri.withAppendedPath(CONTENT_EX_URI, "group");
  public static final Uri CONTENT_EX_URI = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, "ex");
  
  public static final class MzPhoto
  {
    public static final String FORCE_SET_PRIMARY = "data12";
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.MzContactsContract.MzContacts
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */