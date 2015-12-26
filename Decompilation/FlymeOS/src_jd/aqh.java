import android.util.LruCache;
import com.ted.android.data.SmsEntity;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class aqh
  extends LruCache<Long, SmsEntity>
{
  private static final String a = aqh.class.getSimpleName();
  private static aqh b;
  private final Set<Long> c = Collections.synchronizedSet(new HashSet(1024));
  
  private aqh()
  {
    super(1024);
  }
  
  public static aqh a()
  {
    if (b == null) {}
    try
    {
      if (b == null) {
        b = new aqh();
      }
      return b;
    }
    finally {}
  }
  
  public void a(Long paramLong, SmsEntity paramSmsEntity)
  {
    if (!c.contains(paramLong)) {
      c.add(paramLong);
    }
    if ((paramLong == null) || (paramSmsEntity == null)) {
      return;
    }
    put(paramLong, paramSmsEntity);
  }
  
  protected void a(boolean paramBoolean, Long paramLong, SmsEntity paramSmsEntity1, SmsEntity paramSmsEntity2)
  {
    super.entryRemoved(paramBoolean, paramLong, paramSmsEntity1, paramSmsEntity2);
    if ((c.contains(paramLong)) && (paramSmsEntity2 == null)) {
      c.remove(paramLong);
    }
  }
  
  public boolean a(Long paramLong)
  {
    return c.contains(paramLong);
  }
}

/* Location:
 * Qualified Name:     aqh
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */