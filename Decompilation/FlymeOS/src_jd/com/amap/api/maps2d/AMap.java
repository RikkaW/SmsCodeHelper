package com.amap.api.maps2d;

import android.graphics.Bitmap;
import android.location.Location;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import com.amap.api.mapcore2d.ag;
import com.amap.api.mapcore2d.cp;
import com.amap.api.mapcore2d.cy;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.Circle;
import com.amap.api.maps2d.model.CircleOptions;
import com.amap.api.maps2d.model.GroundOverlay;
import com.amap.api.maps2d.model.GroundOverlayOptions;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.maps2d.model.Polygon;
import com.amap.api.maps2d.model.PolygonOptions;
import com.amap.api.maps2d.model.Polyline;
import com.amap.api.maps2d.model.PolylineOptions;
import com.amap.api.maps2d.model.RuntimeRemoteException;
import com.amap.api.maps2d.model.Text;
import com.amap.api.maps2d.model.TextOptions;
import com.amap.api.maps2d.model.TileOverlay;
import com.amap.api.maps2d.model.TileOverlayOptions;
import java.util.List;

public final class AMap
{
  public static final String CHINESE = "zh_cn";
  public static final String ENGLISH = "en";
  public static final int MAP_TYPE_NORMAL = 1;
  public static final int MAP_TYPE_SATELLITE = 2;
  private final ag a;
  private UiSettings b;
  private Projection c;
  
  protected AMap(ag paramag)
  {
    a = paramag;
  }
  
  private ag a()
  {
    return a;
  }
  
  public static String getVersion()
  {
    return "2.5.0";
  }
  
  public final Circle addCircle(CircleOptions paramCircleOptions)
  {
    try
    {
      paramCircleOptions = new Circle(a().a(paramCircleOptions));
      return paramCircleOptions;
    }
    catch (RemoteException paramCircleOptions)
    {
      cy.a(paramCircleOptions, "AMap", "addCircle");
      throw new RuntimeRemoteException(paramCircleOptions);
    }
  }
  
  public final GroundOverlay addGroundOverlay(GroundOverlayOptions paramGroundOverlayOptions)
  {
    try
    {
      paramGroundOverlayOptions = new GroundOverlay(a().a(paramGroundOverlayOptions));
      return paramGroundOverlayOptions;
    }
    catch (RemoteException paramGroundOverlayOptions)
    {
      cy.a(paramGroundOverlayOptions, "AMap", "addGroundOverlay");
      throw new RuntimeRemoteException(paramGroundOverlayOptions);
    }
  }
  
  public final Marker addMarker(MarkerOptions paramMarkerOptions)
  {
    try
    {
      paramMarkerOptions = a().a(paramMarkerOptions);
      return paramMarkerOptions;
    }
    catch (RemoteException paramMarkerOptions)
    {
      cy.a(paramMarkerOptions, "AMap", "addMarker");
      throw new RuntimeRemoteException(paramMarkerOptions);
    }
  }
  
  public final Polygon addPolygon(PolygonOptions paramPolygonOptions)
  {
    try
    {
      paramPolygonOptions = new Polygon(a().a(paramPolygonOptions));
      return paramPolygonOptions;
    }
    catch (RemoteException paramPolygonOptions)
    {
      cy.a(paramPolygonOptions, "AMap", "addPolygon");
      throw new RuntimeRemoteException(paramPolygonOptions);
    }
  }
  
  public final Polyline addPolyline(PolylineOptions paramPolylineOptions)
  {
    try
    {
      paramPolylineOptions = new Polyline(a().a(paramPolylineOptions));
      return paramPolylineOptions;
    }
    catch (RemoteException paramPolylineOptions)
    {
      cy.a(paramPolylineOptions, "AMap", "addPolyline");
      throw new RuntimeRemoteException(paramPolylineOptions);
    }
  }
  
  public final Text addText(TextOptions paramTextOptions)
  {
    try
    {
      paramTextOptions = a.a(paramTextOptions);
      return paramTextOptions;
    }
    catch (RemoteException paramTextOptions)
    {
      cy.a(paramTextOptions, "AMap", "addText");
      throw new RuntimeRemoteException(paramTextOptions);
    }
  }
  
  public final TileOverlay addTileOverlay(TileOverlayOptions paramTileOverlayOptions)
  {
    try
    {
      paramTileOverlayOptions = a().a(paramTileOverlayOptions);
      return paramTileOverlayOptions;
    }
    catch (RemoteException paramTileOverlayOptions)
    {
      cy.a(paramTileOverlayOptions, "AMap", "addtileOverlay");
      throw new RuntimeRemoteException(paramTileOverlayOptions);
    }
  }
  
  public final void animateCamera(CameraUpdate paramCameraUpdate)
  {
    try
    {
      a().b(paramCameraUpdate.a());
      return;
    }
    catch (RemoteException paramCameraUpdate)
    {
      cy.a(paramCameraUpdate, "AMap", "animateCamera");
      throw new RuntimeRemoteException(paramCameraUpdate);
    }
  }
  
  public final void animateCamera(CameraUpdate paramCameraUpdate, long paramLong, CancelableCallback paramCancelableCallback)
  {
    if (paramLong > 0L) {}
    for (boolean bool = true;; bool = false) {
      try
      {
        cp.b(bool, "durationMs must be positive");
        a().a(paramCameraUpdate.a(), paramLong, paramCancelableCallback);
        return;
      }
      catch (RemoteException paramCameraUpdate)
      {
        cy.a(paramCameraUpdate, "AMap", "animateCamera");
        throw new RuntimeRemoteException(paramCameraUpdate);
      }
    }
  }
  
  public final void animateCamera(CameraUpdate paramCameraUpdate, CancelableCallback paramCancelableCallback)
  {
    try
    {
      a().a(paramCameraUpdate.a(), paramCancelableCallback);
      return;
    }
    catch (RemoteException paramCameraUpdate)
    {
      cy.a(paramCameraUpdate, "AMap", "animateCamera");
      throw new RuntimeRemoteException(paramCameraUpdate);
    }
  }
  
  public final void clear()
  {
    try
    {
      if (a() != null) {
        a().k();
      }
      return;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "AMap", "clear");
      throw new RuntimeRemoteException(localRemoteException);
    }
    catch (Throwable localThrowable)
    {
      cy.a(localThrowable, "AMap", "clear");
    }
  }
  
  public final CameraPosition getCameraPosition()
  {
    try
    {
      CameraPosition localCameraPosition = a().g();
      return localCameraPosition;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "AMap", "getCameraPosition");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final List<Marker> getMapScreenMarkers()
  {
    try
    {
      List localList = a.Q();
      return localList;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "AMap", "getMapScreenaMarkers");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void getMapScreenShot(OnMapScreenShotListener paramOnMapScreenShotListener)
  {
    a().a(paramOnMapScreenShotListener);
    invalidate();
  }
  
  public final int getMapType()
  {
    try
    {
      int i = a().l();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "AMap", "getMapType");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final float getMaxZoomLevel()
  {
    return a().h();
  }
  
  public final float getMinZoomLevel()
  {
    return a().i();
  }
  
  public final Location getMyLocation()
  {
    try
    {
      Location localLocation = a().p();
      return localLocation;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "AMap", "getMyLocation");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final Projection getProjection()
  {
    try
    {
      if (c == null) {
        c = new Projection(a().r());
      }
      Projection localProjection = c;
      return localProjection;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "AMap", "getProjection");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public float getScalePerPixel()
  {
    return a().w();
  }
  
  public final UiSettings getUiSettings()
  {
    try
    {
      if (b == null) {
        b = new UiSettings(a().q());
      }
      UiSettings localUiSettings = b;
      return localUiSettings;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "AMap", "getUiSettings");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void invalidate()
  {
    postInvalidate();
  }
  
  public final boolean isMyLocationEnabled()
  {
    try
    {
      boolean bool = a().n();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "AMap", "isMyLocationEnabled");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final boolean isTrafficEnabled()
  {
    try
    {
      boolean bool = a().m();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "AMap", "isTrafficEnable");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final void moveCamera(CameraUpdate paramCameraUpdate)
  {
    try
    {
      a().a(paramCameraUpdate.a());
      return;
    }
    catch (RemoteException paramCameraUpdate)
    {
      cy.a(paramCameraUpdate, "AMap", "moveCamera");
      throw new RuntimeRemoteException(paramCameraUpdate);
    }
  }
  
  public void postInvalidate()
  {
    a().P();
  }
  
  public final void setInfoWindowAdapter(InfoWindowAdapter paramInfoWindowAdapter)
  {
    try
    {
      a().a(paramInfoWindowAdapter);
      return;
    }
    catch (RemoteException paramInfoWindowAdapter)
    {
      cy.a(paramInfoWindowAdapter, "AMap", "setInfoWindowAdapter");
      throw new RuntimeRemoteException(paramInfoWindowAdapter);
    }
  }
  
  public final void setLocationSource(LocationSource paramLocationSource)
  {
    try
    {
      a().a(paramLocationSource);
      return;
    }
    catch (RemoteException paramLocationSource)
    {
      cy.a(paramLocationSource, "AMap", "setLocationSource");
      throw new RuntimeRemoteException(paramLocationSource);
    }
  }
  
  public void setMapLanguage(String paramString)
  {
    try
    {
      a.c(paramString);
      return;
    }
    catch (RemoteException paramString)
    {
      cy.a(paramString, "AMap", "setMapLanguage");
      throw new RuntimeRemoteException(paramString);
    }
  }
  
  public final void setMapType(int paramInt)
  {
    try
    {
      a().b(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "AMap", "setMapType");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final void setMyLocationEnabled(boolean paramBoolean)
  {
    try
    {
      a().c(paramBoolean);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "AMap", "setMyLocationEnabled");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final void setMyLocationRotateAngle(float paramFloat)
  {
    try
    {
      a.a(paramFloat);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "AMap", "setMyLocationRoteteAngle");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final void setMyLocationStyle(MyLocationStyle paramMyLocationStyle)
  {
    try
    {
      a().a(paramMyLocationStyle);
      return;
    }
    catch (RemoteException paramMyLocationStyle)
    {
      cy.a(paramMyLocationStyle, "AMap", "setMyLocationStyle");
      throw new RuntimeRemoteException(paramMyLocationStyle);
    }
  }
  
  public final void setOnCameraChangeListener(OnCameraChangeListener paramOnCameraChangeListener)
  {
    try
    {
      a().a(paramOnCameraChangeListener);
      return;
    }
    catch (RemoteException paramOnCameraChangeListener)
    {
      cy.a(paramOnCameraChangeListener, "AMap", "setOnCameraChangeListener");
      throw new RuntimeRemoteException(paramOnCameraChangeListener);
    }
  }
  
  public final void setOnInfoWindowClickListener(OnInfoWindowClickListener paramOnInfoWindowClickListener)
  {
    try
    {
      a().a(paramOnInfoWindowClickListener);
      return;
    }
    catch (RemoteException paramOnInfoWindowClickListener)
    {
      cy.a(paramOnInfoWindowClickListener, "AMap", "setOnInfoWindowClickListener");
      throw new RuntimeRemoteException(paramOnInfoWindowClickListener);
    }
  }
  
  public final void setOnMapClickListener(OnMapClickListener paramOnMapClickListener)
  {
    try
    {
      a().a(paramOnMapClickListener);
      return;
    }
    catch (RemoteException paramOnMapClickListener)
    {
      cy.a(paramOnMapClickListener, "AMap", "setOnMapClickListener");
      throw new RuntimeRemoteException(paramOnMapClickListener);
    }
  }
  
  public final void setOnMapLoadedListener(OnMapLoadedListener paramOnMapLoadedListener)
  {
    try
    {
      a().a(paramOnMapLoadedListener);
      return;
    }
    catch (RemoteException paramOnMapLoadedListener)
    {
      cy.a(paramOnMapLoadedListener, "AMap", "setOnMapLoadedListener");
      throw new RuntimeRemoteException(paramOnMapLoadedListener);
    }
  }
  
  public final void setOnMapLongClickListener(OnMapLongClickListener paramOnMapLongClickListener)
  {
    try
    {
      a().a(paramOnMapLongClickListener);
      return;
    }
    catch (RemoteException paramOnMapLongClickListener)
    {
      cy.a(paramOnMapLongClickListener, "AMap", "setOnMapLongClickListener");
      throw new RuntimeRemoteException(paramOnMapLongClickListener);
    }
  }
  
  public final void setOnMapTouchListener(OnMapTouchListener paramOnMapTouchListener)
  {
    try
    {
      a.a(paramOnMapTouchListener);
      return;
    }
    catch (RemoteException paramOnMapTouchListener)
    {
      cy.a(paramOnMapTouchListener, "AMap", "setOnMapTouchListener");
      throw new RuntimeRemoteException(paramOnMapTouchListener);
    }
  }
  
  public final void setOnMarkerClickListener(OnMarkerClickListener paramOnMarkerClickListener)
  {
    try
    {
      a().a(paramOnMarkerClickListener);
      return;
    }
    catch (RemoteException paramOnMarkerClickListener)
    {
      cy.a(paramOnMarkerClickListener, "AMap", "setOnMarkerClickListener");
      throw new RuntimeRemoteException(paramOnMarkerClickListener);
    }
  }
  
  public final void setOnMarkerDragListener(OnMarkerDragListener paramOnMarkerDragListener)
  {
    try
    {
      a().a(paramOnMarkerDragListener);
      return;
    }
    catch (RemoteException paramOnMarkerDragListener)
    {
      cy.a(paramOnMarkerDragListener, "AMap", "setOnMarkerDragListener");
      throw new RuntimeRemoteException(paramOnMarkerDragListener);
    }
  }
  
  public final void setOnMyLocationChangeListener(OnMyLocationChangeListener paramOnMyLocationChangeListener)
  {
    try
    {
      a().a(paramOnMyLocationChangeListener);
      return;
    }
    catch (RemoteException paramOnMyLocationChangeListener)
    {
      cy.a(paramOnMyLocationChangeListener, "AMap", "setOnMyLocaitonChangeListener");
      throw new RuntimeRemoteException(paramOnMyLocationChangeListener);
    }
  }
  
  public void setTrafficEnabled(boolean paramBoolean)
  {
    try
    {
      a().b(paramBoolean);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "AMap", "setTradficEnabled");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final void stopAnimation()
  {
    try
    {
      a().j();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "AMap", "stopAnimation");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public static abstract interface CancelableCallback
  {
    public abstract void onCancel();
    
    public abstract void onFinish();
  }
  
  public static abstract interface InfoWindowAdapter
  {
    public abstract View getInfoContents(Marker paramMarker);
    
    public abstract View getInfoWindow(Marker paramMarker);
  }
  
  public static abstract interface OnCameraChangeListener
  {
    public abstract void onCameraChange(CameraPosition paramCameraPosition);
    
    public abstract void onCameraChangeFinish(CameraPosition paramCameraPosition);
  }
  
  public static abstract interface OnInfoWindowClickListener
  {
    public abstract void onInfoWindowClick(Marker paramMarker);
  }
  
  public static abstract interface OnMapClickListener
  {
    public abstract void onMapClick(LatLng paramLatLng);
  }
  
  public static abstract interface OnMapLoadedListener
  {
    public abstract void onMapLoaded();
  }
  
  public static abstract interface OnMapLongClickListener
  {
    public abstract void onMapLongClick(LatLng paramLatLng);
  }
  
  public static abstract interface OnMapScreenShotListener
  {
    public abstract void onMapScreenShot(Bitmap paramBitmap);
  }
  
  public static abstract interface OnMapTouchListener
  {
    public abstract void onTouch(MotionEvent paramMotionEvent);
  }
  
  public static abstract interface OnMarkerClickListener
  {
    public abstract boolean onMarkerClick(Marker paramMarker);
  }
  
  public static abstract interface OnMarkerDragListener
  {
    public abstract void onMarkerDrag(Marker paramMarker);
    
    public abstract void onMarkerDragEnd(Marker paramMarker);
    
    public abstract void onMarkerDragStart(Marker paramMarker);
  }
  
  public static abstract interface OnMyLocationChangeListener
  {
    public abstract void onMyLocationChange(Location paramLocation);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.AMap
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */