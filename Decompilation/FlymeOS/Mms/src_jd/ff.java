import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;
import com.ted.android.contacts.common.DataBus;
import com.ted.android.contacts.common.util.NovoFileUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ff
{
  public static boolean a(Context paramContext, String paramString1, String paramString2)
  {
    return a(paramContext, paramString1, paramString2, null);
  }
  
  public static boolean a(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    String str = paramString3;
    if (TextUtils.isEmpty(paramString3)) {
      str = paramString1;
    }
    paramString2 = new File(paramString2 + File.separator + str);
    try
    {
      paramContext = NovoFileUtil.openLatestInputFile(paramContext, paramString1);
      paramString1 = new FileOutputStream(paramString2);
      anv.a(paramContext, paramString1, DataBus.FILE_MASK);
      paramString1.close();
      paramContext.close();
      return true;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean a(InputStream paramInputStream, String paramString)
  {
    return a(paramInputStream, DataBus.U1_YEK, paramString);
  }
  
  public static boolean a(InputStream paramInputStream, String paramString1, String paramString2)
  {
    for (;;)
    {
      try
      {
        paramInputStream = new ZipInputStream(new any(paramInputStream, paramString1));
        paramString1 = paramInputStream.getNextEntry();
        if (paramString1 == null)
        {
          paramInputStream.close();
          return true;
        }
        paramString1 = new FileOutputStream(paramString2 + paramString1.getName());
        byte[] arrayOfByte = new byte[32768];
        int i = paramInputStream.read(arrayOfByte);
        if (i == -1)
        {
          paramString1.close();
          paramInputStream.closeEntry();
        }
        else
        {
          paramString1.write(arrayOfByte, 0, i);
        }
      }
      catch (Exception paramInputStream)
      {
        paramInputStream.printStackTrace();
        return false;
      }
    }
  }
  
  public static boolean b(Context paramContext, String paramString1, String paramString2)
  {
    return b(paramContext, paramString1, paramString2, null);
  }
  
  public static boolean b(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    String str = paramString3;
    if (TextUtils.isEmpty(paramString3)) {
      str = paramString1;
    }
    paramString2 = new File(paramString2 + File.separator + str);
    try
    {
      paramContext = paramContext.getAssets().open(paramString1);
      paramString1 = new FileOutputStream(paramString2);
      paramString2 = new byte['Ѐ'];
      for (;;)
      {
        int i = paramContext.read(paramString2);
        if (i <= 0)
        {
          paramString1.close();
          paramContext.close();
          return true;
        }
        paramString1.write(paramString2, 0, i);
      }
      return false;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     ff
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */