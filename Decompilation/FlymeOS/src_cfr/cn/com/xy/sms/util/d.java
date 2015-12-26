/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package cn.com.xy.sms.util;

import cn.com.xy.sms.sdk.db.entity.MatchCacheManager;
import cn.com.xy.sms.util.ParseRichBubbleManager;
import java.util.Iterator;
import java.util.Set;

final class d
implements Runnable {
    private final /* synthetic */ Set a;

    d(Set set) {
        this.a = set;
    }

    @Override
    public final void run() {
        try {
            Iterator iterator = this.a.iterator();
            do {
                if (!iterator.hasNext()) {
                    MatchCacheManager.deleteDataByMsgIds(this.a);
                    return;
                }
                ParseRichBubbleManager.deleteBubbleDataFromCache("", String.valueOf((Object)((Integer)iterator.next())));
            } while (true);
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }
}

