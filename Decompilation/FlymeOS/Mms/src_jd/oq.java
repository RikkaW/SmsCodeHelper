import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.telephony.SmsManager;
import android.util.Log;
import com.google.android.mms.MmsException;
import com.meizu.android.mms.pdu.MzAcknowledgeInd;
import com.meizu.android.mms.pdu.MzEncodedStringValue;
import com.meizu.android.mms.pdu.MzPduComposer;
import com.meizu.android.mms.pdu.MzPduPersister;
import com.meizu.android.mms.pdu.MzRetrieveConf;
import java.io.File;

public class oq
  extends ow
  implements Runnable
{
  static final String[] a = { "ct_l", "locked", "m_size" };
  private final String p;
  private boolean q;
  private long r;
  
  public oq(Context paramContext, int paramInt, String paramString, long paramLong)
  {
    super(paramContext, paramInt, paramLong);
    if (paramString.startsWith("content://"))
    {
      g = Uri.parse(paramString);
      paramString = a(paramContext, g);
      p = paramString;
      c = paramString;
      Log.v("RetrieveTransaction", "X-Mms-Content-Location: " + p);
      l = "";
      a(or.a(paramContext));
      return;
    }
    throw new IllegalArgumentException("Initializing from X-Mms-Content-Location is abandoned!");
  }
  
  private String a(Context paramContext, Uri paramUri)
  {
    paramContext = paramContext.getContentResolver().query(paramUri, a, null, null, null);
    q = false;
    if (paramContext != null) {}
    try
    {
      if ((paramContext.getCount() == 1) && (paramContext.moveToFirst()))
      {
        if (paramContext.getInt(1) == 1) {}
        for (boolean bool = true;; bool = false)
        {
          q = bool;
          r = paramContext.getInt(2);
          paramUri = paramContext.getString(0);
          return paramUri;
        }
      }
      throw new MmsException("Cannot get X-Mms-Content-Location from: " + paramUri);
    }
    finally
    {
      paramContext.close();
    }
  }
  
  public void a()
  {
    new Thread(this, "RetrieveTransaction").start();
  }
  
  public void a(String paramString)
  {
    Log.d("RetrieveTransaction", "RetrieveTransaction -> sendAcknowledgeInd");
    SmsManager localSmsManager;
    try
    {
      paramString = Uri.parse(paramString);
      paramString = ((MzRetrieveConf)MzPduPersister.getPduPersister(b).load(paramString)).getTransactionId();
      if (paramString == null) {
        return;
      }
      paramString = new MzAcknowledgeInd(18, paramString);
      paramString.setFrom(new MzEncodedStringValue(wd.a()));
      paramString = a(new MzPduComposer(b, paramString).make(), "AckResp_retr" + g.getLastPathSegment());
      if (paramString == null) {
        return;
      }
      localSmsManager = aac.c(f);
      if (ga.v())
      {
        localSmsManager.sendMultimediaMessage(b, Uri.fromFile(paramString), p, null, null);
        return;
      }
    }
    catch (MmsException paramString)
    {
      paramString.printStackTrace();
      return;
    }
    localSmsManager.sendMultimediaMessage(b, Uri.fromFile(paramString), null, null, null);
  }
  
  public int b()
  {
    return b("RetrieveResult_retr" + g.getLastPathSegment());
  }
  
  public int c()
  {
    return 1;
  }
  
  public void run()
  {
    Log.v("RetrieveTransaction", "RetrieveTransaction: run()");
    try
    {
      zn.a().a(g, 129);
      File localFile = a(null, "RetrieveResult_retr" + g.getLastPathSegment());
      localFile.setWritable(true, false);
      Object localObject = new Intent("com.android.mms.transaction.TRANSACION_PROCESSED");
      ((Intent)localObject).putExtra("subscription", f);
      ((Intent)localObject).putExtra("bundle_uri", g.toString());
      localObject = PendingIntent.getBroadcast(b, 0, (Intent)localObject, 134217728);
      SmsManager localSmsManager = aac.c(f);
      Log.d("RetrieveTransaction", "download MMS with param, mContentLocation = " + p + ", mUri = " + g + ", subId" + f);
      localSmsManager.downloadMultimediaMessage(b, p, Uri.fromFile(localFile), ga.F(), (PendingIntent)localObject);
      return;
    }
    catch (Throwable localThrowable)
    {
      Log.e("RetrieveTransaction", Log.getStackTraceString(localThrowable));
      d.a(2);
      d.a(g);
      d();
    }
  }
}

/* Location:
 * Qualified Name:     oq
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */