import android.net.Uri;
import android.text.TextUtils;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.http.client.utils.URIUtils;

public class anx
{
  private Uri a;
  private String b;
  private String c;
  private int d;
  private String e;
  private String f;
  private String g;
  private List<String> h;
  
  public anx(String paramString)
  {
    a = Uri.parse(paramString);
    b = a.getScheme();
    c = a.getHost();
    d = a.getPort();
    e = a.getPath();
    f = a.getQuery();
    g = a.getFragment();
    h = new ArrayList();
    if (f == null) {
      f = "";
    }
    String[] arrayOfString = f.split("&");
    int j = arrayOfString.length;
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return;
      }
      String str1 = arrayOfString[i];
      if (!TextUtils.isEmpty(str1))
      {
        int k = str1.indexOf("=");
        paramString = str1;
        if (k > -1)
        {
          String str2 = str1.substring(k + 1);
          paramString = str1;
          if (!TextUtils.isEmpty(str2)) {
            paramString = str1.replace(str2, URLEncoder.encode(str2));
          }
        }
        h.add(paramString);
      }
      i += 1;
    }
  }
  
  private boolean b(String paramString1, String paramString2)
  {
    boolean bool2 = false;
    paramString2 = paramString2.split("=");
    boolean bool1 = bool2;
    if (paramString2.length > 0)
    {
      bool1 = bool2;
      if (paramString1.equalsIgnoreCase(paramString2[0])) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public String a()
  {
    for (;;)
    {
      try
      {
        f = "";
        Iterator localIterator = h.iterator();
        if (!localIterator.hasNext()) {
          return URIUtils.createURI(b, c, d, e, f, g).toString();
        }
        String str = (String)localIterator.next();
        if (f.length() == 0) {
          f += str;
        } else {
          f = (f + "&" + str);
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return null;
      }
    }
  }
  
  public void a(String paramString1, String paramString2)
  {
    int j = 0;
    paramString2 = paramString1 + "=" + paramString2;
    int i = 0;
    for (;;)
    {
      if (i >= h.size()) {}
      for (i = j;; i = 1)
      {
        if (i == 0) {
          h.add(paramString2);
        }
        return;
        if (!b(paramString1, (String)h.get(i))) {
          break;
        }
        h.set(i, paramString2);
      }
      i += 1;
    }
  }
  
  public boolean a(String paramString)
  {
    return b(paramString) != null;
  }
  
  public String b(String paramString)
  {
    Iterator localIterator = h.iterator();
    String str;
    do
    {
      if (!localIterator.hasNext()) {
        return null;
      }
      str = (String)localIterator.next();
    } while (!b(paramString, str));
    return str;
  }
  
  public boolean c(String paramString)
  {
    paramString = b(paramString);
    return (paramString != null) && (paramString.contains("=")) && (!paramString.endsWith("="));
  }
}

/* Location:
 * Qualified Name:     anx
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */