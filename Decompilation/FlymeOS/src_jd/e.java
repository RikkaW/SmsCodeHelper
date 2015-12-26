import java.util.HashMap;

public class e
  extends HashMap<String, Object>
{
  private static final long serialVersionUID = -373852423907495496L;
  private d a = null;
  private boolean b = true;
  
  public d<?> a()
  {
    return a;
  }
  
  public o a(String paramString)
  {
    if (containsKey(paramString)) {}
    for (Object localObject = get(paramString); (localObject instanceof o); localObject = b(paramString)) {
      return (o)localObject;
    }
    return o.a(paramString, localObject);
  }
  
  public void a(d<?> paramd)
  {
    a = paramd;
  }
  
  public Object b(String paramString)
  {
    return null;
  }
  
  public boolean b()
  {
    return b;
  }
}

/* Location:
 * Qualified Name:     e
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */