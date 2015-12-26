import java.lang.ref.WeakReference;

public class aus
{
  private final WeakReference<asi> a;
  
  public aus(asi paramasi)
  {
    a = new WeakReference(paramasi);
  }
  
  public boolean a()
  {
    asi localasi = (asi)a.get();
    return (localasi == null) || (localasi.b());
  }
  
  public boolean b()
  {
    asi localasi = (asi)a.get();
    return (localasi == null) || (localasi.a());
  }
  
  public boolean c()
  {
    if ((!b()) && (!a())) {}
    for (boolean bool = false;; bool = true)
    {
      if (bool) {
        a.clear();
      }
      return bool;
    }
  }
}

/* Location:
 * Qualified Name:     aus
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */