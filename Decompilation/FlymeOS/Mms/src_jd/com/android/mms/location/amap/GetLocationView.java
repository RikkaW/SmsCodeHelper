package com.android.mms.location.amap;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewConfiguration;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.AMap.OnMapLoadedListener;
import com.amap.api.maps2d.AMap.OnMapScreenShotListener;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.maps2d.overlay.PoiOverlay;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.android.mms.location.BaseGetLocationView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kf;
import kr;
import kt;
import kv;
import kw;
import ky;
import kz;
import la;
import la.a;
import lb;
import lb.b;
import ld;
import wd;

public class GetLocationView
  extends BaseGetLocationView
  implements AMap.OnMapLoadedListener, AMap.OnMapScreenShotListener
{
  private static int H = 20;
  private boolean A = false;
  private int B;
  private int C;
  private LatLng D;
  private boolean E = false;
  private a F = new a();
  private b G = new b(null);
  private AMap v;
  private UiSettings w;
  private la x;
  private lb y;
  private PoiOverlay z;
  
  public GetLocationView(Context paramContext)
  {
    super(paramContext);
    H = ViewConfiguration.get(paramContext).getScaledTouchSlop();
  }
  
  public GetLocationView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    H = ViewConfiguration.get(paramContext).getScaledTouchSlop();
  }
  
  public GetLocationView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    H = ViewConfiguration.get(paramContext).getScaledTouchSlop();
  }
  
  private void a(LatLng paramLatLng)
  {
    v.moveCamera(CameraUpdateFactory.changeLatLng(paramLatLng));
  }
  
  private void a(LatLng paramLatLng, String paramString1, String paramString2)
  {
    a(paramLatLng);
    a(paramString1, paramString2);
  }
  
  private void a(LatLng paramLatLng, String paramString1, String paramString2, String paramString3)
  {
    a(paramLatLng);
    y.a(paramLatLng, paramString1, paramString2, paramString3);
    a(paramString1, paramString2);
  }
  
  private void a(String paramString1, String paramString2)
  {
    a(paramString1);
    b(paramString2);
    l();
  }
  
  private void o()
  {
    if (v == null)
    {
      v = ((MapView)findViewById(2131886581)).getMap();
      v.setMyLocationStyle(p());
      v.setOnMapLoadedListener(this);
      w = v.getUiSettings();
      w.setZoomControlsEnabled(false);
      w.setLogoPosition(2);
      w.setMyLocationButtonEnabled(false);
      q();
    }
  }
  
  private MyLocationStyle p()
  {
    MyLocationStyle localMyLocationStyle = new MyLocationStyle();
    localMyLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(2130837720));
    localMyLocationStyle.strokeColor(s.getResources().getColor(2131820657));
    localMyLocationStyle.strokeWidth(3.0F);
    localMyLocationStyle.radiusFillColor(s.getResources().getColor(2131820655));
    return localMyLocationStyle;
  }
  
  private void q()
  {
    v.setOnCameraChangeListener(new ky(this));
    v.setOnMapTouchListener(new kz(this));
  }
  
  private void r()
  {
    a(false, x.b());
    y.a(2, getMapCenter());
    A = false;
  }
  
  public void a(double paramDouble1, double paramDouble2)
  {
    SharedPreferences.Editor localEditor = g.edit();
    localEditor.putInt("last_latitude_amap", (int)paramDouble1);
    localEditor.putInt("last_longitude_amap", (int)paramDouble2);
    localEditor.apply();
    g = null;
  }
  
  public void a(PoiItem paramPoiItem)
  {
    paramPoiItem = y.a(paramPoiItem);
    if (paramPoiItem != null) {
      m.a(paramPoiItem);
    }
  }
  
  public void a(kr paramkr)
  {
    ld localld;
    LatLng localLatLng;
    String str1;
    String str2;
    if ((paramkr instanceof ld))
    {
      localld = (ld)paramkr;
      localLatLng = localld.h();
      str1 = paramkr.d();
      str2 = paramkr.b();
      if (localld.j() != null) {
        break label50;
      }
    }
    label50:
    for (paramkr = null;; paramkr = localld.j().getPoiId())
    {
      a(localLatLng, str1, str2, paramkr);
      return;
    }
  }
  
  public void a(kt paramkt)
  {
    super.a(paramkt);
    o();
    x = new la(getContext());
    x.a(F);
    if (v != null)
    {
      v.setLocationSource(x);
      v.setMyLocationEnabled(true);
      Log.i("amap/GetLocationView", "init finish setMyLocationEnabled");
    }
    y = new lb(getContext());
    y.a(G);
    v.setOnMarkerClickListener(y);
  }
  
  public void b(int paramInt)
  {
    kr localkr = (kr)c.getItem(paramInt);
    if ((localkr instanceof ld))
    {
      y.a((ld)localkr);
      return;
    }
    y.a(localkr);
  }
  
  public void b(boolean paramBoolean)
  {
    x.b(paramBoolean);
  }
  
  public void c(String paramString)
  {
    y.b(paramString);
  }
  
  public void d(String paramString)
  {
    e(paramString);
  }
  
  public void e()
  {
    x.a();
  }
  
  public void e(String paramString)
  {
    y.a(paramString, x.e());
  }
  
  public void f()
  {
    if (x.c() != null) {
      a(x.c().latitude, x.c().longitude);
    }
    y.a();
    super.f();
  }
  
  public void g()
  {
    super.g();
    if ((d) && (v != null))
    {
      j();
      m();
      return;
    }
    h.a(2131493499);
  }
  
  public void getCurrentMap()
  {
    if (z != null) {
      z.removeFromMap();
    }
    kf.a(true, 4, "amap/GetLocationView", "getCurrentMap");
    v.getMapScreenShot(this);
  }
  
  public LatLng getMapCenter()
  {
    if (v != null) {
      return v.getCameraPosition().target;
    }
    return null;
  }
  
  public void h()
  {
    Log.i("amap/GetLocationView", "GetLocationView requestLocation");
    x.d();
  }
  
  public void m()
  {
    setUrl(y.c());
    if (b())
    {
      getCurrentMap();
      return;
    }
    a(false, null);
  }
  
  protected void onFinishInflate()
  {
    ((LayoutInflater)s.getSystemService("layout_inflater")).inflate(2130968724, this);
    super.onFinishInflate();
  }
  
  public void onMapLoaded()
  {
    Log.i("amap/GetLocationView", "onMapLoaded mLocateHasChanged = " + E);
    if (!E) {
      v.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(g.getInt("last_latitude_amap", 39945000), g.getInt("last_longitude_amap", 116404000)), 14.0F));
    }
  }
  
  public void onMapScreenShot(Bitmap paramBitmap)
  {
    if (q) {
      return;
    }
    kf.a(true, 4, "amap/GetLocationView", "onMapScreenShot");
    a(paramBitmap);
  }
  
  public void setMoveByUser(boolean paramBoolean)
  {
    A = paramBoolean;
  }
  
  public class a
    implements la.a
  {
    public a() {}
    
    public Activity a()
    {
      return GetLocationView.a(GetLocationView.this);
    }
    
    public void a(int paramInt, LatLng paramLatLng)
    {
      GetLocationView.d(GetLocationView.this).a(paramInt, paramLatLng);
    }
    
    public void a(LatLng paramLatLng, String paramString1, String paramString2)
    {
      GetLocationView.d(GetLocationView.this).a(paramLatLng, paramString1, paramString2);
      GetLocationView.a(GetLocationView.this, paramString1, paramString2);
    }
    
    public void a(LatLng paramLatLng, boolean paramBoolean)
    {
      if ((paramBoolean) && (GetLocationView.b(GetLocationView.this)))
      {
        GetLocationView.c(GetLocationView.this).moveCamera(CameraUpdateFactory.zoomTo(14.0F));
        return;
      }
      GetLocationView.a(GetLocationView.this, paramLatLng);
    }
    
    public void a(String paramString)
    {
      GetLocationView.d(GetLocationView.this).a(paramString);
    }
    
    public void a(ld paramld)
    {
      Log.i("amap/GetLocationView", "GetLocationView onCurrentLocationChanged currentLocation.getAddr = " + paramld.b());
      if (GetLocationView.e(GetLocationView.this).size() == 0)
      {
        GetLocationView.f(GetLocationView.this).b(0);
        GetLocationView.g(GetLocationView.this).add(0, paramld);
      }
      for (;;)
      {
        GetLocationView.i(GetLocationView.this).p().notifyDataSetChanged();
        return;
        GetLocationView.h(GetLocationView.this).set(0, paramld);
      }
    }
    
    public void a(boolean paramBoolean)
    {
      a(paramBoolean, GetLocationView.j(GetLocationView.this).b());
    }
    
    public void b(boolean paramBoolean)
    {
      GetLocationView.a(GetLocationView.this, paramBoolean);
    }
    
    public boolean b()
    {
      return d();
    }
    
    public boolean c()
    {
      return k();
    }
    
    public LatLng d()
    {
      return cgetCameraPositiontarget;
    }
  }
  
  class b
    implements lb.b
  {
    private b() {}
    
    public void a(int paramInt)
    {
      wd.a(paramInt, GetLocationView.m(GetLocationView.this), 0, 1, true, 0);
    }
    
    public void a(LatLng paramLatLng, String paramString1, String paramString2, boolean paramBoolean)
    {
      GetLocationView.a(GetLocationView.this, paramLatLng, paramString1, paramString2);
    }
    
    public void a(PoiResult arg1, int paramInt)
    {
      if (paramInt == 0)
      {
        if ((???.getPois() != null) && (???.getPois().size() > 0))
        {
          ??? = ???.getPois().iterator();
          while (???.hasNext())
          {
            PoiItem localPoiItem = (PoiItem)???.next();
            if (localPoiItem.getLatLonPoint() != null) {
              a(localPoiItem);
            }
          }
        }
        synchronized (n)
        {
          n.notify();
          return;
        }
      }
    }
    
    public void a(String paramString)
    {
      GetLocationView.j(GetLocationView.this).a(paramString);
    }
    
    public void a(String paramString1, String paramString2)
    {
      GetLocationView.a(GetLocationView.this, paramString1, paramString2);
    }
    
    public void a(ArrayList<PoiItem> paramArrayList)
    {
      GetLocationView.c(GetLocationView.this).clear();
      GetLocationView.a(GetLocationView.this, new PoiOverlay(GetLocationView.c(GetLocationView.this), paramArrayList));
      GetLocationView.n(GetLocationView.this).removeFromMap();
      GetLocationView.n(GetLocationView.this).addToMap();
      GetLocationView.n(GetLocationView.this).zoomToSpan();
    }
    
    public void a(List<PoiItem> paramList)
    {
      if (paramList != null)
      {
        GetLocationView.d(GetLocationView.this).a(paramList, GetLocationView.k(GetLocationView.this));
        GetLocationView.l(GetLocationView.this).p().notifyDataSetChanged();
      }
    }
    
    public void a(ld paramld)
    {
      a(paramld, GetLocationView.j(GetLocationView.this).b());
    }
    
    public void a(boolean paramBoolean)
    {
      GetLocationView.b(GetLocationView.this, paramBoolean);
    }
    
    public boolean a()
    {
      return k();
    }
    
    public LatLng b()
    {
      return GetLocationView.j(GetLocationView.this).c();
    }
    
    public void c()
    {
      GetLocationView.this.c();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.location.amap.GetLocationView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */