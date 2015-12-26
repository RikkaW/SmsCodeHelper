/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.HashMap
 */
import android.util.Log;
import com.ted.android.contacts.netparser.model.NumItem;
import com.ted.sdk.yellow.YellowPageEngine;
import com.ted.sdk.yellow.entry.BaseItem;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class aso
implements aod {
    final /* synthetic */ YellowPageEngine.c a;
    private boolean b;
    private final /* synthetic */ asr c;
    private final /* synthetic */ HashMap d;

    public aso(YellowPageEngine.c c2, asr asr2, HashMap hashMap) {
        this.a = c2;
        this.c = asr2;
        this.d = hashMap;
    }

    @Override
    public void a(aod.a a2, NumItem numItem) {
    }

    @Override
    public void a(aod.a object, List<NumItem> object2) {
        if (this.b) {
            return;
        }
        object = object2.iterator();
        do {
            if (!object.hasNext()) {
                YellowPageEngine.c.a(this.a, this.d, this.c);
                YellowPageEngine.c.a(this.a, this.d);
                return;
            }
            object2 = (NumItem)object.next();
            this.c.a(object2.a(), (NumItem)object2);
        } while (true);
    }

    @Override
    public void a(Throwable throwable) {
        Log.e((String)YellowPageEngine.access$0(), (String)"Get list from network failed.");
        this.b = true;
        if (YellowPageEngine.c.a(this.a) == null) {
            return;
        }
        if (this.d.size() <= 0) {
            YellowPageEngine.c.a(this.a).onFail();
            return;
        }
        if (this.d.size() != YellowPageEngine.c.b(this.a)) {
            YellowPageEngine.c.a(this.a).onPartSuccess(this.d);
            return;
        }
        YellowPageEngine.c.a(this.a).onSuccess(this.d);
    }
}

