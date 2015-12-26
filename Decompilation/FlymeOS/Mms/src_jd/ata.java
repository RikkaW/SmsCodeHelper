import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ata
{
  public ata()
  {
    atb.a();
  }
  
  private asu a(Matcher paramMatcher, ast paramast, String paramString1, Map<Integer, List<asu>> paramMap, String paramString2)
  {
    asu localasu = new asu();
    localasu.a(paramString2);
    localasu.b(a(paramString1, paramMatcher.group(0)));
    if (paramast.c().booleanValue()) {
      localasu.c(paramMatcher.group(paramast.i().intValue()));
    }
    for (;;)
    {
      if (paramast.b().booleanValue()) {
        localasu.d(paramMatcher.group(paramast.i().intValue()));
      }
      localasu.a(paramast.g());
      localasu.a(paramast.e().booleanValue());
      a(paramast, paramMap, Integer.valueOf(paramMatcher.start()), localasu);
      return localasu;
      localasu.c("回复" + localasu.a());
    }
  }
  
  private String a(String paramString1, String paramString2)
  {
    if (paramString1.contains(paramString2)) {}
    do
    {
      return paramString2;
      paramString1 = Pattern.compile(paramString2.replaceAll(",|\\.|/|\"|\\[|\\]|;|'|<|>|\\(|\\)|:|\\{|\\}|\\+|\\*", "[，。、”“【】；‘’《》（）：／/\\{\\}\\+\\*]")).matcher(paramString1);
    } while (!paramString1.find());
    return paramString1.group();
  }
  
  private void a(ast paramast, Map<Integer, List<asu>> paramMap, Integer paramInteger, asu paramasu)
  {
    if ((paramast.f() != null) && (!paramast.f().b(paramasu.a()))) {}
    do
    {
      return;
      if (paramast.f() != null) {
        paramast.f().a(paramasu);
      }
      if (!paramMap.containsKey(paramInteger)) {
        break;
      }
      if (paramasu.d().intValue() > ((asu)((List)paramMap.get(paramInteger)).get(0)).d().intValue())
      {
        paramast = (List)paramMap.get(paramInteger);
        paramast.clear();
        paramast.add(paramasu);
        paramMap.put(paramInteger, paramast);
      }
    } while (paramasu.d() != ((asu)((List)paramMap.get(paramInteger)).get(0)).d());
    ((List)paramMap.get(paramInteger)).add(paramasu);
    return;
    paramast = new ArrayList();
    paramast.add(paramasu);
    paramMap.put(paramInteger, paramast);
  }
  
  public Set<asu> a(String paramString)
  {
    TreeSet localTreeSet = new TreeSet();
    HashMap localHashMap = new HashMap();
    String str1 = atc.a(paramString);
    Iterator localIterator = atb.a.iterator();
    while (localIterator.hasNext())
    {
      ast localast = (ast)localIterator.next();
      if ((localast.f() == null) || (localast.f().a(str1)))
      {
        Matcher localMatcher = Pattern.compile(localast.d()).matcher(str1);
        while (localMatcher.find()) {
          if (localast.a().booleanValue())
          {
            int i = 1;
            while (i <= localMatcher.groupCount())
            {
              String str2 = localMatcher.group(i);
              if (str2 != null) {
                a(localMatcher, localast, paramString, localHashMap, str2);
              }
              i += 1;
            }
          }
          else
          {
            a(localMatcher, localast, paramString, localHashMap, localMatcher.group(localast.h().intValue()));
          }
        }
      }
    }
    paramString = localHashMap.values().iterator();
    while (paramString.hasNext()) {
      localTreeSet.addAll((Collection)paramString.next());
    }
    return localTreeSet;
  }
}

/* Location:
 * Qualified Name:     ata
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */