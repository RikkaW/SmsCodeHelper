import android.text.TextUtils;
import java.util.zip.CRC32;

public class aht
{
  public byte[] A = null;
  public String B = null;
  public String C = null;
  public String D = null;
  public String E = null;
  public String a = "1";
  public short b = 0;
  public String c = null;
  public String d = null;
  public String e = null;
  public String f = null;
  public String g = null;
  public String h = null;
  public String i = null;
  public String j = null;
  public String k = null;
  public String l = null;
  public String m = null;
  public String n = null;
  public String o = null;
  public String p = null;
  public String q = null;
  public String r = null;
  public String s = null;
  public String t = null;
  public String u = null;
  public String v = null;
  public String w = null;
  public String x = null;
  public String y = null;
  public String z = null;
  
  private String a(String paramString, int paramInt)
  {
    String[] arrayOfString = w.split("\\*")[paramInt].split(",");
    if (paramString.equals("lac")) {
      return arrayOfString[0];
    }
    if (paramString.equals("cellid")) {
      return arrayOfString[1];
    }
    if (paramString.equals("signal")) {
      return arrayOfString[2];
    }
    return null;
  }
  
  private byte[] a(String paramString)
  {
    Object localObject = paramString.split(":");
    if (localObject != null)
    {
      paramString = (String)localObject;
      if (localObject.length == 6) {}
    }
    else
    {
      paramString = new String[6];
      i1 = 0;
      while (i1 < paramString.length)
      {
        paramString[i1] = "0";
        i1 += 1;
      }
    }
    localObject = new byte[6];
    int i1 = 0;
    while (i1 < paramString.length)
    {
      if (paramString[i1].length() > 2) {
        paramString[i1] = paramString[i1].substring(0, 2);
      }
      localObject[i1] = ((byte)Integer.parseInt(paramString[i1], 16));
      i1 += 1;
    }
    return (byte[])localObject;
  }
  
  private String b(String paramString)
  {
    if (!v.contains(paramString + ">")) {
      return "0";
    }
    int i1 = v.indexOf(paramString + ">");
    int i2 = v.indexOf("</" + paramString);
    return v.substring(i1 + paramString.length() + 1, i2);
  }
  
  private void b()
  {
    if (TextUtils.isEmpty(a)) {
      a = "";
    }
    if (TextUtils.isEmpty(c)) {
      c = "";
    }
    if (TextUtils.isEmpty(d)) {
      d = "";
    }
    if (TextUtils.isEmpty(e)) {
      e = "";
    }
    if (TextUtils.isEmpty(f)) {
      f = "";
    }
    if (TextUtils.isEmpty(g)) {
      g = "";
    }
    if (TextUtils.isEmpty(h)) {
      h = "";
    }
    if (TextUtils.isEmpty(i)) {
      i = "";
    }
    if (TextUtils.isEmpty(j))
    {
      j = "0";
      if (!TextUtils.isEmpty(D)) {
        break label512;
      }
      D = "0";
      label160:
      if (!TextUtils.isEmpty(k)) {
        break label545;
      }
      k = "";
      label176:
      if (!TextUtils.isEmpty(l)) {
        break label572;
      }
      l = "";
      label192:
      if (TextUtils.isEmpty(m)) {
        m = "";
      }
      if (TextUtils.isEmpty(n)) {
        n = "";
      }
      if (TextUtils.isEmpty(o)) {
        o = "";
      }
      if (TextUtils.isEmpty(p)) {
        p = "";
      }
      if (TextUtils.isEmpty(q)) {
        q = "";
      }
      if (TextUtils.isEmpty(r)) {
        r = "";
      }
      if (TextUtils.isEmpty(B)) {
        B = "";
      }
      if (TextUtils.isEmpty(C)) {
        C = "";
      }
      if (TextUtils.isEmpty(s)) {
        s = "";
      }
      if (!TextUtils.isEmpty(t)) {
        break label599;
      }
      t = "0";
      label352:
      if (!TextUtils.isEmpty(u)) {
        break label632;
      }
    }
    for (u = "0";; u = "0") {
      label512:
      label545:
      label572:
      label599:
      label632:
      do
      {
        if (TextUtils.isEmpty(v)) {
          v = "";
        }
        if (TextUtils.isEmpty(w)) {
          w = "";
        }
        if (TextUtils.isEmpty(x)) {
          x = "";
        }
        if (TextUtils.isEmpty(y)) {
          y = "";
        }
        if (TextUtils.isEmpty(E)) {
          E = "";
        }
        if (TextUtils.isEmpty(z)) {
          z = "";
        }
        if (A == null) {
          A = new byte[0];
        }
        return;
        if ((j.equals("1")) || (j.equals("2"))) {
          break;
        }
        j = "0";
        break;
        if ((D.equals("0")) || (D.equals("1"))) {
          break label160;
        }
        D = "0";
        break label160;
        k = String.valueOf(Double.valueOf(Double.parseDouble(k) * 1200000.0D).intValue());
        break label176;
        l = String.valueOf(Double.valueOf(Double.parseDouble(l) * 1000000.0D).intValue());
        break label192;
        if ((t.equals("1")) || (t.equals("2"))) {
          break label352;
        }
        t = "0";
        break label352;
      } while ((u.equals("1")) || (u.equals("2")));
    }
  }
  
  public byte[] a()
  {
    b();
    i1 = 3072;
    if (A != null) {
      i1 = 3072 + (A.length + 1);
    }
    arrayOfByte3 = new byte[i1];
    arrayOfByte3[0] = Byte.parseByte(a);
    Object localObject1 = ahv.b(b);
    System.arraycopy(localObject1, 0, arrayOfByte3, 1, localObject1.length);
    i2 = localObject1.length + 1;
    i1 = i2;
    try
    {
      localObject1 = c.getBytes("GBK");
      i1 = i2;
      arrayOfByte3[i2] = ((byte)localObject1.length);
      i2 += 1;
      i1 = i2;
      System.arraycopy(localObject1, 0, arrayOfByte3, i2, localObject1.length);
      i1 = i2;
      i3 = localObject1.length;
      i1 = i2 + i3;
    }
    catch (Exception localException1)
    {
      for (;;)
      {
        arrayOfByte3[i1] = 0;
        i1 += 1;
      }
    }
    i2 = i1;
    try
    {
      localObject1 = d.getBytes("GBK");
      i2 = i1;
      arrayOfByte3[i1] = ((byte)localObject1.length);
      i1 += 1;
      i2 = i1;
      System.arraycopy(localObject1, 0, arrayOfByte3, i1, localObject1.length);
      i2 = i1;
      i3 = localObject1.length;
      i1 += i3;
    }
    catch (Exception localException2)
    {
      for (;;)
      {
        arrayOfByte3[i2] = 0;
        i1 = i2 + 1;
      }
    }
    i2 = i1;
    try
    {
      localObject1 = n.getBytes("GBK");
      i2 = i1;
      arrayOfByte3[i1] = ((byte)localObject1.length);
      i1 += 1;
      i2 = i1;
      System.arraycopy(localObject1, 0, arrayOfByte3, i1, localObject1.length);
      i2 = i1;
      i3 = localObject1.length;
      i1 += i3;
    }
    catch (Exception localException3)
    {
      for (;;)
      {
        arrayOfByte3[i2] = 0;
        i1 = i2 + 1;
      }
    }
    i2 = i1;
    try
    {
      localObject1 = e.getBytes("GBK");
      i2 = i1;
      arrayOfByte3[i1] = ((byte)localObject1.length);
      i1 += 1;
      i2 = i1;
      System.arraycopy(localObject1, 0, arrayOfByte3, i1, localObject1.length);
      i2 = i1;
      i3 = localObject1.length;
      i1 += i3;
    }
    catch (Exception localException4)
    {
      for (;;)
      {
        arrayOfByte3[i2] = 0;
        i1 = i2 + 1;
      }
    }
    i2 = i1;
    try
    {
      localObject1 = f.getBytes("GBK");
      i2 = i1;
      arrayOfByte3[i1] = ((byte)localObject1.length);
      i1 += 1;
      i2 = i1;
      System.arraycopy(localObject1, 0, arrayOfByte3, i1, localObject1.length);
      i2 = i1;
      i3 = localObject1.length;
      i1 += i3;
    }
    catch (Exception localException5)
    {
      for (;;)
      {
        arrayOfByte3[i2] = 0;
        i1 = i2 + 1;
      }
    }
    i2 = i1;
    try
    {
      localObject1 = g.getBytes("GBK");
      i2 = i1;
      arrayOfByte3[i1] = ((byte)localObject1.length);
      i1 += 1;
      i2 = i1;
      System.arraycopy(localObject1, 0, arrayOfByte3, i1, localObject1.length);
      i2 = i1;
      i3 = localObject1.length;
      i1 += i3;
    }
    catch (Exception localException6)
    {
      for (;;)
      {
        arrayOfByte3[i2] = 0;
        i1 = i2 + 1;
      }
    }
    i2 = i1;
    try
    {
      localObject1 = r.getBytes("GBK");
      i2 = i1;
      arrayOfByte3[i1] = ((byte)localObject1.length);
      i1 += 1;
      i2 = i1;
      System.arraycopy(localObject1, 0, arrayOfByte3, i1, localObject1.length);
      i2 = i1;
      i3 = localObject1.length;
      i1 += i3;
    }
    catch (Exception localException7)
    {
      for (;;)
      {
        arrayOfByte3[i2] = 0;
        i1 = i2 + 1;
      }
    }
    i2 = i1;
    try
    {
      localObject1 = h.getBytes("GBK");
      i2 = i1;
      arrayOfByte3[i1] = ((byte)localObject1.length);
      i1 += 1;
      i2 = i1;
      System.arraycopy(localObject1, 0, arrayOfByte3, i1, localObject1.length);
      i2 = i1;
      i3 = localObject1.length;
      i1 += i3;
    }
    catch (Exception localException8)
    {
      for (;;)
      {
        arrayOfByte3[i2] = 0;
        i1 = i2 + 1;
      }
    }
    i2 = i1;
    try
    {
      localObject1 = o.getBytes("GBK");
      i2 = i1;
      arrayOfByte3[i1] = ((byte)localObject1.length);
      i1 += 1;
      i2 = i1;
      System.arraycopy(localObject1, 0, arrayOfByte3, i1, localObject1.length);
      i2 = i1;
      i3 = localObject1.length;
      i1 += i3;
    }
    catch (Exception localException9)
    {
      for (;;)
      {
        arrayOfByte3[i2] = 0;
        i1 = i2 + 1;
      }
    }
    i2 = i1;
    try
    {
      localObject1 = p.getBytes("GBK");
      i2 = i1;
      arrayOfByte3[i1] = ((byte)localObject1.length);
      i1 += 1;
      i2 = i1;
      System.arraycopy(localObject1, 0, arrayOfByte3, i1, localObject1.length);
      i2 = i1;
      i3 = localObject1.length;
      i1 += i3;
    }
    catch (Exception localException10)
    {
      for (;;)
      {
        arrayOfByte3[i2] = 0;
        i1 = i2 + 1;
        continue;
        arrayOfByte1 = a(q);
        arrayOfByte3[i1] = ((byte)arrayOfByte1.length);
        i1 += 1;
        System.arraycopy(arrayOfByte1, 0, arrayOfByte3, i1, arrayOfByte1.length);
        i1 += arrayOfByte1.length;
      }
    }
    if (TextUtils.isEmpty(q))
    {
      arrayOfByte3[i1] = 0;
      i1 += 1;
      i2 = i1;
    }
    try
    {
      localObject1 = B.getBytes("GBK");
      i2 = i1;
      arrayOfByte3[i1] = ((byte)localObject1.length);
      i1 += 1;
      i2 = i1;
      System.arraycopy(localObject1, 0, arrayOfByte3, i1, localObject1.length);
      i2 = i1;
      i3 = localObject1.length;
      i1 += i3;
    }
    catch (Exception localException11)
    {
      for (;;)
      {
        arrayOfByte3[i2] = 0;
        i1 = i2 + 1;
      }
    }
    i2 = i1;
    try
    {
      localObject1 = C.getBytes("GBK");
      i2 = i1;
      arrayOfByte3[i1] = ((byte)localObject1.length);
      i1 += 1;
      i2 = i1;
      System.arraycopy(localObject1, 0, arrayOfByte3, i1, localObject1.length);
      i2 = i1;
      i3 = localObject1.length;
      i1 += i3;
    }
    catch (Exception localException12)
    {
      for (;;)
      {
        arrayOfByte3[i2] = 0;
        i1 = i2 + 1;
      }
    }
    i2 = i1;
    try
    {
      localObject1 = s.getBytes("GBK");
      i2 = i1;
      arrayOfByte3[i1] = ((byte)localObject1.length);
      i1 += 1;
      i2 = i1;
      System.arraycopy(localObject1, 0, arrayOfByte3, i1, localObject1.length);
      i2 = i1;
      i3 = localObject1.length;
      i1 += i3;
    }
    catch (Exception localException13)
    {
      for (;;)
      {
        arrayOfByte3[i2] = 0;
        i1 = i2 + 1;
      }
    }
    arrayOfByte3[i1] = Byte.parseByte(t);
    i1 += 1;
    arrayOfByte3[i1] = Byte.parseByte(j);
    i2 = i1 + 1;
    i1 = i2;
    if (j.equals("1")) {}
    try
    {
      arrayOfByte3[i2] = Byte.parseByte(D);
      i1 = i2 + 1;
    }
    catch (Throwable localThrowable1)
    {
      for (;;)
      {
        localThrowable1.printStackTrace();
        i1 = i2;
        continue;
        i1 = i2;
        if (i2 < -128)
        {
          i1 = 0;
          continue;
          i4 = w.split("\\*").length;
          arrayOfByte3[i1] = ((byte)i4);
          i2 = i1 + 1;
          i3 = 0;
          i1 = i2;
          if (i3 < i4)
          {
            localObject2 = ahv.b(a("lac", i3));
            System.arraycopy(localObject2, 0, arrayOfByte3, i2, localObject2.length);
            i1 = i2 + localObject2.length;
            localObject2 = ahv.a(a("cellid", i3));
            System.arraycopy(localObject2, 0, arrayOfByte3, i1, localObject2.length);
            i5 = localObject2.length + i1;
            i2 = Integer.parseInt(a("signal", i3));
            if (i2 > 127) {
              i1 = 0;
            }
            for (;;)
            {
              arrayOfByte3[i5] = ((byte)i1);
              i3 += 1;
              i2 = i5 + 1;
              break;
              i1 = i2;
              if (i2 < -128) {
                i1 = 0;
              }
            }
            i1 = i2;
            if (u.equals("2"))
            {
              localObject2 = ahv.b(b("mcc"));
              System.arraycopy(localObject2, 0, arrayOfByte3, i2, localObject2.length);
              i1 = i2 + localObject2.length;
              localObject2 = ahv.b(b("sid"));
              System.arraycopy(localObject2, 0, arrayOfByte3, i1, localObject2.length);
              i1 += localObject2.length;
              localObject2 = ahv.b(b("nid"));
              System.arraycopy(localObject2, 0, arrayOfByte3, i1, localObject2.length);
              i1 += localObject2.length;
              localObject2 = ahv.b(b("bid"));
              System.arraycopy(localObject2, 0, arrayOfByte3, i1, localObject2.length);
              i1 += localObject2.length;
              localObject2 = ahv.a(b("lon"));
              System.arraycopy(localObject2, 0, arrayOfByte3, i1, localObject2.length);
              i1 += localObject2.length;
              localObject2 = ahv.a(b("lat"));
              System.arraycopy(localObject2, 0, arrayOfByte3, i1, localObject2.length);
              i3 = localObject2.length + i1;
              i2 = Integer.parseInt(b("signal"));
              if (i2 > 127) {
                i1 = 0;
              }
              for (;;)
              {
                arrayOfByte3[i3] = ((byte)i1);
                i1 = i3 + 1;
                arrayOfByte3[i1] = 0;
                i1 += 1;
                break;
                i1 = i2;
                if (i2 < -128) {
                  i1 = 0;
                }
              }
              arrayOfByte3[i1] = 1;
              i2 = i1 + 1;
              i1 = i2;
              try
              {
                localObject2 = x.split(",");
                i1 = i2;
                localObject3 = a(localObject2[0]);
                i1 = i2;
                System.arraycopy(localObject3, 0, arrayOfByte3, i2, localObject3.length);
                i1 = i2;
                i3 = localObject3.length;
                i3 = i2 + i3;
                i2 = i3;
                i1 = i3;
                try
                {
                  localObject3 = localObject2[2].getBytes("GBK");
                  i2 = i3;
                  i1 = i3;
                  arrayOfByte3[i3] = ((byte)localObject3.length);
                  i3 += 1;
                  i2 = i3;
                  i1 = i3;
                  System.arraycopy(localObject3, 0, arrayOfByte3, i3, localObject3.length);
                  i2 = i3;
                  i1 = i3;
                  i4 = localObject3.length;
                  i2 = i3 + i4;
                }
                catch (Exception localException15)
                {
                  for (;;)
                  {
                    arrayOfByte3[i2] = 0;
                    i2 += 1;
                    continue;
                    i3 = i4;
                    if (i4 < -128) {
                      i3 = 0;
                    }
                  }
                }
                i1 = i2;
                i4 = Integer.parseInt(localObject2[1]);
                if (i4 > 127)
                {
                  i3 = 0;
                  i1 = i2;
                  arrayOfByte3[i2] = Byte.parseByte(String.valueOf(i3));
                  i1 = i2 + 1;
                  continue;
                }
              }
              catch (Throwable localThrowable2)
              {
                arrayOfByte2 = a("00:00:00:00:00:00");
                System.arraycopy(arrayOfByte2, 0, arrayOfByte3, i1, arrayOfByte2.length);
                i1 += arrayOfByte2.length;
                arrayOfByte3[i1] = 0;
                i1 += 1;
                arrayOfByte3[i1] = Byte.parseByte("0");
                i1 += 1;
              }
              arrayOfByte3[i1] = ((byte)arrayOfByte2.length);
              i2 = i1 + 1;
              i3 = 0;
              for (;;)
              {
                if (i3 >= arrayOfByte2.length) {
                  break label2476;
                }
                arrayOfString = arrayOfByte2[i3].split(",");
                arrayOfByte4 = a(arrayOfString[0]);
                System.arraycopy(arrayOfByte4, 0, arrayOfByte3, i2, arrayOfByte4.length);
                i2 += arrayOfByte4.length;
                i1 = i2;
                try
                {
                  arrayOfByte4 = arrayOfString[2].getBytes("GBK");
                  i1 = i2;
                  arrayOfByte3[i2] = ((byte)arrayOfByte4.length);
                  i2 += 1;
                  i1 = i2;
                  System.arraycopy(arrayOfByte4, 0, arrayOfByte3, i2, arrayOfByte4.length);
                  i1 = i2;
                  i4 = arrayOfByte4.length;
                  i2 += i4;
                }
                catch (Exception localException16)
                {
                  for (;;)
                  {
                    arrayOfByte3[i1] = 0;
                    i2 = i1 + 1;
                    continue;
                    i1 = i4;
                    if (i4 < -128) {
                      i1 = 0;
                    }
                  }
                }
                i4 = Integer.parseInt(arrayOfString[1]);
                if (i4 <= 127) {
                  break;
                }
                i1 = 0;
                arrayOfByte3[i2] = Byte.parseByte(String.valueOf(i1));
                i3 += 1;
                i2 += 1;
              }
              i1 = i2;
              if (E != null)
              {
                i1 = i2;
                if (E.length() > 0)
                {
                  try
                  {
                    arrayOfByte2 = ahv.b(Integer.parseInt(E));
                    System.arraycopy(arrayOfByte2, 0, arrayOfByte3, i2, arrayOfByte2.length);
                    i1 = arrayOfByte2.length;
                    i1 = i2 + i1;
                  }
                  catch (Throwable localThrowable3)
                  {
                    localThrowable3.printStackTrace();
                    i1 = i2;
                  }
                  continue;
                  i2 = i1;
                  arrayOfByte3[i1] = ((byte)localThrowable3.length);
                  i1 += 1;
                  i2 = i1;
                  System.arraycopy(localThrowable3, 0, arrayOfByte3, i1, localThrowable3.length);
                  i2 = i1;
                  i3 = localThrowable3.length;
                  i1 += i3;
                }
              }
            }
          }
        }
      }
    }
    if (!j.equals("1"))
    {
      i2 = i1;
      if (!j.equals("2")) {}
    }
    else
    {
      localObject1 = ahv.a(Integer.parseInt(k));
      System.arraycopy(localObject1, 0, arrayOfByte3, i1, localObject1.length);
      i2 = i1 + localObject1.length;
    }
    if (!j.equals("1"))
    {
      i1 = i2;
      if (!j.equals("2")) {}
    }
    else
    {
      localObject1 = ahv.a(Integer.parseInt(l));
      System.arraycopy(localObject1, 0, arrayOfByte3, i2, localObject1.length);
      i1 = i2 + localObject1.length;
    }
    if (!j.equals("1"))
    {
      i2 = i1;
      if (!j.equals("2")) {}
    }
    else
    {
      localObject1 = ahv.b(m);
      System.arraycopy(localObject1, 0, arrayOfByte3, i1, localObject1.length);
      i2 = i1 + localObject1.length;
    }
    arrayOfByte3[i2] = Byte.parseByte(u);
    i2 += 1;
    if (u.equals("1"))
    {
      localObject1 = ahv.b(b("mcc"));
      System.arraycopy(localObject1, 0, arrayOfByte3, i2, localObject1.length);
      i1 = i2 + localObject1.length;
      localObject1 = ahv.b(b("mnc"));
      System.arraycopy(localObject1, 0, arrayOfByte3, i1, localObject1.length);
      i1 += localObject1.length;
      localObject1 = ahv.b(b("lac"));
      System.arraycopy(localObject1, 0, arrayOfByte3, i1, localObject1.length);
      i1 += localObject1.length;
      localObject1 = ahv.a(b("cellid"));
      System.arraycopy(localObject1, 0, arrayOfByte3, i1, localObject1.length);
      i3 = localObject1.length + i1;
      i2 = Integer.parseInt(b("signal"));
      if (i2 > 127)
      {
        i1 = 0;
        arrayOfByte3[i3] = ((byte)i1);
        i1 = i3 + 1;
        if (w.length() != 0) {
          break label1661;
        }
        arrayOfByte3[i1] = 0;
        i1 += 1;
        if (x.length() != 0) {
          break label2052;
        }
        arrayOfByte3[i1] = 0;
        i1 += 1;
        localObject1 = y.split("\\*");
        if ((!TextUtils.isEmpty(y)) && (localObject1.length != 0)) {
          break label2294;
        }
        arrayOfByte3[i1] = 0;
        i1 += 1;
        i2 = i1;
      }
    }
    try
    {
      localObject3 = z.getBytes("GBK");
      localObject1 = localObject3;
      i2 = i1;
      if (localObject3.length > 127) {
        localObject1 = null;
      }
      if (localObject1 != null) {
        break label2544;
      }
      arrayOfByte3[i1] = 0;
      i1 += 1;
    }
    catch (Exception localException14)
    {
      for (;;)
      {
        Object localObject3;
        byte[] arrayOfByte1;
        int i4;
        Object localObject2;
        int i5;
        byte[] arrayOfByte2;
        String[] arrayOfString;
        byte[] arrayOfByte4;
        arrayOfByte3[i2] = 0;
        i1 = i2 + 1;
        continue;
        i2 = 0;
      }
    }
    if (A != null)
    {
      i2 = A.length;
      localObject1 = ahv.b(i2);
      System.arraycopy(localObject1, 0, arrayOfByte3, i1, localObject1.length);
      i3 = i1 + localObject1.length;
      i1 = i3;
      if (i2 > 0)
      {
        System.arraycopy(A, 0, arrayOfByte3, i3, A.length);
        i1 = i3 + A.length;
      }
      localObject1 = new byte[i1];
      System.arraycopy(arrayOfByte3, 0, localObject1, 0, i1);
      localObject3 = new CRC32();
      ((CRC32)localObject3).update((byte[])localObject1);
      localObject3 = ahv.a(((CRC32)localObject3).getValue());
      arrayOfByte3 = new byte[localObject3.length + i1];
      System.arraycopy(localObject1, 0, arrayOfByte3, 0, i1);
      System.arraycopy(localObject3, 0, arrayOfByte3, i1, localObject3.length);
      i1 = localObject3.length;
      return arrayOfByte3;
    }
  }
}

/* Location:
 * Qualified Name:     aht
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */