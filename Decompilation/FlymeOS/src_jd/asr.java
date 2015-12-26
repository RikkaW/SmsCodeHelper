import android.text.TextUtils;
import android.util.LruCache;
import com.ted.android.contacts.netparser.model.NumItem;
import com.ted.sdk.yellow.entry.NumAllInfoItem;
import com.ted.sdk.yellow.entry.RequestData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class asr
  extends LruCache<String, NumItem>
{
  private static final String a = asr.class.getSimpleName();
  private static asr b;
  private final Set<String> c = new HashSet(1024);
  private final Set<String> d = new HashSet(1024);
  
  private asr()
  {
    super(1024);
  }
  
  public static asr a()
  {
    if (b == null) {
      b = new asr();
    }
    return b;
  }
  
  public HashMap<String, NumAllInfoItem> a(List<RequestData> paramList)
  {
    if ((paramList == null) || (paramList.size() <= 0)) {
      return null;
    }
    HashMap localHashMap = new HashMap(paramList.size());
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramList.iterator();
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        paramList.clear();
        paramList.addAll(localArrayList);
        return localHashMap;
      }
      RequestData localRequestData = (RequestData)localIterator.next();
      String str = localRequestData.getNumber();
      NumItem localNumItem = (NumItem)get(str);
      if (localNumItem != null) {
        localHashMap.put(str, new NumAllInfoItem(str, localNumItem));
      } else {
        localArrayList.add(localRequestData);
      }
    }
  }
  
  public void a(String paramString)
  {
    remove(paramString);
    c.remove(paramString);
    d.remove(paramString);
  }
  
  public void a(String paramString, NumItem paramNumItem)
  {
    if (!TextUtils.isEmpty(paramString)) {
      d.add(paramString);
    }
    b(paramString, paramNumItem);
  }
  
  protected void a(boolean paramBoolean, String paramString, NumItem paramNumItem1, NumItem paramNumItem2)
  {
    super.entryRemoved(paramBoolean, paramString, paramNumItem1, paramNumItem2);
    if ((c.contains(paramString)) && (paramNumItem2 == null)) {
      c.remove(paramString);
    }
    if ((d.contains(paramString)) && (paramNumItem2 == null)) {
      d.remove(paramString);
    }
  }
  
  public boolean a(boolean paramBoolean, String paramString)
  {
    if (paramBoolean) {
      return d.contains(paramString);
    }
    return c.contains(paramString);
  }
  
  public void b()
  {
    evictAll();
    c.clear();
    d.clear();
  }
  
  public void b(String paramString, NumItem paramNumItem)
  {
    if (!TextUtils.isEmpty(paramString)) {
      c.add(paramString);
    }
    if ((TextUtils.isEmpty(paramString)) || (paramNumItem == null)) {
      return;
    }
    put(paramString, paramNumItem);
  }
}

/* Location:
 * Qualified Name:     asr
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */