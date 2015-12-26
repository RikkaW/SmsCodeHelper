package android.support.v4.content;

import android.net.Uri;
import java.io.File;

abstract interface FileProvider$PathStrategy
{
  public abstract File getFileForUri(Uri paramUri);
  
  public abstract Uri getUriForFile(File paramFile);
}

/* Location:
 * Qualified Name:     android.support.v4.content.FileProvider.PathStrategy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */