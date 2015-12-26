import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Set;

public class aie
{
  private static float P = 1.1F;
  private static float Q = 2.2F;
  private static float R = 2.3F;
  private static float S = 3.8F;
  private static int T = 3;
  private static int U = 10;
  private static int V = 2;
  private static int W = 7;
  private static int X = 20;
  private static int Y = 70;
  private static int Z = 120;
  protected static boolean a = false;
  protected static boolean b = true;
  private static int c = 10;
  private static int d = 2;
  private static int e = 10;
  private static int f = 10;
  private static int g = 50;
  private static int h = 200;
  private static Object i = new Object();
  private static aie j;
  private Thread A = null;
  private Looper B = null;
  private agq C = null;
  private Location D = null;
  private agp E = null;
  private Handler F = null;
  private agr G = new agr(this);
  private LocationListener H = new agl(this);
  private BroadcastReceiver I = new agm(this);
  private GpsStatus J = null;
  private int K = 0;
  private int L = 0;
  private HashMap M = null;
  private int N = 0;
  private int O = 0;
  private boolean k = false;
  private boolean l = false;
  private int m = -1;
  private int n = 0;
  private int o = 0;
  private int p = 10000;
  private long q = 0L;
  private Context r;
  private LocationManager s;
  private agf t;
  private agt u;
  private ahd v;
  private agc w;
  private ahc x;
  private ags y;
  private afw z;
  
  private aie(Context paramContext)
  {
    r = paramContext;
    t = agf.a(paramContext);
    z = new afw();
    u = new agt(t);
    w = new agc(paramContext);
    v = new ahd(w);
    x = new ahc(w);
    s = ((LocationManager)r.getSystemService("location"));
    y = ags.a(r);
    y.a(G);
    n();
    List localList = s.getAllProviders();
    if ((localList != null) && (localList.contains("gps")) && (localList.contains("passive"))) {}
    for (boolean bool = true;; bool = false)
    {
      l = bool;
      ahe.a(paramContext);
      return;
    }
  }
  
  private int a(HashMap paramHashMap)
  {
    Object localObject1;
    ArrayList localArrayList;
    int i1;
    Object localObject2;
    int i2;
    if (K > 4)
    {
      localObject1 = new ArrayList();
      localArrayList = new ArrayList();
      paramHashMap = paramHashMap.entrySet().iterator();
      i1 = 0;
      if (paramHashMap.hasNext())
      {
        localObject2 = (List)((Map.Entry)paramHashMap.next()).getValue();
        if (localObject2 == null) {
          break label460;
        }
        localObject2 = a((List)localObject2);
        if (localObject2 == null) {
          break label460;
        }
        ((List)localObject1).add(localObject2);
        i2 = i1 + 1;
        localArrayList.add(Integer.valueOf(i1));
        i1 = i2;
      }
    }
    label460:
    for (;;)
    {
      break;
      if (!((List)localObject1).isEmpty())
      {
        paramHashMap = new double[2];
        i2 = ((List)localObject1).size();
        i1 = 0;
        while (i1 < i2)
        {
          localObject2 = (double[])((List)localObject1).get(i1);
          int i3 = ((Integer)localArrayList.get(i1)).intValue();
          localObject2[0] *= i3;
          localObject2[1] *= i3;
          paramHashMap[0] += localObject2[0];
          paramHashMap[1] += localObject2[1];
          i1 += 1;
        }
        paramHashMap[0] /= i2;
        paramHashMap[1] /= i2;
        double d2 = paramHashMap[0];
        double d3 = paramHashMap[1];
        double d1;
        if (d3 == 0.0D) {
          if (d2 > 0.0D) {
            d1 = 90.0D;
          }
        }
        for (;;)
        {
          localObject1 = new double[2];
          localObject1[0] = Math.sqrt(d2 * d2 + d3 * d3);
          localObject1[1] = d1;
          String.format(Locale.CHINA, "%d,%d,%d,%d", new Object[] { Long.valueOf(Math.round(paramHashMap[0] * 100.0D)), Long.valueOf(Math.round(paramHashMap[1] * 100.0D)), Long.valueOf(Math.round(localObject1[0] * 100.0D)), Long.valueOf(Math.round(localObject1[1] * 100.0D)) });
          if (localObject1[0] > Y) {
            break;
          }
          return 1;
          if (d2 < 0.0D)
          {
            d1 = 270.0D;
          }
          else
          {
            d1 = 0.0D;
            continue;
            d1 = Math.toDegrees(Math.atan(d2 / d3));
          }
        }
        if (localObject1[0] >= Z) {
          return 4;
        }
      }
      return 3;
    }
  }
  
  public static aie a(Context paramContext)
  {
    if (j == null) {}
    synchronized (i)
    {
      if (j == null) {
        j = new aie(paramContext);
      }
      return j;
    }
  }
  
  public static String a(String paramString)
  {
    if (paramString.equals("version")) {
      return "COL.14.1126r";
    }
    return null;
  }
  
  private double[] a(List paramList)
  {
    if ((paramList == null) || (paramList.isEmpty())) {
      return null;
    }
    double[] arrayOfDouble = new double[2];
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (GpsSatellite)localIterator.next();
      if (localObject != null)
      {
        double d1 = 90.0F - ((GpsSatellite)localObject).getElevation();
        double d2 = ((GpsSatellite)localObject).getAzimuth();
        localObject = new double[2];
        localObject[0] = (Math.sin(Math.toRadians(d2)) * d1);
        localObject[1] = (d1 * Math.cos(Math.toRadians(d2)));
        arrayOfDouble[0] += localObject[0];
        arrayOfDouble[1] += localObject[1];
      }
    }
    int i1 = paramList.size();
    arrayOfDouble[0] /= i1;
    arrayOfDouble[1] /= i1;
    return arrayOfDouble;
  }
  
  private void n()
  {
    n = (y.b() * 1000);
    o = y.c();
    agt localagt = u;
    int i1 = n;
    i1 = o;
    agt.a();
  }
  
  public void a()
  {
    ahb.a = true;
    if ((!l) || (t == null)) {}
    while (a) {
      return;
    }
    IntentFilter localIntentFilter = new IntentFilter("android.location.GPS_ENABLED_CHANGE");
    localIntentFilter.addAction("android.location.GPS_FIX_CHANGE");
    b = true;
    r.registerReceiver(I, localIntentFilter);
    s.removeUpdates(H);
    if (B != null)
    {
      B.quit();
      B = null;
    }
    if (A != null)
    {
      A.interrupt();
      A = null;
    }
    A = new agn(this, "");
    A.start();
    t.a();
    a = true;
  }
  
  public void a(int paramInt)
  {
    if ((paramInt != 256) && (paramInt != 8736) && (paramInt != 768)) {
      throw new RuntimeException("invalid Size! must be COLLECTOR_SMALL_SIZE or COLLECTOR_BIG_SIZE or COLLECTOR_MEDIUM_SIZE");
    }
    w.a(paramInt);
  }
  
  public void a(agb paramagb, String paramString)
  {
    boolean bool = y.a(paramString);
    Object localObject;
    int i1;
    if (paramagb != null)
    {
      paramString = paramagb.a();
      if ((bool) && (paramString != null))
      {
        localObject = ((ConnectivityManager)r.getSystemService("connectivity")).getActiveNetworkInfo();
        if ((localObject != null) && (((NetworkInfo)localObject).isConnected()))
        {
          if (((NetworkInfo)localObject).getType() != 1) {
            break label106;
          }
          localObject = y;
          i1 = y.e();
          ((ags)localObject).a(paramString.length + i1);
        }
      }
    }
    for (;;)
    {
      paramagb.a(bool);
      x.a(paramagb);
      return;
      label106:
      localObject = y;
      i1 = y.f();
      ((ags)localObject).b(paramString.length + i1);
    }
  }
  
  public void b()
  {
    ahb.a = false;
    if ((!l) || (t == null)) {}
    while (!a) {
      return;
    }
    if (I != null) {}
    try
    {
      r.unregisterReceiver(I);
      if (t != null) {
        t.w();
      }
      s.removeGpsStatusListener(E);
      s.removeNmeaListener(E);
      E = null;
      s.removeUpdates(H);
      if (B != null)
      {
        B.quit();
        B = null;
      }
      if (A != null)
      {
        A.interrupt();
        A = null;
      }
      if (C != null)
      {
        k = false;
        C.interrupt();
        C = null;
      }
      t.b();
      a = false;
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public void c()
  {
    if (!l) {
      return;
    }
    b();
  }
  
  public agb d()
  {
    if (x == null) {}
    do
    {
      return null;
      e();
    } while (!y.a());
    return x.a(y.d());
  }
  
  public boolean e()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (t != null)
    {
      List localList = t.n();
      bool1 = bool2;
      if (localList != null)
      {
        bool1 = bool2;
        if (localList.size() > 0) {
          bool1 = w.b(((Long)localList.get(0)).longValue());
        }
      }
    }
    return bool1;
  }
  
  public int f()
  {
    if (x != null) {
      return x.a();
    }
    return 0;
  }
}

/* Location:
 * Qualified Name:     aie
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */