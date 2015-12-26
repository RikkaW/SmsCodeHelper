import com.android.mms.view.MzContactHeaderWidget;
import java.util.ArrayList;

public class aes
  implements Runnable
{
  public aes(MzContactHeaderWidget paramMzContactHeaderWidget) {}
  
  public void run()
  {
    if (MzContactHeaderWidget.a(a) != null)
    {
      String[] arrayOfString = (String[])MzContactHeaderWidget.b(a).toArray(new String[MzContactHeaderWidget.b(a).size()]);
      int i = 0;
      while (i < MzContactHeaderWidget.b(a).size())
      {
        arrayOfString[i] = arrayOfString[i].replaceAll(" ", "");
        i += 1;
      }
      MzContactHeaderWidget.a(a).a(arrayOfString);
    }
  }
}

/* Location:
 * Qualified Name:     aes
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */