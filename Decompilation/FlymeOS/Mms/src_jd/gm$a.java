import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.Presence;
import android.provider.ContactsContract.Profile;
import android.telephony.MzTelephony.MmsExt;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

class gm$a
{
  static CharBuffer a = CharBuffer.allocate(5);
  private static final Uri d = ContactsContract.Data.CONTENT_URI;
  private static final Uri e = Uri.withAppendedPath(Uri.parse("content://com.meizu.netcontactservice.directory"), "data").buildUpon().appendQueryParameter("request_net_data", "true").appendQueryParameter("wait_net_data", "false").build();
  private static final String[] f = { "_id", "data1", "data3", "display_name", "contact_id", "contact_presence", "contact_status", "data4", "send_to_voicemail", "lookup" };
  private static final String[] g = { "_id", "display_name" };
  private static final Uri h = ContactsContract.Data.CONTENT_URI;
  private static final String[] i = { "_id", "data4", "contact_presence", "contact_id", "display_name", "send_to_voicemail", "lookup" };
  private static final Uri j = ContactsContract.Data.CONTENT_URI;
  private static final String[] k = { "_id", "display_name", "contact_presence", "contact_id", "display_name", "lookup" };
  private final gm.a.a b = new gm.a.a();
  private final gm.a.a c = new gm.a.a();
  private final Context l;
  private final HashMap<String, ArrayList<gm>> m = new HashMap();
  
  private gm$a(Context paramContext)
  {
    l = paramContext;
  }
  
  private int a(int paramInt)
  {
    if (paramInt != 0) {
      return ContactsContract.Presence.getPresenceIconResourceId(paramInt);
    }
    return 0;
  }
  
  private gm a(String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    String str = paramString;
    if (TextUtils.isEmpty(paramString)) {
      str = "";
    }
    gm localgm = b(str, paramBoolean1);
    str = null;
    for (;;)
    {
      if (paramBoolean2) {}
      try
      {
        paramBoolean1 = gm.a(localgm);
        if (paramBoolean1)
        {
          try
          {
            localgm.wait();
          }
          catch (InterruptedException paramString) {}
        }
        else
        {
          paramString = str;
          if (gm.b(localgm))
          {
            paramString = str;
            if (!gm.a(localgm))
            {
              gm.a(localgm, false);
              paramString = new go(this, localgm);
              gm.b(localgm, true);
            }
          }
          if (paramString != null)
          {
            if (paramBoolean2) {
              paramString.run();
            }
          }
          else {
            return localgm;
          }
        }
      }
      finally {}
    }
    a(paramString);
    return localgm;
  }
  
  private String a(String paramString, CharBuffer paramCharBuffer)
  {
    paramCharBuffer.clear();
    paramCharBuffer.mark();
    int i1 = paramString.length();
    int n = 0;
    int i2;
    do
    {
      int i3;
      char c1;
      do
      {
        i3 = i1 - 1;
        i2 = n;
        if (i3 < 0) {
          break;
        }
        c1 = paramString.charAt(i3);
        i1 = i3;
      } while (!Character.isDigit(c1));
      paramCharBuffer.put(c1);
      i2 = n + 1;
      n = i2;
      i1 = i3;
    } while (i2 != 5);
    paramCharBuffer.reset();
    if (i2 > 0) {
      paramString = paramCharBuffer.toString();
    }
    return paramString;
  }
  
  /* Error */
  private void a(gm paramgm)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +4 -> 5
    //   4: return
    //   5: aload_0
    //   6: aload_1
    //   7: invokespecial 223	gm$a:b	(Lgm;)Lgm;
    //   10: astore_2
    //   11: aload_1
    //   12: monitorenter
    //   13: aload_0
    //   14: aload_1
    //   15: aload_2
    //   16: invokespecial 226	gm$a:a	(Lgm;Lgm;)Z
    //   19: ifeq +276 -> 295
    //   22: ldc -28
    //   24: iconst_2
    //   25: invokestatic 234	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   28: ifeq +28 -> 56
    //   31: new 236	java/lang/StringBuilder
    //   34: dup
    //   35: invokespecial 237	java/lang/StringBuilder:<init>	()V
    //   38: ldc -17
    //   40: invokevirtual 243	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   43: aload_2
    //   44: invokestatic 246	gm:i	(Lgm;)Ljava/lang/String;
    //   47: invokevirtual 243	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: invokevirtual 247	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   53: invokestatic 250	gm:b	(Ljava/lang/String;)V
    //   56: aload_1
    //   57: aload_2
    //   58: invokestatic 252	gm:c	(Lgm;)Ljava/lang/String;
    //   61: invokestatic 255	gm:a	(Lgm;Ljava/lang/String;)Ljava/lang/String;
    //   64: pop
    //   65: aload_1
    //   66: aload_2
    //   67: invokestatic 257	gm:j	(Lgm;)Ljava/lang/String;
    //   70: invokestatic 259	gm:b	(Lgm;Ljava/lang/String;)Ljava/lang/String;
    //   73: pop
    //   74: aload_1
    //   75: aload_2
    //   76: invokestatic 262	gm:f	(Lgm;)J
    //   79: invokestatic 265	gm:a	(Lgm;J)J
    //   82: pop2
    //   83: aload_1
    //   84: aload_2
    //   85: invokestatic 268	gm:g	(Lgm;)I
    //   88: invokestatic 271	gm:a	(Lgm;I)I
    //   91: pop
    //   92: aload_1
    //   93: aload_2
    //   94: invokestatic 273	gm:l	(Lgm;)Ljava/lang/String;
    //   97: invokestatic 275	gm:c	(Lgm;Ljava/lang/String;)Ljava/lang/String;
    //   100: pop
    //   101: aload_1
    //   102: aload_2
    //   103: invokestatic 278	gm:k	(Lgm;)[B
    //   106: invokestatic 281	gm:a	(Lgm;[B)[B
    //   109: pop
    //   110: aload_1
    //   111: aload_2
    //   112: invokestatic 284	gm:m	(Lgm;)Landroid/graphics/drawable/BitmapDrawable;
    //   115: invokestatic 287	gm:a	(Lgm;Landroid/graphics/drawable/BitmapDrawable;)Landroid/graphics/drawable/BitmapDrawable;
    //   118: pop
    //   119: aload_1
    //   120: aload_2
    //   121: invokestatic 289	gm:e	(Lgm;)J
    //   124: invokestatic 291	gm:b	(Lgm;J)J
    //   127: pop2
    //   128: aload_1
    //   129: aload_2
    //   130: invokestatic 293	gm:d	(Lgm;)I
    //   133: invokestatic 295	gm:b	(Lgm;I)I
    //   136: pop
    //   137: aload_1
    //   138: aload_2
    //   139: invokestatic 298	gm:n	(Lgm;)Ljava/lang/String;
    //   142: invokestatic 300	gm:d	(Lgm;Ljava/lang/String;)Ljava/lang/String;
    //   145: pop
    //   146: aload_1
    //   147: aload_2
    //   148: invokestatic 246	gm:i	(Lgm;)Ljava/lang/String;
    //   151: invokestatic 302	gm:e	(Lgm;Ljava/lang/String;)Ljava/lang/String;
    //   154: pop
    //   155: aload_1
    //   156: aload_2
    //   157: invokestatic 304	gm:h	(Lgm;)Z
    //   160: invokestatic 306	gm:c	(Lgm;Z)Z
    //   163: pop
    //   164: aload_1
    //   165: aload_2
    //   166: invokestatic 309	gm:o	(Lgm;)Ljava/lang/String;
    //   169: invokestatic 311	gm:f	(Lgm;Ljava/lang/String;)Ljava/lang/String;
    //   172: pop
    //   173: aload_1
    //   174: aload_2
    //   175: invokestatic 314	gm:p	(Lgm;)Z
    //   178: invokestatic 316	gm:d	(Lgm;Z)Z
    //   181: pop
    //   182: aload_1
    //   183: invokestatic 319	gm:q	(Lgm;)V
    //   186: aload_1
    //   187: invokestatic 252	gm:c	(Lgm;)Ljava/lang/String;
    //   190: invokestatic 153	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   193: ifne +102 -> 295
    //   196: invokestatic 322	gm:q	()Ljava/util/HashSet;
    //   199: astore_2
    //   200: aload_2
    //   201: monitorenter
    //   202: invokestatic 322	gm:q	()Ljava/util/HashSet;
    //   205: invokevirtual 328	java/util/HashSet:clone	()Ljava/lang/Object;
    //   208: checkcast 324	java/util/HashSet
    //   211: astore_3
    //   212: aload_2
    //   213: monitorexit
    //   214: aload_3
    //   215: invokevirtual 332	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   218: astore_2
    //   219: aload_2
    //   220: invokeinterface 338 1 0
    //   225: ifeq +70 -> 295
    //   228: aload_2
    //   229: invokeinterface 341 1 0
    //   234: checkcast 343	gm$b
    //   237: astore_3
    //   238: ldc_w 345
    //   241: iconst_3
    //   242: invokestatic 234	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   245: ifeq +30 -> 275
    //   248: ldc_w 347
    //   251: new 236	java/lang/StringBuilder
    //   254: dup
    //   255: invokespecial 237	java/lang/StringBuilder:<init>	()V
    //   258: ldc_w 349
    //   261: invokevirtual 243	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   264: aload_3
    //   265: invokevirtual 352	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   268: invokevirtual 247	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   271: invokestatic 355	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   274: pop
    //   275: aload_3
    //   276: aload_1
    //   277: invokeinterface 357 2 0
    //   282: goto -63 -> 219
    //   285: astore_2
    //   286: aload_1
    //   287: monitorexit
    //   288: aload_2
    //   289: athrow
    //   290: astore_3
    //   291: aload_2
    //   292: monitorexit
    //   293: aload_3
    //   294: athrow
    //   295: aload_1
    //   296: monitorenter
    //   297: aload_1
    //   298: iconst_0
    //   299: invokestatic 176	gm:b	(Lgm;Z)Z
    //   302: pop
    //   303: aload_1
    //   304: invokevirtual 360	java/lang/Object:notifyAll	()V
    //   307: aload_1
    //   308: monitorexit
    //   309: aload_1
    //   310: monitorexit
    //   311: return
    //   312: astore_2
    //   313: aload_1
    //   314: monitorexit
    //   315: aload_2
    //   316: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	317	0	this	a
    //   0	317	1	paramgm	gm
    //   285	7	2	localObject2	Object
    //   312	4	2	localObject3	Object
    //   211	65	3	localObject4	Object
    //   290	4	3	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   13	56	285	finally
    //   56	202	285	finally
    //   214	219	285	finally
    //   219	275	285	finally
    //   275	282	285	finally
    //   286	288	285	finally
    //   293	295	285	finally
    //   295	297	285	finally
    //   309	311	285	finally
    //   315	317	285	finally
    //   202	214	290	finally
    //   291	293	290	finally
    //   297	309	312	finally
    //   313	315	312	finally
  }
  
  private void a(gm paramgm, Cursor paramCursor)
  {
    boolean bool = true;
    for (;;)
    {
      try
      {
        gm.b(paramgm, 1);
        gm.b(paramgm, paramCursor.getLong(0));
        gm.b(paramgm, paramCursor.getString(2));
        gm.e(paramgm, paramCursor.getString(3));
        gm.a(paramgm, paramCursor.getLong(4));
        gm.a(paramgm, a(paramCursor.getInt(5)));
        gm.c(paramgm, paramCursor.getString(6));
        gm.d(paramgm, paramCursor.getString(7));
        if (paramCursor.getInt(8) == 1)
        {
          gm.c(paramgm, bool);
          gm.f(paramgm, paramCursor.getString(9));
          if (Log.isLoggable("Mms:contact", 3)) {
            gm.b("fillPhoneTypeContact: name=" + gm.i(paramgm) + ", number=" + gm.c(paramgm) + ", presence=" + gm.g(paramgm) + " SendToVoicemail: " + gm.h(paramgm));
          }
          paramCursor = c(paramgm);
        }
      }
      finally {}
      try
      {
        gm.a(paramgm, paramCursor);
        return;
      }
      finally {}
      bool = false;
    }
  }
  
  private boolean a(gm paramgm1, gm paramgm2)
  {
    if (gm.d(paramgm1) != gm.d(paramgm2)) {}
    do
    {
      String str1;
      String str2;
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  return true;
                } while (gm.e(paramgm1) != gm.e(paramgm2));
                if (gm.f(paramgm1) == gm.f(paramgm2)) {
                  break;
                }
              } while (!Log.isLoggable("Mms:contact", 3));
              Log.d("Contact", "person id changed");
              return true;
              if (gm.g(paramgm1) == gm.g(paramgm2)) {
                break;
              }
            } while (!Log.isLoggable("Mms:contact", 3));
            Log.d("Contact", "presence changed");
            return true;
          } while (gm.h(paramgm1) != gm.h(paramgm2));
          str1 = gm.c(gm.i(paramgm1));
          str2 = gm.c(gm.i(paramgm2));
          if (str1.equals(str2)) {
            break;
          }
        } while (!Log.isLoggable("Mms:contact", 3));
        Log.d("Contact", String.format("name changed: %s -> %s", new Object[] { str1, str2 }));
        return true;
        str1 = gm.c(gm.j(paramgm1));
        str2 = gm.c(gm.j(paramgm2));
        if (str1.equals(str2)) {
          break;
        }
      } while (!Log.isLoggable("Mms:contact", 3));
      Log.d("Contact", String.format("label changed: %s -> %s", new Object[] { str1, str2 }));
      return true;
      if (Arrays.equals(gm.k(paramgm1), gm.k(paramgm2))) {
        break;
      }
    } while (!Log.isLoggable("Mms:contact", 3));
    Log.d("Contact", "avatar changed");
    return true;
    return false;
  }
  
  private boolean a(String paramString)
  {
    if (!PhoneNumberUtils.isWellFormedSmsAddress(paramString)) {}
    do
    {
      do
      {
        do
        {
          do
          {
            return true;
          } while (wd.b(paramString));
          paramString = PhoneNumberUtils.extractNetworkPortion(paramString);
        } while (TextUtils.isEmpty(paramString));
        if (paramString.length() >= 3) {
          break;
        }
      } while (!wd.g(paramString));
      return false;
    } while (paramString.length() < 3);
    return false;
  }
  
  private gm b()
  {
    gm localgm = new gm(true, null);
    gm.b(localgm, 3);
    if (Log.isLoggable("Mms:contact", 3)) {
      gm.b("getContactInfoForSelf");
    }
    Cursor localCursor = l.getContentResolver().query(ContactsContract.Profile.CONTENT_URI, g, null, null, null);
    if (localCursor == null)
    {
      Log.w("Contact", "getContactInfoForSelf() returned NULL cursor! contact uri used " + ContactsContract.Profile.CONTENT_URI);
      return localgm;
    }
    try
    {
      if (localCursor.moveToFirst()) {
        b(localgm, localCursor);
      }
      return localgm;
    }
    finally
    {
      localCursor.close();
    }
  }
  
  private gm b(gm paramgm)
  {
    if (gm.r(paramgm)) {
      localObject = b();
    }
    gm localgm;
    do
    {
      return (gm)localObject;
      if (MzTelephony.MmsExt.isSnsRecipient(gm.c(paramgm))) {
        return d(gm.c(paramgm));
      }
      if (aau.d(gm.c(paramgm))) {
        return c(gm.c(paramgm));
      }
      if (!a(gm.c(paramgm))) {
        break;
      }
      localgm = c(gm.c(paramgm));
      localObject = localgm;
    } while (localgm.k());
    return b(gm.c(paramgm));
    Object localObject = PhoneNumberUtils.stripSeparators(gm.c(paramgm));
    if ((localObject != null) && (((String)localObject).length() < 2))
    {
      Log.w("Contact", "getContactInfo number is " + gm.c(paramgm));
      Log.w("Contact", "getContactInfo strippedNumber is " + (String)localObject);
    }
    return b((String)localObject);
  }
  
  /* Error */
  private gm b(String paramString)
  {
    // Byte code:
    //   0: new 6	gm
    //   3: dup
    //   4: aload_1
    //   5: aconst_null
    //   6: invokespecial 504	gm:<init>	(Ljava/lang/String;Lgn;)V
    //   9: astore 7
    //   11: aload 7
    //   13: iconst_1
    //   14: invokestatic 295	gm:b	(Lgm;I)I
    //   17: pop
    //   18: ldc_w 345
    //   21: iconst_3
    //   22: invokestatic 234	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   25: ifeq +26 -> 51
    //   28: new 236	java/lang/StringBuilder
    //   31: dup
    //   32: invokespecial 237	java/lang/StringBuilder:<init>	()V
    //   35: ldc_w 506
    //   38: invokevirtual 243	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: aload_1
    //   42: invokevirtual 243	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   45: invokevirtual 247	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   48: invokestatic 250	gm:b	(Ljava/lang/String;)V
    //   51: aload_1
    //   52: invokestatic 511	android/telephony/MzPhoneNumberUtilsExt:normalizeNumber	(Ljava/lang/String;)Ljava/lang/String;
    //   55: astore_3
    //   56: aload_3
    //   57: invokestatic 514	android/telephony/PhoneNumberUtils:toCallerIDMinMatch	(Ljava/lang/String;)Ljava/lang/String;
    //   60: astore 6
    //   62: aload_3
    //   63: invokestatic 153	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   66: ifne +480 -> 546
    //   69: aload 6
    //   71: invokestatic 153	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   74: ifne +472 -> 546
    //   77: aload_3
    //   78: invokevirtual 196	java/lang/String:length	()I
    //   81: invokestatic 517	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   84: astore 8
    //   86: aload_1
    //   87: invokestatic 522	com/android/mms/MmsApp:c	()Lcom/android/mms/MmsApp;
    //   90: invokevirtual 524	com/android/mms/MmsApp:h	()Ljava/lang/String;
    //   93: invokestatic 528	android/telephony/PhoneNumberUtils:formatNumberToE164	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   96: astore 9
    //   98: aload 9
    //   100: invokestatic 153	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   103: ifeq +272 -> 375
    //   106: ldc_w 530
    //   109: astore 4
    //   111: iconst_4
    //   112: anewarray 77	java/lang/String
    //   115: astore 5
    //   117: aload 5
    //   119: iconst_0
    //   120: aload 6
    //   122: aastore
    //   123: aload 5
    //   125: iconst_1
    //   126: aload 8
    //   128: aastore
    //   129: aload 5
    //   131: iconst_2
    //   132: aload_3
    //   133: aastore
    //   134: aload 5
    //   136: iconst_3
    //   137: aload 8
    //   139: aastore
    //   140: aload_0
    //   141: getfield 134	gm$a:l	Landroid/content/Context;
    //   144: invokevirtual 445	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   147: getstatic 37	gm$a:d	Landroid/net/Uri;
    //   150: getstatic 99	gm$a:f	[Ljava/lang/String;
    //   153: aload 4
    //   155: aload 5
    //   157: aconst_null
    //   158: invokevirtual 454	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   161: astore_3
    //   162: aload_3
    //   163: ifnull +12 -> 175
    //   166: aload_3
    //   167: invokeinterface 533 1 0
    //   172: ifne +451 -> 623
    //   175: aload_0
    //   176: getfield 134	gm$a:l	Landroid/content/Context;
    //   179: aload_1
    //   180: invokestatic 539	android/telephony/MzPhoneNumberUtils:isNotificationNumber	(Landroid/content/Context;Ljava/lang/String;)Z
    //   183: ifeq +440 -> 623
    //   186: aload_3
    //   187: ifnull +9 -> 196
    //   190: aload_3
    //   191: invokeinterface 467 1 0
    //   196: aload_0
    //   197: getfield 134	gm$a:l	Landroid/content/Context;
    //   200: invokevirtual 445	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   203: getstatic 75	gm$a:e	Landroid/net/Uri;
    //   206: getstatic 99	gm$a:f	[Ljava/lang/String;
    //   209: aload 4
    //   211: aload 5
    //   213: aconst_null
    //   214: invokevirtual 454	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   217: astore 5
    //   219: aload 5
    //   221: astore 4
    //   223: aload 5
    //   225: astore 6
    //   227: aload 7
    //   229: iconst_1
    //   230: invokestatic 316	gm:d	(Lgm;Z)Z
    //   233: pop
    //   234: aload 5
    //   236: astore_3
    //   237: aload 5
    //   239: ifnull +91 -> 330
    //   242: aload 5
    //   244: astore_3
    //   245: aload 5
    //   247: astore 4
    //   249: aload 5
    //   251: astore 6
    //   253: aload 5
    //   255: invokeinterface 533 1 0
    //   260: ifle +70 -> 330
    //   263: aload 5
    //   265: astore_3
    //   266: aload 5
    //   268: astore 4
    //   270: aload 5
    //   272: astore 6
    //   274: aload 5
    //   276: invokeinterface 462 1 0
    //   281: ifeq +49 -> 330
    //   284: aload 5
    //   286: astore_3
    //   287: aload 5
    //   289: astore 4
    //   291: aload 5
    //   293: astore 6
    //   295: aload 5
    //   297: iconst_3
    //   298: invokeinterface 371 2 0
    //   303: ifnonnull +27 -> 330
    //   306: aload 5
    //   308: astore 4
    //   310: aload 5
    //   312: astore 6
    //   314: aload 5
    //   316: invokeinterface 467 1 0
    //   321: aload 7
    //   323: iconst_0
    //   324: invokestatic 316	gm:d	(Lgm;Z)Z
    //   327: pop
    //   328: aconst_null
    //   329: astore_3
    //   330: aload_3
    //   331: ifnull +18 -> 349
    //   334: aload_3
    //   335: astore 4
    //   337: aload_3
    //   338: astore 6
    //   340: aload_3
    //   341: invokeinterface 533 1 0
    //   346: ifne +72 -> 418
    //   349: aload_3
    //   350: astore 4
    //   352: aload_3
    //   353: astore 6
    //   355: aload 7
    //   357: iconst_0
    //   358: invokestatic 316	gm:d	(Lgm;Z)Z
    //   361: pop
    //   362: aload_3
    //   363: ifnull +9 -> 372
    //   366: aload_3
    //   367: invokeinterface 467 1 0
    //   372: aload 7
    //   374: areturn
    //   375: ldc_w 541
    //   378: astore 4
    //   380: iconst_5
    //   381: anewarray 77	java/lang/String
    //   384: astore 5
    //   386: aload 5
    //   388: iconst_0
    //   389: aload 6
    //   391: aastore
    //   392: aload 5
    //   394: iconst_1
    //   395: aload 9
    //   397: aastore
    //   398: aload 5
    //   400: iconst_2
    //   401: aload 8
    //   403: aastore
    //   404: aload 5
    //   406: iconst_3
    //   407: aload_3
    //   408: aastore
    //   409: aload 5
    //   411: iconst_4
    //   412: aload 8
    //   414: aastore
    //   415: goto -275 -> 140
    //   418: aload_3
    //   419: astore 4
    //   421: aload_3
    //   422: astore 6
    //   424: aload_3
    //   425: invokeinterface 462 1 0
    //   430: ifeq +106 -> 536
    //   433: aload_3
    //   434: astore 4
    //   436: aload_3
    //   437: astore 6
    //   439: aload_1
    //   440: aload_3
    //   441: iconst_1
    //   442: invokeinterface 371 2 0
    //   447: invokevirtual 403	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   450: ifne +73 -> 523
    //   453: aload_3
    //   454: astore 4
    //   456: aload_3
    //   457: astore 6
    //   459: aload_3
    //   460: invokeinterface 533 1 0
    //   465: iconst_1
    //   466: if_icmple +57 -> 523
    //   469: aload_3
    //   470: astore 4
    //   472: aload_3
    //   473: astore 6
    //   475: aload_3
    //   476: invokeinterface 544 1 0
    //   481: ifeq +137 -> 618
    //   484: aload_3
    //   485: astore 4
    //   487: aload_3
    //   488: astore 6
    //   490: aload_1
    //   491: aload_3
    //   492: iconst_1
    //   493: invokeinterface 371 2 0
    //   498: invokevirtual 403	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   501: ifeq -32 -> 469
    //   504: iconst_1
    //   505: istore_2
    //   506: iload_2
    //   507: ifne +16 -> 523
    //   510: aload_3
    //   511: astore 4
    //   513: aload_3
    //   514: astore 6
    //   516: aload_3
    //   517: invokeinterface 462 1 0
    //   522: pop
    //   523: aload_3
    //   524: astore 4
    //   526: aload_3
    //   527: astore 6
    //   529: aload_0
    //   530: aload 7
    //   532: aload_3
    //   533: invokespecial 546	gm$a:a	(Lgm;Landroid/database/Cursor;)V
    //   536: aload_3
    //   537: ifnull +9 -> 546
    //   540: aload_3
    //   541: invokeinterface 467 1 0
    //   546: aload 7
    //   548: areturn
    //   549: astore_3
    //   550: aconst_null
    //   551: astore_1
    //   552: aload_1
    //   553: astore 4
    //   555: aload_3
    //   556: invokevirtual 549	java/lang/Exception:printStackTrace	()V
    //   559: aload_1
    //   560: ifnull -14 -> 546
    //   563: aload_1
    //   564: invokeinterface 467 1 0
    //   569: goto -23 -> 546
    //   572: astore_1
    //   573: aconst_null
    //   574: astore 4
    //   576: aload 4
    //   578: ifnull +10 -> 588
    //   581: aload 4
    //   583: invokeinterface 467 1 0
    //   588: aload_1
    //   589: athrow
    //   590: astore_1
    //   591: aload_3
    //   592: astore 4
    //   594: goto -18 -> 576
    //   597: astore_1
    //   598: goto -22 -> 576
    //   601: astore 4
    //   603: aload_3
    //   604: astore_1
    //   605: aload 4
    //   607: astore_3
    //   608: goto -56 -> 552
    //   611: astore_3
    //   612: aload 6
    //   614: astore_1
    //   615: goto -63 -> 552
    //   618: iconst_0
    //   619: istore_2
    //   620: goto -114 -> 506
    //   623: goto -293 -> 330
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	626	0	this	a
    //   0	626	1	paramString	String
    //   505	115	2	n	int
    //   55	486	3	localObject1	Object
    //   549	55	3	localException1	Exception
    //   607	1	3	localObject2	Object
    //   611	1	3	localException2	Exception
    //   109	484	4	localObject3	Object
    //   601	5	4	localException3	Exception
    //   115	295	5	localObject4	Object
    //   60	553	6	localObject5	Object
    //   9	538	7	localgm	gm
    //   84	329	8	str1	String
    //   96	300	9	str2	String
    // Exception table:
    //   from	to	target	type
    //   140	162	549	java/lang/Exception
    //   321	328	549	java/lang/Exception
    //   140	162	572	finally
    //   321	328	572	finally
    //   166	175	590	finally
    //   175	186	590	finally
    //   190	196	590	finally
    //   196	219	590	finally
    //   227	234	597	finally
    //   253	263	597	finally
    //   274	284	597	finally
    //   295	306	597	finally
    //   314	321	597	finally
    //   340	349	597	finally
    //   355	362	597	finally
    //   424	433	597	finally
    //   439	453	597	finally
    //   459	469	597	finally
    //   475	484	597	finally
    //   490	504	597	finally
    //   516	523	597	finally
    //   529	536	597	finally
    //   555	559	597	finally
    //   166	175	601	java/lang/Exception
    //   175	186	601	java/lang/Exception
    //   190	196	601	java/lang/Exception
    //   196	219	601	java/lang/Exception
    //   227	234	611	java/lang/Exception
    //   253	263	611	java/lang/Exception
    //   274	284	611	java/lang/Exception
    //   295	306	611	java/lang/Exception
    //   314	321	611	java/lang/Exception
    //   340	349	611	java/lang/Exception
    //   355	362	611	java/lang/Exception
    //   424	433	611	java/lang/Exception
    //   439	453	611	java/lang/Exception
    //   459	469	611	java/lang/Exception
    //   475	484	611	java/lang/Exception
    //   490	504	611	java/lang/Exception
    //   516	523	611	java/lang/Exception
    //   529	536	611	java/lang/Exception
  }
  
  private gm b(String paramString, boolean paramBoolean)
  {
    Object localObject;
    ArrayList localArrayList;
    int i1;
    label59:
    int n;
    if (!paramBoolean)
    {
      try
      {
        if ((MzTelephony.MmsExt.isSnsRecipient(paramString)) || (aau.d(paramString))) {
          break label213;
        }
        if (!wd.b(paramString)) {
          break label225;
        }
      }
      finally {}
      localArrayList = (ArrayList)m.get(localObject);
      if (localArrayList != null)
      {
        int i2 = localArrayList.size();
        i1 = 0;
        localObject = localArrayList;
        if (i1 >= i2) {
          break label175;
        }
        localObject = (gm)localArrayList.get(i1);
        if (n != 0)
        {
          if (!paramString.equals(gm.c((gm)localObject))) {
            break label141;
          }
          return (gm)localObject;
        }
      }
    }
    label141:
    label175:
    label213:
    label225:
    label228:
    for (;;)
    {
      localObject = a(paramString, a);
      break;
      if (wd.d(paramString).equals(gm.s((gm)localObject))) {
        return (gm)localObject;
      }
      i1 += 1;
      break label59;
      localArrayList = new ArrayList();
      m.put(localObject, localArrayList);
      localObject = localArrayList;
      if (paramBoolean) {}
      for (paramString = new gm(true, null);; paramString = new gm(paramString, null))
      {
        ((ArrayList)localObject).add(paramString);
        return paramString;
      }
      for (n = 1;; n = 0)
      {
        if (n == 0) {
          break label228;
        }
        localObject = paramString;
        break;
      }
    }
  }
  
  /* Error */
  private void b(gm paramgm, Cursor paramCursor)
  {
    // Byte code:
    //   0: aload_1
    //   1: monitorenter
    //   2: aload_1
    //   3: aload_2
    //   4: iconst_1
    //   5: invokeinterface 371 2 0
    //   10: invokestatic 302	gm:e	(Lgm;Ljava/lang/String;)Ljava/lang/String;
    //   13: pop
    //   14: aload_1
    //   15: invokestatic 246	gm:i	(Lgm;)Ljava/lang/String;
    //   18: invokestatic 153	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   21: ifeq +18 -> 39
    //   24: aload_1
    //   25: aload_0
    //   26: getfield 134	gm$a:l	Landroid/content/Context;
    //   29: ldc_w 577
    //   32: invokevirtual 578	android/content/Context:getString	(I)Ljava/lang/String;
    //   35: invokestatic 302	gm:e	(Lgm;Ljava/lang/String;)Ljava/lang/String;
    //   38: pop
    //   39: ldc_w 345
    //   42: iconst_3
    //   43: invokestatic 234	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   46: ifeq +42 -> 88
    //   49: new 236	java/lang/StringBuilder
    //   52: dup
    //   53: invokespecial 237	java/lang/StringBuilder:<init>	()V
    //   56: ldc_w 580
    //   59: invokevirtual 243	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: aload_1
    //   63: invokestatic 246	gm:i	(Lgm;)Ljava/lang/String;
    //   66: invokevirtual 243	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   69: ldc_w 380
    //   72: invokevirtual 243	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   75: aload_1
    //   76: invokestatic 252	gm:c	(Lgm;)Ljava/lang/String;
    //   79: invokevirtual 243	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   82: invokevirtual 247	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   85: invokestatic 250	gm:b	(Ljava/lang/String;)V
    //   88: aload_1
    //   89: monitorexit
    //   90: aload_0
    //   91: aload_1
    //   92: invokespecial 392	gm$a:c	(Lgm;)[B
    //   95: astore_2
    //   96: aload_1
    //   97: monitorenter
    //   98: aload_1
    //   99: aload_2
    //   100: invokestatic 281	gm:a	(Lgm;[B)[B
    //   103: pop
    //   104: aload_1
    //   105: monitorexit
    //   106: return
    //   107: astore_2
    //   108: aload_1
    //   109: monitorexit
    //   110: aload_2
    //   111: athrow
    //   112: astore_2
    //   113: aload_1
    //   114: monitorexit
    //   115: aload_2
    //   116: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	117	0	this	a
    //   0	117	1	paramgm	gm
    //   0	117	2	paramCursor	Cursor
    // Exception table:
    //   from	to	target	type
    //   2	39	107	finally
    //   39	88	107	finally
    //   88	90	107	finally
    //   108	110	107	finally
    //   98	106	112	finally
    //   113	115	112	finally
  }
  
  /* Error */
  private gm c(String paramString)
  {
    // Byte code:
    //   0: new 6	gm
    //   3: dup
    //   4: aload_1
    //   5: aconst_null
    //   6: invokespecial 504	gm:<init>	(Ljava/lang/String;Lgn;)V
    //   9: astore 7
    //   11: aload 7
    //   13: iconst_2
    //   14: invokestatic 295	gm:b	(Lgm;I)I
    //   17: pop
    //   18: aload_0
    //   19: getfield 134	gm$a:l	Landroid/content/Context;
    //   22: invokevirtual 445	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   25: getstatic 103	gm$a:h	Landroid/net/Uri;
    //   28: getstatic 105	gm$a:i	[Ljava/lang/String;
    //   31: ldc_w 582
    //   34: iconst_1
    //   35: anewarray 77	java/lang/String
    //   38: dup
    //   39: iconst_0
    //   40: aload_1
    //   41: aastore
    //   42: aconst_null
    //   43: invokevirtual 454	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   46: astore 6
    //   48: aload 6
    //   50: ifnull +244 -> 294
    //   53: aload 6
    //   55: invokeinterface 544 1 0
    //   60: ifeq +227 -> 287
    //   63: aload 7
    //   65: monitorenter
    //   66: aload 7
    //   68: aload 6
    //   70: iconst_0
    //   71: invokeinterface 367 2 0
    //   76: invokestatic 291	gm:b	(Lgm;J)J
    //   79: pop2
    //   80: aload 7
    //   82: aload_0
    //   83: aload 6
    //   85: iconst_2
    //   86: invokeinterface 374 2 0
    //   91: invokespecial 376	gm$a:a	(I)I
    //   94: invokestatic 271	gm:a	(Lgm;I)I
    //   97: pop
    //   98: aload 7
    //   100: aload 6
    //   102: iconst_3
    //   103: invokeinterface 367 2 0
    //   108: invokestatic 265	gm:a	(Lgm;J)J
    //   111: pop2
    //   112: aload 6
    //   114: iconst_5
    //   115: invokeinterface 374 2 0
    //   120: iconst_1
    //   121: if_icmpne +176 -> 297
    //   124: iconst_1
    //   125: istore_3
    //   126: aload 7
    //   128: iload_3
    //   129: invokestatic 306	gm:c	(Lgm;Z)Z
    //   132: pop
    //   133: aload 7
    //   135: aload 6
    //   137: bipush 6
    //   139: invokeinterface 371 2 0
    //   144: invokestatic 311	gm:f	(Lgm;Ljava/lang/String;)Ljava/lang/String;
    //   147: pop
    //   148: aload 6
    //   150: iconst_1
    //   151: invokeinterface 371 2 0
    //   156: astore 5
    //   158: aload 5
    //   160: astore 4
    //   162: aload 5
    //   164: invokestatic 153	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   167: ifeq +13 -> 180
    //   170: aload 6
    //   172: iconst_4
    //   173: invokeinterface 371 2 0
    //   178: astore 4
    //   180: aload 4
    //   182: invokestatic 153	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   185: ifne +139 -> 324
    //   188: aload 7
    //   190: aload 4
    //   192: invokestatic 302	gm:e	(Lgm;Ljava/lang/String;)Ljava/lang/String;
    //   195: pop
    //   196: ldc_w 345
    //   199: iconst_3
    //   200: invokestatic 234	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   203: ifeq +126 -> 329
    //   206: new 236	java/lang/StringBuilder
    //   209: dup
    //   210: invokespecial 237	java/lang/StringBuilder:<init>	()V
    //   213: ldc_w 584
    //   216: invokevirtual 243	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   219: aload 7
    //   221: invokestatic 246	gm:i	(Lgm;)Ljava/lang/String;
    //   224: invokevirtual 243	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   227: ldc_w 586
    //   230: invokevirtual 243	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   233: aload_1
    //   234: invokevirtual 243	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   237: ldc_w 382
    //   240: invokevirtual 243	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   243: aload 7
    //   245: invokestatic 268	gm:g	(Lgm;)I
    //   248: invokevirtual 385	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   251: invokevirtual 247	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   254: invokestatic 250	gm:b	(Ljava/lang/String;)V
    //   257: goto +72 -> 329
    //   260: aload 7
    //   262: monitorexit
    //   263: iload_2
    //   264: ifeq -211 -> 53
    //   267: aload_0
    //   268: aload 7
    //   270: invokespecial 392	gm$a:c	(Lgm;)[B
    //   273: astore_1
    //   274: aload 7
    //   276: monitorenter
    //   277: aload 7
    //   279: aload_1
    //   280: invokestatic 281	gm:a	(Lgm;[B)[B
    //   283: pop
    //   284: aload 7
    //   286: monitorexit
    //   287: aload 6
    //   289: invokeinterface 467 1 0
    //   294: aload 7
    //   296: areturn
    //   297: iconst_0
    //   298: istore_3
    //   299: goto -173 -> 126
    //   302: astore_1
    //   303: aload 7
    //   305: monitorexit
    //   306: aload_1
    //   307: athrow
    //   308: astore_1
    //   309: aload 6
    //   311: invokeinterface 467 1 0
    //   316: aload_1
    //   317: athrow
    //   318: astore_1
    //   319: aload 7
    //   321: monitorexit
    //   322: aload_1
    //   323: athrow
    //   324: iconst_0
    //   325: istore_2
    //   326: goto -66 -> 260
    //   329: iconst_1
    //   330: istore_2
    //   331: goto -71 -> 260
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	334	0	this	a
    //   0	334	1	paramString	String
    //   263	68	2	n	int
    //   125	174	3	bool	boolean
    //   160	31	4	str1	String
    //   156	7	5	str2	String
    //   46	264	6	localCursor	Cursor
    //   9	311	7	localgm	gm
    // Exception table:
    //   from	to	target	type
    //   66	124	302	finally
    //   126	158	302	finally
    //   162	180	302	finally
    //   180	257	302	finally
    //   260	263	302	finally
    //   303	306	302	finally
    //   53	66	308	finally
    //   267	277	308	finally
    //   306	308	308	finally
    //   322	324	308	finally
    //   277	287	318	finally
    //   319	322	318	finally
  }
  
  private byte[] c(gm paramgm)
  {
    if (((!gm.r(paramgm)) && (gm.f(paramgm) == 0L)) || (gm.m(paramgm) != null))
    {
      paramgm = null;
      return paramgm;
    }
    if (Log.isLoggable("Mms:contact", 3)) {
      gm.b("loadAvatarData: name=" + gm.i(paramgm) + ", number=" + gm.c(paramgm));
    }
    Object localObject1;
    Object localObject2;
    if (gm.r(paramgm))
    {
      localObject1 = ContactsContract.Profile.CONTENT_URI;
      localObject2 = localObject1;
      if (gm.p(paramgm)) {
        localObject2 = wd.a((Uri)localObject1, wd.a(l, "Net Contact", "com.meizu.netcontactservice"));
      }
      localObject2 = ContactsContract.Contacts.openContactPhotoInputStream(l.getContentResolver(), (Uri)localObject2);
      if (localObject2 == null) {
        break label216;
      }
    }
    for (;;)
    {
      try
      {
        paramgm = new byte[((InputStream)localObject2).available()];
      }
      catch (IOException paramgm)
      {
        paramgm = paramgm;
        localObject1 = null;
        paramgm = (gm)localObject1;
        if (localObject2 == null) {
          break;
        }
        try
        {
          ((InputStream)localObject2).close();
          return (byte[])localObject1;
        }
        catch (IOException paramgm)
        {
          return (byte[])localObject1;
        }
      }
      finally
      {
        if (localObject2 != null) {}
        try
        {
          ((InputStream)localObject2).close();
          throw paramgm;
        }
        catch (IOException localIOException1)
        {
          continue;
        }
      }
      label216:
      gm localgm = null;
    }
  }
  
  private gm d(String paramString)
  {
    gm localgm = new gm(paramString, null);
    gm.b(localgm, 4);
    Cursor localCursor = l.getContentResolver().query(j, k, "UPPER(data1)=UPPER(?) AND mimetype='vnd.android.cursor.item/social_profile'", new String[] { paramString }, null);
    if (localCursor != null) {}
    for (;;)
    {
      try
      {
        if (localCursor.moveToNext()) {}
        try
        {
          gm.b(localgm, localCursor.getLong(0));
          gm.a(localgm, a(localCursor.getInt(2)));
          gm.a(localgm, localCursor.getLong(3));
          gm.f(localgm, localCursor.getString(5));
          String str2 = localCursor.getString(1);
          String str1 = str2;
          if (TextUtils.isEmpty(str2)) {
            str1 = localCursor.getString(4);
          }
          if (TextUtils.isEmpty(str1)) {
            break label293;
          }
          gm.e(localgm, str1);
          if (!Log.isLoggable("Mms:contact", 3)) {
            break label298;
          }
          gm.b("getContactInfoForSnsAddress: name=" + gm.i(localgm) + ", sns=" + paramString + ", presence=" + gm.g(localgm));
        }
        finally
        {
          try
          {
            gm.a(localgm, paramString);
            return localgm;
          }
          finally {}
          paramString = finally;
        }
        if (n == 0) {
          continue;
        }
        paramString = c(localgm);
        n = 0;
      }
      finally
      {
        localCursor.close();
      }
      label293:
      continue;
      label298:
      int n = 1;
    }
  }
  
  private void d(gm paramgm)
  {
    String str;
    int i1;
    label61:
    gm localgm;
    int n;
    label102:
    label135:
    try
    {
      str = paramgm.d();
      if ((paramgm.c()) || (aau.d(str))) {
        break label170;
      }
      if (!wd.b(str)) {
        break label182;
      }
    }
    finally {}
    ArrayList localArrayList = (ArrayList)m.get(paramgm);
    if (localArrayList != null)
    {
      int i2 = localArrayList.size();
      i1 = 0;
      if (i1 < i2)
      {
        localgm = (gm)localArrayList.get(i1);
        if (n == 0) {
          break label135;
        }
        if (!str.equals(gm.c(localgm))) {
          break label163;
        }
        localArrayList.remove(i1);
      }
      if (localArrayList.size() == 0) {
        m.remove(paramgm);
      }
    }
    return;
    label163:
    label170:
    label182:
    label185:
    for (;;)
    {
      paramgm = a(str, a);
      break;
      if (PhoneNumberUtils.compare(str, gm.c(localgm)))
      {
        localArrayList.remove(i1);
        break label102;
      }
      i1 += 1;
      break label61;
      for (n = 1;; n = 0)
      {
        if (n == 0) {
          break label185;
        }
        paramgm = str;
        break;
      }
    }
  }
  
  public gm a(String paramString, boolean paramBoolean)
  {
    return a(paramString, false, paramBoolean);
  }
  
  public ArrayList<ArrayList<gm>> a(boolean paramBoolean)
  {
    try
    {
      ArrayList localArrayList = new ArrayList(m.values());
      return localArrayList;
    }
    finally {}
  }
  
  void a()
  {
    try
    {
      Iterator localIterator1 = m.values().iterator();
      if (localIterator1.hasNext())
      {
        Iterator localIterator2 = ((ArrayList)localIterator1.next()).iterator();
        while (localIterator2.hasNext()) {
          synchronized ((gm)localIterator2.next())
          {
            gm.a(???, true);
          }
        }
      }
    }
    finally {}
  }
  
  public void a(Runnable paramRunnable)
  {
    b.a(paramRunnable);
  }
  
  static class a
  {
    Thread a = new Thread(new gp(this), "Contact.ContactsCache.TaskStack worker thread");
    private final ArrayList<Runnable> b = new ArrayList();
    
    public a()
    {
      a.setPriority(1);
      a.start();
    }
    
    public void a(Runnable paramRunnable)
    {
      synchronized (b)
      {
        b.add(paramRunnable);
        b.notify();
        return;
      }
    }
  }
}

/* Location:
 * Qualified Name:     gm.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */