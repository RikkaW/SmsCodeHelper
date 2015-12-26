import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.provider.Telephony.Mms;
import android.util.Log;
import com.android.mms.MmsApp;
import com.android.mms.transaction.flyme.FlymeTransactionService;
import java.util.HashMap;

public class pe
  extends aba.a
{
  public pe(FlymeTransactionService paramFlymeTransactionService) {}
  
  public void a(String paramString)
  {
    Log.v("FlymeTransactionService", "onFilePause" + paramString);
  }
  
  public void a(String paramString, long paramLong)
  {
    Log.v("FlymeTransactionService", "onFileBegin uuid : " + paramString + " startpos : " + paramLong);
  }
  
  public void a(String paramString, long paramLong1, long paramLong2)
  {
    if (fl.a) {
      Log.v("FlymeTransactionService", "onFileProgressUpdate :" + paramString + "  sendsize:" + paramLong1 + "  totalsize:" + paramLong2);
    }
    if (FlymeTransactionService.a(a).size() > 0) {}
    for (int i = 1; (i & FlymeTransactionService.a(a).containsKey(paramString)) != 0; i = 0) {
      try
      {
        long l = Long.valueOf(((ow)FlymeTransactionService.a(a).get(paramString)).i().getLastPathSegment()).longValue();
        ((ow)FlymeTransactionService.a(a).get(paramString)).a(paramLong1);
        MmsApp.c().a(ContentUris.withAppendedId(Telephony.Mms.CONTENT_URI, l), paramLong1, paramLong2);
        return;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        Log.e("FlymeTransactionService", "onFileProgressUpdate Can't find uri " + ((ow)FlymeTransactionService.a(a).get(paramString)).i());
        return;
      }
    }
    Log.e("FlymeTransactionService", "onFileProgressUpdate Can't find uuid " + paramString);
  }
  
  public void a(String paramString1, String arg2)
  {
    Log.v("FlymeTransactionService", "onFileError :" + paramString1 + "  err:" + ???);
    if (FlymeTransactionService.a(a).size() > 0) {}
    for (int i = 1;; i = 0)
    {
      if ((i & FlymeTransactionService.a(a).containsKey(paramString1)) != 0)
      {
        ((ow)FlymeTransactionService.a(a).get(paramString1)).b(130);
        synchronized (aa).get(paramString1)).i)
        {
          aa).get(paramString1)).i.notify();
          MmsApp.c().b(((ow)FlymeTransactionService.a(a).get(paramString1)).i());
          return;
        }
      }
      Log.e("FlymeTransactionService", "onFileError Can't find uuid " + paramString1);
      return;
    }
  }
  
  public void a(String paramString, byte[] arg2)
  {
    Log.v("FlymeTransactionService", "onFileDownloadFinish :" + paramString);
    if (FlymeTransactionService.a(a).size() > 0) {}
    for (int i = 1;; i = 0)
    {
      if ((i & FlymeTransactionService.a(a).containsKey(paramString)) != 0)
      {
        ((ow)FlymeTransactionService.a(a).get(paramString)).b(128);
        ((ow)FlymeTransactionService.a(a).get(paramString)).f().a(???);
        synchronized (aa).get(paramString)).i)
        {
          aa).get(paramString)).i.notify();
          MmsApp.c().b(((ow)FlymeTransactionService.a(a).get(paramString)).i());
          return;
        }
      }
      Log.e("FlymeTransactionService", "onFileDownloadFinish Can't find uuid " + paramString);
      return;
    }
  }
  
  public void b(String paramString)
  {
    Log.v("FlymeTransactionService", "onFileUploadFinish :" + paramString);
    if (FlymeTransactionService.a(a).size() > 0) {}
    for (int i = 1;; i = 0)
    {
      if ((i & FlymeTransactionService.a(a).containsKey(paramString)) != 0)
      {
        ((ow)FlymeTransactionService.a(a).get(paramString)).b(128);
        synchronized (aa).get(paramString)).i)
        {
          aa).get(paramString)).i.notify();
          MmsApp.c().b(((ow)FlymeTransactionService.a(a).get(paramString)).i());
          return;
        }
      }
      Log.e("FlymeTransactionService", "onFileUploadFinish Can't find uuid " + paramString);
      return;
    }
  }
  
  public void b(String paramString1, String paramString2)
  {
    Log.v("FlymeTransactionService", "onFileGetName :" + paramString1 + ", theNewFileName = " + paramString2);
    if (FlymeTransactionService.a(a).size() > 0) {}
    for (int i = 1; (i & FlymeTransactionService.a(a).containsKey(paramString1)) != 0; i = 0)
    {
      ContentValues localContentValues = new ContentValues(1);
      localContentValues.put("file_link", "/sdcard/Download/FMessage/" + paramString2);
      a.getApplicationContext().getContentResolver().update(((ow)FlymeTransactionService.a(a).get(paramString1)).i(), localContentValues, null, null);
      return;
    }
    Log.e("FlymeTransactionService", "onFileGetName Can't find uuid " + paramString1);
  }
  
  public void c(String paramString)
  {
    Log.v("FlymeTransactionService", "onFileCancel :" + paramString);
    if (FlymeTransactionService.a(a).size() > 0) {}
    for (int i = 1;; i = 0)
    {
      if ((i & FlymeTransactionService.a(a).containsKey(paramString)) != 0)
      {
        ((ow)FlymeTransactionService.a(a).get(paramString)).b(9528);
        synchronized (aa).get(paramString)).i)
        {
          aa).get(paramString)).i.notify();
          MmsApp.c().b(((ow)FlymeTransactionService.a(a).get(paramString)).i());
          return;
        }
      }
      Log.e("FlymeTransactionService", "onFileCancel Can't find uuid " + paramString);
      return;
    }
  }
}

/* Location:
 * Qualified Name:     pe
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */