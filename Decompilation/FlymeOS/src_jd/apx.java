import android.content.Context;
import android.util.Pair;
import com.ted.android.contacts.bubble.SmsCoreDatabaseHelper;
import com.ted.android.contacts.bubble.SmsCoreEngine;
import com.ted.android.smscard.CardBase;
import com.ted.android.utils.TedSDKLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class apx
{
  public static List<Pair<String, String>> a = new ArrayList();
  private static final String b = apx.class.getSimpleName();
  private static apx c;
  private Context d;
  
  private apx(Context paramContext)
  {
    d = paramContext;
    SmsCoreEngine.init(paramContext);
  }
  
  public static apx a(Context paramContext)
  {
    if (c == null) {}
    try
    {
      c = new apx(paramContext);
      return c;
    }
    finally {}
  }
  
  private CardBase a(bn parambn, String paramString)
  {
    if (Pattern.compile(b).matcher(paramString).find()) {
      return parambn.a(paramString);
    }
    return null;
  }
  
  public CardBase a(String paramString1, String paramString2)
  {
    Object localObject1 = null;
    Object localObject2 = SmsCoreEngine.searchCard(atd.a().a(paramString1), paramString2);
    if ((localObject2 == null) || (localObject2.length == 0)) {}
    int i;
    do
    {
      do
      {
        return null;
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append("ids: ");
        i = 0;
        if (i < localObject2.length) {
          break;
        }
        TedSDKLog.d(b, ((StringBuilder)localObject3).toString());
      } while ((localObject2 == null) || (localObject2.length == 0));
      localObject2 = SmsCoreDatabaseHelper.a(d).a((int[])localObject2);
    } while ((localObject2 == null) || (((List)localObject2).size() <= 0));
    Object localObject3 = ((List)localObject2).iterator();
    for (;;)
    {
      label115:
      if (!((Iterator)localObject3).hasNext()) {}
      do
      {
        return (CardBase)localObject1;
        ((StringBuilder)localObject3).append(localObject2[i]).append(" ");
        i += 1;
        break;
        localObject2 = (bn)((Iterator)localObject3).next();
        if (b(paramString2, String.valueOf(a))) {
          break label115;
        }
        localObject2 = a((bn)localObject2, paramString1);
        localObject1 = localObject2;
      } while (localObject2 != null);
      localObject1 = localObject2;
    }
  }
  
  public boolean b(String paramString1, String paramString2)
  {
    Iterator localIterator = a.iterator();
    Pair localPair;
    do
    {
      if (!localIterator.hasNext()) {
        return false;
      }
      localPair = (Pair)localIterator.next();
    } while ((!paramString1.equals(first)) || (!paramString2.equals(second)));
    return true;
  }
}

/* Location:
 * Qualified Name:     apx
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */