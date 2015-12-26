import android.content.Context;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.MmsApp;
import com.android.mms.MmsApp.e;
import com.android.mms.MmsApp.f;
import java.io.IOException;
import java.util.HashMap;

public class ach
{
  private static final String c = MmsApp.c().getPackageName();
  private static ach d = null;
  private static final HashMap<Uri, ach.a> o = new HashMap();
  AudioManager a = null;
  AudioManager.OnAudioFocusChangeListener b = new acl(this);
  private MediaPlayer e;
  private ach.b f = new ach.b(null);
  private boolean g;
  private int h = 0;
  private aaq i;
  private final MmsApp.f j = MmsApp.c().q();
  private final PowerManager.WakeLock k;
  private PowerManager.WakeLock l;
  private int m = 0;
  private final MmsApp.e n = new aci(this);
  
  private ach()
  {
    j.a(n);
    i = new aaq(MmsApp.c(), j);
    Log.d("VoicePlayer", "VoicePlayer()-->mCurrentAudioMode = " + h);
    PowerManager localPowerManager = (PowerManager)MmsApp.c().getSystemService("power");
    if (localPowerManager.isWakeLockLevelSupported(32)) {}
    for (k = localPowerManager.newWakeLock(32, c);; k = null)
    {
      l = localPowerManager.newWakeLock(536870913, c);
      return;
    }
  }
  
  public static ach a()
  {
    if (d == null) {}
    try
    {
      if (d == null) {
        d = new ach();
      }
      return d;
    }
    finally {}
  }
  
  private final boolean a(int paramInt)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (e != null)
    {
      bool1 = bool2;
      if (e.isPlaying())
      {
        e.seekTo(Math.max(0, m - paramInt));
        bool1 = true;
      }
    }
    return bool1;
  }
  
  private void b(Context paramContext)
  {
    Log.d("VoicePlayer", "doPlay()~~~~");
    e = MediaPlayer.create(paramContext, wd.m(ach.b.a(f)));
    int i1;
    if (e != null)
    {
      a(paramContext);
      e.setAudioStreamType(3);
      e.setOnCompletionListener(new acj(this));
      e.setOnErrorListener(new ack(this));
      i1 = a.requestAudioFocus(b, 3, 2);
      if (i1 != 1) {
        break label223;
      }
      g = true;
      Log.d("VoicePlayer", "doPlay():mAudioPlayer.start()~~~~");
      e.start();
      b(false);
    }
    for (;;)
    {
      e.getCurrentPosition();
      if (l != null)
      {
        l.acquire();
        Log.i("VoicePlayer", "acquire partial wake lock: mPartialWakeLock = " + l);
      }
      if ((k != null) && (!k.isHeld()))
      {
        k.acquire();
        Log.i("VoicePlayer", "acquire Proximity wake lock: mProximityWakeLock = " + k);
      }
      return;
      label223:
      if (i1 == 0) {
        Log.d("VoicePlayer", "request Audio focus fail!");
      }
    }
  }
  
  private void c(boolean paramBoolean)
  {
    i();
    if (paramBoolean) {}
    for (int i1 = 0;; i1 = 3) {
      try
      {
        if (e != null)
        {
          Log.d("VoicePlayer", "filePath = " + ach.b.a(f));
          e.setDataSource(ach.b.a(f));
          e.setAudioStreamType(i1);
          e.setOnCompletionListener(new acm(this));
          e.setOnErrorListener(new acn(this));
          e.prepare();
          e.start();
          a(1000);
        }
        return;
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
      }
    }
  }
  
  private void g()
  {
    Log.e("VoicePlayer", "Error occurred while playing audio.");
    h();
  }
  
  private final void h()
  {
    try
    {
      a(false);
      if (e != null)
      {
        e.stop();
        e.release();
      }
      if ((k != null) && (k.isHeld()))
      {
        k.release();
        Log.i("VoicePlayer", "release Proximity wake lock");
      }
      if ((l != null) && (l.isHeld()))
      {
        l.release();
        Log.i("VoicePlayer", "release Partial wake lock");
      }
      return;
    }
    finally
    {
      b(true);
      h = 0;
      e = null;
      f.a();
      g = false;
      a.abandonAudioFocus(b);
    }
  }
  
  private void i()
  {
    if (e == null)
    {
      e = new MediaPlayer();
      return;
    }
    m = e.getCurrentPosition();
    e.setOnCompletionListener(null);
    e.stop();
    e.reset();
    e = null;
    e = new MediaPlayer();
  }
  
  public void a(Context paramContext)
  {
    i.a();
  }
  
  public final void a(Context paramContext, String paramString, Uri paramUri)
  {
    if (g)
    {
      if (f == null)
      {
        g();
        return;
      }
      if (f.a(paramString, paramUri))
      {
        h();
        return;
      }
      h();
      ach.b.a(f, paramString, paramUri);
      b(paramContext);
      return;
    }
    ach.b.a(f, paramString, paramUri);
    b(paramContext);
  }
  
  public void a(Uri paramUri)
  {
    Log.d("VoicePlayer", "removePlayerCallback()~~~~");
    synchronized (o)
    {
      o.remove(paramUri);
      return;
    }
  }
  
  public void a(Uri paramUri, ach.a parama)
  {
    Log.d("VoicePlayer", "addPlayerCallback()~~~~");
    synchronized (o)
    {
      o.put(paramUri, parama);
      return;
    }
  }
  
  public void a(boolean paramBoolean)
  {
    i.a(paramBoolean);
    if (j != null) {
      j.c();
    }
  }
  
  public boolean a(String paramString, Uri paramUri)
  {
    if (f == null) {}
    while ((!g) || (!f.a(paramString, paramUri))) {
      return false;
    }
    return true;
  }
  
  public final void b()
  {
    Log.i("VoicePlayer", "flushPlayer():MmsApp.getIsScreenOffByPhoneCell() = " + MmsApp.r());
    if (MmsApp.r()) {
      return;
    }
    h();
    c();
  }
  
  public void b(boolean paramBoolean)
  {
    if (o.containsKey(ach.b.b(f)))
    {
      if (!paramBoolean) {
        ((ach.a)o.get(ach.b.b(f))).a(ach.b.b(f));
      }
    }
    else {
      return;
    }
    ((ach.a)o.get(ach.b.b(f))).c(ach.b.b(f));
    a(ach.b.b(f));
  }
  
  public void c()
  {
    Log.d("VoicePlayer", "removeAllCallback()~~~~");
    synchronized (o)
    {
      o.clear();
      return;
    }
  }
  
  public int d()
  {
    if (e != null) {
      return e.getCurrentPosition();
    }
    return 0;
  }
  
  public int e()
  {
    if (e != null) {
      return e.getDuration();
    }
    return 0;
  }
  
  public static abstract interface a
  {
    public abstract void a(Uri paramUri);
    
    public abstract void b(Uri paramUri);
    
    public abstract void c(Uri paramUri);
  }
  
  final class b
  {
    private String b = null;
    private Uri c = null;
    
    private b() {}
    
    private void b(String paramString, Uri paramUri)
    {
      b = paramString;
      c = paramUri;
    }
    
    public void a()
    {
      b = null;
      c = null;
    }
    
    public boolean a(String paramString, Uri paramUri)
    {
      if ((TextUtils.isEmpty(paramString)) || (paramUri == null) || (TextUtils.isEmpty(paramUri.toString()))) {}
      while ((!paramString.equals(b)) || (!paramUri.equals(c))) {
        return false;
      }
      return true;
    }
  }
}

/* Location:
 * Qualified Name:     ach
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */