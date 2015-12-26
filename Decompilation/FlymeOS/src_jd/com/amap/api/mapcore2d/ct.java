package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.amap.api.maps2d.model.Tile;
import com.amap.api.maps2d.model.TileProvider;

public class ct
  extends cu
{
  private TileProvider b = null;
  
  public ct(Context paramContext, int paramInt1, int paramInt2)
  {
    super(paramContext, paramInt1, paramInt2);
    a(paramContext);
  }
  
  private void a(Context paramContext)
  {
    b(paramContext);
  }
  
  private void b(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if ((paramContext == null) || (!paramContext.isConnectedOrConnecting())) {
      cw.a("ImageFetcher", "checkConnection - no connection found", 112);
    }
  }
  
  private Bitmap c(cb.a parama)
  {
    cw.a("ImageFetcher", "processBitmap - " + parama, 111);
    Object localObject = null;
    Tile localTile = b.getTile(a, b, c);
    parama = (cb.a)localObject;
    if (localTile != null)
    {
      parama = (cb.a)localObject;
      if (localTile != TileProvider.NO_TILE) {
        parama = BitmapFactory.decodeByteArray(data, 0, data.length);
      }
    }
    return parama;
  }
  
  protected Bitmap a(Object paramObject)
  {
    return c((cb.a)paramObject);
  }
  
  public void a(TileProvider paramTileProvider)
  {
    b = paramTileProvider;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.ct
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */