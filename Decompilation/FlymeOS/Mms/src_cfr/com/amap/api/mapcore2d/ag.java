/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.location.Location
 *  android.view.View
 *  java.lang.Object
 *  java.lang.String
 */
package com.amap.api.mapcore2d;

import android.location.Location;
import android.view.View;
import com.amap.api.mapcore2d.aa;
import com.amap.api.mapcore2d.ab;
import com.amap.api.mapcore2d.ah;
import com.amap.api.mapcore2d.ai;
import com.amap.api.mapcore2d.an;
import com.amap.api.mapcore2d.ao;
import com.amap.api.mapcore2d.ap;
import com.amap.api.mapcore2d.aq;
import com.amap.api.mapcore2d.au;
import com.amap.api.mapcore2d.bh;
import com.amap.api.mapcore2d.bk;
import com.amap.api.mapcore2d.bl;
import com.amap.api.mapcore2d.br;
import com.amap.api.mapcore2d.u;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.CircleOptions;
import com.amap.api.maps2d.model.GroundOverlayOptions;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.maps2d.model.PolygonOptions;
import com.amap.api.maps2d.model.PolylineOptions;
import com.amap.api.maps2d.model.Text;
import com.amap.api.maps2d.model.TextOptions;
import com.amap.api.maps2d.model.TileOverlay;
import com.amap.api.maps2d.model.TileOverlayOptions;
import java.util.List;

public interface ag {
    public void P();

    public List<Marker> Q();

    public ah a(CircleOptions var1);

    public ai a(GroundOverlayOptions var1);

    public ao a(PolygonOptions var1);

    public ap a(PolylineOptions var1);

    public bl a();

    public Marker a(MarkerOptions var1);

    public Text a(TextOptions var1);

    public TileOverlay a(TileOverlayOptions var1);

    public void a(double var1, double var3, ab var5);

    public void a(float var1);

    public void a(int var1, int var2, aa var3);

    public void a(Location var1);

    public void a(u var1);

    public void a(u var1, long var2, AMap.CancelableCallback var4);

    public void a(u var1, AMap.CancelableCallback var2);

    public void a(AMap.InfoWindowAdapter var1);

    public void a(AMap.OnCameraChangeListener var1);

    public void a(AMap.OnInfoWindowClickListener var1);

    public void a(AMap.OnMapClickListener var1);

    public void a(AMap.OnMapLoadedListener var1);

    public void a(AMap.OnMapLongClickListener var1);

    public void a(AMap.OnMapScreenShotListener var1);

    public void a(AMap.OnMapTouchListener var1);

    public void a(AMap.OnMarkerClickListener var1);

    public void a(AMap.OnMarkerDragListener var1);

    public void a(AMap.OnMyLocationChangeListener var1);

    public void a(LocationSource var1);

    public void a(MyLocationStyle var1);

    public void a(boolean var1);

    public boolean a(String var1);

    public bh b();

    public bk b(MarkerOptions var1);

    public void b(double var1, double var3, an var5);

    public void b(int var1);

    public void b(u var1);

    public void b(boolean var1);

    public boolean b(String var1);

    public int c();

    public void c(int var1);

    public void c(String var1);

    public void c(boolean var1);

    public int d();

    public void d(int var1);

    public void d(boolean var1);

    public View e();

    public void e(boolean var1);

    public float f();

    public void f(boolean var1);

    public CameraPosition g();

    public void g(boolean var1);

    public float h();

    public float i();

    public void j();

    public void k();

    public int l();

    public boolean m();

    public boolean n();

    public Location p();

    public au q();

    public aq r();

    public br s();

    public void v();

    public float w();

    public void y();

    public void z();
}

