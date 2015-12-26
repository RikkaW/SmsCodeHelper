import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsManager;
import android.util.Log;
import com.google.android.mms.InvalidHeaderValueException;
import com.google.android.mms.MmsException;
import com.meizu.android.mms.pdu.MzGenericPdu;
import com.meizu.android.mms.pdu.MzNotificationInd;
import com.meizu.android.mms.pdu.MzNotifyRespInd;
import com.meizu.android.mms.pdu.MzPduComposer;
import com.meizu.android.mms.pdu.MzPduPersister;
import java.io.File;

public class om
  extends ow
  implements Runnable
{
  private MzNotificationInd a;
  private String p;
  private long q;
  
  /* Error */
  public om(Context paramContext, int paramInt, MzNotificationInd paramMzNotificationInd, long paramLong)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: iload_2
    //   3: lload 4
    //   5: invokespecial 19	ow:<init>	(Landroid/content/Context;IJ)V
    //   8: aload_1
    //   9: invokestatic 25	com/meizu/android/mms/pdu/MzPduPersister:getPduPersister	(Landroid/content/Context;)Lcom/meizu/android/mms/pdu/MzPduPersister;
    //   12: astore 7
    //   14: getstatic 31	android/provider/Telephony$Mms$Inbox:CONTENT_URI	Landroid/net/Uri;
    //   17: astore 8
    //   19: aload_1
    //   20: lload 4
    //   22: invokestatic 34	om:a	(Landroid/content/Context;J)Z
    //   25: ifne +49 -> 74
    //   28: iconst_1
    //   29: istore 6
    //   31: aload_0
    //   32: aload 7
    //   34: aload_3
    //   35: aload 8
    //   37: iload 6
    //   39: aload_1
    //   40: invokestatic 40	com/android/mms/ui/MessagingPreferenceActivity:b	(Landroid/content/Context;)Z
    //   43: aconst_null
    //   44: invokevirtual 44	com/meizu/android/mms/pdu/MzPduPersister:persist	(Lcom/meizu/android/mms/pdu/MzGenericPdu;Landroid/net/Uri;ZZLjava/util/HashMap;)Landroid/net/Uri;
    //   47: putfield 47	om:g	Landroid/net/Uri;
    //   50: aload_0
    //   51: aload_3
    //   52: putfield 49	om:a	Lcom/meizu/android/mms/pdu/MzNotificationInd;
    //   55: aload_0
    //   56: new 51	java/lang/String
    //   59: dup
    //   60: aload_0
    //   61: getfield 49	om:a	Lcom/meizu/android/mms/pdu/MzNotificationInd;
    //   64: invokevirtual 57	com/meizu/android/mms/pdu/MzNotificationInd:getContentLocation	()[B
    //   67: invokespecial 60	java/lang/String:<init>	([B)V
    //   70: putfield 63	om:c	Ljava/lang/String;
    //   73: return
    //   74: iconst_0
    //   75: istore 6
    //   77: goto -46 -> 31
    //   80: astore_1
    //   81: ldc 65
    //   83: ldc 67
    //   85: aload_1
    //   86: invokestatic 73	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   89: pop
    //   90: new 75	java/lang/IllegalArgumentException
    //   93: dup
    //   94: invokespecial 78	java/lang/IllegalArgumentException:<init>	()V
    //   97: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	98	0	this	om
    //   0	98	1	paramContext	Context
    //   0	98	2	paramInt	int
    //   0	98	3	paramMzNotificationInd	MzNotificationInd
    //   0	98	4	paramLong	long
    //   29	47	6	bool	boolean
    //   12	21	7	localMzPduPersister	MzPduPersister
    //   17	19	8	localUri	Uri
    // Exception table:
    //   from	to	target	type
    //   8	28	80	com/google/android/mms/MmsException
    //   31	50	80	com/google/android/mms/MmsException
  }
  
  public om(Context paramContext, int paramInt, String paramString, long paramLong)
  {
    super(paramContext, paramInt, paramLong);
    g = Uri.parse(paramString);
    try
    {
      a = ((MzNotificationInd)MzPduPersister.getPduPersister(paramContext).load(g));
      p = new String(a.getContentLocation());
      c = p;
      q = a.getMessageSize();
      l = "";
      a(or.a(paramContext));
      return;
    }
    catch (MmsException paramContext)
    {
      Log.e("NotificationTransaction", "Failed to load MzNotificationInd from: " + paramString, paramContext);
      throw new IllegalArgumentException();
    }
  }
  
  public static boolean a(Context paramContext, long paramLong)
  {
    return zn.a().a(paramLong);
  }
  
  public void a()
  {
    new Thread(this, "NotificationTransaction").start();
  }
  
  public void a(int paramInt)
  {
    Log.v("NotificationTransaction", "NotificationTransaction: sendNotifyRespInd()");
    try
    {
      Object localObject = new MzNotifyRespInd(18, a.getTransactionId(), paramInt);
      localObject = a(new MzPduComposer(b, (MzGenericPdu)localObject).make(), "NotifyResp_noti" + g.getLastPathSegment());
      if (localObject == null) {
        return;
      }
    }
    catch (InvalidHeaderValueException localInvalidHeaderValueException)
    {
      localInvalidHeaderValueException.printStackTrace();
      return;
    }
    SmsManager localSmsManager = aac.c(f);
    if (ga.v())
    {
      localSmsManager.sendMultimediaMessage(b, Uri.fromFile(localInvalidHeaderValueException), p, null, null);
      return;
    }
    localSmsManager.sendMultimediaMessage(b, Uri.fromFile(localInvalidHeaderValueException), null, null, null);
  }
  
  public int b()
  {
    return b("RetrieveResult_noti" + g.getLastPathSegment());
  }
  
  public int c()
  {
    return 0;
  }
  
  public void run()
  {
    Log.d("NotificationTransaction", "NotificationTransaction: run");
    Object localObject1 = zn.a();
    boolean bool = a(b, f);
    try
    {
      Log.v("NotificationTransaction", "Notification transaction launched: " + this + ", mSubId = " + f);
      if (!bool)
      {
        ((zn)localObject1).a(g, 128);
        a(131);
        f().a(1);
        f().a(g);
        d();
        return;
      }
      ((zn)localObject1).a(g, 129);
      Log.v("NotificationTransaction", "Content-Location: " + p);
      localObject1 = a(null, "RetrieveResult_noti" + g.getLastPathSegment());
      ((File)localObject1).setWritable(true, false);
      Object localObject2 = new Intent("com.android.mms.transaction.TRANSACION_PROCESSED");
      ((Intent)localObject2).putExtra("subscription", f);
      ((Intent)localObject2).putExtra("bundle_uri", g.toString());
      localObject2 = PendingIntent.getBroadcast(b, 0, (Intent)localObject2, 134217728);
      SmsManager localSmsManager = aac.c(f);
      Log.d("NotificationTransaction", "download MMS with param, mContentLocation = " + p + ", mUri = " + g + ", subId" + f + ", pduFile.path = " + ((File)localObject1).getAbsolutePath());
      localSmsManager.downloadMultimediaMessage(b, p, Uri.fromFile((File)localObject1), ga.F(), (PendingIntent)localObject2);
      aat.b().a(b, g);
      aat.c(b);
      aev.a(b);
      return;
    }
    catch (Throwable localThrowable)
    {
      f().a(2);
      f().a(g);
      d();
      Log.e("NotificationTransaction", Log.getStackTraceString(localThrowable));
    }
  }
}

/* Location:
 * Qualified Name:     om
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */