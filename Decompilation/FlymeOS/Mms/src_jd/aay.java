import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class aay<K, V>
{
  private final aay<K, V>.b a;
  private final aay<K, V>.a b;
  
  public aay(int paramInt1, int paramInt2, float paramFloat, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      a = null;
      b = new aay.a(paramInt1, paramInt2, paramFloat);
      return;
    }
    a = new aay.b(paramInt1, paramInt2, paramFloat);
    b = null;
  }
  
  private static <V> V a(SoftReference<V> paramSoftReference)
  {
    if (paramSoftReference != null) {
      return (V)paramSoftReference.get();
    }
    return null;
  }
  
  public V a(Object paramObject)
  {
    if (a != null) {
      return (V)a((SoftReference)a.get(paramObject));
    }
    return (V)b.get(paramObject);
  }
  
  public V a(K paramK, V paramV)
  {
    if (a != null) {
      return (V)a((SoftReference)a.put(paramK, new SoftReference(paramV)));
    }
    return (V)b.put(paramK, paramV);
  }
  
  public void a()
  {
    if (a != null)
    {
      a.clear();
      return;
    }
    b.clear();
  }
  
  public V b(K paramK)
  {
    if (a != null) {
      return (V)a((SoftReference)a.remove(paramK));
    }
    return (V)b.remove(paramK);
  }
  
  class a
    extends LinkedHashMap<K, V>
  {
    private final int b;
    
    public a(int paramInt1, int paramInt2, float paramFloat)
    {
      super(paramFloat, true);
      b = paramInt2;
    }
    
    protected boolean removeEldestEntry(Map.Entry<K, V> paramEntry)
    {
      return size() > b;
    }
  }
  
  class b
    extends LinkedHashMap<K, SoftReference<V>>
  {
    private final int b;
    
    public b(int paramInt1, int paramInt2, float paramFloat)
    {
      super(paramFloat, true);
      b = paramInt2;
    }
    
    protected boolean removeEldestEntry(Map.Entry<K, SoftReference<V>> paramEntry)
    {
      return size() > b;
    }
  }
}

/* Location:
 * Qualified Name:     aay
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */