import android.content.Context;
import android.net.Uri;

public abstract class lo
  extends lm
{
  protected lp o;
  protected boolean p = true;
  
  public lo(Context paramContext, String paramString, Uri paramUri, lp paramlp)
  {
    this(paramContext, paramString, null, null, paramUri, paramlp);
  }
  
  public lo(Context paramContext, String paramString1, String paramString2, String paramString3, Uri paramUri, lp paramlp)
  {
    super(paramContext, paramString1, paramString2, paramString3, paramUri);
    o = paramlp;
  }
  
  public lo(Context paramContext, String paramString1, String paramString2, String paramString3, byte[] paramArrayOfByte, lp paramlp)
  {
    super(paramContext, paramString1, paramString2, paramString3, paramArrayOfByte);
    o = paramlp;
  }
  
  public lp C()
  {
    return o;
  }
  
  public boolean D()
  {
    return p;
  }
}

/* Location:
 * Qualified Name:     lo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */