import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public class us
  extends ur
{
  public us(Context paramContext)
  {
    super(paramContext, a(paramContext));
  }
  
  protected static List<ur.a> a(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList(2);
    a(localArrayList, paramContext.getString(2131493105), 2130837750);
    a(localArrayList, paramContext.getString(2131493101), 2130837749);
    return localArrayList;
  }
  
  protected static void a(List<ur.a> paramList, String paramString, int paramInt)
  {
    paramList.add(new ur.a(paramString, paramInt));
  }
}

/* Location:
 * Qualified Name:     us
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */