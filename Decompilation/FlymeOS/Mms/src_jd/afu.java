import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;

final class afu
  implements Serializable
{
  protected int a = 0;
  protected int b = 0;
  protected int c = 0;
  protected int d = 0;
  protected int e = 0;
  protected short f = 0;
  protected byte g = 0;
  protected byte h = 0;
  protected long i = 0L;
  protected long j = 0L;
  private byte k = 1;
  
  protected final Boolean a(DataOutputStream paramDataOutputStream)
  {
    Boolean localBoolean = Boolean.valueOf(false);
    if (paramDataOutputStream == null) {
      return localBoolean;
    }
    try
    {
      paramDataOutputStream.writeByte(k);
      paramDataOutputStream.writeInt(a);
      paramDataOutputStream.writeInt(b);
      paramDataOutputStream.writeInt(c);
      paramDataOutputStream.writeInt(d);
      paramDataOutputStream.writeInt(e);
      paramDataOutputStream.writeShort(f);
      paramDataOutputStream.writeByte(g);
      paramDataOutputStream.writeByte(h);
      paramDataOutputStream.writeLong(i);
      paramDataOutputStream.writeLong(j);
      return Boolean.valueOf(true);
    }
    catch (IOException paramDataOutputStream) {}
    return localBoolean;
  }
}

/* Location:
 * Qualified Name:     afu
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */