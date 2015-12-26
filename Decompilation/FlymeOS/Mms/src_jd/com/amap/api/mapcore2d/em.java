package com.amap.api.mapcore2d;

import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpEntity;

public class em
  extends ey
{
  private byte[] a;
  
  public em(byte[] paramArrayOfByte)
  {
    a = ((byte[])paramArrayOfByte.clone());
  }
  
  private String f()
  {
    byte[] arrayOfByte1 = dz.a.getBytes();
    byte[] arrayOfByte2 = new byte[arrayOfByte1.length + 50];
    System.arraycopy(a, 0, arrayOfByte2, 0, 50);
    System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 50, arrayOfByte1.length);
    return df.a(arrayOfByte2);
  }
  
  public byte[] a_()
  {
    return a;
  }
  
  public Map<String, String> b()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("Content-Type", "application/zip");
    localHashMap.put("Content-Length", String.valueOf(a.length));
    return localHashMap;
  }
  
  public Map<String, String> c()
  {
    return null;
  }
  
  public String d()
  {
    return String.format(dz.b, new Object[] { "1", "1", "1", "open", f() });
  }
  
  public HttpEntity e()
  {
    return null;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.em
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */