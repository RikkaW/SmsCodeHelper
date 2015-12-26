import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

final class afv
  implements Serializable
{
  protected byte[] a = new byte[16];
  protected byte[] b = new byte[16];
  protected byte[] c = new byte[16];
  protected short d = 0;
  protected short e = 0;
  protected byte f = 0;
  protected byte[] g = new byte[16];
  protected byte[] h = new byte[32];
  protected short i = 0;
  protected ArrayList j = new ArrayList();
  private byte k = 41;
  private short l = 0;
  
  private Boolean a(DataOutputStream paramDataOutputStream)
  {
    try
    {
      ByteArrayOutputStream localByteArrayOutputStream1 = new ByteArrayOutputStream();
      DataOutputStream localDataOutputStream1 = new DataOutputStream(localByteArrayOutputStream1);
      localDataOutputStream1.flush();
      localDataOutputStream1.write(a);
      localDataOutputStream1.write(b);
      localDataOutputStream1.write(c);
      localDataOutputStream1.writeShort(d);
      localDataOutputStream1.writeShort(e);
      localDataOutputStream1.writeByte(f);
      g[15] = 0;
      localDataOutputStream1.write(agc.a(g, g.length));
      h[31] = 0;
      localDataOutputStream1.write(agc.a(h, h.length));
      localDataOutputStream1.writeShort(i);
      for (int m = 0; m < i; m = (short)(m + 1))
      {
        ByteArrayOutputStream localByteArrayOutputStream2 = new ByteArrayOutputStream();
        DataOutputStream localDataOutputStream2 = new DataOutputStream(localByteArrayOutputStream2);
        localDataOutputStream2.flush();
        aid localaid = (aid)j.get(m);
        if (((c == null) || (c.a(localDataOutputStream2).booleanValue())) || (((d == null) || (d.a(localDataOutputStream2).booleanValue())) || (((e == null) || (e.a(localDataOutputStream2).booleanValue())) || (((f == null) || (f.a(localDataOutputStream2).booleanValue())) || ((g != null) && (!g.a(localDataOutputStream2).booleanValue())))))) {}
        a = Integer.valueOf(localByteArrayOutputStream2.size() + 4).shortValue();
        localDataOutputStream1.writeShort(a);
        localDataOutputStream1.writeInt(b);
        localDataOutputStream1.write(localByteArrayOutputStream2.toByteArray());
      }
      l = Integer.valueOf(localByteArrayOutputStream1.size()).shortValue();
      paramDataOutputStream.writeByte(k);
      paramDataOutputStream.writeShort(l);
      paramDataOutputStream.write(localByteArrayOutputStream1.toByteArray());
      return Boolean.valueOf(true);
    }
    catch (IOException paramDataOutputStream) {}
    return Boolean.valueOf(false);
  }
  
  protected final byte[] a()
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    a(new DataOutputStream(localByteArrayOutputStream));
    return localByteArrayOutputStream.toByteArray();
  }
}

/* Location:
 * Qualified Name:     afv
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */