package com.amap.api.maps2d.model;

import com.amap.api.mapcore2d.cw;
import com.amap.api.mapcore2d.cy;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public abstract class UrlTileProvider
  implements TileProvider
{
  private final int a;
  private final int b;
  
  public UrlTileProvider(int paramInt1, int paramInt2)
  {
    a = paramInt1;
    b = paramInt2;
  }
  
  private static long a(InputStream paramInputStream, OutputStream paramOutputStream)
  {
    byte[] arrayOfByte = new byte['က'];
    int i;
    for (long l = 0L;; l += i)
    {
      i = paramInputStream.read(arrayOfByte);
      if (i == -1) {
        return l;
      }
      paramOutputStream.write(arrayOfByte, 0, i);
    }
  }
  
  private static byte[] a(InputStream paramInputStream)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    a(paramInputStream, localByteArrayOutputStream);
    return localByteArrayOutputStream.toByteArray();
  }
  
  public final Tile getTile(int paramInt1, int paramInt2, int paramInt3)
  {
    Object localObject = getTileUrl(paramInt1, paramInt2, paramInt3);
    if (localObject == null) {
      return NO_TILE;
    }
    cw.a("UrlTileProvider", "url: " + ((URL)localObject).toString(), 111);
    try
    {
      localObject = new Tile(a, b, a(((URL)localObject).openStream()));
      return (Tile)localObject;
    }
    catch (IOException localIOException)
    {
      cy.a(localIOException, "UrlTileProvider", "getTile");
    }
    return NO_TILE;
  }
  
  public int getTileHeight()
  {
    return b;
  }
  
  public abstract URL getTileUrl(int paramInt1, int paramInt2, int paramInt3);
  
  public int getTileWidth()
  {
    return a;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.model.UrlTileProvider
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */