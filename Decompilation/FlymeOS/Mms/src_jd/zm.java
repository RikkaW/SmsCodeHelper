import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class zm
{
  public static void a(Context paramContext, String paramString)
  {
    paramString = new Intent("android.intent.action.CALL", Uri.parse("tel:" + paramString));
    paramString.addFlags(268435456);
    paramContext.startActivity(paramString);
  }
}

/* Location:
 * Qualified Name:     zm
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */