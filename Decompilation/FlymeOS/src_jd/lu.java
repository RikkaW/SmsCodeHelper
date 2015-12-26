import android.content.Context;
import android.util.Log;
import com.meizu.android.mms.pdu.MzCharacterSets;
import java.io.UnsupportedEncodingException;

public class lu
  extends lo
{
  private CharSequence q;
  private final int r;
  
  public lu(Context paramContext, String paramString1, String paramString2, int paramInt, byte[] paramArrayOfByte, lp paramlp) {}
  
  public lu(Context paramContext, String paramString1, String paramString2, lp paramlp)
  {
    this(paramContext, paramString1, paramString2, 106, new byte[0], paramlp);
  }
  
  private CharSequence a(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte != null) {
      try
      {
        if (r == 0) {
          return new String(paramArrayOfByte);
        }
        String str = new String(paramArrayOfByte, MzCharacterSets.getMimeName(r));
        return str;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        Log.e("Mms/text", "Unsupported encoding: " + r, localUnsupportedEncodingException);
        return new String(paramArrayOfByte);
      }
    }
    return "";
  }
  
  public String a()
  {
    if (q == null) {
      q = a(m());
    }
    if (!(q instanceof String)) {
      q = q.toString();
    }
    return q.toString();
  }
  
  public void a(att paramatt)
  {
    if (paramatt.b().equals("SmilMediaStart")) {
      p = true;
    }
    for (;;)
    {
      a(false);
      return;
      if (h != 1) {
        p = false;
      }
    }
  }
  
  public void a(CharSequence paramCharSequence)
  {
    q = paramCharSequence;
    a(true);
  }
  
  public void b()
  {
    if (q != null) {}
    for (String str = q.toString();; str = "")
    {
      q = str;
      return;
    }
  }
  
  public int d()
  {
    return r;
  }
}

/* Location:
 * Qualified Name:     lu
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */