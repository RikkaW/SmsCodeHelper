package com.amap.api.services.core;

import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpEntity;

public class bh
  extends bt
{
  private byte[] a;
  
  public bh(byte[] paramArrayOfByte)
  {
    a = ((byte[])paramArrayOfByte.clone());
  }
  
  private String f()
  {
    byte[] arrayOfByte1 = au.a.getBytes();
    byte[] arrayOfByte2 = new byte[arrayOfByte1.length + 50];
    System.arraycopy(a, 0, arrayOfByte2, 0, 50);
    System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 50, arrayOfByte1.length);
    return ab.a(arrayOfByte2);
  }
  
  public String b()
  {
    return String.format(au.b, new Object[] { "1", "1", "1", "open", f() });
  }
  
  public Map<String, String> c_()
  {
    return null;
  }
  
  public Map<String, String> d_()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("Content-Type", "application/zip");
    localHashMap.put("Content-Length", String.valueOf(a.length));
    return localHashMap;
  }
  
  public HttpEntity e()
  {
    return null;
  }
  
  public byte[] e_()
  {
    return a;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.bh
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */