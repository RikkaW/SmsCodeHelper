import org.apache.http.Header;

public abstract class ath<JSON_TYPE>
  extends auv
{
  public ath()
  {
    this("UTF-8");
  }
  
  public ath(String paramString)
  {
    super(paramString);
  }
  
  public final void a(int paramInt, Header[] paramArrayOfHeader, String paramString)
  {
    if (paramInt != 204)
    {
      paramArrayOfHeader = new ati(this, paramString, paramInt, paramArrayOfHeader);
      if (!d())
      {
        new Thread(paramArrayOfHeader).start();
        return;
      }
      paramArrayOfHeader.run();
      return;
    }
    a(paramInt, paramArrayOfHeader, null, null);
  }
  
  public abstract void a(int paramInt, Header[] paramArrayOfHeader, String paramString, JSON_TYPE paramJSON_TYPE);
  
  public final void a(int paramInt, Header[] paramArrayOfHeader, String paramString, Throwable paramThrowable)
  {
    if (paramString != null)
    {
      paramArrayOfHeader = new atp(this, paramString, paramInt, paramArrayOfHeader, paramThrowable);
      if (!d())
      {
        new Thread(paramArrayOfHeader).start();
        return;
      }
      paramArrayOfHeader.run();
      return;
    }
    a(paramInt, paramArrayOfHeader, paramThrowable, null, null);
  }
  
  public abstract void a(int paramInt, Header[] paramArrayOfHeader, Throwable paramThrowable, String paramString, JSON_TYPE paramJSON_TYPE);
  
  protected abstract JSON_TYPE b(String paramString, boolean paramBoolean);
}

/* Location:
 * Qualified Name:     ath
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */