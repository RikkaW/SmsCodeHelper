import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;

final class agd
  implements Serializable
{
  protected int a = 0;
  protected int b = 0;
  protected short c = 0;
  protected short d = 0;
  protected int e = 0;
  protected byte f = 0;
  private byte g = 4;
  
  protected final Boolean a(DataOutputStream paramDataOutputStream)
  {
    try
    {
      paramDataOutputStream.writeByte(g);
      paramDataOutputStream.writeInt(a);
      paramDataOutputStream.writeInt(b);
      paramDataOutputStream.writeShort(c);
      paramDataOutputStream.writeShort(d);
      paramDataOutputStream.writeInt(e);
      paramDataOutputStream.writeByte(f);
      return Boolean.valueOf(true);
    }
    catch (IOException paramDataOutputStream) {}
    return Boolean.valueOf(false);
  }
}

/* Location:
 * Qualified Name:     agd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */