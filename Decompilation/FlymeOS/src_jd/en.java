import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;

public class en
{
  public static void a(Activity paramActivity)
  {
    try
    {
      Field localField = Application.class.getDeclaredField("mComponentCallbacks");
      if (!localField.isAccessible()) {
        localField.setAccessible(true);
      }
      paramActivity = localField.get(paramActivity.getApplication());
      if ((paramActivity != null) && ((paramActivity instanceof ArrayList)))
      {
        paramActivity = ((ArrayList)paramActivity).iterator();
        while (paramActivity.hasNext()) {
          if (((ComponentCallbacks)paramActivity.next()).getClass().getName().equals("com.android.org.chromium.android_webview.AwContents$AwComponentCallbacks")) {
            paramActivity.remove();
          }
        }
      }
      return;
    }
    catch (Throwable paramActivity)
    {
      paramActivity.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     en
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */