import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

class aay$b
  extends LinkedHashMap<K, SoftReference<V>>
{
  private final int b;
  
  public aay$b(aay paramaay, int paramInt1, int paramInt2, float paramFloat)
  {
    super(paramInt1, paramFloat, true);
    b = paramInt2;
  }
  
  protected boolean removeEldestEntry(Map.Entry<K, SoftReference<V>> paramEntry)
  {
    return size() > b;
  }
}

/* Location:
 * Qualified Name:     aay.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */