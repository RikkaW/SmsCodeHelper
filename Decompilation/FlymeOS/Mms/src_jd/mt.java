import android.net.Uri;
import android.util.Log;
import java.util.Locale;

public class mt
{
  public static final Uri a = Uri.parse("content://smsquickreply/all");
  
  public static String a()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (Locale.getDefault() != null)
    {
      localStringBuilder.append(Locale.getDefault().getLanguage());
      localStringBuilder.append("_");
      localStringBuilder.append(Locale.getDefault().getCountry());
    }
    for (;;)
    {
      return localStringBuilder.toString();
      Log.i("QuickReplyProvider", "Locale.getDefault() is null");
    }
  }
}

/* Location:
 * Qualified Name:     mt
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */