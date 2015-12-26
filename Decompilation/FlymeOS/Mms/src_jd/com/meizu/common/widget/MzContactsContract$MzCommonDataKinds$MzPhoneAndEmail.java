package com.meizu.common.widget;

import android.net.Uri;
import android.provider.ContactsContract.Data;

public final class MzContactsContract$MzCommonDataKinds$MzPhoneAndEmail
{
  public static final Uri CONTENT_FILTER_URI = Uri.withAppendedPath(CONTENT_URI, "filter");
  public static final Uri CONTENT_GROUP_URI = Uri.withAppendedPath(CONTENT_URI, "group");
  public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/phone_email";
  public static final String CONTENT_TYPE = "vnd.android.cursor.dir/phone_email";
  public static final Uri CONTENT_URI = Uri.withAppendedPath(ContactsContract.Data.CONTENT_URI, "phones_emails");
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.MzContactsContract.MzCommonDataKinds.MzPhoneAndEmail
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */