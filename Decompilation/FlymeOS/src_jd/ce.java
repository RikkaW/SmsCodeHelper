import android.annotation.TargetApi;
import android.util.Log;
import android.util.LruCache;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@TargetApi(12)
public class ce
{
  public static final HashMap<String, ce> d = new HashMap();
  public LruCache<String, BusinessSmsMessage> a = new LruCache(200);
  public LruCache<String, LinkedList<dj>> b = new LruCache(200);
  public HashMap<String, Map<String, dk>> c = new HashMap();
  
  public static ce a(String paramString)
  {
    ce localce2 = (ce)d.get(paramString);
    ce localce1 = localce2;
    if (localce2 == null)
    {
      localce1 = new ce();
      d.put(paramString, localce1);
    }
    return localce1;
  }
  
  public void a(String paramString, BusinessSmsMessage paramBusinessSmsMessage)
  {
    if ((paramString == null) || (paramBusinessSmsMessage == null))
    {
      Log.w("XIAOYUAN", "DuoquBubbleViewManager.pubMsgToCache cacheKey or msg is null. ");
      return;
    }
    synchronized (a)
    {
      a.put(paramString, paramBusinessSmsMessage);
      return;
    }
  }
  
  public void a(String paramString, LinkedList<dj> paramLinkedList)
  {
    if ((paramString == null) || (paramLinkedList == null))
    {
      Log.w("XIAOYUAN", "DuoquBubbleViewManager.putBubbleItemTypeViewToCache cacheKey or msg is null. ");
      return;
    }
    synchronized (b)
    {
      b.put(paramString, paramLinkedList);
      return;
    }
  }
  
  public BusinessSmsMessage b(String paramString)
  {
    if (paramString == null)
    {
      Log.w("XIAOYUAN", "DuoquBubbleViewManager.getMsgFromCache cacheKey is null. ");
      return null;
    }
    return (BusinessSmsMessage)a.get(paramString);
  }
  
  public LinkedList<dj> c(String paramString)
  {
    if (paramString == null)
    {
      Log.w("XIAOYUAN", "DuoquBubbleViewManager.getBubbleItemTypeViewFromCache cacheKey is null. ");
      return null;
    }
    return (LinkedList)b.get(paramString);
  }
}

/* Location:
 * Qualified Name:     ce
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */