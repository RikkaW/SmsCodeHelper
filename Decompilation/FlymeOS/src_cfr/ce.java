/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.annotation.TargetApi
 *  android.util.Log
 *  android.util.LruCache
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 *  java.util.LinkedList
 */
import android.annotation.TargetApi;
import android.util.Log;
import android.util.LruCache;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@TargetApi(value=12)
public class ce {
    public static final HashMap<String, ce> d = new HashMap();
    public LruCache<String, BusinessSmsMessage> a = new LruCache(200);
    public LruCache<String, LinkedList<dj>> b = new LruCache(200);
    public HashMap<String, Map<String, dk>> c = new HashMap();

    public static ce a(String string2) {
        ce ce2;
        ce ce3 = ce2 = (ce)d.get((Object)string2);
        if (ce2 == null) {
            ce3 = new ce();
            d.put((Object)string2, (Object)ce3);
        }
        return ce3;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(String string2, BusinessSmsMessage businessSmsMessage) {
        if (string2 != null && businessSmsMessage != null) {
            LruCache<String, BusinessSmsMessage> lruCache = this.a;
            synchronized (lruCache) {
                this.a.put((Object)string2, (Object)businessSmsMessage);
                return;
            }
        }
        Log.w((String)"XIAOYUAN", (String)"DuoquBubbleViewManager.pubMsgToCache cacheKey or msg is null. ");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(String string2, LinkedList<dj> linkedList) {
        if (string2 != null && linkedList != null) {
            LruCache<String, LinkedList<dj>> lruCache = this.b;
            synchronized (lruCache) {
                this.b.put((Object)string2, linkedList);
                return;
            }
        }
        Log.w((String)"XIAOYUAN", (String)"DuoquBubbleViewManager.putBubbleItemTypeViewToCache cacheKey or msg is null. ");
    }

    public BusinessSmsMessage b(String string2) {
        if (string2 == null) {
            Log.w((String)"XIAOYUAN", (String)"DuoquBubbleViewManager.getMsgFromCache cacheKey is null. ");
            return null;
        }
        return (BusinessSmsMessage)this.a.get((Object)string2);
    }

    public LinkedList<dj> c(String string2) {
        if (string2 == null) {
            Log.w((String)"XIAOYUAN", (String)"DuoquBubbleViewManager.getBubbleItemTypeViewFromCache cacheKey is null. ");
            return null;
        }
        return (LinkedList)this.b.get((Object)string2);
    }
}

