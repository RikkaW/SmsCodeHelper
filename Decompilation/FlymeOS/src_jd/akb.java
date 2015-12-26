import java.io.OutputStream;
import org.apache.http.util.EncodingUtils;

public class akb
  extends aka
{
  private byte[] i;
  private String j;
  
  public akb(String paramString1, String paramString2)
  {
    this(paramString1, paramString2, null);
  }
  
  public akb(String paramString1, String paramString2, String paramString3)
  {
    super(paramString1, "text/plain", str, "8bit");
    paramString1 = paramString2;
    if (paramString2 == null) {
      paramString1 = "";
    }
    if (paramString1.indexOf(0) != -1) {
      throw new IllegalArgumentException("NULs may not be present in string parts");
    }
    j = paramString1;
  }
  
  private byte[] i()
  {
    if (i == null) {
      i = EncodingUtils.getBytes(j, d());
    }
    return i;
  }
  
  protected long a()
  {
    return i().length;
  }
  
  protected void b(OutputStream paramOutputStream)
  {
    paramOutputStream.write(i());
  }
}

/* Location:
 * Qualified Name:     akb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */