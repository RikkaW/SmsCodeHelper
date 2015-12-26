import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ask
{
  private static final String a = ask.class.getSimpleName();
  private static ask b;
  private static Map<String, String> c = new HashMap();
  private Context d;
  
  private ask(Context paramContext)
  {
    d = paramContext;
    a();
  }
  
  public static ask a(Context paramContext)
  {
    if (b == null) {}
    try
    {
      b = new ask(paramContext);
      return b;
    }
    finally {}
  }
  
  private void a()
  {
    try
    {
      Object localObject1 = d.getAssets().open("md5.txt");
      if (localObject1 != null)
      {
        localObject1 = new BufferedReader(new InputStreamReader((InputStream)localObject1));
        for (;;)
        {
          Object localObject2 = ((BufferedReader)localObject1).readLine();
          if (localObject2 == null) {
            return;
          }
          localObject2 = ((String)localObject2).split("\t");
          if (localObject2.length >= 2) {
            c.put(localObject2[0], localObject2[1]);
          }
        }
      }
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      Log.e(a, "md5文件不存在!");
    }
  }
  
  public int a(String paramString)
  {
    Object localObject = new File(paramString);
    if (!((File)localObject).exists()) {
      return 1;
    }
    localObject = ((File)localObject).getName();
    paramString = anv.a(paramString);
    localObject = (String)c.get(localObject);
    if (TextUtils.isEmpty((CharSequence)localObject)) {
      return 2;
    }
    if (TextUtils.equals(paramString, (CharSequence)localObject)) {
      return 0;
    }
    return 3;
  }
}

/* Location:
 * Qualified Name:     ask
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */