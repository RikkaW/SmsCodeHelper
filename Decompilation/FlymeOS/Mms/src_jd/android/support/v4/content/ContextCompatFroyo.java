package android.support.v4.content;

import android.content.Context;
import java.io.File;

class ContextCompatFroyo
{
  public static File getExternalCacheDir(Context paramContext)
  {
    return paramContext.getExternalCacheDir();
  }
  
  public static File getExternalFilesDir(Context paramContext, String paramString)
  {
    return paramContext.getExternalFilesDir(paramString);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.content.ContextCompatFroyo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */