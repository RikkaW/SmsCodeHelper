package com.amap.api.maps2d.overlay;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;
import com.amap.api.maps2d.model.LatLngBounds.Builder;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.Polyline;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class b
{
  private Marker a;
  protected List<Polyline> allPolyLines = new ArrayList();
  private Marker b;
  private Context c;
  private Bitmap d;
  private Bitmap e;
  protected LatLng endPoint;
  private Bitmap f;
  private Bitmap g;
  private Bitmap h;
  private AssetManager i;
  protected AMap mAMap;
  protected boolean mNodeIconVisible = true;
  protected LatLng startPoint;
  protected List<Marker> stationMarkers = new ArrayList();
  
  public b(Context paramContext)
  {
    c = paramContext;
    i = c.getResources().getAssets();
  }
  
  private void a()
  {
    if (d != null)
    {
      d.recycle();
      d = null;
    }
    if (e != null)
    {
      e.recycle();
      e = null;
    }
    if (f != null)
    {
      f.recycle();
      f = null;
    }
    if (g != null)
    {
      g.recycle();
      g = null;
    }
    if (h != null)
    {
      h.recycle();
      h = null;
    }
  }
  
  protected void addStartAndEndMarker()
  {
    a = mAMap.addMarker(new MarkerOptions().position(startPoint).icon(getStartBitmapDescriptor()).title("起点"));
    b = mAMap.addMarker(new MarkerOptions().position(endPoint).icon(getEndBitmapDescriptor()).title("终点"));
  }
  
  /* Error */
  protected BitmapDescriptor getBitDes(Bitmap paramBitmap, String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: astore_3
    //   2: aload_1
    //   3: astore 4
    //   5: aload_0
    //   6: getfield 59	com/amap/api/maps2d/overlay/b:i	Landroid/content/res/AssetManager;
    //   9: aload_2
    //   10: invokevirtual 130	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   13: astore_2
    //   14: aload_1
    //   15: astore_3
    //   16: aload_1
    //   17: astore 4
    //   19: aload_2
    //   20: invokestatic 136	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   23: astore_1
    //   24: aload_1
    //   25: astore_3
    //   26: aload_1
    //   27: astore 4
    //   29: aload_1
    //   30: getstatic 141	com/amap/api/mapcore2d/y:a	F
    //   33: invokestatic 146	com/amap/api/maps2d/overlay/a:a	(Landroid/graphics/Bitmap;F)Landroid/graphics/Bitmap;
    //   36: astore_1
    //   37: aload_2
    //   38: invokevirtual 151	java/io/InputStream:close	()V
    //   41: aload_1
    //   42: invokestatic 157	com/amap/api/maps2d/model/BitmapDescriptorFactory:fromBitmap	(Landroid/graphics/Bitmap;)Lcom/amap/api/maps2d/model/BitmapDescriptor;
    //   45: areturn
    //   46: astore_1
    //   47: aload_1
    //   48: astore_2
    //   49: aload_3
    //   50: astore_1
    //   51: aload_2
    //   52: ldc -97
    //   54: ldc -96
    //   56: invokestatic 165	com/amap/api/mapcore2d/cy:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   59: goto -18 -> 41
    //   62: astore_1
    //   63: aload_1
    //   64: astore_2
    //   65: aload 4
    //   67: astore_1
    //   68: aload_2
    //   69: ldc -97
    //   71: ldc -96
    //   73: invokestatic 165	com/amap/api/mapcore2d/cy:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   76: goto -35 -> 41
    //   79: astore_2
    //   80: goto -12 -> 68
    //   83: astore_2
    //   84: goto -33 -> 51
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	87	0	this	b
    //   0	87	1	paramBitmap	Bitmap
    //   0	87	2	paramString	String
    //   1	49	3	localBitmap1	Bitmap
    //   3	63	4	localBitmap2	Bitmap
    // Exception table:
    //   from	to	target	type
    //   5	14	46	java/io/IOException
    //   19	24	46	java/io/IOException
    //   29	37	46	java/io/IOException
    //   5	14	62	java/lang/Exception
    //   19	24	62	java/lang/Exception
    //   29	37	62	java/lang/Exception
    //   37	41	79	java/lang/Exception
    //   37	41	83	java/io/IOException
  }
  
  protected BitmapDescriptor getBusBitmapDescriptor()
  {
    return getBitDes(f, "amap_bus.png");
  }
  
  protected int getBusColor()
  {
    return Color.parseColor("#537edc");
  }
  
  protected BitmapDescriptor getDriveBitmapDescriptor()
  {
    return getBitDes(h, "amap_car.png");
  }
  
  protected int getDriveColor()
  {
    return Color.parseColor("#537edc");
  }
  
  protected BitmapDescriptor getEndBitmapDescriptor()
  {
    return getBitDes(e, "amap_end.png");
  }
  
  protected LatLngBounds getLatLngBounds()
  {
    LatLngBounds.Builder localBuilder = LatLngBounds.builder();
    localBuilder.include(new LatLng(startPoint.latitude, startPoint.longitude));
    localBuilder.include(new LatLng(endPoint.latitude, endPoint.longitude));
    return localBuilder.build();
  }
  
  protected BitmapDescriptor getStartBitmapDescriptor()
  {
    return getBitDes(d, "amap_start.png");
  }
  
  protected BitmapDescriptor getWalkBitmapDescriptor()
  {
    return getBitDes(g, "amap_man.png");
  }
  
  protected int getWalkColor()
  {
    return Color.parseColor("#6db74d");
  }
  
  public void removeFromMap()
  {
    if (a != null) {
      a.remove();
    }
    if (b != null) {
      b.remove();
    }
    Iterator localIterator = stationMarkers.iterator();
    while (localIterator.hasNext()) {
      ((Marker)localIterator.next()).remove();
    }
    localIterator = allPolyLines.iterator();
    while (localIterator.hasNext()) {
      ((Polyline)localIterator.next()).remove();
    }
    a();
  }
  
  public void setNodeIconVisibility(boolean paramBoolean)
  {
    mNodeIconVisible = paramBoolean;
    Iterator localIterator = stationMarkers.iterator();
    while (localIterator.hasNext()) {
      ((Marker)localIterator.next()).setVisible(paramBoolean);
    }
    mAMap.postInvalidate();
  }
  
  public void zoomToSpan()
  {
    if ((startPoint == null) || (endPoint == null) || (mAMap == null)) {
      return;
    }
    LatLngBounds localLatLngBounds = getLatLngBounds();
    mAMap.moveCamera(CameraUpdateFactory.newLatLngBounds(localLatLngBounds, 50));
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.overlay.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */