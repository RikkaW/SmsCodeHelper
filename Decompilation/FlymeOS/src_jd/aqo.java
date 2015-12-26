import android.text.TextUtils;
import com.ted.android.data.BubbleEntity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class aqo
  extends aqc
{
  private static final String a = aqo.class.getSimpleName();
  private static final Pattern b = Pattern.compile("[\\d\\-/]{1,5}");
  private static final Pattern[] c = { aqp.c, aru.c, arv.c, arw.c, arx.c, ary.c };
  private static aqo d;
  private long e;
  private List<aqm> f = new ArrayList();
  
  private aqo()
  {
    f.add(new aqp());
    f.add(new aru());
    f.add(new arv());
    f.add(new arw());
    f.add(new arx());
    f.add(new ary());
  }
  
  public static aqo a()
  {
    if (d == null) {}
    try
    {
      d = new aqo();
      return d;
    }
    finally {}
  }
  
  public static String a(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      Pattern[] arrayOfPattern = c;
      int j = arrayOfPattern.length;
      int i = 0;
      String str1 = paramString;
      if (i >= j) {
        return str1;
      }
      Matcher localMatcher = arrayOfPattern[i].matcher(paramString);
      for (;;)
      {
        if (!localMatcher.find())
        {
          i += 1;
          break;
        }
        String str2 = localMatcher.group(1);
        if ((!b.matcher(str2).matches()) && (!TextUtils.isEmpty(str2))) {
          str1 = str1.replace(str2, "");
        }
      }
    }
    return paramString;
  }
  
  public List<BubbleEntity> a(String paramString1, String paramString2)
  {
    ArrayList localArrayList = new ArrayList();
    String[] arrayOfString = paramString1.split("，|。|；|！|\\n|和|或|：|\\.|,|;");
    int j = arrayOfString.length;
    int i = 0;
    if (i >= j) {
      return localArrayList;
    }
    paramString2 = arrayOfString[i];
    Iterator localIterator = f.iterator();
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        i += 1;
        break;
      }
      paramString2 = ((aqm)localIterator.next()).a(paramString2, localArrayList, e, paramString1);
    }
  }
  
  public void a(long paramLong)
  {
    e = paramLong;
  }
}

/* Location:
 * Qualified Name:     aqo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */