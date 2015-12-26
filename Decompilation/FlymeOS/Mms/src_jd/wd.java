import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.CamcorderProfile;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.preference.PreferenceManager;
import android.provider.ContactsContract.Contacts;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.provider.Telephony.Mms.Inbox;
import android.provider.Telephony.Mms.Outbox;
import android.provider.Telephony.MmsSms.PendingMessages;
import android.telephony.MzPhoneNumberUtils;
import android.telephony.MzTelephony.MmsExt;
import android.telephony.ServiceState;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.mms.MmsApp;
import com.android.mms.TempFileProvider;
import com.android.mms.ui.ConversationList;
import com.android.mms.ui.MeizuSlideshowActivity;
import com.android.mms.ui.SlideshowActivity;
import com.google.android.mms.MmsException;
import com.meizu.android.mms.pdu.MzEncodedStringValue;
import com.meizu.android.mms.pdu.MzMultimediaMessagePdu;
import com.meizu.android.mms.pdu.MzPduPart;
import com.meizu.android.mms.pdu.MzPduPersister;
import com.meizu.common.app.PermissionDialogBuilder;
import com.meizu.common.app.SlideNotice;
import com.meizu.common.util.DateTimeUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class wd
{
  static final String[] a;
  public static long b;
  protected static SlideNotice c = null;
  private static String d;
  private static String[] e;
  private static final Map<String, String> f = new ConcurrentHashMap(20);
  private static final int[] g = { 0, 5, 10, 15, 20, 30, 40, 50, 60, 90, 120 };
  private static final String[] h = { "+10011" };
  private static final char[] i = { 45, 46, 44, 40, 41, 32, 47, 92, 42, 35, 43 };
  private static HashMap j = new HashMap(i.length);
  private static String k;
  private static String l;
  private static boolean m;
  private static boolean n;
  private static String o;
  private static String p;
  private static final String[] q;
  private static final int r;
  private static final int s;
  private static final int t;
  private static boolean u = false;
  private static HashMap<Long, wd.a> v = new HashMap();
  private static HashMap<Long, wd.a> w = new HashMap();
  private static HashMap<Long, wd.a> x = new HashMap();
  private static Object y = new Object();
  private static wd.b z;
  
  static
  {
    int i1 = 0;
    while (i1 < i.length)
    {
      j.put(Character.valueOf(i[i1]), Character.valueOf(i[i1]));
      i1 += 1;
    }
    k = "";
    m = false;
    n = false;
    o = null;
    p = null;
    a = new String[] { "_id", "display_name", "starred", "times_contacted", "contact_presence", "photo_id", "lookup", "has_phone_number" };
    b = -1L;
    q = new String[] { "\000", "\f" };
    r = "http://mo.amap.com/?".length();
    s = "http://m.amap.com/?".length();
    t = "name=".length();
  }
  
  public static int a(int paramInt1, int paramInt2)
  {
    int i2 = 4;
    if (paramInt1 == paramInt2) {
      return paramInt1;
    }
    int i1;
    switch (paramInt1)
    {
    default: 
      i1 = 0;
      switch (paramInt2)
      {
      default: 
        label58:
        i2 = 0;
      }
      break;
    }
    while (i1 <= i2)
    {
      return paramInt2;
      i1 = 4;
      break label58;
      i1 = 2;
      break label58;
      i1 = 3;
      break label58;
      i1 = 5;
      break label58;
      i1 = 6;
      break label58;
      i1 = 7;
      break label58;
      i1 = 1;
      break label58;
      i1 = 8;
      break label58;
      i2 = 2;
      continue;
      i2 = 3;
      continue;
      i2 = 5;
      continue;
      i2 = 6;
      continue;
      i2 = 7;
      continue;
      i2 = 1;
      continue;
      i2 = 8;
    }
  }
  
  public static int a(long paramLong)
  {
    CamcorderProfile localCamcorderProfile = CamcorderProfile.get(0);
    if (localCamcorderProfile == null) {}
    for (;;)
    {
      return 0;
      int i1 = audioBitRate;
      paramLong = 8L * paramLong / (videoBitRate + i1);
      i1 = g.length - 1;
      while (i1 >= 0)
      {
        if (paramLong >= g[i1]) {
          return g[i1];
        }
        i1 -= 1;
      }
    }
  }
  
  private static int a(Context paramContext, vv paramvv, int paramInt)
  {
    int i1 = 1;
    String str2 = o;
    long l1 = ab;
    String str3 = m;
    String str1 = null;
    int i2 = T;
    if (i2 == 0)
    {
      if (abe.a != null) {
        str1 = abe.a;
      }
    }
    else
    {
      if (i2 == 1)
      {
        if (abe.b == null) {
          break label259;
        }
        str1 = abe.b;
      }
      label62:
      if (g != 1) {
        break label266;
      }
    }
    for (;;)
    {
      Log.i("TAG", "messageItem.mBoxId = " + g);
      Log.d("Mms", "msgBody : " + str2);
      Log.d("Mms", "timestamp : " + l1);
      Log.d("Mms", "number : " + str3);
      Log.d("Mms", "smscAddress : " + str1);
      Log.d("Mms", "smsStatus : " + i1);
      return zv.a(paramContext, str1, str3, aac.c(aac.b(paramInt)).divideMessage(str2), i1, l1, paramInt);
      str1 = "";
      break;
      label259:
      str1 = "";
      break label62;
      label266:
      if (g == 2) {
        i1 = 5;
      } else if (g == 5) {
        i1 = 7;
      } else {
        i1 = 0;
      }
    }
  }
  
  public static int a(lr paramlr, MzMultimediaMessagePdu paramMzMultimediaMessagePdu)
  {
    int i2 = 4;
    int i1;
    if ((paramlr == null) || (paramMzMultimediaMessagePdu == null)) {
      i1 = vv.b;
    }
    do
    {
      do
      {
        int i3;
        do
        {
          return i1;
          i3 = paramlr.size();
          i1 = i2;
        } while (i3 > 1);
        if (i3 != 1) {
          break;
        }
        paramlr = paramlr.a(0);
        i1 = i2;
      } while (paramlr.w());
      if (paramlr.g()) {
        return 2;
      }
      if (!paramlr.f()) {
        break;
      }
      i1 = i2;
    } while (paramlr.e());
    if (paramlr.f()) {
      return 3;
    }
    if (paramlr.i()) {
      return 5;
    }
    if (paramlr.j()) {
      return 7;
    }
    if (paramlr.k()) {
      return 8;
    }
    if (paramlr.e()) {
      return 1;
    }
    if (paramlr.d()) {
      return 0;
    }
    if (paramMzMultimediaMessagePdu.getSubject() != null) {}
    for (paramlr = paramMzMultimediaMessagePdu.getSubject().getString(); !TextUtils.isEmpty(paramlr); paramlr = null) {
      return 0;
    }
    return vv.b;
  }
  
  /* Error */
  public static long a(Context paramContext, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: getstatic 132	wd:b	J
    //   3: lconst_0
    //   4: lcmp
    //   5: iflt +7 -> 12
    //   8: getstatic 132	wd:b	J
    //   11: lreturn
    //   12: aload_0
    //   13: invokevirtual 321	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   16: getstatic 327	android/provider/ContactsContract$Directory:CONTENT_URI	Landroid/net/Uri;
    //   19: iconst_1
    //   20: anewarray 66	java/lang/String
    //   23: dup
    //   24: iconst_0
    //   25: ldc 112
    //   27: aastore
    //   28: ldc_w 329
    //   31: iconst_2
    //   32: anewarray 66	java/lang/String
    //   35: dup
    //   36: iconst_0
    //   37: aload_1
    //   38: aastore
    //   39: dup
    //   40: iconst_1
    //   41: aload_2
    //   42: aastore
    //   43: aconst_null
    //   44: invokevirtual 335	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   47: astore_3
    //   48: aload_3
    //   49: ifnull +37 -> 86
    //   52: aload_3
    //   53: astore_0
    //   54: aload_3
    //   55: invokeinterface 340 1 0
    //   60: ifne +26 -> 86
    //   63: aload_3
    //   64: astore_0
    //   65: aload_3
    //   66: invokeinterface 343 1 0
    //   71: ifeq +15 -> 86
    //   74: aload_3
    //   75: astore_0
    //   76: aload_3
    //   77: iconst_0
    //   78: invokeinterface 346 2 0
    //   83: putstatic 132	wd:b	J
    //   86: aload_3
    //   87: ifnull +60 -> 147
    //   90: aload_3
    //   91: astore_0
    //   92: aload_3
    //   93: invokeinterface 349 1 0
    //   98: iconst_1
    //   99: if_icmple +48 -> 147
    //   102: aload_3
    //   103: astore_0
    //   104: ldc_w 351
    //   107: new 210	java/lang/StringBuilder
    //   110: dup
    //   111: invokespecial 211	java/lang/StringBuilder:<init>	()V
    //   114: ldc_w 353
    //   117: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   120: aload_1
    //   121: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   124: ldc_w 355
    //   127: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   130: aload_2
    //   131: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   134: ldc_w 357
    //   137: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   143: invokestatic 359	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   146: pop
    //   147: aload_3
    //   148: ifnull +9 -> 157
    //   151: aload_3
    //   152: invokeinterface 362 1 0
    //   157: getstatic 132	wd:b	J
    //   160: lreturn
    //   161: astore_1
    //   162: aconst_null
    //   163: astore_3
    //   164: aload_3
    //   165: astore_0
    //   166: aload_1
    //   167: invokevirtual 365	java/lang/Exception:printStackTrace	()V
    //   170: aload_3
    //   171: ifnull -14 -> 157
    //   174: aload_3
    //   175: invokeinterface 362 1 0
    //   180: goto -23 -> 157
    //   183: astore_1
    //   184: aconst_null
    //   185: astore_0
    //   186: aload_0
    //   187: ifnull +9 -> 196
    //   190: aload_0
    //   191: invokeinterface 362 1 0
    //   196: aload_1
    //   197: athrow
    //   198: astore_1
    //   199: goto -13 -> 186
    //   202: astore_1
    //   203: goto -39 -> 164
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	206	0	paramContext	Context
    //   0	206	1	paramString1	String
    //   0	206	2	paramString2	String
    //   47	128	3	localCursor	Cursor
    // Exception table:
    //   from	to	target	type
    //   12	48	161	java/lang/Exception
    //   12	48	183	finally
    //   54	63	198	finally
    //   65	74	198	finally
    //   76	86	198	finally
    //   92	102	198	finally
    //   104	147	198	finally
    //   166	170	198	finally
    //   54	63	202	java/lang/Exception
    //   65	74	202	java/lang/Exception
    //   76	86	202	java/lang/Exception
    //   92	102	202	java/lang/Exception
    //   104	147	202	java/lang/Exception
  }
  
  public static Bitmap a(Bitmap paramBitmap, int paramInt)
  {
    Object localObject2;
    if (paramBitmap == null)
    {
      localObject2 = null;
      return (Bitmap)localObject2;
    }
    int i1 = paramBitmap.getWidth();
    int i2 = paramBitmap.getHeight();
    Object localObject1 = new Matrix();
    ((Matrix)localObject1).postScale(1.0F, 1.0F);
    switch (paramInt)
    {
    case 4: 
    case 5: 
    case 7: 
    default: 
      label76:
      if (paramInt == 3)
      {
        ((Matrix)localObject1).setRotate(90.0F);
        localObject2 = Bitmap.createBitmap(paramBitmap, 0, 0, i1, i2, (Matrix)localObject1, true);
      }
      break;
    }
    for (localObject1 = Bitmap.createBitmap((Bitmap)localObject2, 0, 0, ((Bitmap)localObject2).getWidth(), ((Bitmap)localObject2).getHeight(), (Matrix)localObject1, true);; localObject1 = Bitmap.createBitmap(paramBitmap, 0, 0, i1, i2, (Matrix)localObject1, true))
    {
      localObject2 = localObject1;
      if (localObject1 == paramBitmap) {
        break;
      }
      paramBitmap.recycle();
      return (Bitmap)localObject1;
      ((Matrix)localObject1).setRotate(90.0F);
      break label76;
      ((Matrix)localObject1).setRotate(270.0F);
      break label76;
      ((Matrix)localObject1).setRotate(180.0F);
      break label76;
    }
  }
  
  public static Uri a(long paramLong1, String paramString, long paramLong2)
  {
    return ContactsContract.Contacts.getLookupUri(paramLong1, paramString).buildUpon().appendQueryParameter("directory", String.valueOf(paramLong2)).build();
  }
  
  public static Uri a(Uri paramUri, long paramLong)
  {
    return paramUri.buildUpon().appendQueryParameter("directory", String.valueOf(paramLong)).build();
  }
  
  public static android.support.v7.app.AlertDialog.Builder a(Context paramContext, CharSequence[] paramArrayOfCharSequence, DialogInterface.OnClickListener paramOnClickListener)
  {
    ColorStateList[] arrayOfColorStateList = new ColorStateList[paramArrayOfCharSequence.length];
    Resources localResources = paramContext.getResources();
    int i1 = 0;
    while (i1 < arrayOfColorStateList.length - 1)
    {
      arrayOfColorStateList[i1] = localResources.getColorStateList(2131820888);
      i1 += 1;
    }
    arrayOfColorStateList[(arrayOfColorStateList.length - 1)] = localResources.getColorStateList(2131820887);
    paramContext = new android.support.v7.app.AlertDialog.Builder(paramContext, 2131624309);
    paramContext.setItems(paramArrayOfCharSequence, new wf(paramOnClickListener), true, arrayOfColorStateList);
    paramContext.create();
    return paramContext;
  }
  
  public static String a()
  {
    if (d == null) {
      d = MmsApp.c().g().getLine1Number();
    }
    return d;
  }
  
  public static String a(long paramLong1, long paramLong2, String paramString1, String paramString2)
  {
    String str = paramString2;
    if (paramString2 == null) {
      str = "0.0";
    }
    return new DecimalFormat(str).format(paramLong1 / paramLong2) + paramString1;
  }
  
  public static String a(long paramLong, Context paramContext)
  {
    if (paramLong < 0L) {
      return "? " + paramContext.getResources().getString(2131492974);
    }
    if (paramLong == 0L) {
      return "0 " + paramContext.getResources().getString(2131492974);
    }
    if (paramLong <= 1048576L) {
      return (paramLong + 1023L) / 1024L + paramContext.getResources().getString(2131492974);
    }
    return a(paramLong + 1023L, 1048576L, "MB", null);
  }
  
  public static String a(Context paramContext, long paramLong)
  {
    return a(paramContext, paramLong, false);
  }
  
  public static String a(Context paramContext, long paramLong, int paramInt, boolean paramBoolean)
  {
    return DateTimeUtils.formatTimeStampString(paramContext, paramLong, paramInt);
  }
  
  public static String a(Context paramContext, long paramLong, boolean paramBoolean)
  {
    return a(paramContext, paramLong, 0, paramBoolean);
  }
  
  public static String a(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return paramString;
      if (e == null) {
        e = paramContext.getResources().getStringArray(2131361793);
      }
      int i2 = e.length;
      int i1 = 0;
      while (i1 < i2)
      {
        if (paramString.equalsIgnoreCase(e[i1])) {
          return null;
        }
        i1 += 1;
      }
    }
  }
  
  public static String a(Cursor paramCursor, int paramInt1, int paramInt2)
  {
    String str = paramCursor.getString(paramInt1);
    paramInt1 = paramCursor.getInt(paramInt2);
    if (TextUtils.isEmpty(str)) {
      paramCursor = "";
    }
    do
    {
      return paramCursor;
      paramCursor = str;
    } while (paramInt1 == 0);
    return new MzEncodedStringValue(paramInt1, MzPduPersister.getBytes(str)).getString();
  }
  
  public static String a(String paramString)
  {
    return paramString.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&quot;", "\"").replaceAll("&apos;", "'").replaceAll("&amp;", "&");
  }
  
  public static final String a(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(MimeTypeMap.getSingleton().getExtensionFromMimeType(paramString2)))
    {
      int i1 = paramString1.lastIndexOf(".");
      if ((i1 > 0) && (i1 < paramString1.length() - 1))
      {
        paramString1 = paramString1.substring(i1 + 1, paramString1.length());
        paramString1 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(paramString1);
        if (!TextUtils.isEmpty(paramString1)) {
          return paramString1;
        }
      }
    }
    return paramString2;
    return paramString1;
  }
  
  public static String a(String paramString1, String paramString2, String paramString3)
  {
    String str1 = "";
    if (TextUtils.isEmpty(paramString1)) {
      paramString1 = String.valueOf(System.currentTimeMillis());
    }
    String str2;
    for (;;)
    {
      str2 = paramString3;
      if (TextUtils.isEmpty(paramString3)) {
        str2 = "jpg";
      }
      paramString3 = str1;
      if (TextUtils.isEmpty(str1)) {
        break label183;
      }
      if (TextUtils.isEmpty(MimeTypeMap.getSingleton().getMimeTypeFromExtension(str1.toLowerCase()))) {
        break;
      }
      return paramString1 + "." + str1;
      str2 = n(paramString1);
      if ((str2 == null) || (str2.equals("")))
      {
        paramString1 = String.valueOf(System.currentTimeMillis());
      }
      else
      {
        int i1 = str2.lastIndexOf(".");
        if ((i1 == 0) || (i1 == str2.length() - 1))
        {
          paramString1 = String.valueOf(System.currentTimeMillis());
        }
        else
        {
          paramString1 = str2;
          if (i1 > 0)
          {
            str1 = str2.substring(i1 + 1);
            paramString1 = str2.substring(0, i1);
          }
        }
      }
    }
    paramString3 = "";
    label183:
    paramString2 = MimeTypeMap.getSingleton().getExtensionFromMimeType(paramString2);
    if (TextUtils.isEmpty(paramString2)) {}
    for (;;)
    {
      if (TextUtils.isEmpty(paramString3)) {
        return paramString1 + "." + str2;
      }
      return paramString1 + "." + paramString3;
      paramString3 = paramString2;
    }
  }
  
  public static String a(lr paramlr)
  {
    if (paramlr == null) {}
    do
    {
      return null;
      paramlr = paramlr.a(0);
    } while ((paramlr == null) || (paramlr.q() == null));
    return paramlr.q().o();
  }
  
  public static void a(int paramInt1, Context paramContext, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4)
  {
    a(paramContext.getResources().getString(paramInt1), paramContext, paramInt2, paramInt3, paramBoolean, paramInt4);
  }
  
  public static void a(int paramInt1, Context paramContext, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4, int paramInt5)
  {
    a(paramContext.getResources().getString(paramInt1), paramContext, paramInt2, paramInt3, paramBoolean, paramInt4, paramInt5);
  }
  
  public static void a(int paramInt, ImageView paramImageView, String paramString, TextView paramTextView, boolean paramBoolean)
  {
    int i1 = 2130838200;
    switch (paramInt)
    {
    case 4: 
    case 6: 
    default: 
      if (paramTextView != null) {
        paramTextView.setText(2131493611);
      }
      if (paramBoolean) {
        paramInt = 2130838213;
      }
      break;
    }
    for (;;)
    {
      paramImageView.setImageResource(paramInt);
      return;
      paramInt = i1;
      if (!paramBoolean)
      {
        paramInt = 2130838233;
        continue;
        if (paramBoolean)
        {
          paramInt = 2130838193;
        }
        else
        {
          paramInt = 2130838231;
          continue;
          if (paramBoolean)
          {
            paramInt = 2130838217;
          }
          else
          {
            paramInt = 2130838310;
            continue;
            if (paramBoolean)
            {
              paramInt = 2130838190;
            }
            else
            {
              paramInt = 2130838230;
              continue;
              a(paramString, paramImageView, paramBoolean);
              paramInt = 2130838215;
              continue;
              if (paramTextView != null) {
                paramTextView.setText(2131493227);
              }
              paramInt = i1;
              if (!paramBoolean)
              {
                paramInt = 2130838233;
                continue;
                paramInt = 2130838309;
              }
            }
          }
        }
      }
    }
  }
  
  public static void a(long paramLong, String paramString)
  {
    Log.v("Mms", "saveTempDraft threadId " + paramLong);
    x.clear();
    x.put(Long.valueOf(paramLong), new wd.a(paramString, System.currentTimeMillis()));
  }
  
  public static void a(Activity paramActivity)
  {
    paramActivity = paramActivity.getWindow();
    WindowManager.LayoutParams localLayoutParams = paramActivity.getAttributes();
    aau.a(localLayoutParams, 1024);
    paramActivity.setAttributes(localLayoutParams);
  }
  
  public static void a(Activity paramActivity, int paramInt)
  {
    Intent localIntent = new Intent("android.intent.action.RINGTONE_PICKER");
    localIntent.putExtra("android.intent.extra.ringtone.SHOW_DEFAULT", false);
    localIntent.putExtra("android.intent.extra.ringtone.SHOW_SILENT", false);
    localIntent.putExtra("android.intent.extra.ringtone.INCLUDE_DRM", false);
    localIntent.putExtra("android.intent.extra.ringtone.TITLE", paramActivity.getString(2131493100));
    paramActivity.startActivityForResult(localIntent, paramInt);
  }
  
  public static void a(Activity paramActivity, int paramInt, long paramLong)
  {
    Intent localIntent = new Intent("android.intent.action.GET_CONTENT");
    localIntent.setType("audio/amr");
    if (MmsApp.a) {
      localIntent.setClassName("com.android.soundrecorder", "com.android.soundrecorder.MzRecorderActivity");
    }
    for (;;)
    {
      localIntent.putExtra("android.provider.MediaStore.extra.MAX_BYTES", paramLong);
      paramActivity.startActivityForResult(localIntent, paramInt);
      return;
      localIntent.setClassName("com.android.soundrecorder", "com.android.soundrecorder.SoundRecorder");
    }
  }
  
  public static void a(Activity paramActivity, Uri paramUri, lr paramlr, pt parampt)
  {
    a(paramActivity, paramUri, paramlr, false, -1, 3, parampt, false, -1L, 0, "");
  }
  
  public static void a(Activity paramActivity, Uri paramUri, lr paramlr, boolean paramBoolean1, int paramInt1, int paramInt2, pt parampt, boolean paramBoolean2, long paramLong, int paramInt3, String paramString)
  {
    if (paramlr == null) {}
    for (boolean bool = false; (!bool) && (paramBoolean1) && ((paramInt1 == 1) || (paramInt1 == 2) || (paramInt1 == 3) || (paramInt1 == 5) || (paramInt1 == 7)); bool = paramlr.g()) {
      return;
    }
    if ((bool) && (!paramBoolean2))
    {
      a(paramActivity, paramlr, paramLong);
      return;
    }
    parampt.a(new wp(paramlr, paramActivity, paramUri), new wq(paramActivity, paramUri, paramInt2, paramLong, paramInt3, paramString), 2131492901);
  }
  
  public static void a(Activity paramActivity, String paramString1, String paramString2)
  {
    if (paramActivity.isFinishing()) {
      return;
    }
    paramActivity = new android.support.v7.app.AlertDialog.Builder(paramActivity);
    paramActivity.setTitle(paramString1);
    paramActivity.setMessage(paramString2);
    paramActivity.setPositiveButton(17039370, new we());
    paramActivity.show();
  }
  
  public static void a(Context paramContext, int paramInt)
  {
    Intent localIntent = new Intent("com.cooliris.media.action.start_video");
    localIntent.putExtra("Mms", "SelectVideo");
    localIntent.addFlags(524288);
    ((Activity)paramContext).startActivityForResult(localIntent, paramInt);
  }
  
  public static void a(Context paramContext, int paramInt1, int paramInt2, int paramInt3)
  {
    j(paramContext, paramContext.getString(paramInt1));
  }
  
  private static void a(Context paramContext, int paramInt1, String paramString1, String paramString2, String paramString3, int paramInt2)
  {
    Intent localIntent;
    if ((paramContext instanceof Activity))
    {
      localIntent = new Intent("android.intent.action.GET_CONTENT");
      localIntent.setType(paramString1);
      localIntent.setClassName(paramString2, paramString3);
      if (paramInt2 != -1) {
        localIntent.putExtra("android.intent.extra.TITLE", paramContext.getResources().getString(paramInt2));
      }
      localIntent.addFlags(524288);
    }
    try
    {
      ((Activity)paramContext).startActivityForResult(localIntent, paramInt1);
      return;
    }
    catch (ActivityNotFoundException paramContext)
    {
      Log.e("Mms", "selectMediaByType. happen ActivityNotFoundException", paramContext);
    }
  }
  
  public static void a(Context paramContext, int paramInt, boolean paramBoolean)
  {
    Intent localIntent;
    if ((paramContext instanceof Activity))
    {
      localIntent = new Intent("android.intent.action.GET_CONTENT");
      localIntent.setType("*/*");
      if (!TextUtils.isEmpty(p)) {
        localIntent.putExtra("init_directory", p);
      }
      if (paramBoolean) {
        break label90;
      }
    }
    label90:
    for (paramBoolean = true;; paramBoolean = false)
    {
      localIntent.putExtra("mmsFilter", paramBoolean);
      localIntent.addFlags(524288);
      localIntent.setClassName("com.meizu.filemanager", "com.meizu.filemanager.choosefile.SingleChoiceActivity");
      ((Activity)paramContext).startActivityForResult(localIntent, paramInt);
      return;
    }
  }
  
  public static void a(Context paramContext, int paramInt, boolean paramBoolean, String[] paramArrayOfString)
  {
    if ((paramContext instanceof Activity))
    {
      Intent localIntent = new Intent("android.intent.action.GET_CONTENT");
      localIntent.setType("*/*");
      localIntent.putExtra("select_dir", false);
      localIntent.putExtra("select_file", true);
      localIntent.putExtra("android.intent.extra.MIME_TYPES", paramArrayOfString);
      localIntent.addFlags(524288);
      localIntent.setClassName("com.meizu.filemanager", "com.meizu.filemanager.choosefile.SingleChoiceActivity");
      ((Activity)paramContext).startActivityForResult(localIntent, paramInt);
    }
  }
  
  public static void a(Context paramContext, Intent paramIntent, String paramString)
  {
    paramIntent = new Intent();
    paramIntent.setAction("android.intent.action.VIEW");
    paramIntent.setDataAndType(Uri.fromFile(new File(paramString)), null);
    try
    {
      paramContext.startActivity(paramIntent);
      return;
    }
    catch (ActivityNotFoundException paramContext)
    {
      paramContext.printStackTrace();
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void a(Context paramContext, Uri paramUri)
  {
    if (paramUri == null) {
      return;
    }
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setClassName("com.meizu.media.gallery", "com.meizu.media.gallery.GalleryActivity");
    localIntent.addFlags(1);
    localIntent.addFlags(524288);
    localIntent.putExtra("mms_video_title", a(paramUri.getLastPathSegment(), "image/jpeg", "jpg"));
    localIntent.putExtra("SingleItemOnly", true);
    localIntent.setDataAndType(paramUri, "image/jpeg");
    localIntent.putExtra("com.android.Mms.SaveFile", true);
    try
    {
      paramContext.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException paramUri)
    {
      a(2131493620, paramContext, 0, 1, true, 0);
    }
  }
  
  public static void a(Context paramContext, Uri paramUri, int paramInt1, long paramLong, int paramInt2, String paramString)
  {
    if (paramInt2 == 0) {}
    for (Intent localIntent = new Intent(paramContext, MeizuSlideshowActivity.class);; localIntent = new Intent(paramContext, SlideshowActivity.class))
    {
      localIntent.setData(paramUri);
      localIntent.putExtra("slideshow_subject", paramString);
      localIntent.setFlags(536870912);
      localIntent.putExtra("thread_id", paramLong);
      if (paramInt1 == 3) {
        localIntent.putExtra("view_slideshow_from_edit", true);
      }
      if ((paramInt1 <= 0) || (!(paramContext instanceof Activity))) {
        break;
      }
      ((Activity)paramContext).startActivityForResult(localIntent, paramInt1);
      return;
    }
    paramContext.startActivity(localIntent);
  }
  
  public static void a(Context paramContext, Uri paramUri, Handler paramHandler, wd.d paramd, boolean paramBoolean, int paramInt)
  {
    paramHandler.postDelayed(new wm(), 1000L);
    new Thread(new wn(paramContext, paramUri, paramHandler, paramd, paramBoolean, paramInt), "MessageUtils.resizeImageAsync").start();
  }
  
  public static final void a(Context paramContext, String paramString, boolean paramBoolean1, long paramLong, boolean paramBoolean2, boolean paramBoolean3)
  {
    a(paramContext, paramString, paramBoolean1, paramLong, paramBoolean2, paramBoolean3, false);
  }
  
  public static final void a(Context paramContext, String paramString, boolean paramBoolean1, long paramLong, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.putExtra("from_mms", true);
    Bundle localBundle = new Bundle();
    localBundle.putLong("contact_id", paramLong);
    if (MzTelephony.MmsExt.isSnsRecipient(paramString))
    {
      localBundle.putString("social_profile", paramString);
      localBundle.putString("android.intent.action.INSERT", "vnd.android.cursor.item/social_profile");
    }
    for (;;)
    {
      localIntent.setType("vnd.android.cursor.dir/data");
      localIntent.putExtras(localBundle);
      if (paramBoolean3)
      {
        localIntent.setData(a(paramLong, "" + paramLong, a(paramContext, "Net Contact", "com.meizu.netcontactservice")));
        paramString = gm.a(paramString, false);
        localIntent.putExtra("contact_id", -paramLong);
        if ((paramString != null) && (paramString.m() != null)) {
          localIntent.putExtra("photo_uri", paramString.m().toString());
        }
      }
      localIntent.putExtra("com.android.contacts.extra.TITLE_EXTRA", paramContext.getResources().getString(2131492888));
      localIntent.putExtra("com.android.contacts.extra.SUB_TITLE_EXTRA", paramContext.getResources().getString(2131493597));
      if (paramBoolean2) {
        localIntent.setFlags(524288);
      }
      if (paramBoolean4) {
        localIntent.addFlags(268435456);
      }
      try
      {
        paramContext.startActivity(localIntent);
        return;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
      if (aau.d(paramString))
      {
        localBundle.putString("email", paramString);
        localBundle.putString("android.intent.action.INSERT", "vnd.android.cursor.item/email_v2");
      }
      else
      {
        if (!h(paramString)) {
          break;
        }
        localBundle.putString("phone", paramString);
        localBundle.putString("android.intent.action.INSERT", "vnd.android.cursor.item/phone_v2");
      }
    }
    if (g(paramString)) {
      localBundle.putString("phone", paramString);
    }
    for (;;)
    {
      localBundle.putString("android.intent.action.INSERT", "vnd.android.cursor.item/phone_v2");
      break;
      localBundle.putString("name", paramString);
    }
  }
  
  private static void a(Context paramContext, li paramli, long paramLong)
  {
    if (paramli == null) {
      return;
    }
    if (paramli.l() == null) {}
    try
    {
      paramli.a(paramContext, paramli.k());
      if (paramli.l() == null)
      {
        a(2131493620, paramContext, 0, 1, true, 0);
        return;
      }
    }
    catch (MmsException paramli)
    {
      a(2131493620, paramContext, 0, 1, true, 0);
      return;
    }
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.addFlags(1);
    localIntent.addFlags(268435456);
    localIntent.addFlags(524288);
    String str2 = paramli.j();
    String str1 = str2;
    if (!TextUtils.isEmpty(str2))
    {
      str1 = str2;
      if (str2.endsWith("-file"))
      {
        str1 = str2.substring(0, str2.lastIndexOf("-file"));
        Log.d("Mms", "the flyme mms contentType is: " + str1 + "--path = " + paramli.l());
      }
    }
    localIntent.setDataAndType(lr.a(paramli.l()), str1);
    paramli = paramli.l();
    if (TextUtils.isEmpty(str1))
    {
      a(paramContext, localIntent, paramli);
      return;
    }
    try
    {
      paramContext.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      Log.d("Mms", "viewFlymeFile(),  ActivityNotFoundException, contentType is " + str1);
      a(paramContext, localIntent, paramli);
    }
  }
  
  public static void a(Context paramContext, lm paramlm, long paramLong)
  {
    if (c(paramContext.getContentResolver())) {
      m(paramContext);
    }
    while ((paramlm == null) || ((paramlm instanceof lu))) {
      return;
    }
    if (!c(paramContext, paramlm.k()))
    {
      a(2131493297, paramContext, 0, 1, true, 0);
      return;
    }
    int i1;
    if ((paramlm instanceof lk)) {
      i1 = 2;
    }
    for (;;)
    {
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.addFlags(1);
      String str = paramlm.j();
      if ((i1 == 6) && (!TextUtils.isEmpty(str)) && (str.endsWith("-file")))
      {
        str = str.substring(0, str.lastIndexOf("-file"));
        Log.d("Mms", "the flyme mms contentType is: " + str);
      }
      for (;;)
      {
        if (i1 == 2)
        {
          localIntent.putExtra("mms_video_title", a(paramlm.n(), str, "jpg"));
          if (paramLong <= 0L)
          {
            localIntent.putExtra("SingleItemOnly", true);
            label196:
            localIntent.setClassName("com.meizu.media.gallery", "com.meizu.media.gallery.GalleryActivity");
          }
        }
        for (;;)
        {
          localIntent.setDataAndType(paramlm.k(), str);
          localIntent.putExtra("com.android.Mms.SaveFile", true);
          paramlm = paramlm.l();
          try
          {
            paramContext.startActivity(localIntent);
            return;
          }
          catch (ActivityNotFoundException localActivityNotFoundException)
          {
            Log.d("Mms", "viewMediaModel(),  ActivityNotFoundException, contentType is " + str);
            a(paramContext, localIntent, paramlm);
            return;
          }
          if ((paramlm instanceof le))
          {
            i1 = 3;
            break;
          }
          if ((paramlm instanceof lw))
          {
            i1 = 4;
            break;
          }
          if ((paramlm instanceof lv))
          {
            i1 = 5;
            break;
          }
          if (!(paramlm instanceof li)) {
            break label502;
          }
          a(paramContext, (li)paramlm, paramLong);
          return;
          if (MmsApp.a) {
            localIntent.putExtra("SingleItemOnly", true);
          }
          localIntent.putExtra("thread_uri", b(paramLong));
          break label196;
          if (i1 == 4)
          {
            localIntent.putExtra("video_title", a(paramlm.n(), str, "mpeg"));
            localIntent.putExtra("meizu_video_record", true);
            localIntent.putExtra("playSource", 8);
            localIntent.setClassName("com.meizu.media.video", "com.meizu.media.video.player.ui.VideoWindowActivity");
          }
          else if (i1 == 3)
          {
            localIntent.putExtra("audio_title", a(paramlm.n(), str, "mp3"));
            localIntent.setClassName("com.meizu.media.music", "com.meizu.media.music.MusicActivity");
          }
          else
          {
            localIntent.putExtra("mms_video_title", paramlm.n());
            str = a(paramlm.n(), str);
          }
        }
      }
      label502:
      i1 = 0;
    }
  }
  
  public static void a(Context paramContext, lr paramlr, long paramLong)
  {
    if (!paramlr.g()) {
      throw new IllegalArgumentException("viewSimpleSlideshow() called on a non-simple slideshow");
    }
    lq locallq = paramlr.a(0);
    paramlr = null;
    if (locallq.e()) {
      paramlr = locallq.q();
    }
    for (;;)
    {
      a(paramContext, paramlr, paramLong);
      return;
      if (locallq.g()) {
        paramlr = locallq.s();
      } else if (locallq.i()) {
        paramlr = locallq.t();
      } else if (locallq.j()) {
        paramlr = locallq.u();
      }
    }
  }
  
  public static void a(View paramView)
  {
    paramView.setBackground(new aqt(paramView));
  }
  
  public static void a(View paramView1, View paramView2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramView2.post(new wr(paramView1, paramInt1, paramInt2, paramInt3, paramInt4, paramView2));
  }
  
  public static void a(View paramView, boolean paramBoolean)
  {
    if (paramView == null)
    {
      Log.i("Mms", "button is null");
      return;
    }
    if ((paramBoolean) && (zv.e > 0)) {}
    for (paramBoolean = true;; paramBoolean = false)
    {
      paramView.setEnabled(paramBoolean);
      return;
    }
  }
  
  public static void a(TextView paramTextView, long paramLong, Context paramContext)
  {
    paramTextView.setText(a(paramLong, paramContext));
  }
  
  public static void a(CharSequence paramCharSequence, Context paramContext, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3)
  {
    a(paramCharSequence, paramContext, paramInt1, paramInt2, paramBoolean, paramInt3, 0);
  }
  
  public static void a(CharSequence paramCharSequence, Context paramContext, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3, int paramInt4)
  {
    switch (paramInt2)
    {
    default: 
      return;
    case 0: 
      Toast.makeText(paramContext, paramCharSequence, paramInt1).show();
      return;
    }
    j(paramContext, paramCharSequence.toString());
  }
  
  public static void a(String paramString, int paramInt)
  {
    Log.i("MessageUtils", "saveMmsDraftText slotId = " + paramInt);
    if ((paramString == null) || (paramString.trim().isEmpty()))
    {
      k = "";
      zv.d();
      return;
    }
    k = paramString;
    zv.b(paramInt);
  }
  
  public static final void a(String paramString, ImageView paramImageView, boolean paramBoolean)
  {
    int i1 = 2130838213;
    if ((TextUtils.isEmpty(paramString)) || (paramString.indexOf(".") == -1))
    {
      if (paramBoolean) {}
      for (i1 = 2130838213;; i1 = 2130838309)
      {
        paramImageView.setImageResource(i1);
        return;
      }
    }
    paramString = j(paramString).toLowerCase();
    if (TextUtils.isEmpty(paramString))
    {
      if (paramBoolean) {}
      for (;;)
      {
        paramImageView.setImageResource(i1);
        return;
        i1 = 2130838309;
      }
    }
    paramString = (Integer[])wd.c.a().get(paramString);
    if ((paramString == null) || (paramString.length < 2))
    {
      if (paramBoolean) {}
      for (;;)
      {
        paramImageView.setImageResource(i1);
        return;
        i1 = 2130838309;
      }
    }
    if (paramBoolean)
    {
      paramImageView.setImageResource(paramString[0].intValue());
      return;
    }
    paramImageView.setImageResource(paramString[1].intValue());
  }
  
  public static void a(String paramString1, String paramString2, long paramLong)
  {
    String str = paramString1;
    if (paramString1 == null) {
      str = "Mms";
    }
    long l1 = System.currentTimeMillis();
    Log.d(str, paramString2 + " cost time :" + (l1 - paramLong));
  }
  
  public static final void a(String paramString1, String paramString2, ImageView paramImageView, boolean paramBoolean)
  {
    int i1 = 2130838213;
    if ((!TextUtils.isEmpty(paramString2)) && (a(paramString2, paramBoolean, paramImageView))) {
      return;
    }
    if ((TextUtils.isEmpty(paramString1)) || (paramString1.indexOf(".") == -1))
    {
      if (paramBoolean) {}
      for (i1 = 2130838213;; i1 = 2130838309)
      {
        paramImageView.setImageResource(i1);
        return;
      }
    }
    paramString1 = j(paramString1).toLowerCase();
    if (TextUtils.isEmpty(paramString1))
    {
      if (paramBoolean) {}
      for (;;)
      {
        paramImageView.setImageResource(i1);
        return;
        i1 = 2130838309;
      }
    }
    paramString1 = (Integer[])wd.c.a().get(paramString1);
    if ((paramString1 == null) || (paramString1.length < 2))
    {
      if (paramBoolean) {}
      for (;;)
      {
        paramImageView.setImageResource(i1);
        return;
        i1 = 2130838309;
      }
    }
    if (paramBoolean)
    {
      paramImageView.setImageResource(paramString1[0].intValue());
      return;
    }
    paramImageView.setImageResource(paramString1[1].intValue());
  }
  
  public static void a(vv paramvv, Context paramContext, int paramInt)
  {
    Log.i("MessageUtils", "copyToSim slotId = " + paramInt + ", msgItem.slotId = " + T);
    paramInt = a(paramContext, paramvv, paramInt);
    if (paramInt == 0)
    {
      if (MmsApp.a) {}
      for (paramInt = 2131493279;; paramInt = 2131493705)
      {
        a(paramInt, paramContext, 0, 0, true, 1, aaa.b());
        return;
      }
    }
    if (paramInt == 7)
    {
      if (MmsApp.a) {}
      for (paramInt = 2131493118;; paramInt = 2131493812)
      {
        a(paramInt, paramContext, 0, 0, true, 0, aaa.b());
        return;
      }
    }
    if (MmsApp.a) {}
    for (paramInt = 2131493278;; paramInt = 2131493704)
    {
      a(paramInt, paramContext, 0, 0, true, 0, aaa.b());
      return;
    }
  }
  
  public static void a(boolean paramBoolean)
  {
    m = paramBoolean;
  }
  
  public static boolean a(int paramInt)
  {
    return (paramInt == 9527) || (paramInt == 132) || (paramInt == 135) || (paramInt == 130);
  }
  
  public static boolean a(ContentResolver paramContentResolver)
  {
    return (b(paramContentResolver)) && (!MmsApp.c().u());
  }
  
  public static boolean a(Context paramContext)
  {
    int i1 = ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkType();
    return (i1 == 0) || (i1 == 1) || (i1 == 2) || (i1 == 4) || (i1 == 11);
  }
  
  public static boolean a(Context paramContext, int paramInt1, int paramInt2)
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
    boolean bool = MmsApp.t();
    paramContext = new ServiceState();
    Log.d("Mms", "isNetworkisAvailable : isPhoneIdle = " + bool + ", slotId = " + paramInt2);
    Object localObject = aau.a("android.os.ServiceManager", "getService", String.class, "phone_ext", "android.telephony.ITelephonyExt$Stub", "asInterface", IBinder.class, "getServiceStateBySlot", Integer.TYPE, Integer.valueOf(paramInt2));
    if ((localObject != null) && ((localObject instanceof ServiceState))) {
      paramContext = (ServiceState)localObject;
    }
    for (;;)
    {
      paramInt2 = ((Integer)aau.b(ServiceState.class, paramContext, "getRilVoiceRadioTechnology")).intValue();
      if ((!bool) && (paramInt2 < 3)) {
        return false;
      }
      return localConnectivityManager.getNetworkInfo(paramInt1).isAvailable();
    }
  }
  
  public static final boolean a(Uri paramUri)
  {
    if (paramUri == null) {}
    while (!"sms_spam".equals(paramUri.getAuthority())) {
      return false;
    }
    return true;
  }
  
  public static boolean a(ConversationList paramConversationList)
  {
    PermissionDialogBuilder localPermissionDialogBuilder = new PermissionDialogBuilder(paramConversationList);
    localPermissionDialogBuilder.setMessage(paramConversationList.getString(2131492888), new String[] { "android.permission.INTERNET" });
    localPermissionDialogBuilder.setOnPermissonListener(new wg(paramConversationList));
    localPermissionDialogBuilder.create().show();
    return true;
  }
  
  public static boolean a(CharSequence paramCharSequence)
  {
    if (!(paramCharSequence instanceof Spannable)) {
      return false;
    }
    int i1 = BaseInputConnection.getComposingSpanStart((Spannable)paramCharSequence);
    int i2 = BaseInputConnection.getComposingSpanEnd((Spannable)paramCharSequence);
    if ((i1 < 0) || (i2 < 0)) {
      return false;
    }
    paramCharSequence = ((Spanned)paramCharSequence).getSpans(i1, i2, aau.c("android.text.style.ParcelableImageSpan"));
    return (paramCharSequence != null) && (paramCharSequence.length != 0);
  }
  
  public static boolean a(String paramString, Context paramContext)
  {
    boolean bool = false;
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      if (paramContext != null) {
        bool = true;
      }
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      Log.i("MessageUtils", "isMapInstall packageName = " + paramString + " is not installed!");
    }
    return false;
  }
  
  private static boolean a(String paramString, boolean paramBoolean, ImageView paramImageView)
  {
    int i1;
    if (paramString.toLowerCase().startsWith("video/"))
    {
      if (paramBoolean) {}
      for (i1 = 2130838190;; i1 = 2130838230)
      {
        paramImageView.setImageResource(i1);
        return true;
      }
    }
    if (paramString.toLowerCase().startsWith("image/"))
    {
      if (paramBoolean) {}
      for (i1 = 2130838200;; i1 = 2130838233)
      {
        paramImageView.setImageResource(i1);
        return true;
      }
    }
    if (paramString.toLowerCase().startsWith("audio/"))
    {
      if (paramBoolean) {}
      for (i1 = 2130838193;; i1 = 2130838231)
      {
        paramImageView.setImageResource(i1);
        return true;
      }
    }
    return false;
  }
  
  public static boolean a(String[] paramArrayOfString, Context paramContext)
  {
    boolean bool2 = false;
    int i2 = Math.min(2, paramArrayOfString.length);
    int i1 = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i1 < i2)
      {
        if ((!TextUtils.isEmpty(paramArrayOfString[i1])) && (MzPhoneNumberUtils.isNotificationNumber(paramContext, paramArrayOfString[i1])))
        {
          Log.i("MessageUtils", "hasNotificationNumber " + paramArrayOfString[i1] + " is notification number.");
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i1 += 1;
    }
  }
  
  public static long b(lr paramlr)
  {
    long l2 = ga.a(true) - 1408 - 102;
    long l1 = l2;
    if (paramlr != null) {
      l1 = l2 - paramlr.d();
    }
    Log.i("MessageUtils", "computeAttachmentSizeLimitForAppen sizeLimit = " + l1);
    if (l1 > 0L) {
      return l1;
    }
    return 0L;
  }
  
  public static final SpannableStringBuilder b(Context paramContext, int paramInt1, int paramInt2)
  {
    String str = String.valueOf(paramInt1);
    SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder();
    localSpannableStringBuilder.append(paramContext.getString(paramInt2)).append("  ").append(str);
    localSpannableStringBuilder.setSpan(new ForegroundColorSpan(paramContext.getResources().getColor(2131820738)), localSpannableStringBuilder.length() - str.length(), localSpannableStringBuilder.length(), 33);
    return localSpannableStringBuilder;
  }
  
  public static String b()
  {
    if (l != null) {
      return l;
    }
    return "";
  }
  
  public static String b(long paramLong)
  {
    if (paramLong <= 0L) {
      return null;
    }
    return "content://mms-sms/conversations/" + paramLong + "/pic";
  }
  
  public static final String b(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1)) {
      return paramString2;
    }
    paramString2 = paramString1.substring(paramString1.lastIndexOf('/') + 1);
    paramString1 = paramString2;
    if (paramString2.startsWith("."))
    {
      paramString1 = paramString2;
      if (paramString2.length() > 1) {
        paramString1 = paramString2.replaceFirst(".", "_");
      }
    }
    return paramString1.replace(' ', '_');
  }
  
  public static void b(Activity paramActivity)
  {
    Intent localIntent = new Intent("com.meizu.c2dm.service.ShowMmsPreference");
    localIntent.putExtra("source", "com.android.mms");
    try
    {
      paramActivity.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException paramActivity)
    {
      Log.e("Mms", "goFlymeManageActivity. happen ActivityNotFoundException", paramActivity);
    }
  }
  
  public static void b(Activity paramActivity, int paramInt)
  {
    Intent localIntent = new Intent("android.media.action.IMAGE_CAPTURE");
    localIntent.putExtra("output", TempFileProvider.b);
    paramActivity.startActivityForResult(localIntent, paramInt);
  }
  
  public static void b(Activity paramActivity, int paramInt, long paramLong)
  {
    paramLong = ((float)paramLong * 0.85F);
    int i1 = a(paramLong);
    if (Log.isLoggable("Mms:app", 2)) {
      p("recordVideo: durationLimit: " + i1 + " sizeLimit: " + paramLong);
    }
    Intent localIntent = new Intent("android.media.action.VIDEO_CAPTURE");
    localIntent.putExtra("android.intent.extra.videoQuality", 0);
    localIntent.putExtra("android.intent.extra.sizeLimit", paramLong);
    localIntent.putExtra("android.intent.extra.durationLimit", i1);
    localIntent.putExtra("output", TempFileProvider.c);
    paramActivity.startActivityForResult(localIntent, paramInt);
  }
  
  public static void b(Context paramContext, int paramInt)
  {
    a(paramContext, paramInt, "image/*", "com.meizu.media.gallery", "com.meizu.media.gallery.AlbumManagerActivity", 2131493464);
  }
  
  public static void b(Context paramContext, Uri paramUri)
  {
    if (paramUri == null) {
      return;
    }
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setClassName("com.meizu.media.video", "com.meizu.media.video.player.ui.VideoWindowActivity");
    localIntent.addFlags(1);
    localIntent.addFlags(524288);
    localIntent.putExtra("video_title", a(paramUri.getLastPathSegment(), "video/mp4", "mpeg"));
    localIntent.putExtra("meizu_video_record", true);
    localIntent.setDataAndType(paramUri, "video/mp4");
    localIntent.putExtra("com.android.Mms.SaveFile", true);
    try
    {
      paramContext.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException paramUri)
    {
      a(2131493620, paramContext, 0, 1, true, 0);
    }
  }
  
  public static void b(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return;
    }
    for (;;)
    {
      Intent localIntent2;
      try
      {
        Intent localIntent1 = new Intent("android.intent.action.VIEW");
        localIntent1.addFlags(524288);
        localIntent1.setData(Uri.parse(paramString));
        if ((kn.a(paramString)) && (a("com.baidu.BaiduMap", paramContext)))
        {
          localIntent1.setPackage("com.baidu.BaiduMap");
          paramContext.startActivity(localIntent1);
          return;
        }
      }
      catch (ActivityNotFoundException localActivityNotFoundException)
      {
        localIntent2 = new Intent("android.intent.action.VIEW");
        localIntent2.addFlags(524288);
        localIntent2.setData(Uri.parse(paramString));
        paramContext.startActivity(localIntent2);
        return;
      }
      if ((kn.b(paramString)) && (a("com.google.android.apps.maps", paramContext)))
      {
        localIntent2.setPackage("com.google.android.apps.maps");
      }
      else if ((kn.c(paramString)) && (a("com.autonavi.minimap", paramContext)))
      {
        localIntent2.setPackage("com.autonavi.minimap");
        localIntent2.setData(l(paramString));
      }
    }
  }
  
  public static void b(ConversationList paramConversationList)
  {
    if (!u)
    {
      u = true;
      new Thread(new wh()).start();
    }
    c(paramConversationList);
  }
  
  public static void b(boolean paramBoolean)
  {
    n = paramBoolean;
  }
  
  public static boolean b(int paramInt)
  {
    return (paramInt == 9527) || (paramInt == 132) || (paramInt == 135) || (paramInt == 130);
  }
  
  public static boolean b(ContentResolver paramContentResolver)
  {
    return (Settings.Secure.getInt(paramContentResolver, (String)aau.a("android.provider.MzSettings$Secure", "MZ_DRIVE_MODE"), 0) == 1) && (Settings.Secure.getInt(paramContentResolver, (String)aau.a("android.provider.MzSettings$Secure", "MZ_DRIVE_MODE_INMSG_VOICE"), 0) == 1);
  }
  
  public static boolean b(Context paramContext)
  {
    return d(paramContext, 2);
  }
  
  public static final boolean b(Context paramContext, long paramLong)
  {
    boolean bool2 = d(paramContext);
    boolean bool1 = bool2;
    if (!bool2)
    {
      bool1 = bool2;
      if (e(paramContext))
      {
        if ((paramLong >= 2097152L) || (!zn.a().a(-1))) {
          break label46;
        }
        bool1 = true;
      }
    }
    return bool1;
    label46:
    return false;
  }
  
  public static boolean b(String paramString)
  {
    if (!ga.w()) {}
    label78:
    for (;;)
    {
      return false;
      if (paramString == null) {}
      for (int i1 = 0;; i1 = paramString.length())
      {
        if ((i1 < ga.x()) || (i1 > ga.y()) || (!Character.isLetter(paramString.charAt(0)))) {
          break label78;
        }
        int i2 = 1;
        for (;;)
        {
          if (i2 >= i1) {
            break label80;
          }
          char c1 = paramString.charAt(i2);
          if ((!Character.isLetterOrDigit(c1)) && (c1 != '.')) {
            break;
          }
          i2 += 1;
        }
      }
    }
    label80:
    return true;
  }
  
  public static String c()
  {
    return k;
  }
  
  public static String c(Context paramContext, long paramLong)
  {
    if (paramLong <= 1048576L) {
      return paramContext.getString(2131493927, new Object[] { Long.valueOf((1023L + paramLong) / 1024L) });
    }
    return a(paramLong + 1023L, 1048576L, paramContext.getResources().getString(2131493367), null);
  }
  
  public static String c(String paramString)
  {
    if (aau.d(paramString)) {}
    do
    {
      do
      {
        return paramString;
      } while (MzTelephony.MmsExt.isSnsRecipient(paramString));
      String str = o(paramString);
      if (str != null) {
        return str;
      }
    } while (b(paramString));
    return null;
  }
  
  public static void c(Context paramContext, int paramInt1, int paramInt2)
  {
    Object localObject = new Intent(paramContext, ConversationList.class);
    ((Intent)localObject).setAction("android.intent.action.VIEW");
    ((Intent)localObject).setFlags(872415232);
    localObject = PendingIntent.getActivity(paramContext, 0, (Intent)localObject, 0);
    NotificationManager localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
    Notification localNotification = new Notification();
    icon = 2130838482;
    largeIcon = BitmapFactory.decodeResource(paramContext.getResources(), 2130837944);
    defaults = -1;
    tickerText = paramContext.getString(paramInt1);
    Log.i("MessageUtils", "notifyNotificationForOldToast notification.tickerText = " + tickerText);
    flags |= 0x10;
    localNotification.setLatestEventInfo(paramContext, paramContext.getString(paramInt1), null, (PendingIntent)localObject);
    localNotificationManager.notify(240, localNotification);
  }
  
  public static void c(ConversationList paramConversationList)
  {
    try
    {
      akp.a(paramConversationList, new wj(paramConversationList));
      return;
    }
    catch (Exception paramConversationList)
    {
      Log.e("MzUpdateComponent", paramConversationList.toString());
    }
  }
  
  public static final boolean c(long paramLong)
  {
    return h() > paramLong;
  }
  
  public static boolean c(ContentResolver paramContentResolver)
  {
    boolean bool = false;
    if (Settings.Secure.getInt(paramContentResolver, "mz_current_power_mode", 0) == 3) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean c(Context paramContext)
  {
    return d(paramContext, 1);
  }
  
  public static boolean c(Context paramContext, int paramInt)
  {
    return a(paramContext, 2, paramInt);
  }
  
  /* Error */
  public static final boolean c(Context paramContext, Uri paramUri)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +5 -> 6
    //   4: iconst_0
    //   5: ireturn
    //   6: aload_0
    //   7: invokevirtual 321	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   10: aload_1
    //   11: invokevirtual 1653	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
    //   14: astore_0
    //   15: aload_0
    //   16: ifnull +7 -> 23
    //   19: aload_0
    //   20: invokevirtual 1656	java/io/InputStream:close	()V
    //   23: iconst_1
    //   24: ireturn
    //   25: astore_0
    //   26: aload_0
    //   27: invokevirtual 1657	java/io/IOException:printStackTrace	()V
    //   30: goto -7 -> 23
    //   33: astore_0
    //   34: iconst_0
    //   35: ifeq -31 -> 4
    //   38: new 1659	java/lang/NullPointerException
    //   41: dup
    //   42: invokespecial 1660	java/lang/NullPointerException:<init>	()V
    //   45: athrow
    //   46: astore_0
    //   47: aload_0
    //   48: invokevirtual 1657	java/io/IOException:printStackTrace	()V
    //   51: iconst_0
    //   52: ireturn
    //   53: astore_0
    //   54: iconst_0
    //   55: ifeq +11 -> 66
    //   58: new 1659	java/lang/NullPointerException
    //   61: dup
    //   62: invokespecial 1660	java/lang/NullPointerException:<init>	()V
    //   65: athrow
    //   66: aload_0
    //   67: athrow
    //   68: astore_1
    //   69: aload_1
    //   70: invokevirtual 1657	java/io/IOException:printStackTrace	()V
    //   73: goto -7 -> 66
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	76	0	paramContext	Context
    //   0	76	1	paramUri	Uri
    // Exception table:
    //   from	to	target	type
    //   19	23	25	java/io/IOException
    //   6	15	33	java/io/FileNotFoundException
    //   38	46	46	java/io/IOException
    //   6	15	53	finally
    //   58	66	68	java/io/IOException
  }
  
  public static final boolean c(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      Log.i("MessageUtils", "isFileExsite fileLink is empty or null");
    }
    for (;;)
    {
      return false;
      if (!paramString.startsWith("content://")) {
        break label129;
      }
      try
      {
        paramContext = paramContext.getContentResolver().openInputStream(Uri.parse(paramString));
        if (paramContext != null) {}
        try
        {
          paramContext.close();
          return true;
        }
        catch (IOException paramContext)
        {
          for (;;)
          {
            paramContext.printStackTrace();
          }
        }
      }
      catch (FileNotFoundException paramContext)
      {
        Log.i("MessageUtils", "isFileExsite FileNotFoundException  fileLink = " + paramString);
        if (0 != 0) {
          try
          {
            throw new NullPointerException();
          }
          catch (IOException paramContext)
          {
            paramContext.printStackTrace();
            return false;
          }
        }
      }
      finally
      {
        if (0 == 0) {}
      }
    }
    try
    {
      throw new NullPointerException();
      throw paramContext;
    }
    catch (IOException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
    label129:
    paramContext = paramString;
    if (paramString.startsWith("file://")) {
      paramContext = paramString.substring(7);
    }
    return new File(paramContext).exists();
  }
  
  public static final boolean c(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1)) {
      return false;
    }
    paramString1 = TextUtils.split(paramString1, paramString2);
    int i1 = 0;
    boolean bool = false;
    for (;;)
    {
      if (i1 >= paramString1.length) {
        return bool;
      }
      if (!paramString1[i1].startsWith("@")) {
        break;
      }
      bool = true;
      i1 += 1;
    }
    return bool;
  }
  
  /* Error */
  public static String d(Context paramContext, Uri paramUri)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 1682	android/net/Uri:getScheme	()Ljava/lang/String;
    //   4: astore_2
    //   5: aload_2
    //   6: ifnull +12 -> 18
    //   9: aload_2
    //   10: ldc 100
    //   12: invokevirtual 609	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   15: ifeq +22 -> 37
    //   18: aload_1
    //   19: invokevirtual 1685	android/net/Uri:getPath	()Ljava/lang/String;
    //   22: astore_2
    //   23: iconst_0
    //   24: ifeq +11 -> 35
    //   27: new 1659	java/lang/NullPointerException
    //   30: dup
    //   31: invokespecial 1660	java/lang/NullPointerException:<init>	()V
    //   34: athrow
    //   35: aload_2
    //   36: areturn
    //   37: aload_2
    //   38: ldc_w 1687
    //   41: invokevirtual 609	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   44: ifeq +398 -> 442
    //   47: aload_0
    //   48: invokevirtual 321	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   51: aload_1
    //   52: aconst_null
    //   53: aconst_null
    //   54: aconst_null
    //   55: aconst_null
    //   56: invokevirtual 335	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   59: astore_0
    //   60: aload_0
    //   61: ifnonnull +66 -> 127
    //   64: aload_0
    //   65: astore_3
    //   66: aload_0
    //   67: astore_2
    //   68: new 1095	java/lang/IllegalArgumentException
    //   71: dup
    //   72: new 210	java/lang/StringBuilder
    //   75: dup
    //   76: invokespecial 211	java/lang/StringBuilder:<init>	()V
    //   79: ldc_w 1689
    //   82: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   85: aload_1
    //   86: invokevirtual 1614	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   89: ldc_w 1691
    //   92: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   95: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   98: invokespecial 1098	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   101: athrow
    //   102: astore_0
    //   103: aload_3
    //   104: astore_2
    //   105: ldc -25
    //   107: ldc_w 1693
    //   110: aload_0
    //   111: invokestatic 817	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   114: pop
    //   115: aload_3
    //   116: ifnull +9 -> 125
    //   119: aload_3
    //   120: invokeinterface 362 1 0
    //   125: aconst_null
    //   126: areturn
    //   127: aload_0
    //   128: astore_3
    //   129: aload_0
    //   130: astore_2
    //   131: aload_0
    //   132: invokeinterface 349 1 0
    //   137: iconst_1
    //   138: if_icmpne +16 -> 154
    //   141: aload_0
    //   142: astore_3
    //   143: aload_0
    //   144: astore_2
    //   145: aload_0
    //   146: invokeinterface 343 1 0
    //   151: ifne +54 -> 205
    //   154: aload_0
    //   155: astore_3
    //   156: aload_0
    //   157: astore_2
    //   158: new 1095	java/lang/IllegalArgumentException
    //   161: dup
    //   162: new 210	java/lang/StringBuilder
    //   165: dup
    //   166: invokespecial 211	java/lang/StringBuilder:<init>	()V
    //   169: ldc_w 1689
    //   172: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   175: aload_1
    //   176: invokevirtual 1614	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   179: ldc_w 1695
    //   182: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   185: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   188: invokespecial 1098	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   191: athrow
    //   192: astore_0
    //   193: aload_2
    //   194: ifnull +9 -> 203
    //   197: aload_2
    //   198: invokeinterface 362 1 0
    //   203: aload_0
    //   204: athrow
    //   205: aload_0
    //   206: astore_3
    //   207: aload_0
    //   208: astore_2
    //   209: aload_1
    //   210: invokevirtual 1290	android/net/Uri:getAuthority	()Ljava/lang/String;
    //   213: ldc_w 1697
    //   216: invokevirtual 1361	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   219: ifeq +177 -> 396
    //   222: aload_0
    //   223: astore_3
    //   224: aload_0
    //   225: astore_2
    //   226: aload_0
    //   227: aload_0
    //   228: ldc_w 1699
    //   231: invokeinterface 1702 2 0
    //   236: invokeinterface 531 2 0
    //   241: astore_1
    //   242: aload_0
    //   243: astore_3
    //   244: aload_0
    //   245: astore_2
    //   246: aload_0
    //   247: aload_0
    //   248: ldc_w 1704
    //   251: invokeinterface 1702 2 0
    //   256: invokeinterface 531 2 0
    //   261: astore 4
    //   263: aload_0
    //   264: astore_3
    //   265: aload_0
    //   266: astore_2
    //   267: new 210	java/lang/StringBuilder
    //   270: dup
    //   271: invokespecial 211	java/lang/StringBuilder:<init>	()V
    //   274: ldc_w 1706
    //   277: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   280: invokestatic 598	java/lang/System:currentTimeMillis	()J
    //   283: invokevirtual 240	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   286: ldc_w 1708
    //   289: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   292: aload 4
    //   294: aload 4
    //   296: ldc_w 580
    //   299: invokevirtual 584	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   302: invokevirtual 611	java/lang/String:substring	(I)Ljava/lang/String;
    //   305: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   308: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   311: astore 5
    //   313: aload_1
    //   314: astore 4
    //   316: aload 5
    //   318: astore_1
    //   319: aload_0
    //   320: astore_3
    //   321: aload_0
    //   322: astore_2
    //   323: invokestatic 1710	wd:i	()V
    //   326: aload_0
    //   327: astore_3
    //   328: aload_0
    //   329: astore_2
    //   330: new 847	java/io/File
    //   333: dup
    //   334: aload_1
    //   335: invokespecial 848	java/io/File:<init>	(Ljava/lang/String;)V
    //   338: astore 5
    //   340: aload_0
    //   341: astore_3
    //   342: aload_0
    //   343: astore_2
    //   344: aload 5
    //   346: invokevirtual 1671	java/io/File:exists	()Z
    //   349: ifne +305 -> 654
    //   352: aload_0
    //   353: astore_3
    //   354: aload_0
    //   355: astore_2
    //   356: aload 5
    //   358: invokevirtual 1713	java/io/File:createNewFile	()Z
    //   361: ifne +269 -> 630
    //   364: aload_0
    //   365: astore_3
    //   366: aload_0
    //   367: astore_2
    //   368: new 1715	java/lang/IllegalStateException
    //   371: dup
    //   372: new 210	java/lang/StringBuilder
    //   375: dup
    //   376: invokespecial 211	java/lang/StringBuilder:<init>	()V
    //   379: ldc_w 1717
    //   382: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   385: aload_1
    //   386: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   389: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   392: invokespecial 1718	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   395: athrow
    //   396: aload_0
    //   397: astore_3
    //   398: aload_0
    //   399: astore_2
    //   400: aload_0
    //   401: aload_0
    //   402: ldc_w 1699
    //   405: invokeinterface 1702 2 0
    //   410: invokeinterface 531 2 0
    //   415: astore_1
    //   416: aload_0
    //   417: astore_3
    //   418: aload_0
    //   419: astore_2
    //   420: aload_1
    //   421: aload_1
    //   422: ldc_w 1720
    //   425: invokevirtual 584	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   428: iconst_1
    //   429: iadd
    //   430: aload_1
    //   431: invokevirtual 144	java/lang/String:length	()I
    //   434: invokevirtual 588	java/lang/String:substring	(II)Ljava/lang/String;
    //   437: astore 4
    //   439: goto -176 -> 263
    //   442: aload_2
    //   443: ldc_w 1722
    //   446: invokevirtual 609	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   449: ifeq +259 -> 708
    //   452: aload_1
    //   453: invokevirtual 983	android/net/Uri:toString	()Ljava/lang/String;
    //   456: ldc_w 1724
    //   459: invokevirtual 1727	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   462: ifeq +29 -> 491
    //   465: ldc -25
    //   467: ldc_w 1729
    //   470: invokestatic 235	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   473: pop
    //   474: aload_1
    //   475: invokevirtual 1685	android/net/Uri:getPath	()Ljava/lang/String;
    //   478: astore_2
    //   479: iconst_0
    //   480: ifeq -445 -> 35
    //   483: new 1659	java/lang/NullPointerException
    //   486: dup
    //   487: invokespecial 1660	java/lang/NullPointerException:<init>	()V
    //   490: athrow
    //   491: ldc -25
    //   493: ldc_w 1731
    //   496: invokestatic 235	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   499: pop
    //   500: aload_1
    //   501: invokevirtual 983	android/net/Uri:toString	()Ljava/lang/String;
    //   504: astore_0
    //   505: aload_0
    //   506: aload_0
    //   507: ldc_w 1720
    //   510: invokevirtual 584	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   513: iconst_1
    //   514: iadd
    //   515: aload_0
    //   516: invokevirtual 144	java/lang/String:length	()I
    //   519: invokevirtual 588	java/lang/String:substring	(II)Ljava/lang/String;
    //   522: astore_0
    //   523: ldc -25
    //   525: new 210	java/lang/StringBuilder
    //   528: dup
    //   529: invokespecial 211	java/lang/StringBuilder:<init>	()V
    //   532: ldc_w 1733
    //   535: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   538: aload_0
    //   539: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   542: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   545: invokestatic 235	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   548: pop
    //   549: aload_1
    //   550: invokevirtual 1685	android/net/Uri:getPath	()Ljava/lang/String;
    //   553: astore 4
    //   555: ldc -25
    //   557: new 210	java/lang/StringBuilder
    //   560: dup
    //   561: invokespecial 211	java/lang/StringBuilder:<init>	()V
    //   564: ldc_w 1735
    //   567: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   570: aload 4
    //   572: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   575: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   578: invokestatic 235	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   581: pop
    //   582: new 210	java/lang/StringBuilder
    //   585: dup
    //   586: invokespecial 211	java/lang/StringBuilder:<init>	()V
    //   589: ldc_w 1706
    //   592: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   595: invokestatic 598	java/lang/System:currentTimeMillis	()J
    //   598: invokevirtual 240	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   601: ldc_w 1708
    //   604: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   607: aload_0
    //   608: aload_0
    //   609: ldc_w 580
    //   612: invokevirtual 584	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   615: invokevirtual 611	java/lang/String:substring	(I)Ljava/lang/String;
    //   618: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   621: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   624: astore_1
    //   625: aconst_null
    //   626: astore_0
    //   627: goto -308 -> 319
    //   630: aload_0
    //   631: astore_3
    //   632: aload_0
    //   633: astore_2
    //   634: aload 4
    //   636: aload_1
    //   637: invokestatic 1737	wd:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   640: aload_1
    //   641: astore_2
    //   642: aload_0
    //   643: ifnull -608 -> 35
    //   646: aload_0
    //   647: invokeinterface 362 1 0
    //   652: aload_1
    //   653: areturn
    //   654: aload_0
    //   655: astore_3
    //   656: aload_0
    //   657: astore_2
    //   658: ldc -25
    //   660: new 210	java/lang/StringBuilder
    //   663: dup
    //   664: invokespecial 211	java/lang/StringBuilder:<init>	()V
    //   667: ldc_w 1739
    //   670: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   673: aload_1
    //   674: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   677: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   680: invokestatic 235	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   683: pop
    //   684: aload_0
    //   685: ifnull +9 -> 694
    //   688: aload_0
    //   689: invokeinterface 362 1 0
    //   694: aconst_null
    //   695: areturn
    //   696: astore_0
    //   697: aconst_null
    //   698: astore_2
    //   699: goto -506 -> 193
    //   702: astore_0
    //   703: aconst_null
    //   704: astore_3
    //   705: goto -602 -> 103
    //   708: aconst_null
    //   709: astore 4
    //   711: aconst_null
    //   712: astore_1
    //   713: aconst_null
    //   714: astore_0
    //   715: goto -396 -> 319
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	718	0	paramContext	Context
    //   0	718	1	paramUri	Uri
    //   4	695	2	localObject1	Object
    //   65	640	3	localContext	Context
    //   261	449	4	localObject2	Object
    //   311	46	5	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   68	102	102	java/lang/Exception
    //   131	141	102	java/lang/Exception
    //   145	154	102	java/lang/Exception
    //   158	192	102	java/lang/Exception
    //   209	222	102	java/lang/Exception
    //   226	242	102	java/lang/Exception
    //   246	263	102	java/lang/Exception
    //   267	313	102	java/lang/Exception
    //   323	326	102	java/lang/Exception
    //   330	340	102	java/lang/Exception
    //   344	352	102	java/lang/Exception
    //   356	364	102	java/lang/Exception
    //   368	396	102	java/lang/Exception
    //   400	416	102	java/lang/Exception
    //   420	439	102	java/lang/Exception
    //   634	640	102	java/lang/Exception
    //   658	684	102	java/lang/Exception
    //   68	102	192	finally
    //   105	115	192	finally
    //   131	141	192	finally
    //   145	154	192	finally
    //   158	192	192	finally
    //   209	222	192	finally
    //   226	242	192	finally
    //   246	263	192	finally
    //   267	313	192	finally
    //   323	326	192	finally
    //   330	340	192	finally
    //   344	352	192	finally
    //   356	364	192	finally
    //   368	396	192	finally
    //   400	416	192	finally
    //   420	439	192	finally
    //   634	640	192	finally
    //   658	684	192	finally
    //   0	5	696	finally
    //   9	18	696	finally
    //   18	23	696	finally
    //   37	60	696	finally
    //   442	479	696	finally
    //   491	625	696	finally
    //   0	5	702	java/lang/Exception
    //   9	18	702	java/lang/Exception
    //   18	23	702	java/lang/Exception
    //   37	60	702	java/lang/Exception
    //   442	479	702	java/lang/Exception
    //   491	625	702	java/lang/Exception
  }
  
  public static String d(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    Object localObject;
    label40:
    do
    {
      return paramString;
      localObject = h;
      int i2 = localObject.length;
      int i1 = 0;
      for (;;)
      {
        if (i1 >= i2) {
          break label40;
        }
        if (localObject[i1].equals(paramString)) {
          break;
        }
        i1 += 1;
      }
      localObject = MzPhoneNumberUtils.removeCountryCode(paramString);
    } while (TextUtils.isEmpty((CharSequence)localObject));
    return (String)localObject;
  }
  
  public static wd.a d(Context paramContext, long paramLong)
  {
    synchronized (y)
    {
      wd.a locala2 = (wd.a)x.get(Long.valueOf(paramLong));
      locala1 = locala2;
      if (locala2 == null) {
        locala1 = (wd.a)v.get(Long.valueOf(paramLong));
      }
      if (locala1 != null)
      {
        if (TextUtils.isEmpty(a)) {
          return null;
        }
        c = a;
        return locala1;
      }
    }
    wd.a locala1 = (wd.a)w.get(Long.valueOf(paramLong));
    if (locala1 == null) {
      return null;
    }
    if (a.equals("")) {}
    for (c = paramContext.getString(2131493835);; c = a)
    {
      c = paramContext.getString(2131493836, new Object[] { c });
      return locala1;
    }
  }
  
  public static void d()
  {
    a("", -1);
    zv.d();
  }
  
  public static final void d(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.LAUNCHER");
    localIntent.setClassName("com.meizu.filemanager", "com.meizu.filemanager.managefile.FileManagerActivity");
    localIntent.putExtra("init_directory", paramString);
    localIntent.putExtra("other_app", true);
    localIntent.addFlags(524288);
    paramContext.startActivity(localIntent);
  }
  
  /* Error */
  public static void d(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 847	java/io/File
    //   5: dup
    //   6: aload_0
    //   7: invokespecial 848	java/io/File:<init>	(Ljava/lang/String;)V
    //   10: astore_0
    //   11: new 847	java/io/File
    //   14: dup
    //   15: aload_1
    //   16: invokespecial 848	java/io/File:<init>	(Ljava/lang/String;)V
    //   19: astore_1
    //   20: new 1764	java/io/BufferedInputStream
    //   23: dup
    //   24: new 1766	java/io/FileInputStream
    //   27: dup
    //   28: aload_0
    //   29: invokespecial 1769	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   32: invokespecial 1772	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   35: astore_0
    //   36: new 1774	java/io/BufferedOutputStream
    //   39: dup
    //   40: new 1776	java/io/FileOutputStream
    //   43: dup
    //   44: aload_1
    //   45: invokespecial 1777	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   48: invokespecial 1780	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   51: astore_1
    //   52: sipush 5120
    //   55: newarray <illegal type>
    //   57: astore_3
    //   58: aload_0
    //   59: aload_3
    //   60: invokevirtual 1784	java/io/BufferedInputStream:read	([B)I
    //   63: istore_2
    //   64: iload_2
    //   65: iconst_m1
    //   66: if_icmpeq +38 -> 104
    //   69: aload_1
    //   70: aload_3
    //   71: iconst_0
    //   72: iload_2
    //   73: invokevirtual 1788	java/io/BufferedOutputStream:write	([BII)V
    //   76: goto -18 -> 58
    //   79: astore 4
    //   81: aload_0
    //   82: astore_3
    //   83: aload 4
    //   85: astore_0
    //   86: aload_3
    //   87: ifnull +7 -> 94
    //   90: aload_3
    //   91: invokevirtual 1789	java/io/BufferedInputStream:close	()V
    //   94: aload_1
    //   95: ifnull +7 -> 102
    //   98: aload_1
    //   99: invokevirtual 1790	java/io/BufferedOutputStream:close	()V
    //   102: aload_0
    //   103: athrow
    //   104: aload_1
    //   105: invokevirtual 1793	java/io/BufferedOutputStream:flush	()V
    //   108: aload_0
    //   109: ifnull +7 -> 116
    //   112: aload_0
    //   113: invokevirtual 1789	java/io/BufferedInputStream:close	()V
    //   116: aload_1
    //   117: ifnull +7 -> 124
    //   120: aload_1
    //   121: invokevirtual 1790	java/io/BufferedOutputStream:close	()V
    //   124: return
    //   125: astore_0
    //   126: aconst_null
    //   127: astore_1
    //   128: goto -42 -> 86
    //   131: astore 4
    //   133: aconst_null
    //   134: astore_1
    //   135: aload_0
    //   136: astore_3
    //   137: aload 4
    //   139: astore_0
    //   140: goto -54 -> 86
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	143	0	paramString1	String
    //   0	143	1	paramString2	String
    //   63	10	2	i1	int
    //   1	136	3	localObject1	Object
    //   79	5	4	localObject2	Object
    //   131	7	4	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   52	58	79	finally
    //   58	64	79	finally
    //   69	76	79	finally
    //   104	108	79	finally
    //   20	36	125	finally
    //   36	52	131	finally
  }
  
  public static boolean d(Context paramContext)
  {
    return e(paramContext, 1);
  }
  
  private static boolean d(Context paramContext, int paramInt)
  {
    return ((ConnectivityManager)paramContext.getSystemService("connectivity")).getNetworkInfo(paramInt).isAvailable();
  }
  
  public static String e()
  {
    if (o != null) {
      return o;
    }
    return Environment.getExternalStorageDirectory().getPath();
  }
  
  public static final void e(Context paramContext, String paramString)
  {
    File localFile = new File(paramString);
    if ((!localFile.exists()) || (localFile.isDirectory()))
    {
      a(paramContext, 2131493296, 0, aaa.b());
      return;
    }
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.LAUNCHER");
    localIntent.setClassName("com.meizu.filemanager", "com.meizu.filemanager.managefile.FileManagerActivity");
    localIntent.putExtra("init_directory", localFile.getParent());
    localIntent.putExtra("selection_file_path", paramString);
    localIntent.putExtra("other_app", true);
    localIntent.addFlags(524288);
    paramContext.startActivity(localIntent);
  }
  
  public static void e(String paramString)
  {
    if (paramString != null) {
      o = paramString;
    }
  }
  
  public static boolean e(Context paramContext)
  {
    return e(paramContext, 0);
  }
  
  private static boolean e(Context paramContext, int paramInt)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.isAvailable()) && (paramContext.getType() == paramInt);
  }
  
  public static void f(String paramString)
  {
    if (!TextUtils.isEmpty(paramString)) {
      p = paramString;
    }
  }
  
  public static final boolean f()
  {
    return true;
  }
  
  public static boolean f(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.isAvailable());
  }
  
  /* Error */
  public static boolean f(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: ldc -25
    //   2: new 210	java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial 211	java/lang/StringBuilder:<init>	()V
    //   9: ldc_w 1822
    //   12: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   15: aload_1
    //   16: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   19: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   22: invokestatic 235	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   25: pop
    //   26: aload_0
    //   27: invokevirtual 321	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   30: astore_0
    //   31: aload_1
    //   32: ifnull +12 -> 44
    //   35: aload_1
    //   36: ldc 100
    //   38: invokevirtual 609	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   41: ifeq +14 -> 55
    //   44: ldc -25
    //   46: ldc_w 1824
    //   49: invokestatic 235	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   52: pop
    //   53: iconst_0
    //   54: ireturn
    //   55: aload_1
    //   56: invokestatic 992	aau:d	(Ljava/lang/String;)Z
    //   59: ifeq +111 -> 170
    //   62: aload_0
    //   63: getstatic 1829	android/provider/ContactsContract$CommonDataKinds$Email:CONTENT_LOOKUP_URI	Landroid/net/Uri;
    //   66: aload_1
    //   67: invokestatic 1832	android/net/Uri:encode	(Ljava/lang/String;)Ljava/lang/String;
    //   70: invokestatic 1836	android/net/Uri:withAppendedPath	(Landroid/net/Uri;Ljava/lang/String;)Landroid/net/Uri;
    //   73: iconst_1
    //   74: anewarray 66	java/lang/String
    //   77: dup
    //   78: iconst_0
    //   79: ldc_w 941
    //   82: aastore
    //   83: aconst_null
    //   84: aconst_null
    //   85: aconst_null
    //   86: invokevirtual 335	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   89: astore_0
    //   90: aload_0
    //   91: ifnull +114 -> 205
    //   94: aload_0
    //   95: astore_1
    //   96: aload_0
    //   97: invokeinterface 349 1 0
    //   102: ifle +103 -> 205
    //   105: aload_0
    //   106: astore_1
    //   107: aload_0
    //   108: invokeinterface 343 1 0
    //   113: pop
    //   114: aload_0
    //   115: astore_1
    //   116: aload_0
    //   117: iconst_0
    //   118: invokeinterface 346 2 0
    //   123: lstore_2
    //   124: aload_0
    //   125: astore_1
    //   126: ldc -25
    //   128: new 210	java/lang/StringBuilder
    //   131: dup
    //   132: invokespecial 211	java/lang/StringBuilder:<init>	()V
    //   135: ldc_w 1838
    //   138: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   141: lload_2
    //   142: invokevirtual 240	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   145: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   148: invokestatic 235	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   151: pop
    //   152: lload_2
    //   153: lconst_0
    //   154: lcmp
    //   155: ifle +50 -> 205
    //   158: aload_0
    //   159: ifnull +9 -> 168
    //   162: aload_0
    //   163: invokeinterface 362 1 0
    //   168: iconst_1
    //   169: ireturn
    //   170: aload_0
    //   171: getstatic 1843	android/provider/ContactsContract$PhoneLookup:CONTENT_FILTER_URI	Landroid/net/Uri;
    //   174: aload_1
    //   175: invokestatic 1832	android/net/Uri:encode	(Ljava/lang/String;)Ljava/lang/String;
    //   178: invokestatic 1836	android/net/Uri:withAppendedPath	(Landroid/net/Uri;Ljava/lang/String;)Landroid/net/Uri;
    //   181: iconst_2
    //   182: anewarray 66	java/lang/String
    //   185: dup
    //   186: iconst_0
    //   187: ldc 112
    //   189: aastore
    //   190: dup
    //   191: iconst_1
    //   192: ldc 124
    //   194: aastore
    //   195: aconst_null
    //   196: aconst_null
    //   197: aconst_null
    //   198: invokevirtual 335	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   201: astore_0
    //   202: goto -112 -> 90
    //   205: aload_0
    //   206: ifnull +9 -> 215
    //   209: aload_0
    //   210: invokeinterface 362 1 0
    //   215: iconst_0
    //   216: ireturn
    //   217: astore 4
    //   219: aconst_null
    //   220: astore_0
    //   221: aload_0
    //   222: astore_1
    //   223: ldc -25
    //   225: new 210	java/lang/StringBuilder
    //   228: dup
    //   229: invokespecial 211	java/lang/StringBuilder:<init>	()V
    //   232: ldc_w 1845
    //   235: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   238: aload 4
    //   240: invokevirtual 1614	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   243: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   246: invokestatic 1638	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   249: pop
    //   250: aload_0
    //   251: ifnull -36 -> 215
    //   254: aload_0
    //   255: invokeinterface 362 1 0
    //   260: goto -45 -> 215
    //   263: astore_0
    //   264: aconst_null
    //   265: astore_1
    //   266: aload_1
    //   267: ifnull +9 -> 276
    //   270: aload_1
    //   271: invokeinterface 362 1 0
    //   276: aload_0
    //   277: athrow
    //   278: astore_0
    //   279: goto -13 -> 266
    //   282: astore 4
    //   284: goto -63 -> 221
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	287	0	paramContext	Context
    //   0	287	1	paramString	String
    //   123	30	2	l1	long
    //   217	22	4	localException1	Exception
    //   282	1	4	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   55	90	217	java/lang/Exception
    //   170	202	217	java/lang/Exception
    //   55	90	263	finally
    //   170	202	263	finally
    //   96	105	278	finally
    //   107	114	278	finally
    //   116	124	278	finally
    //   126	152	278	finally
    //   223	250	278	finally
    //   96	105	282	java/lang/Exception
    //   107	114	282	java/lang/Exception
    //   116	124	282	java/lang/Exception
    //   126	152	282	java/lang/Exception
  }
  
  public static final boolean g()
  {
    return ((String)aau.a(StorageManager.class, (StorageManager)MmsApp.c().getApplicationContext().getSystemService("storage"), "getVolumeState", String.class, "/storage/sdcard1")).equals("mounted");
  }
  
  public static final boolean g(Context paramContext)
  {
    paramContext = (InputMethodManager)paramContext.getSystemService("input_method");
    if (paramContext != null) {
      return new mb(paramContext).c();
    }
    return false;
  }
  
  /* Error */
  public static boolean g(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aload_0
    //   3: invokevirtual 321	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   6: getstatic 1843	android/provider/ContactsContract$PhoneLookup:CONTENT_FILTER_URI	Landroid/net/Uri;
    //   9: aload_1
    //   10: invokestatic 1836	android/net/Uri:withAppendedPath	(Landroid/net/Uri;Ljava/lang/String;)Landroid/net/Uri;
    //   13: iconst_1
    //   14: anewarray 66	java/lang/String
    //   17: dup
    //   18: iconst_0
    //   19: ldc 112
    //   21: aastore
    //   22: aconst_null
    //   23: aconst_null
    //   24: aconst_null
    //   25: invokevirtual 335	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   28: astore_1
    //   29: aload_1
    //   30: ifnull +86 -> 116
    //   33: aload_1
    //   34: astore_0
    //   35: aload_1
    //   36: invokeinterface 343 1 0
    //   41: istore_2
    //   42: iload_2
    //   43: ifeq +73 -> 116
    //   46: iconst_1
    //   47: istore_2
    //   48: aload_1
    //   49: ifnull +9 -> 58
    //   52: aload_1
    //   53: invokeinterface 362 1 0
    //   58: iload_2
    //   59: ireturn
    //   60: astore_3
    //   61: aconst_null
    //   62: astore_1
    //   63: aload_1
    //   64: astore_0
    //   65: ldc -25
    //   67: ldc_w 1875
    //   70: aload_3
    //   71: invokestatic 817	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   74: pop
    //   75: aload_1
    //   76: ifnull +38 -> 114
    //   79: aload_1
    //   80: invokeinterface 362 1 0
    //   85: iconst_0
    //   86: ireturn
    //   87: astore_0
    //   88: aload_3
    //   89: astore_1
    //   90: aload_1
    //   91: ifnull +9 -> 100
    //   94: aload_1
    //   95: invokeinterface 362 1 0
    //   100: aload_0
    //   101: athrow
    //   102: astore_3
    //   103: aload_0
    //   104: astore_1
    //   105: aload_3
    //   106: astore_0
    //   107: goto -17 -> 90
    //   110: astore_3
    //   111: goto -48 -> 63
    //   114: iconst_0
    //   115: ireturn
    //   116: iconst_0
    //   117: istore_2
    //   118: goto -70 -> 48
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	121	0	paramContext	Context
    //   0	121	1	paramString	String
    //   41	77	2	bool	boolean
    //   1	1	3	localObject1	Object
    //   60	29	3	localException1	Exception
    //   102	4	3	localObject2	Object
    //   110	1	3	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   2	29	60	java/lang/Exception
    //   2	29	87	finally
    //   35	42	102	finally
    //   65	75	102	finally
    //   35	42	110	java/lang/Exception
  }
  
  public static boolean g(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    return TextUtils.isDigitsOnly(paramString.trim().replace("+", ""));
  }
  
  public static final long h()
  {
    StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
    long l1 = localStatFs.getBlockSize();
    return localStatFs.getAvailableBlocks() * l1;
  }
  
  public static final boolean h(Context paramContext)
  {
    return d(paramContext);
  }
  
  public static boolean h(Context paramContext, String paramString)
  {
    return (b(paramContext.getContentResolver())) && (!MmsApp.c().u()) && (!i(paramContext, paramString));
  }
  
  public static boolean h(String paramString)
  {
    return nc.c(paramString);
  }
  
  public static void i()
  {
    File localFile = new File("/sdcard/Download/FMessage/");
    if (!localFile.exists())
    {
      boolean bool = localFile.mkdirs();
      Log.d("Mms", "create Download dir " + bool);
    }
  }
  
  public static boolean i(Context paramContext)
  {
    boolean bool = false;
    if (Settings.System.getInt(paramContext.getContentResolver(), "flyme_msg_reject_stranger", 0) != 0) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean i(Context paramContext, String paramString)
  {
    boolean bool2 = g(paramContext, paramString);
    if (!MmsApp.d) {}
    for (boolean bool1 = MzPhoneNumberUtils.isNotificationNumber(paramContext, paramString);; bool1 = false)
    {
      if (bool2) {
        return false;
      }
      return bool1;
    }
  }
  
  public static final boolean i(String paramString)
  {
    paramString = new File(paramString);
    return ((paramString.exists()) && (paramString.isDirectory()) && (paramString.canRead())) || (paramString.mkdirs());
  }
  
  public static final String j(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    int i1;
    do
    {
      return "";
      i1 = paramString.lastIndexOf('.');
    } while (i1 < 0);
    return paramString.substring(i1 + 1);
  }
  
  public static void j(Context paramContext, String paramString)
  {
    try
    {
      paramContext = new android.app.AlertDialog.Builder(paramContext, 2131624320);
      paramContext.setMessage(paramString);
      paramContext.setPositiveButton(17039370, null);
      paramContext = paramContext.create();
      paramContext.getWindow().setType(2003);
      paramContext.show();
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static boolean j()
  {
    String str = aau.b("ro.meizu.voip.support");
    if (str == null) {
      return false;
    }
    return Boolean.parseBoolean(str);
  }
  
  public static boolean j(Context paramContext)
  {
    paramContext = paramContext.getContentResolver().query(Uri.parse("content://com.meizu.cloud/flyme_communication"), null, null, null, null);
    if (paramContext != null) {}
    for (;;)
    {
      try
      {
        if (paramContext.moveToFirst())
        {
          int i1 = paramContext.getInt(paramContext.getColumnIndex("mms_status"));
          return i1 == 1;
        }
        return false;
      }
      finally
      {
        paramContext.close();
      }
      Log.i("MessageUtils", "isFlymeOpened cursor is null");
    }
  }
  
  public static long k()
  {
    long l1 = ga.a(true) - 1408 - 102;
    Log.i("MessageUtils", "computeAttachmentSizeLimitForReplace sizeLimit = " + l1);
    return l1;
  }
  
  public static Uri k(Context paramContext)
  {
    return (Uri)aau.a("android.media.MzRingtoneManager", "getActualDefaultRingtoneUri", "TYPE_MMS", new Class[] { Context.class, Integer.TYPE }, paramContext);
  }
  
  public static String k(String paramString)
  {
    int i1 = 0;
    Object localObject2 = paramString;
    if (i1 < q.length)
    {
      Object localObject1 = localObject2;
      if (paramString.contains(q[i1]))
      {
        if (q[i1] != "\000") {
          break label57;
        }
        localObject1 = paramString.replace(q[i1], "");
      }
      for (;;)
      {
        i1 += 1;
        localObject2 = localObject1;
        break;
        label57:
        localObject1 = localObject2;
        if (q[i1] == "\f") {
          localObject1 = paramString.replace(q[i1], "\n");
        }
      }
    }
    return (String)localObject2;
  }
  
  public static int l()
  {
    if (MmsApp.a) {
      return 2131493820;
    }
    if (MmsApp.c) {
      return 2131493838;
    }
    return 2131492960;
  }
  
  public static Uri l(String paramString)
  {
    Object localObject = null;
    int i3;
    if (paramString.startsWith("http://mo.amap.com/?"))
    {
      localObject = paramString.substring(r);
      paramString = new StringBuilder("androidamap://viewMap?sourceApplication=com.android.mms");
      if (!TextUtils.isEmpty((CharSequence)localObject))
      {
        localObject = ((String)localObject).split("&");
        i3 = 0;
      }
    }
    int i4;
    for (int i2 = 0;; i2 = i4)
    {
      int i1;
      if (i3 < localObject.length)
      {
        i4 = i2;
        if (TextUtils.isEmpty(localObject[i3])) {
          break label258;
        }
        if (!localObject[i3].startsWith("q=")) {
          break label214;
        }
        i2 += 1;
        String str = localObject[i3].substring(2);
        i1 = i2;
        if (!TextUtils.isEmpty(str))
        {
          i4 = str.indexOf(',');
          i1 = i2;
          if (i4 > -1)
          {
            paramString.append("&lat=").append(str.substring(0, i4));
            paramString.append("&lon=").append(str.substring(i4 + 1));
            i1 = i2;
          }
        }
      }
      for (;;)
      {
        i4 = i1;
        if (i1 < 2) {
          break label258;
        }
        paramString.append("&dev=0");
        return Uri.parse(paramString.toString());
        if (!paramString.startsWith("http://m.amap.com/?")) {
          break;
        }
        localObject = paramString.substring(s);
        break;
        label214:
        i1 = i2;
        if (localObject[i3].startsWith("name="))
        {
          i1 = i2 + 1;
          paramString.append("&poiname=");
          paramString.append(localObject[i3].substring(t));
        }
      }
      label258:
      i3 += 1;
    }
  }
  
  public static void l(Context paramContext)
  {
    ContentValues localContentValues = new ContentValues(1);
    localContentValues.put("resp_st", Integer.valueOf(9527));
    paramContext.getContentResolver().update(Telephony.Mms.Outbox.CONTENT_URI, localContentValues, "msg_box = 4", null);
    localContentValues.clear();
    localContentValues.put("err_type", Integer.valueOf(19));
    paramContext.getContentResolver().update(Telephony.MmsSms.PendingMessages.CONTENT_URI, localContentValues, "msg_type != 130 AND msg_type != 135", null);
    localContentValues.clear();
    localContentValues.put("st", Integer.valueOf(130));
    paramContext.getContentResolver().update(Telephony.Mms.Inbox.CONTENT_URI, localContentValues, "st = 129 OR st = 131 OR st = 135", null);
  }
  
  public static Uri m(String paramString)
  {
    if (paramString == null) {
      throw new NullPointerException("fullName");
    }
    Uri.Builder localBuilder = new Uri.Builder();
    localBuilder.scheme("file");
    localBuilder.authority("");
    localBuilder.path(paramString);
    return localBuilder.build();
  }
  
  public static void m(Context paramContext)
  {
    a(2131493765, paramContext, 0, 2, true, 0);
  }
  
  private static String n(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      paramString = "";
    }
    String str;
    do
    {
      return paramString;
      str = a(paramString);
      paramString = str;
      if (str.startsWith("<"))
      {
        paramString = str;
        if (str.startsWith(">")) {
          paramString = str.substring(1, str.length() - 1);
        }
      }
      if (TextUtils.isEmpty(paramString)) {
        return "";
      }
      str = paramString;
      if (paramString.startsWith("cid:")) {
        str = paramString.substring("cid:".length());
      }
      paramString = str;
      if (str.startsWith(".")) {
        paramString = str.replaceFirst(".", "_");
      }
      str = paramString.replace(' ', '_');
      paramString = str;
    } while (!TextUtils.isEmpty(str));
    return "";
  }
  
  public static boolean n(Context paramContext)
  {
    if ((MmsApp.d) || (c(paramContext.getContentResolver()))) {
      return false;
    }
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("show_cta_dialog", true);
  }
  
  private static String o(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i2 = paramString.length();
    int i1 = 0;
    if (i1 < i2)
    {
      char c1 = paramString.charAt(i1);
      if ((c1 == '+') && (localStringBuilder.length() == 0)) {
        localStringBuilder.append(c1);
      }
      label72:
      do
      {
        for (;;)
        {
          i1 += 1;
          break;
          if (!Character.isDigit(c1)) {
            break label72;
          }
          localStringBuilder.append(c1);
        }
      } while (j.get(Character.valueOf(c1)) != null);
      return null;
    }
    return localStringBuilder.toString();
  }
  
  public static void o(Context paramContext)
  {
    new android.app.AlertDialog.Builder(paramContext).setMessage(2131493763).setPositiveButton(2131493764, new wi(paramContext)).setNegativeButton(2131493022, null).show();
  }
  
  public static void p(Context paramContext)
  {
    Log.v("Mms", "queryDraft begin");
    if ((z != null) && (z.getStatus() == AsyncTask.Status.RUNNING)) {
      z.cancel(true);
    }
    z = new wd.b(paramContext);
    z.execute(new Void[0]);
  }
  
  private static void p(String paramString)
  {
    Log.d("Mms", "[MsgUtils] " + paramString);
  }
  
  public static void q(Context paramContext)
  {
    aat.a = false;
    String str = paramContext.getString(2131493210);
    View localView = View.inflate(paramContext, 2130968621, null);
    localView.findViewById(2131886260).setVisibility(8);
    CheckBox localCheckBox = (CheckBox)localView.findViewById(2131886261);
    localCheckBox.setVisibility(0);
    localCheckBox.setText(2131493768);
    localCheckBox.setChecked(false);
    new android.app.AlertDialog.Builder(paramContext).setMessage(str).setPositiveButton(paramContext.getString(2131493166), new wl(paramContext, localCheckBox)).setView(localView).show();
  }
  
  private static void r()
  {
    if ((v == null) || (v.size() == 0))
    {
      Log.v("Mms", "sDraftData count is 0");
      if ((w != null) && (w.size() != 0)) {
        break label158;
      }
      Log.v("Mms", "sDraftData count is 0");
    }
    for (;;)
    {
      return;
      Iterator localIterator = v.entrySet().iterator();
      Object localObject;
      long l1;
      while (localIterator.hasNext())
      {
        localObject = (Map.Entry)localIterator.next();
        l1 = ((Long)((Map.Entry)localObject).getKey()).longValue();
        localObject = (wd.a)((Map.Entry)localObject).getValue();
        Log.v("Mms", "queryDraft sms thread: " + l1 + ", draft: " + a + ", date = " + b);
      }
      break;
      label158:
      localIterator = w.entrySet().iterator();
      while (localIterator.hasNext())
      {
        localObject = (Map.Entry)localIterator.next();
        l1 = ((Long)((Map.Entry)localObject).getKey()).longValue();
        localObject = (wd.a)((Map.Entry)localObject).getValue();
        Log.v("Mms", "queryDraft mms thread: " + l1 + ", draft: " + a + ", date = " + b);
      }
    }
  }
  
  public static class a
  {
    public String a;
    public long b;
    public String c;
    
    public a(String paramString, long paramLong)
    {
      a = paramString;
      b = paramLong;
    }
  }
  
  static class b
    extends AsyncTask<Void, Void, Void>
  {
    Context a;
    
    public b(Context paramContext)
    {
      a = paramContext;
    }
    
    /* Error */
    protected Void a(Void... paramVarArgs)
    {
      // Byte code:
      //   0: ldc 23
      //   2: ldc 25
      //   4: invokestatic 31	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
      //   7: pop
      //   8: invokestatic 35	wd:m	()Ljava/lang/Object;
      //   11: astore 7
      //   13: aload 7
      //   15: monitorenter
      //   16: invokestatic 39	wd:n	()Ljava/util/HashMap;
      //   19: invokevirtual 44	java/util/HashMap:clear	()V
      //   22: invokestatic 47	wd:o	()Ljava/util/HashMap;
      //   25: invokevirtual 44	java/util/HashMap:clear	()V
      //   28: aload_0
      //   29: getfield 17	wd$b:a	Landroid/content/Context;
      //   32: invokevirtual 53	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
      //   35: astore 8
      //   37: aload 8
      //   39: getstatic 59	android/provider/Telephony$Sms:CONTENT_URI	Landroid/net/Uri;
      //   42: iconst_3
      //   43: anewarray 61	java/lang/String
      //   46: dup
      //   47: iconst_0
      //   48: ldc 63
      //   50: aastore
      //   51: dup
      //   52: iconst_1
      //   53: ldc 65
      //   55: aastore
      //   56: dup
      //   57: iconst_2
      //   58: ldc 67
      //   60: aastore
      //   61: ldc 69
      //   63: aconst_null
      //   64: aconst_null
      //   65: invokevirtual 75	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
      //   68: astore_1
      //   69: aload_1
      //   70: ifnull +87 -> 157
      //   73: aload_1
      //   74: astore 5
      //   76: aload_1
      //   77: invokeinterface 81 1 0
      //   82: ifle +75 -> 157
      //   85: aload_1
      //   86: astore 5
      //   88: aload_1
      //   89: invokeinterface 85 1 0
      //   94: pop
      //   95: aload_1
      //   96: astore 5
      //   98: aload_1
      //   99: iconst_0
      //   100: invokeinterface 89 2 0
      //   105: lstore_2
      //   106: aload_1
      //   107: astore 5
      //   109: invokestatic 39	wd:n	()Ljava/util/HashMap;
      //   112: lload_2
      //   113: invokestatic 95	java/lang/Long:valueOf	(J)Ljava/lang/Long;
      //   116: new 97	wd$a
      //   119: dup
      //   120: aload_1
      //   121: iconst_1
      //   122: invokeinterface 101 2 0
      //   127: aload_1
      //   128: iconst_2
      //   129: invokeinterface 89 2 0
      //   134: invokespecial 104	wd$a:<init>	(Ljava/lang/String;J)V
      //   137: invokevirtual 108	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   140: pop
      //   141: aload_1
      //   142: astore 5
      //   144: aload_1
      //   145: invokeinterface 111 1 0
      //   150: istore 4
      //   152: iload 4
      //   154: ifne -59 -> 95
      //   157: aload_1
      //   158: astore 5
      //   160: aload_1
      //   161: ifnull +520 -> 681
      //   164: aload_1
      //   165: invokeinterface 114 1 0
      //   170: aload 8
      //   172: getstatic 117	android/provider/Telephony$Mms$Draft:CONTENT_URI	Landroid/net/Uri;
      //   175: iconst_4
      //   176: anewarray 61	java/lang/String
      //   179: dup
      //   180: iconst_0
      //   181: ldc 63
      //   183: aastore
      //   184: dup
      //   185: iconst_1
      //   186: ldc 119
      //   188: aastore
      //   189: dup
      //   190: iconst_2
      //   191: ldc 121
      //   193: aastore
      //   194: dup
      //   195: iconst_3
      //   196: ldc 67
      //   198: aastore
      //   199: aconst_null
      //   200: aconst_null
      //   201: aconst_null
      //   202: invokevirtual 75	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
      //   205: astore 5
      //   207: aload 5
      //   209: ifnull +103 -> 312
      //   212: aload 5
      //   214: astore_1
      //   215: aload 5
      //   217: invokeinterface 81 1 0
      //   222: ifle +90 -> 312
      //   225: aload 5
      //   227: astore_1
      //   228: aload 5
      //   230: invokeinterface 85 1 0
      //   235: pop
      //   236: aload 5
      //   238: astore_1
      //   239: aload 5
      //   241: iconst_1
      //   242: iconst_2
      //   243: invokestatic 124	wd:a	(Landroid/database/Cursor;II)Ljava/lang/String;
      //   246: astore 6
      //   248: aload 5
      //   250: astore_1
      //   251: aload 5
      //   253: iconst_0
      //   254: invokeinterface 89 2 0
      //   259: lstore_2
      //   260: aload 5
      //   262: astore_1
      //   263: invokestatic 47	wd:o	()Ljava/util/HashMap;
      //   266: lload_2
      //   267: invokestatic 95	java/lang/Long:valueOf	(J)Ljava/lang/Long;
      //   270: new 97	wd$a
      //   273: dup
      //   274: aload 6
      //   276: aload 5
      //   278: iconst_3
      //   279: invokeinterface 89 2 0
      //   284: ldc2_w 125
      //   287: lmul
      //   288: invokespecial 104	wd$a:<init>	(Ljava/lang/String;J)V
      //   291: invokevirtual 108	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   294: pop
      //   295: aload 5
      //   297: astore_1
      //   298: aload 5
      //   300: invokeinterface 111 1 0
      //   305: istore 4
      //   307: iload 4
      //   309: ifne -73 -> 236
      //   312: aload 5
      //   314: ifnull +10 -> 324
      //   317: aload 5
      //   319: invokeinterface 114 1 0
      //   324: invokestatic 129	wd:p	()Ljava/util/HashMap;
      //   327: invokevirtual 132	java/util/HashMap:size	()I
      //   330: ifle +324 -> 654
      //   333: invokestatic 129	wd:p	()Ljava/util/HashMap;
      //   336: invokevirtual 136	java/util/HashMap:entrySet	()Ljava/util/Set;
      //   339: invokeinterface 142 1 0
      //   344: astore_1
      //   345: aload_1
      //   346: invokeinterface 147 1 0
      //   351: ifeq +303 -> 654
      //   354: aload_1
      //   355: invokeinterface 150 1 0
      //   360: checkcast 152	java/util/Map$Entry
      //   363: astore 5
      //   365: aload 5
      //   367: invokeinterface 155 1 0
      //   372: checkcast 91	java/lang/Long
      //   375: invokevirtual 159	java/lang/Long:longValue	()J
      //   378: lstore_2
      //   379: aload 5
      //   381: invokeinterface 162 1 0
      //   386: checkcast 97	wd$a
      //   389: getfield 165	wd$a:a	Ljava/lang/String;
      //   392: invokestatic 171	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   395: ifeq +132 -> 527
      //   398: invokestatic 39	wd:n	()Ljava/util/HashMap;
      //   401: lload_2
      //   402: invokestatic 95	java/lang/Long:valueOf	(J)Ljava/lang/Long;
      //   405: invokevirtual 175	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
      //   408: ifne -63 -> 345
      //   411: invokestatic 47	wd:o	()Ljava/util/HashMap;
      //   414: lload_2
      //   415: invokestatic 95	java/lang/Long:valueOf	(J)Ljava/lang/Long;
      //   418: invokevirtual 175	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
      //   421: ifne -76 -> 345
      //   424: invokestatic 129	wd:p	()Ljava/util/HashMap;
      //   427: lload_2
      //   428: invokestatic 95	java/lang/Long:valueOf	(J)Ljava/lang/Long;
      //   431: invokevirtual 179	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
      //   434: pop
      //   435: goto -90 -> 345
      //   438: astore_1
      //   439: aload 7
      //   441: monitorexit
      //   442: aload_1
      //   443: athrow
      //   444: astore 6
      //   446: aconst_null
      //   447: astore_1
      //   448: aload_1
      //   449: astore 5
      //   451: aload 6
      //   453: invokevirtual 182	java/lang/Exception:printStackTrace	()V
      //   456: aload_1
      //   457: astore 5
      //   459: aload_1
      //   460: ifnull +221 -> 681
      //   463: aload_1
      //   464: invokeinterface 114 1 0
      //   469: goto -299 -> 170
      //   472: aload 5
      //   474: ifnull +10 -> 484
      //   477: aload 5
      //   479: invokeinterface 114 1 0
      //   484: aload_1
      //   485: athrow
      //   486: astore 6
      //   488: aload_1
      //   489: astore 5
      //   491: aload 5
      //   493: astore_1
      //   494: aload 6
      //   496: invokevirtual 182	java/lang/Exception:printStackTrace	()V
      //   499: aload 5
      //   501: ifnull -177 -> 324
      //   504: aload 5
      //   506: invokeinterface 114 1 0
      //   511: goto -187 -> 324
      //   514: aload_1
      //   515: ifnull +9 -> 524
      //   518: aload_1
      //   519: invokeinterface 114 1 0
      //   524: aload 5
      //   526: athrow
      //   527: invokestatic 39	wd:n	()Ljava/util/HashMap;
      //   530: lload_2
      //   531: invokestatic 95	java/lang/Long:valueOf	(J)Ljava/lang/Long;
      //   534: invokevirtual 185	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   537: ifnull +52 -> 589
      //   540: invokestatic 39	wd:n	()Ljava/util/HashMap;
      //   543: lload_2
      //   544: invokestatic 95	java/lang/Long:valueOf	(J)Ljava/lang/Long;
      //   547: invokevirtual 185	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   550: checkcast 97	wd$a
      //   553: getfield 165	wd$a:a	Ljava/lang/String;
      //   556: invokestatic 129	wd:p	()Ljava/util/HashMap;
      //   559: lload_2
      //   560: invokestatic 95	java/lang/Long:valueOf	(J)Ljava/lang/Long;
      //   563: invokevirtual 185	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   566: checkcast 97	wd$a
      //   569: getfield 165	wd$a:a	Ljava/lang/String;
      //   572: invokevirtual 188	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   575: ifeq +14 -> 589
      //   578: invokestatic 129	wd:p	()Ljava/util/HashMap;
      //   581: lload_2
      //   582: invokestatic 95	java/lang/Long:valueOf	(J)Ljava/lang/Long;
      //   585: invokevirtual 179	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
      //   588: pop
      //   589: invokestatic 47	wd:o	()Ljava/util/HashMap;
      //   592: lload_2
      //   593: invokestatic 95	java/lang/Long:valueOf	(J)Ljava/lang/Long;
      //   596: invokevirtual 185	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   599: ifnull -254 -> 345
      //   602: invokestatic 47	wd:o	()Ljava/util/HashMap;
      //   605: lload_2
      //   606: invokestatic 95	java/lang/Long:valueOf	(J)Ljava/lang/Long;
      //   609: invokevirtual 185	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   612: checkcast 97	wd$a
      //   615: getfield 165	wd$a:a	Ljava/lang/String;
      //   618: invokestatic 129	wd:p	()Ljava/util/HashMap;
      //   621: lload_2
      //   622: invokestatic 95	java/lang/Long:valueOf	(J)Ljava/lang/Long;
      //   625: invokevirtual 185	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   628: checkcast 97	wd$a
      //   631: getfield 165	wd$a:a	Ljava/lang/String;
      //   634: invokevirtual 188	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   637: ifeq -292 -> 345
      //   640: invokestatic 129	wd:p	()Ljava/util/HashMap;
      //   643: lload_2
      //   644: invokestatic 95	java/lang/Long:valueOf	(J)Ljava/lang/Long;
      //   647: invokevirtual 179	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
      //   650: pop
      //   651: goto -306 -> 345
      //   654: invokestatic 191	wd:q	()V
      //   657: aload 7
      //   659: monitorexit
      //   660: aconst_null
      //   661: areturn
      //   662: astore 5
      //   664: goto -150 -> 514
      //   667: astore 6
      //   669: goto -178 -> 491
      //   672: astore_1
      //   673: goto -201 -> 472
      //   676: astore 6
      //   678: goto -230 -> 448
      //   681: aload 5
      //   683: astore_1
      //   684: goto -514 -> 170
      //   687: astore_1
      //   688: aconst_null
      //   689: astore 5
      //   691: goto -219 -> 472
      //   694: astore 5
      //   696: goto -182 -> 514
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	699	0	this	b
      //   0	699	1	paramVarArgs	Void[]
      //   105	539	2	l	long
      //   150	158	4	bool	boolean
      //   74	451	5	localObject1	Object
      //   662	20	5	localObject2	Object
      //   689	1	5	localObject3	Object
      //   694	1	5	localObject4	Object
      //   246	29	6	str	String
      //   444	8	6	localException1	Exception
      //   486	9	6	localException2	Exception
      //   667	1	6	localException3	Exception
      //   676	1	6	localException4	Exception
      //   11	647	7	localObject5	Object
      //   35	136	8	localContentResolver	ContentResolver
      // Exception table:
      //   from	to	target	type
      //   16	37	438	finally
      //   164	170	438	finally
      //   317	324	438	finally
      //   324	345	438	finally
      //   345	435	438	finally
      //   439	442	438	finally
      //   463	469	438	finally
      //   477	484	438	finally
      //   484	486	438	finally
      //   504	511	438	finally
      //   518	524	438	finally
      //   524	527	438	finally
      //   527	589	438	finally
      //   589	651	438	finally
      //   654	660	438	finally
      //   37	69	444	java/lang/Exception
      //   170	207	486	java/lang/Exception
      //   215	225	662	finally
      //   228	236	662	finally
      //   239	248	662	finally
      //   251	260	662	finally
      //   263	295	662	finally
      //   298	307	662	finally
      //   494	499	662	finally
      //   215	225	667	java/lang/Exception
      //   228	236	667	java/lang/Exception
      //   239	248	667	java/lang/Exception
      //   251	260	667	java/lang/Exception
      //   263	295	667	java/lang/Exception
      //   298	307	667	java/lang/Exception
      //   76	85	672	finally
      //   88	95	672	finally
      //   98	106	672	finally
      //   109	141	672	finally
      //   144	152	672	finally
      //   451	456	672	finally
      //   76	85	676	java/lang/Exception
      //   88	95	676	java/lang/Exception
      //   98	106	676	java/lang/Exception
      //   109	141	676	java/lang/Exception
      //   144	152	676	java/lang/Exception
      //   37	69	687	finally
      //   170	207	694	finally
    }
  }
  
  public static class c
  {
    private static final Map<String, Integer[]> a = new HashMap();
    private static final Integer[] b = { Integer.valueOf(2130838225), Integer.valueOf(2130838312) };
    private static final Integer[] c = { Integer.valueOf(2130838171), Integer.valueOf(2130838158) };
    private static final Integer[] d = { Integer.valueOf(2130838222), Integer.valueOf(2130838311) };
    private static final Integer[] e = { Integer.valueOf(2130838203), Integer.valueOf(2130838243) };
    private static final Integer[] f = { Integer.valueOf(2130838165), Integer.valueOf(2130838155) };
    private static final Integer[] g = { Integer.valueOf(2130838197), Integer.valueOf(2130838232) };
    private static final Integer[] h = { Integer.valueOf(2130838210), Integer.valueOf(2130838308) };
    private static final Integer[] i = { Integer.valueOf(2130838176), Integer.valueOf(2130838164) };
    private static final Integer[] j = { Integer.valueOf(2130838217), Integer.valueOf(2130838310) };
    private static final Integer[] k = { Integer.valueOf(2130838168), Integer.valueOf(2130838156) };
    
    static
    {
      a.put("rar", b);
      a.put("zip", b);
      a.put("gtar", b);
      a.put("tgz", b);
      a.put("taz", b);
      a.put("doc", c);
      a.put("dot", c);
      a.put("docx", c);
      a.put("dotx", c);
      a.put("xls", d);
      a.put("xlt", d);
      a.put("xlsx", d);
      a.put("xltx", d);
      a.put("ppt", e);
      a.put("pot", e);
      a.put("pps", e);
      a.put("pptx", e);
      a.put("potx", e);
      a.put("ppsx", e);
      a.put("apk", f);
      a.put("pdf", g);
      a.put("txt", h);
      a.put("asc", h);
      a.put("text", h);
      a.put("diff", h);
      a.put("rtx", h);
      a.put("rtf", h);
      a.put("h", h);
      a.put("c++", h);
      a.put("cpp", h);
      a.put("java", h);
      a.put("xhtml", i);
      a.put("xml", i);
      a.put("webarchivexml", i);
      a.put("css", i);
      a.put("html", i);
      a.put("htm", i);
      a.put("vcf", j);
      a.put("bin", k);
      a.put("img", k);
    }
  }
  
  public static abstract interface d
  {
    public abstract void a(MzPduPart paramMzPduPart, boolean paramBoolean, int paramInt);
  }
}

/* Location:
 * Qualified Name:     wd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */