import com.android.mms.model.Model;

public class lp
  extends Model
{
  private final String a;
  private String b;
  private int c;
  private int d;
  private int e;
  private int f;
  private String g;
  
  public lp(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this(paramString, "meet", paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public lp(String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this(paramString1, paramString2, paramInt1, paramInt2, paramInt3, paramInt4, null);
  }
  
  public lp(String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString3)
  {
    a = paramString1;
    b = paramString2;
    c = paramInt1;
    d = paramInt2;
    e = paramInt3;
    f = paramInt4;
    g = paramString3;
  }
  
  public String a()
  {
    return a;
  }
  
  public void a(int paramInt)
  {
    d = paramInt;
    a(true);
  }
  
  public String b()
  {
    return b;
  }
  
  public int c()
  {
    return c;
  }
  
  public int d()
  {
    return d;
  }
  
  public int e()
  {
    return e;
  }
  
  public int f()
  {
    return f;
  }
  
  public String g()
  {
    return g;
  }
}

/* Location:
 * Qualified Name:     lp
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */