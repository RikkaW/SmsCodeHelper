import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.ted.sdk.yellow.provider.YellowPageProvider;

public class ass
  implements aoj
{
  private final Uri a;
  private final Context b;
  
  public ass(Context paramContext, String paramString)
  {
    b = paramContext;
    a = Uri.withAppendedPath(YellowPageProvider.a, paramString);
  }
  
  public int a(ContentValues paramContentValues, String paramString, String[] paramArrayOfString)
  {
    if (b == null) {
      return 0;
    }
    return b.getContentResolver().update(a, paramContentValues, paramString, paramArrayOfString);
  }
  
  public int a(String paramString, String[] paramArrayOfString)
  {
    if (b == null) {
      return 0;
    }
    return b.getContentResolver().delete(a, paramString, paramArrayOfString);
  }
  
  public Cursor a(String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2)
  {
    if (b == null) {
      return null;
    }
    return b.getContentResolver().query(a, paramArrayOfString1, paramString1, paramArrayOfString2, paramString2);
  }
  
  public boolean a(ContentValues paramContentValues)
  {
    if (b == null) {
      return false;
    }
    b.getContentResolver().insert(a, paramContentValues);
    return true;
  }
}

/* Location:
 * Qualified Name:     ass
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */