package cn.com.xy.sms.sdk.net.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CommandAnalyzer
{
  private static String a = "#(\\d+)\\{([^}]+)\\}";
  private static Pattern b = Pattern.compile("#(\\d+)\\{([^}]+)\\}");
  
  public static List<d> a(String paramString)
  {
    if ((paramString == null) || (paramString.trim().length() == 0)) {}
    do
    {
      return null;
      paramString = b.matcher(paramString);
    } while (!paramString.find());
    ArrayList localArrayList = new ArrayList();
    do
    {
      String str1 = paramString.group(1);
      String str2 = paramString.group(2);
      localArrayList.add(new d(Integer.valueOf(str1).intValue(), str2));
    } while (paramString.find());
    return localArrayList;
  }
  
  private static void a()
  {
    Object localObject = a("#0{-noWait} #4{-noWait -wait=10} #10{-ids=1,2,3 -domain=http://bizport.cn/newservice} #11{-sql=asfa dfa sdff}");
    e locale = new e();
    localObject = ((List)localObject).iterator();
    for (;;)
    {
      if (!((Iterator)localObject).hasNext()) {
        return;
      }
      locale.exeCmd((d)((Iterator)localObject).next());
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.net.util.CommandAnalyzer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */