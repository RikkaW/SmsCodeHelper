import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

final class aib
  implements Serializable
{
  protected short a = 0;
  protected int b = 0;
  protected byte c = 0;
  protected byte d = 0;
  protected ArrayList e = new ArrayList();
  private byte f = 2;
  
  protected final Boolean a(DataOutputStream paramDataOutputStream)
  {
    try
    {
      paramDataOutputStream.writeByte(f);
      paramDataOutputStream.writeShort(a);
      paramDataOutputStream.writeInt(b);
      paramDataOutputStream.writeByte(c);
      paramDataOutputStream.writeByte(d);
      int i = 0;
      while (i < d)
      {
        paramDataOutputStream.writeShort(e.get(i)).a);
        paramDataOutputStream.writeInt(e.get(i)).b);
        paramDataOutputStream.writeByte(e.get(i)).c);
        i += 1;
      }
      return Boolean.valueOf(true);
    }
    catch (IOException paramDataOutputStream) {}
    return Boolean.valueOf(false);
  }
}

/* Location:
 * Qualified Name:     aib
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */