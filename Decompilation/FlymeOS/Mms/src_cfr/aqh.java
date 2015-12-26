/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.LruCache
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Collections
 *  java.util.HashSet
 */
import android.util.LruCache;
import com.ted.android.data.SmsEntity;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class aqh
extends LruCache<Long, SmsEntity> {
    private static final String a = aqh.class.getSimpleName();
    private static aqh b;
    private final Set<Long> c = Collections.synchronizedSet((Set)new HashSet(1024));

    private aqh() {
        super(1024);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static aqh a() {
        if (b == null) {
            synchronized (aqh.class) {
                if (b == null) {
                    b = new aqh();
                }
            }
        }
        return b;
    }

    public void a(Long l2, SmsEntity smsEntity) {
        if (!this.c.contains((Object)l2)) {
            this.c.add(l2);
        }
        if (l2 == null || smsEntity == null) {
            return;
        }
        this.put((Object)l2, (Object)smsEntity);
    }

    protected void a(boolean bl2, Long l2, SmsEntity smsEntity, SmsEntity smsEntity2) {
        super.entryRemoved(bl2, (Object)l2, (Object)smsEntity, (Object)smsEntity2);
        if (this.c.contains((Object)l2) && smsEntity2 == null) {
            this.c.remove((Object)l2);
        }
    }

    public boolean a(Long l2) {
        return this.c.contains((Object)l2);
    }

    protected /* synthetic */ void entryRemoved(boolean bl2, Object object, Object object2, Object object3) {
        this.a(bl2, (Long)object, (SmsEntity)object2, (SmsEntity)object3);
    }
}

