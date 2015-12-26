package cn.com.xy.sms.sdk.util;

import java.io.File;
import java.io.FileFilter;

public final class n
  implements FileFilter
{
  private String a = "";
  private String b = "";
  
  public n(String paramString1, String paramString2)
  {
    a = paramString1;
    b = paramString2;
  }
  
  public final boolean accept(File paramFile)
  {
    paramFile = paramFile.getName();
    return (paramFile.startsWith(a)) && (paramFile.endsWith(b));
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.util.n
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */