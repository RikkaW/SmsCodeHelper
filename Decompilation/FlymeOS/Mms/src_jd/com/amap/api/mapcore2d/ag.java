package com.amap.api.mapcore2d;

import android.location.Location;
import android.view.View;
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

public abstract interface ag
{
  public abstract void P();
  
  public abstract List<Marker> Q();
  
  public abstract ah a(CircleOptions paramCircleOptions);
  
  public abstract ai a(GroundOverlayOptions paramGroundOverlayOptions);
  
  public abstract ao a(PolygonOptions paramPolygonOptions);
  
  public abstract ap a(PolylineOptions paramPolylineOptions);
  
  public abstract bl a();
  
  public abstract Marker a(MarkerOptions paramMarkerOptions);
  
  public abstract Text a(TextOptions paramTextOptions);
  
  public abstract TileOverlay a(TileOverlayOptions paramTileOverlayOptions);
  
  public abstract void a(double paramDouble1, double paramDouble2, ab paramab);
  
  public abstract void a(float paramFloat);
  
  public abstract void a(int paramInt1, int paramInt2, aa paramaa);
  
  public abstract void a(Location paramLocation);
  
  public abstract void a(u paramu);
  
  public abstract void a(u paramu, long paramLong, AMap.CancelableCallback paramCancelableCallback);
  
  public abstract void a(u paramu, AMap.CancelableCallback paramCancelableCallback);
  
  public abstract void a(AMap.InfoWindowAdapter paramInfoWindowAdapter);
  
  public abstract void a(AMap.OnCameraChangeListener paramOnCameraChangeListener);
  
  public abstract void a(AMap.OnInfoWindowClickListener paramOnInfoWindowClickListener);
  
  public abstract void a(AMap.OnMapClickListener paramOnMapClickListener);
  
  public abstract void a(AMap.OnMapLoadedListener paramOnMapLoadedListener);
  
  public abstract void a(AMap.OnMapLongClickListener paramOnMapLongClickListener);
  
  public abstract void a(AMap.OnMapScreenShotListener paramOnMapScreenShotListener);
  
  public abstract void a(AMap.OnMapTouchListener paramOnMapTouchListener);
  
  public abstract void a(AMap.OnMarkerClickListener paramOnMarkerClickListener);
  
  public abstract void a(AMap.OnMarkerDragListener paramOnMarkerDragListener);
  
  public abstract void a(AMap.OnMyLocationChangeListener paramOnMyLocationChangeListener);
  
  public abstract void a(LocationSource paramLocationSource);
  
  public abstract void a(MyLocationStyle paramMyLocationStyle);
  
  public abstract void a(boolean paramBoolean);
  
  public abstract boolean a(String paramString);
  
  public abstract bh b();
  
  public abstract bk b(MarkerOptions paramMarkerOptions);
  
  public abstract void b(double paramDouble1, double paramDouble2, an paraman);
  
  public abstract void b(int paramInt);
  
  public abstract void b(u paramu);
  
  public abstract void b(boolean paramBoolean);
  
  public abstract boolean b(String paramString);
  
  public abstract int c();
  
  public abstract void c(int paramInt);
  
  public abstract void c(String paramString);
  
  public abstract void c(boolean paramBoolean);
  
  public abstract int d();
  
  public abstract void d(int paramInt);
  
  public abstract void d(boolean paramBoolean);
  
  public abstract View e();
  
  public abstract void e(boolean paramBoolean);
  
  public abstract float f();
  
  public abstract void f(boolean paramBoolean);
  
  public abstract CameraPosition g();
  
  public abstract void g(boolean paramBoolean);
  
  public abstract float h();
  
  public abstract float i();
  
  public abstract void j();
  
  public abstract void k();
  
  public abstract int l();
  
  public abstract boolean m();
  
  public abstract boolean n();
  
  public abstract Location p();
  
  public abstract au q();
  
  public abstract aq r();
  
  public abstract br s();
  
  public abstract void v();
  
  public abstract float w();
  
  public abstract void y();
  
  public abstract void z();
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.ag
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */