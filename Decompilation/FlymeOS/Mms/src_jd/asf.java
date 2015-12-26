import android.content.Context;
import android.text.TextUtils;
import com.ted.android.contacts.common.util.NovoFileUtil;
import com.ted.android.utils.TedSDKLog;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class asf
{
  public static asf a;
  private static final String b = asf.class.getSimpleName();
  private Map<String, Integer> c = Collections.synchronizedMap(new HashMap());
  private Context d;
  private int e;
  private boolean f = false;
  private boolean g;
  private boolean h = false;
  private String i;
  
  public static asf a()
  {
    if (a == null) {}
    try
    {
      a = new asf();
      return a;
    }
    finally {}
  }
  
  private void b(String paramString)
  {
    TedSDKLog.begin(b);
    try
    {
      paramString = new JSONObject(paramString);
      Object localObject = paramString.getString("service");
      e = paramString.getInt("version");
      if ((paramString.has("recharge")) && ("oppo".equals(paramString.getString("recharge")))) {
        f = true;
      }
      paramString = asf.a.b((String)localObject);
      if ((paramString != null) && (paramString.size() > 0))
      {
        c.clear();
        paramString = paramString.iterator();
      }
      for (;;)
      {
        if (!paramString.hasNext())
        {
          TedSDKLog.d(b, "parseConfig: " + c);
          return;
        }
        localObject = (asf.a)paramString.next();
        c.put(a, Integer.valueOf(b));
      }
      return;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  private void c()
  {
    for (;;)
    {
      try
      {
        Object localObject = NovoFileUtil.openLatestInputFile(d, "sms.cfg");
        if (localObject != null)
        {
          localObject = new BufferedReader(new InputStreamReader((InputStream)localObject));
          localStringBuilder = new StringBuilder();
          str = ((BufferedReader)localObject).readLine();
          if (str != null) {
            continue;
          }
          g = true;
          i = localStringBuilder.toString();
          ((BufferedReader)localObject).close();
        }
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        StringBuilder localStringBuilder;
        String str;
        g = false;
        continue;
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
        g = false;
        continue;
      }
      if (!TextUtils.isEmpty(i)) {
        b(i);
      }
      return;
      localStringBuilder.append(str);
    }
  }
  
  public int a(String paramString)
  {
    if (!h) {
      throw new IllegalStateException("initialise first");
    }
    if (c.containsKey(paramString)) {
      return ((Integer)c.get(paramString)).intValue();
    }
    try
    {
      int j = Integer.parseInt(paramString);
      if (j > 100000)
      {
        j = j / 100000 * 100000;
        if (c.containsKey(String.valueOf(j)))
        {
          j = ((Integer)c.get(String.valueOf(j))).intValue();
          return j;
        }
      }
    }
    catch (NumberFormatException localNumberFormatException)
    {
      localNumberFormatException.printStackTrace();
      TedSDKLog.e(b, paramString + " is not a number");
    }
    return 0;
  }
  
  public void a(Context paramContext)
  {
    d = paramContext.getApplicationContext();
    new Thread(new asg(this)).start();
    h = true;
  }
  
  public boolean b()
  {
    return g;
  }
  
  static class a
  {
    String a;
    int b;
    
    public static a a(String paramString)
    {
      try
      {
        paramString = new JSONObject(paramString);
        a locala = new a();
        Object localObject = paramString.keys();
        if ((localObject != null) && (((Iterator)localObject).hasNext()))
        {
          localObject = (String)((Iterator)localObject).next();
          int i = paramString.getInt((String)localObject);
          a = ((String)localObject);
          b = i;
        }
        return locala;
      }
      catch (JSONException paramString)
      {
        paramString.printStackTrace();
      }
      return null;
    }
    
    public static List<a> b(String paramString)
    {
      localArrayList = new ArrayList();
      try
      {
        paramString = new JSONArray(paramString);
        if ((paramString != null) && (paramString.length() > 1))
        {
          int i = 0;
          for (;;)
          {
            if (i >= paramString.length()) {
              return localArrayList;
            }
            a locala = a(paramString.getString(i));
            if (locala != null) {
              localArrayList.add(locala);
            }
            i += 1;
          }
        }
        return localArrayList;
      }
      catch (JSONException paramString)
      {
        paramString.printStackTrace();
      }
    }
  }
}

/* Location:
 * Qualified Name:     asf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */