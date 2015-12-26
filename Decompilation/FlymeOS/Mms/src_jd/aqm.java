import android.text.TextUtils;
import com.ted.android.data.BubbleEntity;
import java.util.List;
import java.util.regex.Pattern;

abstract class aqm
{
  private static final Pattern c = Pattern.compile("[\\d\\-/]{1,5}");
  protected long a;
  protected String b;
  
  protected static boolean b(String paramString)
  {
    return (TextUtils.isEmpty(paramString)) || ("null".equals(paramString));
  }
  
  public BubbleEntity a()
  {
    BubbleEntity localBubbleEntity = new BubbleEntity();
    localBubbleEntity.setId("-4");
    localBubbleEntity.setShowType(2);
    return localBubbleEntity;
  }
  
  public abstract String a(String paramString, List<BubbleEntity> paramList);
  
  public String a(String paramString1, List<BubbleEntity> paramList, long paramLong, String paramString2)
  {
    a = paramLong;
    b = paramString2;
    return a(paramString1, paramList);
  }
  
  protected boolean a(String paramString)
  {
    return paramString.length() <= 3;
  }
}

/* Location:
 * Qualified Name:     aqm
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */