import android.content.Context;
import android.content.res.Resources;
import android.util.SparseIntArray;

public final class abh$b
{
  public static final SparseIntArray a = new SparseIntArray();
  public static final SparseIntArray b = new SparseIntArray();
  
  static
  {
    a.put(1, 2131493583);
    a.put(2, 2131493585);
  }
  
  public static final String a(Context paramContext, int paramInt1, int paramInt2)
  {
    String str = null;
    Object localObject = paramContext.getResources();
    Integer localInteger = Integer.valueOf(a.get(paramInt1));
    if (((paramInt1 == 1) || (paramInt1 == 2)) && (localInteger != null)) {
      str = ((Resources)localObject).getString(localInteger.intValue());
    }
    do
    {
      do
      {
        return str;
        paramInt1 -= 2;
        if (paramInt1 >= 0) {
          break;
        }
      } while (paramInt2 == -1);
      return paramContext.getResources().getString(paramInt2);
      localObject = ((Resources)localObject).getStringArray(2131361798);
      if (localObject.length > paramInt1) {
        break;
      }
    } while (paramInt2 == -1);
    return paramContext.getResources().getString(paramInt2);
    return localObject[paramInt1];
  }
  
  public static final void a(Context paramContext)
  {
    paramContext = paramContext.getResources().getIntArray(2131361814);
    int i = 0;
    while (i < paramContext.length)
    {
      b.put(paramContext[i], i);
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     abh.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */