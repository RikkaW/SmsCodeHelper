import com.android.mms.MmsApp;
import com.android.mms.MmsApp.d;
import java.util.HashSet;
import java.util.Iterator;

public class fs
  implements aba.b
{
  public fs(MmsApp paramMmsApp) {}
  
  public void a(int paramInt)
  {
    Iterator localIterator = MmsApp.r.iterator();
    while (localIterator.hasNext()) {
      ((MmsApp.d)localIterator.next()).a(paramInt);
    }
  }
}

/* Location:
 * Qualified Name:     fs
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */