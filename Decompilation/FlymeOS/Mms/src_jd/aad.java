import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.Telephony.Sms.Inbox;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.MmsApp;

public class aad
{
  private static final String[] g = { "_id" };
  private ano a;
  private aad.b b;
  private aad.a c;
  private Context d = MmsApp.c().getApplicationContext();
  private Uri e;
  private Handler f = new aae(this);
  
  private int a(int paramInt)
  {
    return 2131493796;
  }
  
  public static void a(Context paramContext)
  {
    new Thread(new aaf(paramContext), "asyncCreateMZAssistant").start();
  }
  
  private void a(Context paramContext, String paramString)
  {
    if (!TextUtils.isEmpty(paramString)) {
      new Thread(new aag(this, paramString, paramContext), "asyncInsertAssistantRecvMsg").start();
    }
  }
  
  private static boolean c()
  {
    return (!MmsApp.a) && (!MmsApp.d);
  }
  
  private static boolean c(Context paramContext)
  {
    if ((c()) && (ga.e(paramContext))) {}
    for (;;)
    {
      try
      {
        Object localObject1 = Uri.parse("content://mms-sms/thread_id").buildUpon();
        ((Uri.Builder)localObject1).appendQueryParameter("recipient", "4007883333");
        localObject1 = ((Uri.Builder)localObject1).build();
        paramContext = paramContext.getContentResolver().query((Uri)localObject1, g, null, null, null);
        Log.d("MzAssistantHelper", "uri = " + localObject1 + ", cursor = " + DatabaseUtils.dumpCursorToString(paramContext));
        if (paramContext == null) {
          break label194;
        }
        try
        {
          if (paramContext.moveToFirst()) {
            l = paramContext.getLong(0);
          }
        }
        finally
        {
          paramContext.close();
        }
      }
      catch (IllegalArgumentException paramContext)
      {
        l = -1L;
      }
      try
      {
        paramContext.close();
        Log.d("MzAssistantHelper", "isNeedCreateMZAssistant() --> threadId = " + l);
        if (l != -1L) {
          break label186;
        }
        return true;
      }
      catch (IllegalArgumentException paramContext)
      {
        for (;;) {}
      }
      Log.d("MzAssistantHelper", "getThreadId returned no rows!");
      long l = -1L;
      continue;
      paramContext.printStackTrace();
      continue;
      label186:
      return false;
      return false;
      label194:
      l = -1L;
    }
  }
  
  public void a()
  {
    b = new aad.b();
    Log.d("MzAssistantHelper", "bind() --> mVoiceAssistantServiceConn = " + b);
    Intent localIntent = new Intent("com.meizu.voiceassistant.support.IVoiceAssistantService");
    localIntent.setPackage("com.meizu.voiceassistant");
    d.bindService(localIntent, b, 1);
  }
  
  public void a(Uri paramUri)
  {
    e = paramUri;
  }
  
  public void a(Uri paramUri, int paramInt)
  {
    if (paramUri == null) {
      return;
    }
    new Thread(new aah(this, paramInt, paramUri), "asyncUpdateMZAssistantSendState").start();
  }
  
  public void a(String paramString)
  {
    if (a == null) {
      return;
    }
    try
    {
      a.a("com.android.mms", paramString);
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public Uri b(String paramString)
  {
    ContentValues localContentValues = new ContentValues(6);
    localContentValues.put("address", "4007883333");
    localContentValues.put("body", paramString);
    localContentValues.put("date", new Long(System.currentTimeMillis()));
    localContentValues.put("read", Integer.valueOf(0));
    localContentValues.put("seen", Integer.valueOf(0));
    localContentValues.put("type", Integer.valueOf(2));
    return d.getContentResolver().insert(Telephony.Sms.Inbox.CONTENT_URI, localContentValues);
  }
  
  public void b()
  {
    if (b != null)
    {
      d.unbindService(b);
      b = null;
      a = null;
    }
  }
  
  public void b(Uri paramUri, int paramInt)
  {
    if (paramUri == null) {
      return;
    }
    ContentValues localContentValues = new ContentValues(1);
    localContentValues.put("type", Integer.valueOf(paramInt));
    d.getContentResolver().update(paramUri, localContentValues, null, null);
  }
  
  public class a
    extends ann.a
  {
    public a() {}
    
    public void a(Intent paramIntent)
    {
      String str = paramIntent.getStringExtra("result_dialog_answer");
      Log.d("MzAssistantHelper", "IVoiceAssistantCallback.Stub | onSuccess | answer = " + str + ", rlt = " + paramIntent);
      aad.a(aad.this, aad.c(aad.this), str);
    }
    
    public void b(Intent paramIntent)
    {
      int i = paramIntent.getIntExtra("error_code", -1);
      Log.d("MzAssistantHelper", "IVoiceAssistantCallback.Stub | onFailure rlt = " + paramIntent + ", errorCode = " + i);
      aad.d(aad.this).sendEmptyMessage(i);
      a(aad.e(aad.this), 5);
    }
  }
  
  public class b
    implements ServiceConnection
  {
    public b() {}
    
    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      Log.d("MzAssistantHelper", "onServiceConnected");
      aad.a(aad.this, ano.a.b(paramIBinder));
      try
      {
        aad.a(aad.this).a("com.android.mms", 4);
        aad.a(aad.this, new aad.a(aad.this));
        aad.a(aad.this).a("com.android.mms", aad.b(aad.this));
        Log.d("MzAssistantHelper", "onServiceConnected | mVoiceAssistantService = " + aad.a(aad.this) + ", mVoiceAssistantCallback = " + aad.b(aad.this));
        return;
      }
      catch (RemoteException paramComponentName)
      {
        for (;;)
        {
          Log.d("MzAssistantHelper", "onServiceConnected | remote exception = " + paramComponentName);
        }
      }
      catch (Exception paramComponentName)
      {
        for (;;)
        {
          Log.d("MzAssistantHelper", "onServiceConnected | exception = " + paramComponentName);
        }
      }
    }
    
    public void onServiceDisconnected(ComponentName paramComponentName)
    {
      Log.d("MzAssistantHelper", "onServiceDisconnected");
      try
      {
        aad.a(aad.this).a("com.android.mms");
        aad.a(aad.this, null);
        return;
      }
      catch (RemoteException paramComponentName)
      {
        Log.d("MzAssistantHelper", "onServiceDisconnected | remote exception = " + paramComponentName);
        return;
      }
      catch (Exception paramComponentName)
      {
        Log.d("MzAssistantHelper", "onServiceDisconnected | exception = " + paramComponentName);
      }
    }
  }
}

/* Location:
 * Qualified Name:     aad
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */