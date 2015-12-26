import java.io.InputStream;

public abstract class afc
{
  public abstract void a(afb paramafb);
  
  public abstract void a(InputStream paramInputStream);
  
  @Deprecated
  public void a(InputStream paramInputStream, afb paramafb)
  {
    a(paramafb);
    a(paramInputStream);
  }
}

/* Location:
 * Qualified Name:     afc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */