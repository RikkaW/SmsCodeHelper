/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  android.util.LruCache
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.HashMap
 *  java.util.HashSet
 */
import android.text.TextUtils;
import android.util.LruCache;
import com.ted.android.contacts.netparser.model.NumItem;
import com.ted.sdk.yellow.entry.NumAllInfoItem;
import com.ted.sdk.yellow.entry.RequestData;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class asr
extends LruCache<String, NumItem> {
    private static final String a = asr.class.getSimpleName();
    private static asr b;
    private final Set<String> c = new HashSet(1024);
    private final Set<String> d = new HashSet(1024);

    private asr() {
        super(1024);
    }

    public static asr a() {
        if (b == null) {
            b = new asr();
        }
        return b;
    }

    public HashMap<String, NumAllInfoItem> a(List<RequestData> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        HashMap hashMap = new HashMap(list.size());
        ArrayList arrayList = new ArrayList();
        Iterator<RequestData> iterator = list.iterator();
        do {
            if (!iterator.hasNext()) {
                list.clear();
                list.addAll((Collection<RequestData>)arrayList);
                return hashMap;
            }
            RequestData requestData = iterator.next();
            String string2 = requestData.getNumber();
            NumItem numItem = (NumItem)this.get((Object)string2);
            if (numItem != null) {
                hashMap.put((Object)string2, (Object)new NumAllInfoItem(string2, numItem));
                continue;
            }
            arrayList.add((Object)requestData);
        } while (true);
    }

    public void a(String string2) {
        this.remove((Object)string2);
        this.c.remove(string2);
        this.d.remove(string2);
    }

    public void a(String string2, NumItem numItem) {
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            this.d.add(string2);
        }
        this.b(string2, numItem);
    }

    protected void a(boolean bl2, String string2, NumItem numItem, NumItem numItem2) {
        super.entryRemoved(bl2, (Object)string2, (Object)numItem, (Object)numItem2);
        if (this.c.contains(string2) && numItem2 == null) {
            this.c.remove(string2);
        }
        if (this.d.contains(string2) && numItem2 == null) {
            this.d.remove(string2);
        }
    }

    public boolean a(boolean bl2, String string2) {
        if (bl2) {
            return this.d.contains(string2);
        }
        return this.c.contains(string2);
    }

    public void b() {
        this.evictAll();
        this.c.clear();
        this.d.clear();
    }

    public void b(String string2, NumItem numItem) {
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            this.c.add(string2);
        }
        if (TextUtils.isEmpty((CharSequence)string2) || numItem == null) {
            return;
        }
        this.put((Object)string2, (Object)numItem);
    }

    protected /* synthetic */ void entryRemoved(boolean bl2, Object object, Object object2, Object object3) {
        this.a(bl2, (String)object, (NumItem)object2, (NumItem)object3);
    }
}

