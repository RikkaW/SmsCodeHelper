package com.meizu.common.widget;

import android.net.Uri;
import android.net.Uri.Builder;

public final class MzContactsContract$MzPhoneLookup
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

/* Location:
 * Qualified Name:     com.meizu.common.widget.MzContactsContract.MzPhoneLookup
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */