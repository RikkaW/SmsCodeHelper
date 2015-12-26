package com.amap.api.maps2d.overlay;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Color;
import com.amap.api.mapcore2d.y;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;
import com.amap.api.maps2d.model.LatLngBounds.Builder;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.Polyline;
import com.amap.api.maps2d.model.PolylineOptions;
import com.amap.api.services.busline.BusLineItem;
import com.amap.api.services.busline.BusStationItem;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BusLineOverlay
{
  private BusLineItem a;
  private AMap b;
  private ArrayList<Marker> c = new ArrayList();
  private Polyline d;
  private List<BusStationItem> e;
  private BitmapDescriptor f;
  private BitmapDescriptor g;
  private BitmapDescriptor h;
  private AssetManager i;
  private Context j;
  
  public BusLineOverlay(Context paramContext, AMap paramAMap, BusLineItem paramBusLineItem)
  {
    j = paramContext;
    a = paramBusLineItem;
    b = paramAMap;
    e = a.getBusStations();
    i = j.getResources().getAssets();
  }
  
  /* Error */
  private BitmapDescriptor a(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 62	com/amap/api/maps2d/overlay/BusLineOverlay:i	Landroid/content/res/AssetManager;
    //   4: aload_1
    //   5: invokevirtual 72	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   8: astore_2
    //   9: aload_2
    //   10: astore_3
    //   11: aload_2
    //   12: invokestatic 78	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   15: astore_1
    //   16: aload_2
    //   17: astore_3
    //   18: aload_1
    //   19: getstatic 83	com/amap/api/mapcore2d/y:a	F
    //   22: invokestatic 88	com/amap/api/mapcore2d/cy:a	(Landroid/graphics/Bitmap;F)Landroid/graphics/Bitmap;
    //   25: astore 4
    //   27: aload 4
    //   29: astore_1
    //   30: aload_1
    //   31: astore_3
    //   32: aload_2
    //   33: ifnull +9 -> 42
    //   36: aload_2
    //   37: invokevirtual 93	java/io/InputStream:close	()V
    //   40: aload_1
    //   41: astore_3
    //   42: aload_3
    //   43: invokestatic 99	com/amap/api/maps2d/model/BitmapDescriptorFactory:fromBitmap	(Landroid/graphics/Bitmap;)Lcom/amap/api/maps2d/model/BitmapDescriptor;
    //   46: areturn
    //   47: astore_2
    //   48: aload_2
    //   49: ldc 101
    //   51: ldc 103
    //   53: invokestatic 106	com/amap/api/mapcore2d/cy:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   56: aload_1
    //   57: astore_3
    //   58: goto -16 -> 42
    //   61: astore 4
    //   63: aconst_null
    //   64: astore_2
    //   65: aconst_null
    //   66: astore_1
    //   67: aload_2
    //   68: astore_3
    //   69: aload 4
    //   71: ldc 101
    //   73: ldc 103
    //   75: invokestatic 106	com/amap/api/mapcore2d/cy:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   78: aload_1
    //   79: astore_3
    //   80: aload_2
    //   81: ifnull -39 -> 42
    //   84: aload_2
    //   85: invokevirtual 93	java/io/InputStream:close	()V
    //   88: aload_1
    //   89: astore_3
    //   90: goto -48 -> 42
    //   93: astore_2
    //   94: aload_2
    //   95: ldc 101
    //   97: ldc 103
    //   99: invokestatic 106	com/amap/api/mapcore2d/cy:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   102: aload_1
    //   103: astore_3
    //   104: goto -62 -> 42
    //   107: astore_1
    //   108: aconst_null
    //   109: astore_3
    //   110: aload_3
    //   111: ifnull +7 -> 118
    //   114: aload_3
    //   115: invokevirtual 93	java/io/InputStream:close	()V
    //   118: aload_1
    //   119: athrow
    //   120: astore_2
    //   121: aload_2
    //   122: ldc 101
    //   124: ldc 103
    //   126: invokestatic 106	com/amap/api/mapcore2d/cy:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   129: goto -11 -> 118
    //   132: astore_1
    //   133: goto -23 -> 110
    //   136: astore 4
    //   138: aconst_null
    //   139: astore_1
    //   140: goto -73 -> 67
    //   143: astore 4
    //   145: goto -78 -> 67
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	148	0	this	BusLineOverlay
    //   0	148	1	paramString	String
    //   8	29	2	localInputStream	java.io.InputStream
    //   47	2	2	localIOException1	java.io.IOException
    //   64	21	2	localObject1	Object
    //   93	2	2	localIOException2	java.io.IOException
    //   120	2	2	localIOException3	java.io.IOException
    //   10	105	3	localObject2	Object
    //   25	3	4	localBitmap	android.graphics.Bitmap
    //   61	9	4	localIOException4	java.io.IOException
    //   136	1	4	localIOException5	java.io.IOException
    //   143	1	4	localIOException6	java.io.IOException
    // Exception table:
    //   from	to	target	type
    //   36	40	47	java/io/IOException
    //   0	9	61	java/io/IOException
    //   84	88	93	java/io/IOException
    //   0	9	107	finally
    //   114	118	120	java/io/IOException
    //   11	16	132	finally
    //   18	27	132	finally
    //   69	78	132	finally
    //   11	16	136	java/io/IOException
    //   18	27	143	java/io/IOException
  }
  
  private LatLngBounds a(List<LatLonPoint> paramList)
  {
    LatLngBounds.Builder localBuilder = LatLngBounds.builder();
    int k = 0;
    while (k < paramList.size())
    {
      localBuilder.include(new LatLng(((LatLonPoint)paramList.get(k)).getLatitude(), ((LatLonPoint)paramList.get(k)).getLongitude()));
      k += 1;
    }
    return localBuilder.build();
  }
  
  private MarkerOptions a(int paramInt)
  {
    MarkerOptions localMarkerOptions = new MarkerOptions().position(new LatLng(((BusStationItem)e.get(paramInt)).getLatLonPoint().getLatitude(), ((BusStationItem)e.get(paramInt)).getLatLonPoint().getLongitude())).title(getTitle(paramInt)).snippet(getSnippet(paramInt));
    if (paramInt == 0)
    {
      localMarkerOptions.icon(getStartBitmapDescriptor());
      return localMarkerOptions;
    }
    if (paramInt == e.size() - 1)
    {
      localMarkerOptions.icon(getEndBitmapDescriptor());
      return localMarkerOptions;
    }
    localMarkerOptions.anchor(0.5F, 0.5F);
    localMarkerOptions.icon(getBusBitmapDescriptor());
    return localMarkerOptions;
  }
  
  private void a()
  {
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
  
  public void addToMap()
  {
    int k = 1;
    Object localObject = a.a(a.getDirectionsCoordinates());
    d = b.addPolyline(new PolylineOptions().addAll((Iterable)localObject).color(getBusColor()).width(getBuslineWidth()));
    if (e.size() < 1) {
      return;
    }
    while (k < e.size() - 1)
    {
      localObject = b.addMarker(a(k));
      c.add(localObject);
      k += 1;
    }
    localObject = b.addMarker(a(0));
    c.add(localObject);
    localObject = b.addMarker(a(e.size() - 1));
    c.add(localObject);
  }
  
  protected BitmapDescriptor getBusBitmapDescriptor()
  {
    h = a("amap_bus.png");
    return h;
  }
  
  protected int getBusColor()
  {
    return Color.parseColor("#537edc");
  }
  
  public int getBusStationIndex(Marker paramMarker)
  {
    int k = 0;
    while (k < c.size())
    {
      if (((Marker)c.get(k)).equals(paramMarker)) {
        return k;
      }
      k += 1;
    }
    return -1;
  }
  
  public BusStationItem getBusStationItem(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= e.size())) {
      return null;
    }
    return (BusStationItem)e.get(paramInt);
  }
  
  protected float getBuslineWidth()
  {
    return y.b;
  }
  
  protected BitmapDescriptor getEndBitmapDescriptor()
  {
    g = a("amap_end.png");
    return g;
  }
  
  protected String getSnippet(int paramInt)
  {
    return "";
  }
  
  protected BitmapDescriptor getStartBitmapDescriptor()
  {
    f = a("amap_start.png");
    return f;
  }
  
  protected String getTitle(int paramInt)
  {
    return ((BusStationItem)e.get(paramInt)).getBusStationName();
  }
  
  public void removeFromMap()
  {
    if (d != null) {
      d.remove();
    }
    Iterator localIterator = c.iterator();
    while (localIterator.hasNext()) {
      ((Marker)localIterator.next()).remove();
    }
    a();
  }
  
  public void zoomToSpan()
  {
    if (b == null) {}
    do
    {
      return;
      localObject = a.getDirectionsCoordinates();
    } while ((localObject == null) || (((List)localObject).size() <= 0));
    Object localObject = a((List)localObject);
    b.moveCamera(CameraUpdateFactory.newLatLngBounds((LatLngBounds)localObject, 5));
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.overlay.BusLineOverlay
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */