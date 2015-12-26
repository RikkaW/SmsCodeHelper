package com.meizu.common.widget;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.provider.BaseColumns;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.Groups;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class MzContactsContract
{
  public static final String ALLOW_CALLLOGS_PARAM_KEY = "allow_calllogs";
  public static final Uri AUTHORITY_URI_NOTIFY = Uri.parse("content://com.android.contacts.notify");
  public static final String HAS_MORE_KEY = "has_more";
  private static Pattern SPLIT_PATTERN = Pattern.compile("([\\w-\\.]+)@((?:[\\w]+\\.)+)([a-zA-Z]{2,4})|[\\w]+");
  public static final String START_PARAM_KEY = "start";
  public static final String USE_WEIGHT_ORDER = "use_weight_order";
  
  private static void MzSplit(String paramString, List<String> paramList, List<Integer> paramList1)
  {
    paramString = SPLIT_PATTERN.matcher(paramString);
    while (paramString.find())
    {
      paramList.add(paramString.group());
      paramList1.add(Integer.valueOf(paramString.start()));
    }
  }
  
  public static String snippetize(String paramString1, String paramString2, String paramString3, char paramChar1, char paramChar2, String paramString4, int paramInt)
  {
    if (paramString3 != null) {}
    for (Object localObject1 = paramString3.toLowerCase(); (TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString3)) || (TextUtils.isEmpty(paramString2)) || (!paramString1.toLowerCase().contains((CharSequence)localObject1)); localObject1 = null) {
      return null;
    }
    if (paramString2 != null) {}
    ArrayList localArrayList;
    for (paramString2 = paramString2.toLowerCase();; paramString2 = "")
    {
      paramString3 = new ArrayList();
      localArrayList = new ArrayList();
      MzSplit(paramString2.trim(), paramString3, localArrayList);
      paramString2 = paramString3.iterator();
      do
      {
        if (!paramString2.hasNext()) {
          break;
        }
      } while (!((String)paramString2.next()).startsWith((String)localObject1));
      return null;
    }
    Object localObject2 = paramString1.split("\n");
    int i1 = localObject2.length;
    int j = 0;
    while (j < i1)
    {
      paramString1 = localObject2[j];
      if (paramString1.toLowerCase().contains((CharSequence)localObject1))
      {
        paramString2 = new ArrayList();
        paramString3 = new ArrayList();
        MzSplit(paramString1, paramString2, paramString3);
        localArrayList = new ArrayList();
        int i = -1;
        int k = -1;
        int m = 0;
        String str;
        if (m < paramString2.size())
        {
          str = (String)paramString2.get(m);
          int n;
          if (str.toLowerCase().startsWith((String)localObject1))
          {
            localArrayList.add(paramChar1 + str + paramChar2);
            n = i;
            if (i == -1)
            {
              n = Math.max(0, m - (int)Math.floor(Math.abs(paramInt) / 2.0D));
              k = Math.min(paramString2.size(), Math.abs(paramInt) + n);
            }
          }
          for (;;)
          {
            m += 1;
            i = n;
            break;
            localArrayList.add(str);
            n = i;
          }
        }
        if (i > -1)
        {
          localObject1 = new StringBuilder();
          paramInt = i;
          if (i > 0)
          {
            ((StringBuilder)localObject1).append(paramString4);
            paramInt = i;
          }
          while (paramInt < k)
          {
            localObject2 = (String)localArrayList.get(paramInt);
            str = (String)paramString2.get(paramInt);
            ((StringBuilder)localObject1).append((String)localObject2);
            if (paramInt < k - 1)
            {
              i = ((Integer)paramString3.get(paramInt)).intValue();
              ((StringBuilder)localObject1).append(paramString1.substring(str.length() + i, ((Integer)paramString3.get(paramInt + 1)).intValue()));
            }
            paramInt += 1;
          }
          if (k < paramString2.size()) {
            ((StringBuilder)localObject1).append(paramString4);
          }
          return ((StringBuilder)localObject1).toString();
        }
      }
      j += 1;
    }
    return null;
  }
  
  public static final class MzAccounts
  {
    public static final Account DEVICES_ONLY_ACCOUNT = new Account("DeviceOnly", "DeviceOnly");
    private static final String DEVICES_ONLY_ACCOUNT_NAME = "DeviceOnly";
    private static final String DEVICES_ONLY_ACCOUNT_TYPE = "DeviceOnly";
    public static final String FLYME_ACCOUNT_TYPE = "com.meizu.account";
    public static final String SINA_ACCOUNT_TYPE = "com.meizu.sns.sina";
    public static final Account VENDER_ACCOUNT = new Account("account.vender", "account.vender");
    private static final String VENDER_ACCOUNT_NAME = "account.vender";
    public static final String VENDER_ACCOUNT_TYPE = "account.vender";
    
    public static Account[] addDeviceOnlyAccount(Account[] paramArrayOfAccount)
    {
      int j = 0;
      Account[] arrayOfAccount = new Account[paramArrayOfAccount.length + 1];
      arrayOfAccount[0] = DEVICES_ONLY_ACCOUNT;
      int i = 1;
      int k = paramArrayOfAccount.length;
      while (j < k)
      {
        arrayOfAccount[i] = paramArrayOfAccount[j];
        j += 1;
        i += 1;
      }
      return arrayOfAccount;
    }
    
    public static boolean isFlymeAccount(Account paramAccount)
    {
      return (paramAccount != null) && (TextUtils.equals(type, "com.meizu.account"));
    }
  }
  
  public static final class MzCommonDataKinds
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
  
  public static abstract interface MzContactColumns
  {
    public static final String ADDRESS = "address";
    public static final String DISTANCE = "distance";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String RECORD_TYPE = "record_type";
    public static final String SNS_TYPE = "sns_type";
  }
  
  public static abstract interface MzContactOptionsColumns
  {
    public static final String ORGANIZATION_NOTE = "organization_note";
  }
  
  public static class MzContacts
    implements MzContactsContract.MzContactColumns
  {
    public static final Uri CONTENT_EX_GROUP_URI = Uri.withAppendedPath(CONTENT_EX_URI, "group");
    public static final Uri CONTENT_EX_URI = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, "ex");
    
    public static final class MzPhoto
    {
      public static final String FORCE_SET_PRIMARY = "data12";
    }
  }
  
  public static final class MzData
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
  
  public static final class MzDirectory
  {
    public static final Uri CONTENT_NOTIFY_URI = Uri.withAppendedPath(MzContactsContract.AUTHORITY_URI_NOTIFY, "directories");
    public static final String IS_VISIBLE = "is_visible";
    public static final long NET_CONTACT = 2L;
  }
  
  public static final class MzDisplayPhoto
  {
    public static final String STORE_ORIGINAL = "store_original";
  }
  
  public static final class MzGroups
    implements BaseColumns, MzContactsContract.MzGroupsColumns
  {
    public static final Uri CONTENT_ACCOUNT_URI = Uri.withAppendedPath(ContactsContract.Groups.CONTENT_URI, "account");
    public static final Uri CONTENT_SUMMARY_FILTER_URI = Uri.withAppendedPath(ContactsContract.Groups.CONTENT_SUMMARY_URI, "filter");
    public static final String GROUP_SPLIT_MARK_EXTRA = ",";
    public static final String GROUP_SPLIT_MARK_SLASH = "/";
    public static final String GROUP_SPLIT_MARK_VCARD = ";";
    public static final String GROUP_SPLIT_MARK_XML = "|";
    
    public static String getGroupTitle(ContentResolver paramContentResolver, long paramLong)
    {
      Object localObject = null;
      if (paramLong <= 0L)
      {
        localObject = null;
        return (String)localObject;
      }
      for (;;)
      {
        try
        {
          localCursor = paramContentResolver.query(ContactsContract.Groups.CONTENT_URI, new String[] { "title" }, "_id=?", new String[] { String.valueOf(paramLong) }, null);
          if (localCursor == null) {}
        }
        finally
        {
          try
          {
            if (!localCursor.moveToFirst()) {
              break label104;
            }
            paramContentResolver = localCursor.getString(0);
            localObject = paramContentResolver;
            if (localCursor == null) {
              break;
            }
            localCursor.close();
            return paramContentResolver;
          }
          finally
          {
            Cursor localCursor;
            localObject = localCursor;
          }
          paramContentResolver = finally;
          if (localObject != null) {
            ((Cursor)localObject).close();
          }
        }
        label104:
        paramContentResolver = null;
      }
    }
  }
  
  public static abstract interface MzGroupsColumns
  {
    public static final String ACCOUNT_ORDER = "account_order";
    public static final String REJECTED = "rejected";
    public static final String SUMMARY_DATA_COUNT = "summary_data_count";
    public static final String VIEW_ORDER = "view_order";
  }
  
  public static final class MzIntents
  {
    public static final String EXTRA_MULTIPLE_PICK_DATAS = "com.android.contacts.extra.MULTIPLE_PICK_DATAS";
    public static final String EXTRA_PICK_DATA = "com.android.contacts.extra.PICK_DATA";
    public static final String EXTRA_REQUEST_DATA_IDS = "com.android.contacts.extra.EXTRA_REQUEST_DATA_IDS";
    
    public static final class MzInsert
    {
      public static final String SOCIAL_PROFILE = "social_profile";
      public static final String SOCIAL_PROFILE_TYPE = "social_profile_type";
    }
    
    public static final class MzUI
    {
      public static final String BROWSE_ALL_CONTACTS_ACTION = "com.android.contacts.action.BROWSE_ALL_CONTACTS";
      public static final String REQUESTED_INFO_TYPE_KEY = "com.android.contacts.extra.REQUESTED_INFO_TYPE";
      public static final int REQUESTED_INFO_TYPE_NONE = -1;
      public static final int REQUESTED_INFO_TYPE_TEXT = 1;
      public static final int REQUESTED_INFO_TYPE_VCARD = 0;
      public static final String REQUESTED_ORIENTATION = "com.android.contacts.extra.REQUESTED_ORIENTATION";
      public static final String SHOULD_INCLUDE_CONTACT_KEY = "com.android.contacts.extra.SHOULD_INCLUDE_CONTACT";
      public static final String SHOULD_INCLUDE_PROFILE_KEY = "com.android.contacts.extra.SHOULD_INCLUDE_PROFILE";
      public static final String SUB_TITLE_EXTRA_KEY = "com.android.contacts.extra.SUB_TITLE_EXTRA";
    }
  }
  
  public static final class MzNetContacts
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
  
  public static final class MzPhoneLookup
    implements MzContactsContract.MzContactColumns, MzContactsContract.MzContactOptionsColumns
  {
    public static final String CALL_ALLOWED_CONTACT_IDS = "call_allowed_contact_ids";
    public static final String CALL_REJECTED_CONTACT_IDS = "call_rejected_contact_ids";
    public static final String CALL_REJECTED_EXTRAS = "call_rejected_extras";
    public static final String CALL_REJECTED_TYPE = "call_rejected_type";
    
    public static Uri buildRejectedExtrasUri(Uri paramUri)
    {
      return paramUri.buildUpon().appendQueryParameter("call_rejected_extras", "true").build();
    }
    
    /* Error */
    public static boolean isPhoneNumberInContact(android.content.Context paramContext, String paramString)
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore 4
      //   3: aload_0
      //   4: ifnull +10 -> 14
      //   7: aload_1
      //   8: invokestatic 59	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   11: ifeq +5 -> 16
      //   14: iconst_0
      //   15: ireturn
      //   16: aload_0
      //   17: invokevirtual 65	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
      //   20: getstatic 71	android/provider/ContactsContract$PhoneLookup:CONTENT_FILTER_URI	Landroid/net/Uri;
      //   23: aload_1
      //   24: invokestatic 75	android/net/Uri:withAppendedPath	(Landroid/net/Uri;Ljava/lang/String;)Landroid/net/Uri;
      //   27: iconst_1
      //   28: anewarray 77	java/lang/String
      //   31: dup
      //   32: iconst_0
      //   33: ldc 79
      //   35: aastore
      //   36: aconst_null
      //   37: aconst_null
      //   38: aconst_null
      //   39: invokevirtual 85	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
      //   42: astore_0
      //   43: aload_0
      //   44: ifnull +33 -> 77
      //   47: aload_0
      //   48: invokeinterface 91 1 0
      //   53: istore_2
      //   54: iload_2
      //   55: ifle +17 -> 72
      //   58: iconst_1
      //   59: istore_3
      //   60: aload_0
      //   61: ifnull +9 -> 70
      //   64: aload_0
      //   65: invokeinterface 94 1 0
      //   70: iload_3
      //   71: ireturn
      //   72: iconst_0
      //   73: istore_3
      //   74: goto -14 -> 60
      //   77: aload_0
      //   78: ifnull -64 -> 14
      //   81: aload_0
      //   82: invokeinterface 94 1 0
      //   87: iconst_0
      //   88: ireturn
      //   89: astore_0
      //   90: aconst_null
      //   91: astore_0
      //   92: aload_0
      //   93: ifnull -79 -> 14
      //   96: aload_0
      //   97: invokeinterface 94 1 0
      //   102: iconst_0
      //   103: ireturn
      //   104: astore_0
      //   105: aload 4
      //   107: astore_1
      //   108: aload_1
      //   109: ifnull +9 -> 118
      //   112: aload_1
      //   113: invokeinterface 94 1 0
      //   118: aload_0
      //   119: athrow
      //   120: astore 4
      //   122: aload_0
      //   123: astore_1
      //   124: aload 4
      //   126: astore_0
      //   127: goto -19 -> 108
      //   130: astore_1
      //   131: goto -39 -> 92
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	134	0	paramContext	android.content.Context
      //   0	134	1	paramString	String
      //   53	2	2	i	int
      //   59	15	3	bool	boolean
      //   1	105	4	localObject1	Object
      //   120	5	4	localObject2	Object
      // Exception table:
      //   from	to	target	type
      //   16	43	89	java/lang/Exception
      //   16	43	104	finally
      //   47	54	120	finally
      //   47	54	130	java/lang/Exception
    }
  }
  
  public static final class MzQuickContact
  {
    public static final String ACTION_MZ_QUICK_CONTACT = "com.android.contacts.action.MZ_QUICK_CONTACT";
  }
  
  public static abstract interface MzRawContactColumns
  {
    public static final String IS_SUPER_PHONE = "is_super_phone";
    public static final String IS_SUPER_PHOTO = "is_super_photo";
    public static final String RAW_PHONE_NUMBER = "raw_phone_number";
    public static final String RAW_PHOTO_FILE_ID = "raw_photo_file_id";
    public static final String RAW_PHOTO_ID = "raw_photo_id";
    public static final String RAW_PHOTO_THUMBNAIL_URI = "raw_photo_thumb_uri";
    public static final String RAW_PHOTO_URI = "raw_photo_uri";
    public static final String SNS_TYPE = "sns_type";
  }
  
  public static class MzSearchSnippetColumns
  {
    public static final String SEARCH_WEIGHT = "search_weight";
    public static final int SEARCH_WEIGHT_CONTENT = 10002;
    public static final int SEARCH_WEIGHT_NAME = 10000;
    public static final int SEARCH_WEIGHT_TOKENS = 10001;
    public static final int SEARCH_WEIGHT_UNKNOW = 10003;
  }
  
  public static final class MzSettings
    implements MzContactsContract.MzSettingsColumns
  {
    public static final Uri CONTENT_NOTIFY_URI = Uri.withAppendedPath(MzContactsContract.AUTHORITY_URI_NOTIFY, "settings");
  }
  
  public static abstract interface MzSettingsColumns
  {
    public static final String DIRECTORY_VISIBLE = "directory_visible";
    public static final String IS_DEFAULT = "is_default";
    public static final String SYNC_WITH_DEFAULT_ACCOUNT = "sync_with_default_account";
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.MzContactsContract
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */