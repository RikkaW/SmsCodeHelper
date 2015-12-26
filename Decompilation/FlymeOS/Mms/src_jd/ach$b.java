import android.net.Uri;
import android.text.TextUtils;

final class ach$b
{
  private String b = null;
  private Uri c = null;
  
  private ach$b(ach paramach) {}
  
  private void b(String paramString, Uri paramUri)
  {
    b = paramString;
    c = paramUri;
  }
  
  public void a()
  {
    b = null;
    c = null;
  }
  
  public boolean a(String paramString, Uri paramUri)
  {
    if ((TextUtils.isEmpty(paramString)) || (paramUri == null) || (TextUtils.isEmpty(paramUri.toString()))) {}
    while ((!paramString.equals(b)) || (!paramUri.equals(c))) {
      return false;
    }
    return true;
  }
}

/* Location:
 * Qualified Name:     ach.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */