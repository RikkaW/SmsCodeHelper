import android.content.Context;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.media.MediaRecorder;
import android.media.MediaRecorder.OnErrorListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.StatFs;
import android.util.Log;
import com.android.mms.MmsApp;
import com.android.mms.view.EditTextEx;
import com.android.mms.view.EditTextEx.a;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class aco
  implements EditTextEx.a
{
  private static aco e = null;
  private static int q = 16000;
  private int A = 0;
  private int B = 0;
  public Context a;
  public EditTextEx b;
  AudioManager c = null;
  AudioManager.OnAudioFocusChangeListener d = new acs(this);
  private boolean f = true;
  private MediaRecorder g;
  private String h = "";
  private boolean i = false;
  private long j = 0L;
  private int k = 0;
  private aco.a l;
  private Object m = new Object();
  private int n;
  private boolean o = false;
  private MediaRecorder.OnErrorListener p = new acp(this);
  private aco.b r;
  private boolean s;
  private final Handler t = new Handler();
  private Runnable u = new acq(this);
  private Runnable v = new acr(this);
  private boolean w = false;
  private long x = 0L;
  private int y = 0;
  private int z = 0;
  
  private aco()
  {
    a("/sdcard/Download/FMessage/");
    r = new aco.b();
    s = false;
    f = wd.f();
    n = 0;
    c = ((AudioManager)MmsApp.c().getSystemService("audio"));
  }
  
  public static aco a()
  {
    if (e == null) {}
    try
    {
      if (e == null) {
        e = new aco();
      }
      return e;
    }
    finally {}
  }
  
  private void a(long paramLong1, long paramLong2, int paramInt)
  {
    Bundle localBundle;
    if (b != null)
    {
      localBundle = new Bundle();
      localBundle.putInt(d("KEY_CURRENT_RADIUX"), paramInt / 4 * 3);
      localBundle.putLong(d("KEY_CURRENT_RECORD_TIME"), paramLong1 / 1000L);
      if (n == 2) {
        break label90;
      }
      localBundle.putInt(d("KEY_CURRENT_COLOR"), e("TIME_COLOR"));
    }
    for (;;)
    {
      b.a(d("ACTION_AMP_REFRESH"), localBundle);
      j = paramLong1;
      return;
      label90:
      if (o)
      {
        o = false;
        localBundle.putInt(d("KEY_CURRENT_COLOR"), 0);
      }
      else
      {
        o = true;
        localBundle.putInt(d("KEY_CURRENT_COLOR"), e("TIME_COLOR"));
      }
    }
  }
  
  private void a(String paramString)
  {
    paramString = new File(paramString);
    if (paramString.exists()) {
      return;
    }
    paramString.mkdirs();
  }
  
  private void a(String paramString, MediaRecorder paramMediaRecorder)
  {
    try
    {
      paramString = paramMediaRecorder.getClass().getDeclaredMethod(paramString, new Class[0]);
      paramString.setAccessible(true);
      paramString.invoke(paramMediaRecorder, new Object[0]);
      return;
    }
    catch (NoSuchMethodException paramString)
    {
      Log.i("VoiceRecorder", "invokeMethod e1 = " + paramString);
      return;
    }
    catch (IllegalAccessException paramString)
    {
      Log.i("VoiceRecorder", "invokeMethod e2 = " + paramString);
      return;
    }
    catch (InvocationTargetException paramString)
    {
      Log.i("VoiceRecorder", "invokeMethod e3 = " + paramString);
    }
  }
  
  private void b(int paramInt)
  {
    if (paramInt <= 0) {
      return;
    }
    wd.a(paramInt, a, 0, 1, true, 0);
  }
  
  private void b(String paramString)
  {
    paramString = new File(paramString);
    if ((paramString.exists()) && (paramString.isFile())) {
      paramString.delete();
    }
  }
  
  /* Error */
  private boolean c(String arg1)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 129	aco:r	Laco$b;
    //   4: invokevirtual 278	aco$b:a	()V
    //   7: aload_0
    //   8: getfield 129	aco:r	Laco$b;
    //   11: sipush 16384
    //   14: invokevirtual 280	aco$b:a	(I)V
    //   17: aload_0
    //   18: getfield 129	aco:r	Laco$b;
    //   21: new 199	java/io/File
    //   24: dup
    //   25: aload_1
    //   26: invokespecial 201	java/io/File:<init>	(Ljava/lang/String;)V
    //   29: ldc2_w 281
    //   32: invokevirtual 285	aco$b:a	(Ljava/io/File;J)V
    //   35: aload_0
    //   36: getfield 78	aco:m	Ljava/lang/Object;
    //   39: astore_2
    //   40: aload_2
    //   41: monitorenter
    //   42: aload_0
    //   43: getfield 156	aco:g	Landroid/media/MediaRecorder;
    //   46: ifnonnull +7 -> 53
    //   49: aload_0
    //   50: invokespecial 287	aco:l	()V
    //   53: aload_0
    //   54: getfield 156	aco:g	Landroid/media/MediaRecorder;
    //   57: aload_1
    //   58: invokevirtual 292	android/media/MediaRecorder:setOutputFile	(Ljava/lang/String;)V
    //   61: aload_0
    //   62: getfield 156	aco:g	Landroid/media/MediaRecorder;
    //   65: invokevirtual 295	android/media/MediaRecorder:prepare	()V
    //   68: aload_0
    //   69: getfield 156	aco:g	Landroid/media/MediaRecorder;
    //   72: invokevirtual 298	android/media/MediaRecorder:start	()V
    //   75: ldc -21
    //   77: ldc_w 300
    //   80: invokestatic 302	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   83: pop
    //   84: aload_0
    //   85: iconst_1
    //   86: putfield 138	aco:n	I
    //   89: aload_2
    //   90: monitorexit
    //   91: aload_0
    //   92: getfield 94	aco:t	Landroid/os/Handler;
    //   95: aload_0
    //   96: getfield 99	aco:u	Ljava/lang/Runnable;
    //   99: invokevirtual 306	android/os/Handler:removeCallbacks	(Ljava/lang/Runnable;)V
    //   102: aload_0
    //   103: getfield 129	aco:r	Laco$b;
    //   106: ldc_w 307
    //   109: sipush 200
    //   112: invokevirtual 310	aco$b:a	(II)V
    //   115: aload_0
    //   116: iconst_1
    //   117: putfield 131	aco:s	Z
    //   120: aload_0
    //   121: getfield 94	aco:t	Landroid/os/Handler;
    //   124: aload_0
    //   125: getfield 99	aco:u	Ljava/lang/Runnable;
    //   128: invokevirtual 314	android/os/Handler:post	(Ljava/lang/Runnable;)Z
    //   131: pop
    //   132: iconst_1
    //   133: ireturn
    //   134: astore_1
    //   135: aload_2
    //   136: monitorexit
    //   137: aload_1
    //   138: athrow
    //   139: astore_1
    //   140: aload_0
    //   141: getfield 78	aco:m	Ljava/lang/Object;
    //   144: astore_1
    //   145: aload_1
    //   146: monitorenter
    //   147: aload_0
    //   148: getfield 156	aco:g	Landroid/media/MediaRecorder;
    //   151: invokevirtual 317	android/media/MediaRecorder:reset	()V
    //   154: aload_0
    //   155: getfield 156	aco:g	Landroid/media/MediaRecorder;
    //   158: invokevirtual 320	android/media/MediaRecorder:release	()V
    //   161: aload_0
    //   162: aconst_null
    //   163: putfield 156	aco:g	Landroid/media/MediaRecorder;
    //   166: aload_1
    //   167: monitorexit
    //   168: aload_0
    //   169: ldc_w 321
    //   172: invokespecial 323	aco:b	(I)V
    //   175: iconst_0
    //   176: ireturn
    //   177: astore_2
    //   178: aload_1
    //   179: monitorexit
    //   180: aload_2
    //   181: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	182	0	this	aco
    //   177	4	2	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   42	53	134	finally
    //   53	91	134	finally
    //   135	137	134	finally
    //   35	42	139	java/lang/Exception
    //   137	139	139	java/lang/Exception
    //   147	168	177	finally
    //   178	180	177	finally
  }
  
  private static String d(String paramString)
  {
    return (String)aau.a("com.meizu.widget.inputmethod.VoiceView", paramString);
  }
  
  private boolean d()
  {
    j = 0L;
    i = true;
    w = false;
    h = (j() + System.currentTimeMillis() + ".amr");
    if (!Log.isLoggable("VoiceRecorder", 3)) {}
    for (;;)
    {
      if (l != null) {
        l.a();
      }
      return c(h);
      Log.d("VoiceRecorder", "----startRecoder()--begin record：" + h);
    }
  }
  
  private static int e(String paramString)
  {
    return ((Integer)aau.a("com.meizu.widget.inputmethod.VoiceView", paramString)).intValue();
  }
  
  private void e()
  {
    if (!Log.isLoggable("VoiceRecorder", 3)) {}
    while ((j < 200L) && (j > 0L))
    {
      a(2131493554);
      return;
      Log.d("VoiceRecorder", "----stopRecoder()--save files：" + h + ", mCurrentRecordTime = " + j);
    }
    f();
    if (l != null)
    {
      if (!MmsApp.a) {
        break label168;
      }
      l.b(wd.m(h));
    }
    for (;;)
    {
      i = false;
      if (w) {
        b.a(d("ACTION_STOP_RECORD"), null);
      }
      if ((c == null) || (d == null)) {
        break;
      }
      c.abandonAudioFocus(d);
      return;
      label168:
      l.a(wd.m(h));
    }
  }
  
  private void f()
  {
    if (g == null) {
      return;
    }
    try
    {
      synchronized (m)
      {
        g.stop();
        g.release();
        g = null;
        return;
      }
      return;
    }
    catch (Exception localException)
    {
      g = null;
    }
  }
  
  private void g()
  {
    if (g == null) {}
    for (;;)
    {
      return;
      try
      {
        if (n == 1)
        {
          r.c();
          a("pause", g);
          n = 2;
          return;
        }
      }
      catch (Exception localException)
      {
        Log.i("VoiceRecorder", "VoiceRecorder pauseRecorder e = " + localException);
        i();
      }
    }
  }
  
  private void h()
  {
    if (g == null) {}
    for (;;)
    {
      return;
      try
      {
        if (n == 2)
        {
          r.e();
          a("resume", g);
          n = 1;
          return;
        }
      }
      catch (Exception localException)
      {
        Log.i("VoiceRecorder", "VoiceRecorder resumeRecorder e = " + localException);
        i();
      }
    }
  }
  
  private void i()
  {
    g.reset();
    g.release();
    g = null;
  }
  
  private final String j()
  {
    return "/sdcard/Download/FMessage/";
  }
  
  private void k()
  {
    int i2 = 10000;
    long l2 = System.currentTimeMillis();
    long l1;
    int i1;
    if (n == 1)
    {
      l1 = r.a(l2);
      l2 = r.b(l2);
      i1 = 0;
      if (g != null) {
        i1 = g.getMaxAmplitude();
      }
      if (i1 <= 10000) {
        break label235;
      }
      i1 = i2;
    }
    label235:
    for (;;)
    {
      a(l1, l2, i1 / 50);
      Log.d("VoiceView", "amplitude = " + i1);
      if (l2 <= 0L) {
        if (l1 == 60000L)
        {
          w = true;
          t.postDelayed(v, 0L);
        }
      }
      while ((r.b() == 2) && ((g == null) || (!s) || (!i)))
      {
        return;
        l1 = r.d();
        break;
        f();
        return;
      }
      if (n != 2)
      {
        t.postDelayed(u, 90L);
        return;
      }
      t.removeCallbacks(u);
      t.postDelayed(u, 500L);
      return;
    }
  }
  
  private void l()
  {
    g = new MediaRecorder();
    g.setAudioSource(1);
    g.setAudioSamplingRate(q);
    g.setOutputFormat(3);
    g.setAudioEncoder(1);
    g.setOnErrorListener(p);
  }
  
  private boolean m()
  {
    Object localObject = aau.a("android.os.ServiceManager", "checkService", String.class, "phone", "com.android.internal.telephony.ITelephony$Stub", "asInterface", IBinder.class, "isIdle", null, null);
    if ((localObject != null) && ((localObject instanceof Boolean))) {
      return ((Boolean)localObject).booleanValue();
    }
    return false;
  }
  
  public void a(int paramInt)
  {
    f();
    i = false;
    if (l != null) {
      l.b();
    }
    b(h);
    if (!Log.isLoggable("VoiceRecorder", 3)) {
      return;
    }
    Log.d("VoiceRecorder", "----cancelRecord()--delete files：" + h);
  }
  
  public void a(Context paramContext, EditTextEx paramEditTextEx, aco.a parama)
  {
    a = paramContext;
    b = paramEditTextEx;
    b.setOnPrivateIMECommandListener(this);
    l = parama;
  }
  
  public void a(Uri paramUri)
  {
    if (paramUri == null) {
      return;
    }
    b(paramUri.getPath());
  }
  
  public boolean a(String paramString, Bundle paramBundle)
  {
    if (d("ACTION_BEGIN_RECORD").equals(paramString))
    {
      if (c.requestAudioFocus(d, 3, 2) != 1)
      {
        if (!m()) {
          b(2131493818);
        }
        Log.d("VoiceRecorder", "request Audio focus fail!");
        return true;
      }
      if (d())
      {
        y = paramBundle.getInt(d("KEY_AMP_MIN_RADIUS"), 0);
        z = paramBundle.getInt(d("KEY_AMP_MAX_RADIUS"), 0);
        x = paramBundle.getLong(d("KEY_RECORD_TOKEN"), 0L);
        b.a(d("ACTION_BEGIN_RECORD"), paramBundle);
        return true;
      }
      a(0);
      b.a(d("ACTION_CANCEL_RECORD"), null);
      return true;
    }
    if (d("ACTION_STOP_RECORD").equals(paramString))
    {
      e();
      b.a(d("ACTION_STOP_RECORD"), null);
      return true;
    }
    if (d("ACTION_CANCEL_RECORD").equals(paramString))
    {
      a(0);
      b.a(d("ACTION_CANCEL_RECORD"), null);
      return true;
    }
    if (d("ACTION_PAUSE_RECORD").equals(paramString))
    {
      g();
      return true;
    }
    if (d("ACTION_RESUME_RECORD").equals(paramString))
    {
      h();
      return true;
    }
    return false;
  }
  
  public boolean a(boolean paramBoolean)
  {
    if (f == paramBoolean) {
      return false;
    }
    f = paramBoolean;
    return true;
  }
  
  public boolean b()
  {
    return i;
  }
  
  public void c()
  {
    l = null;
    b = null;
  }
  
  public static abstract interface a
  {
    public abstract void a();
    
    public abstract void a(Uri paramUri);
    
    public abstract void b();
    
    public abstract void b(Uri paramUri);
  }
  
  public static final class b
  {
    private int a = 0;
    private File b;
    private long c;
    private int d = 1000;
    private long e;
    private long f;
    private long g;
    private long h;
    private long i = -1L;
    private long j = -1L;
    private long k = -1L;
    private long l = 0L;
    private long m = 0L;
    private long n = 0L;
    
    private long c(long paramLong)
    {
      long l1 = 0L;
      StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
      long l2 = localStatFs.getAvailableBlocks() - 32;
      long l3 = localStatFs.getBlockSize();
      if (l2 < 0L) {}
      for (;;)
      {
        if ((e == -1L) || (l1 != f))
        {
          e = paramLong;
          f = l1;
        }
        l1 = f * l3 / d - (paramLong - e) / 1000L;
        if (b == null)
        {
          a = 2;
          return l1;
        }
        b = new File(b.getAbsolutePath());
        l2 = b.length();
        if ((g == -1L) || (l2 != h))
        {
          g = paramLong;
          h = l2;
        }
        paramLong = (c - l2) / d - (paramLong - g) / 1000L - 1L;
        if (l1 < paramLong) {}
        for (int i1 = 2;; i1 = 1)
        {
          a = i1;
          return Math.min(l1, paramLong);
        }
        l1 = l2;
      }
    }
    
    public long a(long paramLong)
    {
      paramLong = paramLong - i - m;
      n = paramLong;
      return paramLong;
    }
    
    public void a()
    {
      a = 0;
      e = -1L;
      g = -1L;
      i = -1L;
      k = -1L;
      j = -1L;
      l = 0L;
      m = 0L;
      n = 0L;
    }
    
    public void a(int paramInt)
    {
      d = (paramInt / 8);
    }
    
    public void a(int paramInt1, int paramInt2)
    {
      i = System.currentTimeMillis();
      k = paramInt1;
      j = paramInt2;
      l = 0L;
      m = 0L;
      n = 0L;
    }
    
    public void a(File paramFile, long paramLong)
    {
      b = paramFile;
      c = paramLong;
    }
    
    public int b()
    {
      return a;
    }
    
    public long b(long paramLong)
    {
      paramLong = 1000L * c(paramLong);
      if (k != -1L)
      {
        long l1 = k - n;
        if (paramLong < l1) {}
        for (int i1 = a;; i1 = 3)
        {
          a = i1;
          return Math.min(paramLong, l1);
        }
      }
      return paramLong;
    }
    
    public void c()
    {
      l = System.currentTimeMillis();
      n = a(l);
    }
    
    public long d()
    {
      return n;
    }
    
    public void e()
    {
      m = (m + System.currentTimeMillis() - l);
    }
  }
}

/* Location:
 * Qualified Name:     aco
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */