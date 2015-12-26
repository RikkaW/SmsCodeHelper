import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

final class afz
  implements Serializable
{
  protected byte a = 0;
  protected ArrayList b = new ArrayList();
  private byte c = 8;
  
  protected final Boolean a(DataOutputStream paramDataOutputStream)
  {
    try
    {
      paramDataOutputStream.writeByte(c);
      paramDataOutputStream.writeByte(a);
      int i = 0;
      while (i < a)
      {
        aga localaga = (aga)b.get(i);
        paramDataOutputStream.write(a);
        paramDataOutputStream.writeShort(b);
        paramDataOutputStream.write(agc.a(c, c.length));
        i += 1;
      }
      return Boolean.valueOf(true);
    }
    catch (IOException paramDataOutputStream) {}
    return Boolean.valueOf(false);
  }
}

/* Location:
 * Qualified Name:     afz
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */