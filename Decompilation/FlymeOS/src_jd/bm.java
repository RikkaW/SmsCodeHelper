import android.content.Context;
import com.ted.android.contacts.common.ComManager;
import com.ted.android.contacts.common.DataBus;
import com.ted.android.contacts.common.util.NovoFileUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class bm
{
  private static ArrayList<String> a = new ArrayList();
  private static boolean b = false;
  
  public static void a(Context paramContext)
  {
    try
    {
      ComManager.a(paramContext);
      localObject1 = NovoFileUtil.openLatestInputFile(paramContext, "w.dat");
      if (localObject1 != null)
      {
        paramContext = paramContext.getCacheDir().getAbsolutePath() + "/" + "w.dat";
        File localFile = new File(paramContext);
        localObject2 = new FileOutputStream(localFile);
        anv.a((InputStream)localObject1, (OutputStream)localObject2, DataBus.FILE_MASK);
        ((OutputStream)localObject2).close();
        ((InputStream)localObject1).close();
        FileInputStream localFileInputStream = new FileInputStream(paramContext);
        InputStreamReader localInputStreamReader = new InputStreamReader(localFileInputStream, "utf-8");
        localBufferedReader = new BufferedReader(localInputStreamReader);
        localObject1 = localBufferedReader.readLine();
        if (localObject1 != null) {
          break label150;
        }
        localInputStreamReader.close();
        localFileInputStream.close();
        localFile.delete();
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        Object localObject1;
        Object localObject2;
        BufferedReader localBufferedReader;
        label150:
        paramContext.printStackTrace();
        continue;
        int i = 0;
      }
    }
    b = true;
    return;
    paramContext = "^";
    if ((((String)localObject1).length() > 0) && (((String)localObject1).charAt(0) == '+'))
    {
      paramContext = "+^";
      i = 1;
      for (;;)
      {
        if (i >= ((String)localObject1).length())
        {
          paramContext = paramContext + "$";
          a.add(paramContext);
          localObject1 = localBufferedReader.readLine();
          break;
        }
        char c = ((String)localObject1).charAt(i);
        if (c != '*')
        {
          localObject2 = paramContext;
          if (c != '?') {}
        }
        else
        {
          localObject2 = paramContext + ".";
        }
        paramContext = localObject2 + c;
        i += 1;
      }
    }
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0)) {}
    for (;;)
    {
      return false;
      if (!b) {
        a(paramContext);
      }
      int i = 0;
      while (i < a.size())
      {
        if (a((String)a.get(i), paramString)) {
          return true;
        }
        i += 1;
      }
    }
  }
  
  private static boolean a(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString1.length() == 0)) {}
    do
    {
      return false;
      str2 = paramString1;
      str1 = paramString2;
      if (paramString1.charAt(0) != '+') {
        break;
      }
    } while (paramString2.charAt(0) != '+');
    String str2 = paramString1.substring(1);
    String str1 = paramString2.substring(1);
    try
    {
      boolean bool = Pattern.matches(str2, str1);
      return bool;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     bm
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */