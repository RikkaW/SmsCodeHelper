package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.http.HttpEntity;

class ce
  extends bt<ArrayList<n.a>, ArrayList<n.a>>
{
  private Context e;
  private aw f = null;
  
  public ce(Context paramContext, ArrayList<n.a> paramArrayList)
  {
    super(paramArrayList);
    e = paramContext;
    a(dg.a(e));
    a(5000);
    b(50000);
  }
  
  private void a(n.a parama, int paramInt)
  {
    if ((parama == null) || (paramInt < 0)) {}
    while ((f == null) || (f.o == null)) {
      return;
    }
    bw localbw = f.o;
    int j = localbw.size();
    int i = 0;
    label44:
    n.a locala;
    if (i < j)
    {
      locala = (n.a)localbw.get(i);
      if (locala != null) {
        break label73;
      }
    }
    label73:
    while (!locala.equals(parama))
    {
      i += 1;
      break label44;
      break;
    }
    g = paramInt;
  }
  
  private byte[] a(Bitmap paramBitmap)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, localByteArrayOutputStream);
    return localByteArrayOutputStream.toByteArray();
  }
  
  public int a(byte[] paramArrayOfByte, n.a parama)
  {
    int i;
    if ((parama == null) || (paramArrayOfByte == null)) {
      i = -1;
    }
    int j;
    do
    {
      do
      {
        do
        {
          do
          {
            return i;
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append(b);
            localStringBuilder.append("-");
            localStringBuilder.append(c);
            localStringBuilder.append("-");
            localStringBuilder.append(d);
            if ((f == null) || (f.m == null)) {
              return -1;
            }
            j = f.m.a(null, paramArrayOfByte, false, null, localStringBuilder.toString());
            if (j < 0) {
              return -1;
            }
            a(parama, j);
            i = j;
          } while (f == null);
          i = j;
        } while (f.g != true);
        paramArrayOfByte = a(f.m.a(j));
        i = j;
      } while (f == null);
      i = j;
    } while (f.n == null);
    f.n.a(paramArrayOfByte, b, c, d);
    return j;
  }
  
  public void a(aw paramaw)
  {
    f = paramaw;
  }
  
  protected ArrayList<n.a> b(byte[] paramArrayOfByte)
  {
    n.a locala = null;
    Object localObject1 = null;
    Object localObject2 = localObject1;
    if (a != null)
    {
      if (paramArrayOfByte == null) {
        localObject2 = localObject1;
      }
    }
    else {
      return (ArrayList<n.a>)localObject2;
    }
    int j = ((ArrayList)a).size();
    int i = 0;
    for (localObject1 = locala;; localObject1 = localObject2)
    {
      localObject2 = localObject1;
      if (i >= j) {
        break;
      }
      locala = (n.a)((ArrayList)a).get(i);
      localObject2 = localObject1;
      if (a(paramArrayOfByte, locala) < 0)
      {
        localObject2 = localObject1;
        if (localObject1 == null) {
          localObject2 = new ArrayList();
        }
        ((ArrayList)localObject2).add(new n.a(locala));
      }
      i += 1;
    }
  }
  
  public Map<String, String> b()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("Accept-Encoding", "gzip");
    localHashMap.put("User-Agent", "AMAP SDK Android 2DMap 2.5.0");
    return localHashMap;
  }
  
  public Map<String, String> c()
  {
    return null;
  }
  
  public String d()
  {
    return f.j.a(a).get(0)).b, a).get(0)).c, a).get(0)).d);
  }
  
  public HttpEntity e()
  {
    return null;
  }
  
  protected ArrayList<n.a> g()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = ((ArrayList)a).iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(new n.a((n.a)localIterator.next()));
    }
    return localArrayList;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.ce
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */