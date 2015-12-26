import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.Resources;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.SparseIntArray;

public class abh
{
  private static abh c;
  private Context a;
  private Handler b;
  private Object d = new Object();
  private aiv e = null;
  private ServiceConnection f = new abi(this);
  
  private abh(Context paramContext)
  {
    a = paramContext;
    b = new Handler();
    b();
  }
  
  public static int a(Context paramContext, boolean paramBoolean)
  {
    if ((!wd.c(paramContext)) && (!wd.e(paramContext))) {
      return 1;
    }
    if (!pj.a())
    {
      if (paramBoolean) {
        b(paramContext);
      }
      return 2;
    }
    return 0;
  }
  
  public static abh a()
  {
    if (c == null) {
      throw new IllegalStateException("Uninitialized.");
    }
    return c;
  }
  
  public static void a(Context paramContext)
  {
    if (c != null) {
      Log.w("SnsMessageManager", "Already initialized.");
    }
    c = new abh(paramContext);
    abh.b.a(paramContext);
  }
  
  private void b()
  {
    if (e == null) {}
    try
    {
      Intent localIntent = new Intent();
      localIntent.setAction("com.meizu.service.IMzSnsService");
      localIntent.setPackage("com.meizu.weiboshare");
      a.bindService(localIntent, f, 1);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public static final void b(Context paramContext)
  {
    Intent localIntent = new Intent("com.meizu.weibo.action.LOGIN");
    localIntent.setFlags(524288);
    try
    {
      paramContext.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException paramContext) {}
  }
  
  private aiv c()
  {
    if (e != null) {
      return e;
    }
    Log.e("SnsMessageManager", "mSnsSmsService is null begin bindSnsSmsService, wait max time is 3000");
    b();
    try
    {
      synchronized (d)
      {
        if (e == null) {
          d.wait(5000L);
        }
        if (e == null) {
          Log.e("SnsMessageManager", "after wait 3000 the mSnsSmsService is null, so bind service is no success...");
        }
        return e;
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;) {}
    }
  }
  
  public String a(String paramString1, String paramString2, String paramString3, byte[] paramArrayOfByte, String paramString4, boolean paramBoolean, String paramString5)
  {
    Log.v("SnsMessageManager", "sendSnsMMS -> destAddress : " + paramString1 + " , userId : " + paramString2 + " , uuid : " + paramString3 + " , attachFilePath : " + paramString4 + " , deliveryReport : " + paramBoolean + " , mimeType : " + paramString5);
    long l = System.currentTimeMillis();
    try
    {
      if (c() != null)
      {
        paramString1 = c().a(paramString1, paramString2, paramString3, paramArrayOfByte, paramString4, paramBoolean, paramString5);
        wd.a("SnsMessageManager", "sendSnsMMS", l);
        return paramString1;
      }
    }
    catch (Exception paramString1)
    {
      Log.e("SnsMessageManager", "sendSnsMMS -> error", paramString1);
    }
    return null;
  }
  
  public void a(String paramString1, String paramString2)
  {
    Log.e("SnsMessageManager", "cancelTransactionSnsAttachment(), uuid is " + paramString1 + ", tovid is " + paramString2);
    long l = System.currentTimeMillis();
    try
    {
      if (c() != null) {
        c().a(paramString1, paramString2);
      }
      wd.a("SnsMessageManager", "cancelTransactionSnsAttachment", l);
      return;
    }
    catch (Exception paramString1)
    {
      for (;;)
      {
        Log.e("SnsMessageManager", "cancelTransactionSnsAttachment error" + paramString1.getMessage());
      }
    }
  }
  
  public void a(String paramString1, String paramString2, PendingIntent paramPendingIntent, String paramString3)
  {
    Log.e("SnsMessageManager", "sendSnsTextMessage");
    long l = System.currentTimeMillis();
    try
    {
      if (c() != null) {
        c().a(paramString1, "", paramString2, "", paramPendingIntent, paramString3);
      }
      wd.a("SnsMessageManager", "sendSnsTextMessage", l);
      return;
    }
    catch (Exception paramString1)
    {
      for (;;)
      {
        Log.e("SnsMessageManager", "sendSnsTextMessage error" + paramString1.getMessage());
      }
    }
  }
  
  public byte[] a(String paramString1, byte[] paramArrayOfByte, String paramString2, String paramString3, long paramLong)
  {
    Log.v("SnsMessageManager", "getSnsMMS -> uuid : " + paramString1 + " , msgBody : " + paramString2 + " , attachFilePath : " + paramString3 + " , mmsSize : " + paramLong);
    long l = System.currentTimeMillis();
    try
    {
      if (c() != null)
      {
        paramString1 = c().a(paramString1, paramArrayOfByte, paramString2, paramString3, paramLong);
        wd.a("SnsMessageManager", "getSnsMMS", l);
        return paramString1;
      }
    }
    catch (Exception paramString1)
    {
      Log.e("SnsMessageManager", "getSnsMMS -> error", paramString1);
    }
    return null;
  }
  
  public static final class a
  {
    public static final String[] a = { "_id", "address", "subject", "uuid" };
    private static a h;
    private final Context b;
    private final Handler c;
    private final SharedPreferences d;
    private boolean e;
    private final SharedPreferences.OnSharedPreferenceChangeListener f = new abj(this);
    private final BroadcastReceiver g = new abk(this);
    
    private a(Context paramContext)
    {
      b = paramContext;
      c = new Handler();
      d = PreferenceManager.getDefaultSharedPreferences(paramContext);
      d.registerOnSharedPreferenceChangeListener(f);
      paramContext.registerReceiver(g, new IntentFilter("android.intent.action.SERVICE_STATE"));
      e = a(d);
    }
    
    public static void a(Context paramContext)
    {
      if (h != null) {
        Log.w("SnsMessageManager.SnsDownloadManager", "Already initialized.");
      }
      h = new a(paramContext);
    }
    
    static boolean a()
    {
      return "true".equals(aau.a("PROPERTY_OPERATOR_ISROAMING", null));
    }
    
    static boolean a(SharedPreferences paramSharedPreferences)
    {
      return a(paramSharedPreferences, a());
    }
    
    static boolean a(SharedPreferences paramSharedPreferences, boolean paramBoolean)
    {
      boolean bool1 = false;
      boolean bool2 = paramSharedPreferences.getBoolean("pref_key_mms_retrieval_during_roaming", false);
      if (paramBoolean)
      {
        paramBoolean = bool1;
        if (!bool2) {}
      }
      else
      {
        paramBoolean = true;
      }
      return paramBoolean;
    }
  }
  
  public static final class b
  {
    public static final SparseIntArray a = new SparseIntArray();
    public static final SparseIntArray b = new SparseIntArray();
    
    static
    {
      a.put(1, 2131493583);
      a.put(2, 2131493585);
    }
    
    public static final String a(Context paramContext, int paramInt1, int paramInt2)
    {
      String str = null;
      Object localObject = paramContext.getResources();
      Integer localInteger = Integer.valueOf(a.get(paramInt1));
      if (((paramInt1 == 1) || (paramInt1 == 2)) && (localInteger != null)) {
        str = ((Resources)localObject).getString(localInteger.intValue());
      }
      do
      {
        do
        {
          return str;
          paramInt1 -= 2;
          if (paramInt1 >= 0) {
            break;
          }
        } while (paramInt2 == -1);
        return paramContext.getResources().getString(paramInt2);
        localObject = ((Resources)localObject).getStringArray(2131361798);
        if (localObject.length > paramInt1) {
          break;
        }
      } while (paramInt2 == -1);
      return paramContext.getResources().getString(paramInt2);
      return localObject[paramInt1];
    }
    
    public static final void a(Context paramContext)
    {
      paramContext = paramContext.getResources().getIntArray(2131361814);
      int i = 0;
      while (i < paramContext.length)
      {
        b.put(paramContext[i], i);
        i += 1;
      }
    }
  }
}

/* Location:
 * Qualified Name:     abh
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */