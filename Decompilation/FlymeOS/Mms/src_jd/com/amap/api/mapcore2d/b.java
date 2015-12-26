package com.amap.api.mapcore2d;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;
import com.amap.api.maps2d.AMap.CancelableCallback;
import com.amap.api.maps2d.AMap.InfoWindowAdapter;
import com.amap.api.maps2d.AMap.OnCameraChangeListener;
import com.amap.api.maps2d.AMap.OnInfoWindowClickListener;
import com.amap.api.maps2d.AMap.OnMapClickListener;
import com.amap.api.maps2d.AMap.OnMapLoadedListener;
import com.amap.api.maps2d.AMap.OnMapLongClickListener;
import com.amap.api.maps2d.AMap.OnMapScreenShotListener;
import com.amap.api.maps2d.AMap.OnMapTouchListener;
import com.amap.api.maps2d.AMap.OnMarkerClickListener;
import com.amap.api.maps2d.AMap.OnMarkerDragListener;
import com.amap.api.maps2d.AMap.OnMyLocationChangeListener;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.CameraPosition.Builder;
import com.amap.api.maps2d.model.CircleOptions;
import com.amap.api.maps2d.model.GroundOverlayOptions;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.maps2d.model.PolygonOptions;
import com.amap.api.maps2d.model.PolylineOptions;
import com.amap.api.maps2d.model.Text;
import com.amap.api.maps2d.model.TextOptions;
import com.amap.api.maps2d.model.TileOverlay;
import com.amap.api.maps2d.model.TileOverlayOptions;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

class b
  extends View
  implements GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener, ag, bm.b, bv.a, t.a, v.a
{
  private cm A;
  private bu B;
  private LocationSource C;
  private x D;
  private a E = null;
  private boolean F = false;
  private boolean G = false;
  private AMap.OnCameraChangeListener H;
  private s I;
  private AMap.CancelableCallback J = null;
  private bh K;
  private boolean L = false;
  private boolean M = false;
  private View N;
  private AMap.OnInfoWindowClickListener O;
  private AMap.InfoWindowAdapter P;
  private bk Q;
  private AMap.OnMarkerClickListener R;
  private Drawable S = null;
  private aq T;
  private boolean U = false;
  private boolean V = false;
  private boolean W = false;
  private AMap.OnMarkerDragListener Z;
  public bb a;
  private int aA = 0;
  private int aB = 0;
  private boolean aC = false;
  private a aD = null;
  private Thread aE = new f(this);
  private AMap.OnMapTouchListener aa;
  private AMap.OnMapLongClickListener ab;
  private AMap.OnMapLoadedListener ac;
  private AMap.OnMapClickListener ad;
  private boolean ae = false;
  private AMap.OnMapScreenShotListener af = null;
  private Timer ag = null;
  private TimerTask ah = new c(this);
  private Handler ai = new d(this);
  private Point aj;
  private GestureDetector ak;
  private bm.a al;
  private ArrayList<GestureDetector.OnGestureListener> am = new ArrayList();
  private ArrayList<bm.b> an = new ArrayList();
  private Scroller ao;
  private int ap = 0;
  private int aq = 0;
  private Matrix ar = new Matrix();
  private float as = 1.0F;
  private boolean at = false;
  private float au;
  private float av;
  private int aw;
  private int ax;
  private long ay = 0L;
  private int az = 0;
  int[] b = new int[2];
  boolean c = false;
  bd d = new bd(this);
  cn e;
  public bg f;
  protected au g;
  public cc h;
  public be i;
  final Handler j = new e(this);
  private Context k;
  private bl l;
  private boolean m = false;
  private boolean n = true;
  private Marker o;
  private ak p;
  private final int[] q = { 10000000, 5000000, 2000000, 1000000, 500000, 200000, 100000, 50000, 30000, 20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50, 25, 10, 5 };
  private boolean r = true;
  private int s = 1;
  private int t = 0;
  private ay u;
  private Location v;
  private g w;
  private AMap.OnMyLocationChangeListener x;
  private boolean y = true;
  private bn z;
  
  public b(Context paramContext)
  {
    super(paramContext);
    R();
    setClickable(true);
    a(paramContext, null);
  }
  
  public b(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
    a(paramContext, paramAttributeSet);
  }
  
  private b(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    R();
    k = paramContext;
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, new int[] { 16843281 });
    if (0 != 0) {
      throw new NullPointerException();
    }
    localTypedArray.getString(0);
    a(paramContext, paramAttributeSet);
  }
  
  private void R()
  {
    Method[] arrayOfMethod = View.class.getMethods();
    int i2 = arrayOfMethod.length;
    int i1 = 0;
    Method localMethod;
    if (i1 < i2)
    {
      localMethod = arrayOfMethod[i1];
      if (!localMethod.getName().equals("setLayerType")) {}
    }
    for (;;)
    {
      if (localMethod != null) {}
      try
      {
        localMethod.invoke(this, new Object[] { Integer.valueOf(View.class.getField("LAYER_TYPE_SOFTWARE").getInt(null)), null });
        return;
      }
      catch (Exception localException)
      {
        cy.a(localException, "AMapDelegateImpGLSurfaceView", "setLayerType");
        return;
      }
      i1 += 1;
      break;
      Object localObject = null;
    }
  }
  
  private void S()
  {
    a(k);
    ViewGroup.LayoutParams localLayoutParams = new ViewGroup.LayoutParams(-1, -1);
    f.addView(this, 0, localLayoutParams);
  }
  
  private void T()
  {
    l.a();
    if (a != null)
    {
      a.a(true);
      a.e();
    }
    a = null;
    l = null;
  }
  
  private void U()
  {
    if (L) {
      L = false;
    }
    u localu;
    if (W)
    {
      W = false;
      localu = u.a();
      i = true;
      d.a(localu);
    }
    if (M)
    {
      M = false;
      localu = u.a();
      i = true;
      d.a(localu);
    }
    V = false;
    if ((Z != null) && (o != null))
    {
      Z.onMarkerDragEnd(o);
      o = null;
      p = null;
    }
  }
  
  private void V()
  {
    if (aj == null) {
      return;
    }
    int i1 = aj.x;
    int i2 = az;
    int i3 = aj.y;
    int i4 = aA;
    aj.x = az;
    aj.y = aA;
    a.c(i1 - i2, i3 - i4);
  }
  
  private CameraPosition W()
  {
    ae localae = C();
    if (localae == null) {
      return null;
    }
    return CameraPosition.fromLatLngZoom(new LatLng(localae.b() / 1000000.0D, localae.a() / 1000000.0D), f());
  }
  
  private void X()
  {
    if (B == null) {
      return;
    }
    int i1 = getWidth();
    Object localObject = new aa();
    aa localaa = new aa();
    a(0, 0, (aa)localObject);
    a(i1, 0, localaa);
    int i3 = (int)f();
    int i2 = q[i3];
    i1 = i2;
    if (y.i == 512) {
      i1 = i2 * 256 / 512;
    }
    i2 = (int)(K.h[i3] * y.k / 0.0254D);
    localObject = cy.b(i1);
    B.a(i2);
    B.a((String)localObject);
    B.invalidate();
  }
  
  private LatLng Y()
  {
    ae localae = C();
    if (localae == null) {
      return null;
    }
    return new LatLng(z.a(localae.b()), z.a(localae.a()));
  }
  
  private an Z()
  {
    ae localae = C();
    an localan = new an();
    a = ((int)localae.e());
    b = ((int)localae.f());
    return localan;
  }
  
  private LatLng a(LatLng paramLatLng)
  {
    an localan = new an();
    b(latitude, longitude, localan);
    b -= 60;
    paramLatLng = new aa();
    a(a, b, paramLatLng);
    return new LatLng(b, a);
  }
  
  private void a(float paramFloat1, PointF paramPointF, float paramFloat2, float paramFloat3)
  {
    int i3;
    int i4;
    try
    {
      boolean bool = g.f();
      if (!bool) {
        return;
      }
    }
    catch (RemoteException paramPointF)
    {
      cy.a(paramPointF, "AMapDelegateImpGLSurfaceView", "doScale");
      aB = 2;
      i3 = l.b.c() / 2;
      i4 = l.b.d() / 2;
      if (paramFloat1 <= 0.0F) {
        break label219;
      }
    }
    e.a(l.b.e() + 1);
    L();
    int i1 = (int)Math.floor(paramFloat1);
    int i2 = 1;
    label102:
    if (i2 != 0) {
      i1 += l.b.e();
    }
    for (;;)
    {
      i1 = a(i1);
      if (i1 == l.b.e()) {
        break;
      }
      b[0] = b[1];
      b[1] = i1;
      if (b[0] == b[1]) {
        break;
      }
      paramPointF = l.a.a(i3, i4);
      l.b.a(i1);
      l.b.a(paramPointF);
      return;
      label219:
      if (paramFloat1 >= 0.0F) {
        break;
      }
      e.a(l.b.e() - 1);
      L();
      i1 = (int)Math.floor(Math.abs(paramFloat1));
      i2 = 0;
      break label102;
      i1 = l.b.e() - i1;
    }
  }
  
  private void a(int paramInt1, int paramInt2)
  {
    if (aj == null) {
      return;
    }
    az = paramInt1;
    aA = paramInt2;
    V();
  }
  
  private void a(int paramInt1, int paramInt2, an paraman)
  {
    int i1 = (int)f();
    Object localObject = new PointF(paramInt1, paramInt2);
    localObject = K.a((PointF)localObject, K.i, K.k, K.h[i1], K.l);
    if (paraman != null)
    {
      a = ((int)((ae)localObject).e());
      b = ((int)((ae)localObject).f());
    }
  }
  
  private void a(Context paramContext)
  {
    aj = null;
    ak = new GestureDetector(this);
    al = bm.a(paramContext, this);
    ao = new Scroller(paramContext);
    new DisplayMetrics();
    paramContext = paramContext.getApplicationContext().getResources().getDisplayMetrics();
    aw = widthPixels;
    ax = heightPixels;
    ap = (widthPixels / 2);
    aq = (heightPixels / 2);
  }
  
  private void a(Context paramContext, AttributeSet paramAttributeSet)
  {
    k = paramContext;
    T = new bs(this);
    setBackgroundColor(Color.rgb(222, 215, 214));
    v.a().a(this);
    bv.a().a(this);
    t.a().a(this);
    E = new a(this);
    w = new g(this);
    I = new s(paramContext);
    h = new cc(k, this);
    l = new bl(k, this);
    h.a(true);
    K = l.h;
    a = new bb(l);
    g = new ci(this);
    e = new cn(k, a, this);
    f = new bg(k, this);
    u = new ay(k, d, this);
    A = new cm(k, this);
    B = new bu(k, this);
    D = new x(k, d, this);
    i = new be(k, paramAttributeSet, this);
    paramContext = new ViewGroup.LayoutParams(-1, -1);
    S();
    f.addView(h, paramContext);
    f.addView(A, paramContext);
    f.addView(B, paramContext);
    paramContext = new bg.a(paramContext);
    f.addView(i, paramContext);
    paramContext = new bg.a(-2, -2, new LatLng(0.0D, 0.0D), 0, 0, 83);
    f.addView(e, paramContext);
    paramContext = new bg.a(-2, -2, new LatLng(0.0D, 0.0D), 0, 0, 83);
    f.addView(u, paramContext);
    try
    {
      if (!q().d()) {
        u.setVisibility(8);
      }
      D.setVisibility(8);
      paramContext = new bg.a(-2, -2, new LatLng(0.0D, 0.0D), 0, 0, 51);
      f.addView(D, paramContext);
      z = new bn(this);
      e.setId(m.a);
    }
    catch (RemoteException paramContext)
    {
      for (;;)
      {
        try
        {
          aE.setName("AuthThread");
          aE.start();
          if (ag == null)
          {
            ag = new Timer();
            ag.schedule(ah, 10000L, 1000L);
          }
          return;
        }
        catch (Exception paramContext)
        {
          cy.a(paramContext, "AMapDelegateImpGLSurfaceView", "initEnviornment");
        }
        paramContext = paramContext;
        cy.a(paramContext, "AMapDelegateImpGLSurfaceView", "initEnviornment");
      }
    }
  }
  
  private void a(MotionEvent paramMotionEvent)
  {
    if ((V) && (p != null) && (o != null))
    {
      int i1 = (int)paramMotionEvent.getX();
      int i2 = (int)(paramMotionEvent.getY() - 60.0F);
      paramMotionEvent = new aa();
      a(i1, i2, paramMotionEvent);
      paramMotionEvent = new LatLng(b, a);
      if ((p != null) && (p.h()))
      {
        p.a(paramMotionEvent);
        if (Z != null) {
          Z.onMarkerDrag(o);
        }
      }
    }
  }
  
  private void a(boolean paramBoolean, CameraPosition paramCameraPosition)
  {
    if (H == null) {}
    while ((!I.a()) || (!isEnabled())) {
      return;
    }
    CameraPosition localCameraPosition1 = paramCameraPosition;
    if (paramCameraPosition == null) {}
    try
    {
      localCameraPosition1 = g();
      try
      {
        H.onCameraChangeFinish(localCameraPosition1);
        return;
      }
      catch (Throwable paramCameraPosition)
      {
        paramCameraPosition.printStackTrace();
        return;
      }
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        cy.a(localRemoteException, "AMapDelegateImpGLSurfaceView", "cameraChangeFinish");
        CameraPosition localCameraPosition2 = paramCameraPosition;
      }
    }
  }
  
  private boolean b(MotionEvent paramMotionEvent)
  {
    boolean bool2 = al.a(paramMotionEvent, getWidth(), getHeight());
    boolean bool1 = bool2;
    if (!bool2) {
      bool1 = ak.onTouchEvent(paramMotionEvent);
    }
    if ((paramMotionEvent.getAction() == 1) && (W)) {
      t.a().b();
    }
    if (paramMotionEvent.getAction() == 2) {
      a(paramMotionEvent);
    }
    if (paramMotionEvent.getAction() == 1) {
      U();
    }
    return bool1;
  }
  
  Point A()
  {
    return A.c();
  }
  
  public boolean B()
  {
    return n;
  }
  
  public ae C()
  {
    if ((l == null) || (l.b == null)) {
      return null;
    }
    return l.b.f();
  }
  
  public bb D()
  {
    return a;
  }
  
  public boolean E()
  {
    if ((l == null) || (l.d == null)) {}
    aw localaw;
    do
    {
      return false;
      localaw = ad.b(ad.f);
    } while (localaw == null);
    return f;
  }
  
  public boolean F()
  {
    boolean bool = E();
    if (a() == null) {}
    Object localObject;
    do
    {
      do
      {
        return false;
      } while (bool);
      localObject = ad.g;
      localObject = ad.b((String)localObject);
    } while (localObject == null);
    return f;
  }
  
  public bm G()
  {
    return al;
  }
  
  public float H()
  {
    return as;
  }
  
  public void I()
  {
    au = 0.0F;
    av = 0.0F;
  }
  
  public int J()
  {
    return 0;
  }
  
  protected void K()
  {
    j.sendEmptyMessage(10);
  }
  
  void L()
  {
    j.sendEmptyMessage(15);
  }
  
  public void M()
  {
    if (J != null) {
      J.onCancel();
    }
  }
  
  public void N()
  {
    if (J != null) {
      J.onFinish();
    }
  }
  
  public void O()
  {
    j.sendEmptyMessage(17);
  }
  
  public void P()
  {
    postInvalidate();
    f.postInvalidate();
  }
  
  public List<Marker> Q()
  {
    if ((getWidth() > 0) && (getHeight() > 0)) {}
    for (boolean bool = true;; bool = false)
    {
      cp.a(bool, "地图未初始化完成！");
      return i.g();
    }
  }
  
  public int a(int paramInt)
  {
    int i1 = paramInt;
    if (paramInt < l.b.b()) {
      i1 = l.b.b();
    }
    paramInt = i1;
    if (i1 > l.b.a()) {
      paramInt = l.b.a();
    }
    return paramInt;
  }
  
  public ah a(CircleOptions paramCircleOptions)
  {
    w localw = new w(this);
    localw.b(paramCircleOptions.getFillColor());
    localw.a(paramCircleOptions.getCenter());
    localw.a(paramCircleOptions.isVisible());
    localw.b(paramCircleOptions.getStrokeWidth());
    localw.a(paramCircleOptions.getZIndex());
    localw.a(paramCircleOptions.getStrokeColor());
    localw.a(paramCircleOptions.getRadius());
    if (l == null) {
      return null;
    }
    l.f.a(localw);
    invalidate();
    return localw;
  }
  
  public ai a(GroundOverlayOptions paramGroundOverlayOptions)
  {
    af localaf = new af(this);
    localaf.b(paramGroundOverlayOptions.getAnchorU(), paramGroundOverlayOptions.getAnchorV());
    localaf.c(paramGroundOverlayOptions.getBearing());
    localaf.a(paramGroundOverlayOptions.getWidth(), paramGroundOverlayOptions.getHeight());
    localaf.a(paramGroundOverlayOptions.getImage());
    localaf.a(paramGroundOverlayOptions.getLocation());
    localaf.a(paramGroundOverlayOptions.getBounds());
    localaf.d(paramGroundOverlayOptions.getTransparency());
    localaf.a(paramGroundOverlayOptions.isVisible());
    localaf.a(paramGroundOverlayOptions.getZIndex());
    if (l == null) {
      return null;
    }
    l.f.a(localaf);
    invalidate();
    return localaf;
  }
  
  public ao a(PolygonOptions paramPolygonOptions)
  {
    bp localbp = new bp(this);
    localbp.a(paramPolygonOptions.getFillColor());
    localbp.a(paramPolygonOptions.getPoints());
    localbp.a(paramPolygonOptions.isVisible());
    localbp.b(paramPolygonOptions.getStrokeWidth());
    localbp.a(paramPolygonOptions.getZIndex());
    localbp.b(paramPolygonOptions.getStrokeColor());
    if (l == null) {
      return null;
    }
    l.f.a(localbp);
    invalidate();
    return localbp;
  }
  
  public ap a(PolylineOptions paramPolylineOptions)
  {
    bq localbq = new bq(this);
    localbq.a(paramPolylineOptions.getColor());
    localbq.b(paramPolylineOptions.isDottedLine());
    localbq.c(paramPolylineOptions.isGeodesic());
    localbq.a(paramPolylineOptions.getPoints());
    localbq.a(paramPolylineOptions.isVisible());
    localbq.b(paramPolylineOptions.getWidth());
    localbq.a(paramPolylineOptions.getZIndex());
    if (l == null) {
      return null;
    }
    af.a(localbq);
    invalidate();
    return localbq;
  }
  
  public bl a()
  {
    return l;
  }
  
  public Marker a(MarkerOptions paramMarkerOptions)
  {
    paramMarkerOptions = new bk(paramMarkerOptions, i);
    i.a(paramMarkerOptions);
    invalidate();
    return new Marker(paramMarkerOptions);
  }
  
  public Text a(TextOptions paramTextOptions)
  {
    paramTextOptions = new by(this, paramTextOptions, i);
    i.a(paramTextOptions);
    invalidate();
    return new Text(paramTextOptions);
  }
  
  public TileOverlay a(TileOverlayOptions paramTileOverlayOptions)
  {
    paramTileOverlayOptions = new cb(paramTileOverlayOptions, h);
    h.a(paramTileOverlayOptions);
    invalidate();
    return new TileOverlay(paramTileOverlayOptions);
  }
  
  public void a(double paramDouble1, double paramDouble2, ab paramab)
  {
    int i1 = (int)f();
    Object localObject = new ae((int)z.a(paramDouble1), (int)z.a(paramDouble2));
    localObject = K.b((ae)localObject, K.i, K.k, K.h[i1]);
    if (paramab != null)
    {
      a = x;
      b = y;
    }
  }
  
  public void a(double paramDouble1, double paramDouble2, an paraman)
  {
    ae localae = K.b(new ae((int)(paramDouble1 * 1000000.0D), (int)(1000000.0D * paramDouble2)));
    a = localae.a();
    b = localae.b();
  }
  
  public void a(float paramFloat)
  {
    if (z != null) {
      z.a(paramFloat);
    }
  }
  
  public void a(float paramFloat, Point paramPoint, boolean paramBoolean)
  {
    if (a == null) {}
    an localan1;
    do
    {
      float f1;
      do
      {
        return;
        f1 = f();
      } while (cy.b(f1 + paramFloat) - f1 == 0.0F);
      new an();
      localan1 = Z();
    } while (paramPoint == null);
    an localan2 = new an();
    a(x, y, localan2);
    int i2 = a - a;
    int i1 = b - b;
    i2 = (int)(i2 / Math.pow(2.0D, paramFloat) - i2);
    i1 = (int)(i1 / Math.pow(2.0D, paramFloat) - i1);
    a = (i2 + a);
    b += i1;
    paramPoint = new ae(b, a, false);
    paramPoint = l.h.b(paramPoint);
    if (paramBoolean)
    {
      a.b(paramPoint, 1000);
      return;
    }
    a.a(paramPoint);
    t.a().b();
  }
  
  public void a(int paramInt1, int paramInt2, aa paramaa)
  {
    int i1 = (int)f();
    Object localObject = new PointF(paramInt1, paramInt2);
    localObject = K.a((PointF)localObject, K.i, K.k, K.h[i1], K.l);
    if (paramaa != null)
    {
      double d1 = z.a(((ae)localObject).b());
      double d2 = z.a(((ae)localObject).a());
      b = d1;
      a = d2;
    }
  }
  
  public void a(Location paramLocation)
  {
    if (paramLocation == null) {
      return;
    }
    LatLng localLatLng = new LatLng(paramLocation.getLatitude(), paramLocation.getLongitude());
    try
    {
      if ((!n()) || (C == null))
      {
        z.a();
        z = null;
        return;
      }
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "AMapDelegateImpGLSurfaceView", "showMyLocationOverlay");
    }
    for (;;)
    {
      z.a(localLatLng, paramLocation.getAccuracy());
      if ((x != null) && ((v == null) || (v.getBearing() != paramLocation.getBearing()) || (v.getAccuracy() != paramLocation.getAccuracy()) || (v.getLatitude() != paramLocation.getLatitude()) || (v.getLongitude() != paramLocation.getLongitude()))) {
        x.onMyLocationChange(paramLocation);
      }
      v = new Location(paramLocation);
      return;
      if ((z == null) || (v == null))
      {
        if (z == null) {
          z = new bn(this);
        }
        if (localLatLng != null) {
          a(u.a(localLatLng, l.b.e()));
        }
      }
    }
  }
  
  public void a(ak paramak)
  {
    int i2 = -2;
    if (paramak == null) {}
    while ((paramak.f() == null) && (paramak.g() == null)) {
      return;
    }
    t();
    Object localObject = new Marker(paramak);
    if (P != null) {
      N = P.getInfoWindow((Marker)localObject);
    }
    try
    {
      if (S == null) {
        S = bo.a(k, "infowindow_bg2d.9.png");
      }
      if ((N == null) && (P != null)) {
        N = P.getInfoContents((Marker)localObject);
      }
      if (N != null)
      {
        if (N.getBackground() == null) {
          N.setBackgroundDrawable(S);
        }
        localObject = N.getLayoutParams();
        N.setDrawingCacheEnabled(true);
        N.setDrawingCacheQuality(0);
        ab localab = paramak.e();
        if (localObject == null) {
          break label387;
        }
        i1 = width;
        i2 = height;
        localObject = new bg.a(i1, i2, paramak.c(), -(int)a + paramak.n() / 2, -(int)b + 2, 81);
        Q = ((bk)paramak);
        f.addView(N, (ViewGroup.LayoutParams)localObject);
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        cy.a(localException, "AMapDelegateImpGLSurfaceView", "showInfoWindow");
        continue;
        localObject = new LinearLayout(k);
        ((LinearLayout)localObject).setBackgroundDrawable(S);
        TextView localTextView1 = new TextView(k);
        localTextView1.setText(paramak.f());
        localTextView1.setTextColor(-16777216);
        TextView localTextView2 = new TextView(k);
        localTextView2.setTextColor(-16777216);
        localTextView2.setText(paramak.g());
        ((LinearLayout)localObject).setOrientation(1);
        ((LinearLayout)localObject).addView(localTextView1);
        ((LinearLayout)localObject).addView(localTextView2);
        N = ((View)localObject);
        continue;
        label387:
        int i1 = -2;
      }
    }
  }
  
  public void a(u paramu)
  {
    E.a(paramu);
  }
  
  public void a(u paramu, long paramLong, AMap.CancelableCallback paramCancelableCallback)
  {
    boolean bool;
    if (a == u.a.j)
    {
      if ((getWidth() > 0) && (getHeight() > 0))
      {
        bool = true;
        cp.a(bool, "the map must have a size");
      }
    }
    else {
      if (a != null) {
        break label49;
      }
    }
    label49:
    do
    {
      return;
      bool = false;
      break;
      if (!I.a())
      {
        I.a(true);
        if (J != null) {
          J.onCancel();
        }
      }
      J = paramCancelableCallback;
      if (F) {
        G = true;
      }
      if (a != u.a.h) {
        break label151;
      }
      K();
    } while ((l == null) || (!m));
    a.c((int)b, (int)c);
    postInvalidate();
    return;
    label151:
    if (a == u.a.b)
    {
      a.c();
      return;
    }
    if (a == u.a.e)
    {
      a.d();
      return;
    }
    int i1;
    if (a == u.a.f)
    {
      i1 = (int)d;
      a.c(i1);
      return;
    }
    if (a == u.a.g)
    {
      a(e, h, true);
      return;
    }
    int i2;
    if (a == u.a.i)
    {
      paramu = f;
      a.c((int)zoom);
      i1 = (int)(target.latitude * 1000000.0D);
      i2 = (int)(target.longitude * 1000000.0D);
      a.b(new ae(i1, i2), (int)paramLong);
      return;
    }
    if (a == u.a.c)
    {
      paramu = f;
      i1 = (int)(target.latitude * 1000000.0D);
      i2 = (int)(target.longitude * 1000000.0D);
      a.b(new ae(i1, i2), (int)paramLong);
      return;
    }
    if ((a == u.a.j) || (a == u.a.k))
    {
      K();
      a(paramu, true, paramLong);
      return;
    }
    i = true;
    d.a(paramu);
  }
  
  public void a(u paramu, AMap.CancelableCallback paramCancelableCallback)
  {
    a(paramu, 250L, paramCancelableCallback);
  }
  
  protected void a(u paramu, boolean paramBoolean, long paramLong)
  {
    if (a == null) {
      return;
    }
    for (;;)
    {
      try
      {
        paramu = g;
        int i1 = (int)(northeast.latitude * 1000000.0D - southwest.latitude * 1000000.0D);
        int i2 = (int)(northeast.longitude * 1000000.0D - southwest.longitude * 1000000.0D);
        paramu = new ae((int)((northeast.latitude * 1000000.0D + southwest.latitude * 1000000.0D) / 2.0D), (int)((northeast.longitude * 1000000.0D + southwest.longitude * 1000000.0D) / 2.0D));
        if (paramBoolean)
        {
          a.b(paramu, (int)paramLong);
          a.a(i1, i2);
          t.a().b();
          return;
        }
      }
      catch (Exception paramu)
      {
        cy.a(paramu, "AMapDelegateImpGLSurfaceView", "newLatLngBoundsWithSize");
        return;
      }
      a.a(paramu);
    }
  }
  
  public void a(AMap.InfoWindowAdapter paramInfoWindowAdapter)
  {
    P = paramInfoWindowAdapter;
  }
  
  public void a(AMap.OnCameraChangeListener paramOnCameraChangeListener)
  {
    H = paramOnCameraChangeListener;
  }
  
  public void a(AMap.OnInfoWindowClickListener paramOnInfoWindowClickListener)
  {
    O = paramOnInfoWindowClickListener;
  }
  
  public void a(AMap.OnMapClickListener paramOnMapClickListener)
  {
    ad = paramOnMapClickListener;
  }
  
  public void a(AMap.OnMapLoadedListener paramOnMapLoadedListener)
  {
    ac = paramOnMapLoadedListener;
  }
  
  public void a(AMap.OnMapLongClickListener paramOnMapLongClickListener)
  {
    ab = paramOnMapLongClickListener;
  }
  
  public void a(AMap.OnMapScreenShotListener paramOnMapScreenShotListener)
  {
    af = paramOnMapScreenShotListener;
    U = true;
  }
  
  public void a(AMap.OnMapTouchListener paramOnMapTouchListener)
  {
    aa = paramOnMapTouchListener;
  }
  
  public void a(AMap.OnMarkerClickListener paramOnMarkerClickListener)
  {
    R = paramOnMarkerClickListener;
  }
  
  public void a(AMap.OnMarkerDragListener paramOnMarkerDragListener)
  {
    Z = paramOnMarkerDragListener;
  }
  
  public void a(AMap.OnMyLocationChangeListener paramOnMyLocationChangeListener)
  {
    x = paramOnMyLocationChangeListener;
  }
  
  public void a(LocationSource paramLocationSource)
  {
    C = paramLocationSource;
    if (paramLocationSource != null)
    {
      u.a(true);
      return;
    }
    u.a(false);
  }
  
  public void a(MyLocationStyle paramMyLocationStyle)
  {
    if (o() != null) {
      o().a(paramMyLocationStyle);
    }
  }
  
  public void a(boolean paramBoolean) {}
  
  public boolean a(float paramFloat1, float paramFloat2)
  {
    a.a(true);
    if (at)
    {
      au += paramFloat1;
      av += paramFloat2;
    }
    invalidate();
    return at;
  }
  
  public boolean a(float paramFloat, PointF paramPointF)
  {
    l.d.c = false;
    K();
    a(paramFloat, paramPointF, au, av);
    at = false;
    postInvalidateDelayed(8L);
    l.a(true);
    t.a().b();
    return true;
  }
  
  public boolean a(Matrix paramMatrix)
  {
    return false;
  }
  
  public boolean a(PointF paramPointF)
  {
    for (;;)
    {
      boolean bool;
      try
      {
        bool = g.f();
        if (!bool) {
          return false;
        }
      }
      catch (RemoteException paramPointF)
      {
        cy.a(paramPointF, "AMapDelegateImpGLSurfaceView", "startScale");
      }
      try
      {
        bool = q().f();
        if (!bool) {}
      }
      catch (RemoteException paramPointF)
      {
        for (;;)
        {
          cy.a(paramPointF, "AMapDelegateImpGLSurfaceView", "startScale");
        }
      }
    }
    l.a(n);
    l.d.a(true);
    l.d.c = true;
    at = true;
    return true;
  }
  
  public boolean a(String paramString)
  {
    if (l == null) {
      return false;
    }
    return l.f.b(paramString);
  }
  
  protected PointF b(PointF paramPointF)
  {
    PointF localPointF = new PointF();
    int i1 = getWidth();
    int i2 = getHeight();
    float f1 = x - (i1 >> 1);
    float f2 = y - (i2 >> 1);
    double d2 = Math.atan2(f2, f1);
    double d1 = Math.pow(f1, 2.0D);
    d1 = Math.sqrt(Math.pow(f2, 2.0D) + d1);
    d2 -= J() * 3.141592653589793D / 180.0D;
    x = ((float)(Math.cos(d2) * d1 + (i1 >> 1)));
    d2 = Math.sin(d2);
    y = ((float)((i2 >> 1) + d1 * d2));
    return localPointF;
  }
  
  public bh b()
  {
    return K;
  }
  
  public bk b(MarkerOptions paramMarkerOptions)
  {
    paramMarkerOptions = new bk(paramMarkerOptions, i);
    i.a(paramMarkerOptions);
    invalidate();
    return paramMarkerOptions;
  }
  
  public void b(double paramDouble1, double paramDouble2, an paraman)
  {
    int i1 = (int)f();
    Object localObject = new ae((int)z.a(paramDouble1), (int)z.a(paramDouble2));
    localObject = K.b((ae)localObject, K.i, K.k, K.h[i1]);
    if (paraman != null)
    {
      a = ((int)x);
      b = ((int)y);
    }
  }
  
  public void b(float paramFloat)
  {
    as = paramFloat;
  }
  
  public void b(int paramInt)
  {
    if (paramInt == 2)
    {
      s = 2;
      h(true);
      A.a(true);
    }
    for (;;)
    {
      postInvalidate();
      return;
      s = 1;
      h(false);
      A.a(false);
    }
  }
  
  public void b(int paramInt1, int paramInt2, aa paramaa)
  {
    if (paramaa != null)
    {
      a = z.a(paramInt1);
      b = z.a(paramInt2);
    }
  }
  
  public void b(u paramu)
  {
    a(paramu, null);
  }
  
  public void b(boolean paramBoolean)
  {
    i(paramBoolean);
    postInvalidate();
  }
  
  public boolean b(Matrix paramMatrix)
  {
    try
    {
      boolean bool = g.f();
      if (!bool) {
        return false;
      }
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "AMapDelegateImpGLSurfaceView", "onScale");
      ar.set(paramMatrix);
      postInvalidate();
    }
    return true;
  }
  
  public boolean b(ak paramak)
  {
    if ((Q != null) && (N != null)) {
      return Q.d().equals(paramak.d());
    }
    return false;
  }
  
  public boolean b(String paramString)
  {
    try
    {
      paramString = i.a(paramString);
      if (paramString != null) {
        return i.b(paramString);
      }
    }
    catch (RemoteException paramString)
    {
      for (;;)
      {
        cy.a(paramString, "AMapDelegateImpGLSurfaceView", "removeMarker");
        paramString = null;
      }
    }
    return false;
  }
  
  public int c()
  {
    if ((l == null) || (l.b == null)) {
      return 0;
    }
    return l.b.c();
  }
  
  protected PointF c(PointF paramPointF)
  {
    PointF localPointF = new PointF();
    int i1 = getWidth();
    int i2 = getHeight();
    float f1 = x - (i1 >> 1);
    float f2 = y - (i2 >> 1);
    double d2 = Math.atan2(f2, f1);
    double d1 = Math.pow(f1, 2.0D);
    d1 = Math.sqrt(Math.pow(f2, 2.0D) + d1);
    d2 += J() * 3.141592653589793D / 180.0D;
    x = ((float)(Math.cos(d2) * d1 + (i1 >> 1)));
    d2 = Math.sin(d2);
    y = ((float)((i2 >> 1) + d1 * d2));
    return localPointF;
  }
  
  public void c(int paramInt)
  {
    if (A != null)
    {
      A.a(paramInt);
      A.invalidate();
      if (B.getVisibility() == 0) {
        B.invalidate();
      }
    }
  }
  
  public void c(String paramString)
  {
    if ((l == null) || (l.d == null)) {}
    while (E()) {
      return;
    }
    l.d.a(paramString);
  }
  
  public void c(boolean paramBoolean)
  {
    if (C != null) {
      if (paramBoolean)
      {
        C.activate(w);
        u.a(true);
        if (z == null) {
          z = new bn(this);
        }
        if (!paramBoolean) {
          break label124;
        }
        u.setVisibility(0);
      }
    }
    for (;;)
    {
      y = paramBoolean;
      return;
      if (z != null)
      {
        z.a();
        z = null;
      }
      v = null;
      C.deactivate();
      u.a(false);
      break;
      u.a(false);
      break;
      label124:
      u.setVisibility(8);
    }
  }
  
  public boolean c(float paramFloat)
  {
    try
    {
      boolean bool = g.f();
      if (!bool) {
        return false;
      }
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "AMapDelegateImpGLSurfaceView", "onScale");
      b(paramFloat);
    }
    return false;
  }
  
  public void computeScroll()
  {
    if (ao.computeScrollOffset())
    {
      int i1 = ao.getCurrX();
      int i2 = ap;
      int i3 = ao.getCurrY();
      int i4 = aq;
      ap = ao.getCurrX();
      aq = ao.getCurrY();
      ae localae = l.a.a(i1 - i2 + l.h.k.x, i3 - i4 + l.h.k.y);
      if (ao.isFinished())
      {
        t.a().b();
        if (H != null) {
          a(true, W());
        }
        l.b.a(false, false);
        return;
      }
      l.b.b(localae);
      return;
    }
    super.computeScroll();
  }
  
  public int d()
  {
    if ((l == null) || (l.b == null)) {
      return 0;
    }
    return l.b.d();
  }
  
  public void d(int paramInt)
  {
    if (e != null)
    {
      e.a(paramInt);
      e.invalidate();
    }
  }
  
  public void d(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      e.setVisibility(0);
      return;
    }
    e.setVisibility(8);
  }
  
  public View e()
  {
    return f;
  }
  
  void e(int paramInt)
  {
    t = paramInt;
  }
  
  public void e(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      u.setVisibility(0);
      return;
    }
    u.setVisibility(8);
  }
  
  public float f()
  {
    if ((l == null) || (l.b == null)) {
      return 0.0F;
    }
    try
    {
      int i1 = l.b.e();
      return i1;
    }
    catch (Exception localException)
    {
      cy.a(localException, "AMapDelegateImpGLSurfaceView", "getZoomLevel");
    }
    return 0.0F;
  }
  
  public void f(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      D.setVisibility(0);
      return;
    }
    D.setVisibility(8);
  }
  
  public CameraPosition g()
  {
    LatLng localLatLng = Y();
    if (localLatLng == null) {
      return null;
    }
    float f1 = f();
    return CameraPosition.builder().target(localLatLng).zoom(f1).build();
  }
  
  public void g(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      B.setVisibility(0);
      L();
      return;
    }
    B.a("");
    B.a(0);
    B.setVisibility(8);
  }
  
  public float h()
  {
    if ((l == null) || (l.b == null)) {
      return y.c;
    }
    return l.b.a();
  }
  
  public void h(boolean paramBoolean)
  {
    if (E() == paramBoolean) {}
    boolean bool;
    do
    {
      return;
      bool = F();
      i(false);
      if (!paramBoolean)
      {
        ad.a(ad.f, false);
        ad.a(ad.e, true);
        if (bool == true) {
          i(true);
        }
        ab.a(false, false);
        return;
      }
    } while (l == null);
    if (ad.b(ad.f) != null)
    {
      ad.a(ad.f, true);
      if (bool == true) {
        i(true);
      }
      ab.a(false, false);
      return;
    }
    aw localaw = new aw();
    j = new b.1(this);
    a = ad.f;
    e = true;
    d = true;
    f = true;
    g = true;
    b = y.c;
    c = y.d;
    ad.a(localaw, getContext());
    ad.a(ad.f, true);
    if (bool == true) {
      i(true);
    }
    ab.a(false, false);
  }
  
  public float i()
  {
    if ((l == null) || (l.b == null)) {
      return y.d;
    }
    return l.b.b();
  }
  
  public void i(boolean paramBoolean)
  {
    if (paramBoolean == F()) {}
    boolean bool;
    do
    {
      return;
      bool = E();
    } while ((l == null) || (bool));
    String str = ad.g;
    if (!paramBoolean)
    {
      ad.a(str, false);
      ab.a(false, false);
      return;
    }
    if (ad.b(str) != null)
    {
      ad.a(str, true);
      ab.a(false, false);
      return;
    }
    aw localaw;
    if (bool == true)
    {
      localaw = new aw();
      h = true;
      i = 120000L;
      a = str;
      e = false;
      d = true;
      f = true;
      g = false;
      b = 18;
      c = 9;
      ad.a(localaw, getContext());
    }
    for (;;)
    {
      ad.a(str, true);
      ab.a(false, false);
      return;
      localaw = new aw();
      h = true;
      i = 120000L;
      j = new b.2(this);
      a = str;
      e = false;
      d = true;
      f = true;
      g = false;
      b = 18;
      c = 9;
      ad.a(localaw, getContext());
    }
  }
  
  public void j()
  {
    if (a == null) {
      return;
    }
    if (!I.a())
    {
      I.a(true);
      t.a().b();
      if (J != null) {
        J.onCancel();
      }
      J = null;
    }
    a.a(true);
  }
  
  public void k()
  {
    try
    {
      t();
      if (l == null) {
        return;
      }
      l.f.a();
      i.c();
      if (z != null) {
        z.a();
      }
      invalidate();
      return;
    }
    catch (Exception localException)
    {
      cy.a(localException, "AMapDelegateImpGLSurfaceView", "clear");
      Log.d("amapApi", "AMapDelegateImpGLSurfaceView clear erro" + localException.getMessage());
      return;
    }
    catch (Throwable localThrowable)
    {
      cy.a(localThrowable, "AMapDelegateImpGLSurfaceView", "clear");
    }
  }
  
  public int l()
  {
    return s;
  }
  
  public boolean m()
  {
    return F();
  }
  
  public boolean n()
  {
    return y;
  }
  
  public bn o()
  {
    return z;
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
  }
  
  public boolean onDoubleTap(MotionEvent paramMotionEvent)
  {
    try
    {
      boolean bool = g.f();
      if (bool) {
        break label27;
      }
    }
    catch (RemoteException localRemoteException)
    {
      label27:
      do
      {
        cy.a(localRemoteException, "AMapDelegateImpGLSurfaceView", "onDoubleTap");
        if (r) {
          a.b((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
        }
      } while (aB > 1);
      aC = true;
      e.a(l.b.e() + 1);
    }
    return true;
    return true;
  }
  
  public boolean onDoubleTapEvent(MotionEvent paramMotionEvent)
  {
    return false;
  }
  
  public boolean onDown(MotionEvent paramMotionEvent)
  {
    W = false;
    if ((!aC) && (!I.a()))
    {
      I.a(true);
      if (J != null) {
        J.onCancel();
      }
      J = null;
    }
    aC = false;
    aB = 0;
    if (aj == null)
    {
      aj = new Point((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
      return true;
    }
    aj.set((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
    return true;
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    if (U)
    {
      setDrawingCacheEnabled(true);
      buildDrawingCache();
      Bitmap localBitmap = getDrawingCache();
      Message localMessage = new Message();
      what = 16;
      obj = localBitmap;
      j.sendMessage(localMessage);
      U = false;
    }
    l.b.a(getWidth(), getHeight());
    l.d.a(paramCanvas, ar, au, av);
    if (!I.a()) {
      j.sendEmptyMessage(13);
    }
    if (!ae)
    {
      j.sendEmptyMessage(11);
      ae = true;
    }
  }
  
  public boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
  {
    if ((al.k) || (paramMotionEvent1.getEventTime() - al.o < 30L)) {}
    for (;;)
    {
      return true;
      invalidate();
      W = false;
      try
      {
        boolean bool = g.e();
        if (!bool) {}
      }
      catch (RemoteException paramMotionEvent1)
      {
        for (;;)
        {
          cy.a(paramMotionEvent1, "AMapDelegateImpGLSurfaceView", "onFling");
        }
      }
    }
    J = null;
    ao.fling(ap, aq, (int)-paramFloat1 * 3 / 5, (int)-paramFloat2 * 3 / 5, -aw, aw, -ax, ax);
    return true;
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    boolean bool2 = false;
    boolean bool1;
    if (l == null) {
      bool1 = true;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (!m);
      if (l.d.a(paramInt, paramKeyEvent)) {
        break;
      }
      bool1 = bool2;
    } while (!a.onKey(this, paramInt, paramKeyEvent));
    return true;
  }
  
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    boolean bool2 = false;
    boolean bool1;
    if (l == null) {
      bool1 = true;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (!m);
      if (l.d.b(paramInt, paramKeyEvent)) {
        break;
      }
      bool1 = bool2;
    } while (!a.onKey(this, paramInt, paramKeyEvent));
    return true;
  }
  
  public void onLongPress(MotionEvent paramMotionEvent)
  {
    W = false;
    if (ab != null)
    {
      aa localaa = new aa();
      a((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY(), localaa);
      ab.onMapLongClick(new LatLng(b, a));
      L = true;
    }
    p = i.a(paramMotionEvent);
    if (p == null) {}
    do
    {
      return;
      o = new Marker(p);
    } while ((Z == null) || (p == null) || (!p.h()));
    paramMotionEvent = a(p.c());
    p.a(paramMotionEvent);
    i.c(p);
    Z.onMarkerDragStart(o);
    V = true;
  }
  
  protected final void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    super.onRestoreInstanceState(paramParcelable);
  }
  
  protected Parcelable onSaveInstanceState()
  {
    return super.onSaveInstanceState();
  }
  
  public boolean onScroll(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
  {
    if ((al.k) || (paramMotionEvent2.getEventTime() - al.o < 30L)) {
      return true;
    }
    try
    {
      if (!g.e())
      {
        W = false;
        return true;
      }
    }
    catch (RemoteException paramMotionEvent1)
    {
      cy.a(paramMotionEvent1, "AMapDelegateImpGLSurfaceView", "onScroll");
      if (aB > 1)
      {
        W = false;
        return true;
      }
      W = true;
      a((int)paramMotionEvent2.getX(), (int)paramMotionEvent2.getY());
      postInvalidate();
      K();
    }
    return true;
  }
  
  public void onShowPress(MotionEvent paramMotionEvent) {}
  
  public boolean onSingleTapConfirmed(MotionEvent paramMotionEvent)
  {
    return false;
  }
  
  public boolean onSingleTapUp(MotionEvent paramMotionEvent)
  {
    if (a == null) {
      return false;
    }
    l.d.b(paramMotionEvent);
    Object localObject = am.iterator();
    while (((Iterator)localObject).hasNext()) {
      ((GestureDetector.OnGestureListener)((Iterator)localObject).next()).onSingleTapUp(paramMotionEvent);
    }
    W = false;
    if (L)
    {
      L = false;
      return true;
    }
    try
    {
      if (N != null)
      {
        localObject = new Rect(N.getLeft(), N.getTop(), N.getRight(), N.getBottom());
        if ((i.a((Rect)localObject, (int)paramMotionEvent.getX(), (int)paramMotionEvent.getY())) && (O != null))
        {
          paramMotionEvent = i.e();
          if (!paramMotionEvent.s()) {
            return true;
          }
          paramMotionEvent = new Marker(paramMotionEvent);
          O.onInfoWindowClick(paramMotionEvent);
          return true;
        }
      }
      if (i.b(paramMotionEvent))
      {
        paramMotionEvent = i.e();
        if ((paramMotionEvent == null) || (!paramMotionEvent.s())) {
          break label416;
        }
        localObject = new Marker(paramMotionEvent);
        if (R != null) {
          if ((R.onMarkerClick((Marker)localObject)) || (i.b() <= 0))
          {
            i.c(paramMotionEvent);
            return true;
          }
        }
        try
        {
          if ((i.e() != null) && (!paramMotionEvent.q()))
          {
            localObject = paramMotionEvent.c();
            if (localObject != null)
            {
              a.a(cy.a((LatLng)localObject));
              t.a().b();
            }
          }
        }
        catch (RemoteException localRemoteException)
        {
          for (;;)
          {
            cy.a(localRemoteException, "AMapDelegateImpGLSurfaceView", "onSingleTapUp");
          }
        }
        a(paramMotionEvent);
        i.c(paramMotionEvent);
        return true;
      }
    }
    catch (Exception paramMotionEvent)
    {
      cy.a(paramMotionEvent, "AMapDelegateImpGLSurfaceView", "onSingleTapUp");
      return true;
    }
    if (ad != null)
    {
      aa localaa = new aa();
      a((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY(), localaa);
      ad.onMapClick(new LatLng(b, a));
    }
    return true;
    label416:
    return true;
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    Point localPoint = new Point(paramInt1 / 2, paramInt2 / 2);
    l.h.a(localPoint);
    l.b.a(paramInt1, paramInt2);
    if ((a.a() != 0) && (a.b() != 0))
    {
      a.a(a.a(), a.b());
      a.a(0);
      a.b(0);
    }
    u();
    if (aD != null) {
      aD.a(paramInt1, paramInt2, paramInt3, paramInt4);
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (!y.o) {}
    do
    {
      do
      {
        return true;
      } while (l == null);
      if (!m) {
        return false;
      }
      if (aa != null)
      {
        ai.removeMessages(1);
        Message localMessage = ai.obtainMessage();
        what = 1;
        obj = MotionEvent.obtain(paramMotionEvent);
        localMessage.sendToTarget();
      }
    } while (l.d.a(paramMotionEvent));
    b(paramMotionEvent);
    return super.onTouchEvent(paramMotionEvent);
  }
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
  }
  
  public Location p()
  {
    if (C != null) {
      return w.a;
    }
    return null;
  }
  
  public au q()
  {
    return g;
  }
  
  public aq r()
  {
    return T;
  }
  
  public br s()
  {
    return l.a;
  }
  
  public void setClickable(boolean paramBoolean)
  {
    m = paramBoolean;
    super.setClickable(paramBoolean);
  }
  
  public void t()
  {
    if (N != null)
    {
      N.clearFocus();
      N.destroyDrawingCache();
      f.removeView(N);
      Drawable localDrawable = N.getBackground();
      if (localDrawable != null) {
        localDrawable.setCallback(null);
      }
      N = null;
    }
    Q = null;
  }
  
  public void u()
  {
    if ((N != null) && (Q != null))
    {
      bg.a locala = (bg.a)N.getLayoutParams();
      if (locala != null) {
        b = Q.c();
      }
      f.a();
    }
  }
  
  public void v()
  {
    try
    {
      if (ag != null)
      {
        ag.cancel();
        ag = null;
      }
      if (ah != null)
      {
        ah.cancel();
        ah = null;
      }
      if (ai != null) {
        ai.removeCallbacksAndMessages(null);
      }
      if (j != null) {
        j.removeCallbacksAndMessages(null);
      }
      v.a().b(this);
      bv.a().b(this);
      t.a().b(this);
      e.a();
      B.a();
      A.a();
      u.a();
      D.a();
      l.f.b();
      i.f();
      if (S != null) {
        S.setCallback(null);
      }
      f.removeAllViews();
      t();
      if (l != null)
      {
        l.c.b();
        T();
      }
      y.h = null;
      y.g = null;
      ed.c();
      System.gc();
      return;
    }
    catch (Exception localException)
    {
      cy.a(localException, "AMapDelegateImpGLSurfaceView", "destroy");
    }
  }
  
  public float w()
  {
    int i1 = getWidth();
    aa localaa1 = new aa();
    aa localaa2 = new aa();
    a(0, 0, localaa1);
    a(i1, 0, localaa2);
    return (float)(cy.a(new LatLng(b, a), new LatLng(b, a)) / i1);
  }
  
  public LatLngBounds x()
  {
    return null;
  }
  
  public void y()
  {
    if (l != null) {
      l.c.c();
    }
  }
  
  public void z()
  {
    if (l != null) {
      l.c.d();
    }
  }
  
  static abstract class a
  {
    public abstract void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */