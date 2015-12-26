import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

final class afx
  implements Serializable
{
  protected byte a = 0;
  protected ArrayList b = new ArrayList();
  private byte c = 3;
  
  protected final Boolean a(DataOutputStream paramDataOutputStream)
  {
    try
    {
      paramDataOutputStream.writeByte(c);
      paramDataOutputStream.writeByte(a);
      int i = 0;
      if (i < b.size())
      {
        afy localafy = (afy)b.get(i);
        paramDataOutputStream.writeByte(a);
        byte[] arrayOfByte1 = new byte[a];
        byte[] arrayOfByte2 = b;
        if (a < b.length)
        {
          j = a;
          label87:
          System.arraycopy(arrayOfByte2, 0, arrayOfByte1, 0, j);
          paramDataOutputStream.write(arrayOfByte1);
          paramDataOutputStream.writeDouble(c);
          paramDataOutputStream.writeInt(d);
          paramDataOutputStream.writeInt(e);
          paramDataOutputStream.writeDouble(f);
          paramDataOutputStream.writeByte(g);
          paramDataOutputStream.writeByte(h);
          arrayOfByte1 = new byte[h];
          arrayOfByte2 = i;
          if (h >= i.length) {
            break label235;
          }
        }
        label235:
        for (int j = h;; j = i.length)
        {
          System.arraycopy(arrayOfByte2, 0, arrayOfByte1, 0, j);
          paramDataOutputStream.write(arrayOfByte1);
          paramDataOutputStream.writeByte(j);
          i += 1;
          break;
          j = b.length;
          break label87;
        }
      }
      return Boolean.valueOf(true);
    }
    catch (IOException paramDataOutputStream) {}
    return Boolean.valueOf(false);
  }
}

/* Location:
 * Qualified Name:     afx
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */